"""Generate the MIDI pitch fixture or verify its recorded WAV. Python standard library only.
Use ORGX Grand, FX OFF, one MIDI source, no rhythm/metronome. Pass the app's transpose.
This checks rendered PCM, not the phone's speaker or microphone response.
"""
import argparse
import array
import json
import math
from pathlib import Path
import statistics
import struct
import sys
import wave


def variable(value):
    result = [value & 127]
    value >>= 7
    while value:
        result.insert(0, (value & 127) | 128)
        value >>= 7
    return bytes(result)


def generate(path):
    events = [(0, bytes.fromhex('ff 51 03 07 a1 20'))]
    for start, bend in [(0, 8192), (9600, 16383), (19200, 0)]:
        events.append((start, bytes([0xe0, bend & 127, bend >> 7])))
        for offset in range(0, 9600, 1920):
            events.append((start + offset, bytes([0x90, 69, 110])))
            events.append((start + offset + 1440, bytes([0x80, 69, 0])))
    events.extend([(28800, bytes.fromhex('e0 00 40')), (28800, bytes.fromhex('ff 2f 00'))])
    track, previous = bytearray(), 0
    for tick, event in sorted(events, key=lambda item: item[0]):
        track.extend(variable(tick - previous) + event)
        previous = tick
    Path(path).write_bytes(b'MThd' + struct.pack('>IHHH', 6, 0, 1, 480)
                           + b'MTrk' + struct.pack('>I', len(track)) + track)


def verify(path, transpose):
    with wave.open(str(path), 'rb') as source:
        if source.getsampwidth() != 2 or source.getnchannels() not in (1, 2):
            raise ValueError('Expected mono/stereo PCM16')
        rate, channels = source.getframerate(), source.getnchannels()
        samples = array.array('h', source.readframes(source.getnframes()))
    if sys.byteorder != 'little':
        samples.byteswap()
    mono = samples[::channels]
    groups = {bend: [] for bend in (0, 2, -2)}
    expected = {bend: 440 * 2 ** ((transpose + bend) / 12) for bend in groups}
    for second in range(int(len(mono) / rate)):
        start = int((second + .25) * rate)
        block = mono[start:start + rate // 2]
        if len(block) != rate // 2 or sum(x*x for x in block) / len(block) < 40**2:
            continue
        crossings = [i - 1 - block[i-1] / (block[i] - block[i-1])
                     for i in range(1, len(block)) if block[i-1] <= 0 < block[i]]
        if len(crossings) < 10:
            continue
        measured = (len(crossings) - 1) * rate / (crossings[-1] - crossings[0])
        bend = min(expected, key=lambda key: abs(math.log2(measured / expected[key])))
        if abs(1200 * math.log2(measured / expected[bend])) <= 5:
            groups[bend].append(measured)
    measurements = []
    for bend, values in groups.items():
        median = statistics.median(values) if values else None
        measurements.append(dict(bend_semitones=bend, expected_hz=expected[bend],
                                 measured_hz=median, matching_windows=len(values),
                                 error_cents=1200 * math.log2(median / expected[bend]) if median else None))
    return dict(passed=all(len(values) >= 2 for values in groups.values()),
                method='upward zero crossings; ORGX Grand; 0.5-second windows; tolerance 5 cents',
                sample_rate=rate, channels=channels, duration_seconds=len(mono)/rate,
                transpose=transpose, measurements=measurements)


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--generate', type=Path)
    parser.add_argument('--wav', type=Path)
    parser.add_argument('--transpose', type=int, default=0)
    parser.add_argument('--report', type=Path)
    args = parser.parse_args()
    if not args.generate and not args.wav:
        parser.error('Provide --generate or --wav')
    if args.generate:
        generate(args.generate)
    if args.wav:
        report = verify(args.wav, args.transpose)
        serialized = json.dumps(report, indent=2)
        print(serialized)
        if args.report:
            args.report.write_text(serialized + '\n', encoding='utf-8')
        sys.exit(0 if report['passed'] else 1)
