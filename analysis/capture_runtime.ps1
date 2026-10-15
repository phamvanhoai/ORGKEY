param(
    [switch]$Install,
    [int]$DurationSeconds = 20
)

$ErrorActionPreference = "Stop"
$ProjectRoot = Split-Path -Parent $PSScriptRoot
$Adb = Join-Path $ProjectRoot "tools\platform-tools\adb.exe"
$Apk = Join-Path $ProjectRoot "ORG+24_+Your+Music_2026.10.01_APKPure.apk"
$Package = "com.sofeh.android.musicstudio3"

if (-not (Test-Path -LiteralPath $Adb)) {
    throw "Không tìm thấy ADB cục bộ tại $Adb"
}

$Devices = @(& $Adb devices | Select-Object -Skip 1 | Where-Object { $_ -match "\sdevice$" })
if ($Devices.Count -ne 1) {
    throw "Cần đúng một thiết bị ở trạng thái device; hiện tìm thấy $($Devices.Count)."
}

$Stamp = Get-Date -Format "yyyyMMdd-HHmmss"
$Output = Join-Path $PSScriptRoot "runtime\$Stamp"
New-Item -ItemType Directory -Path $Output -Force | Out-Null

& $Adb get-serialno | Set-Content -Encoding utf8 (Join-Path $Output "serial.txt")
& $Adb shell getprop | Set-Content -Encoding utf8 (Join-Path $Output "getprop.txt")

if ($Install) {
    & $Adb install -r $Apk | Tee-Object -FilePath (Join-Path $Output "install.txt")
    if ($LASTEXITCODE -ne 0) { throw "Cài APK thất bại." }
}

& $Adb shell dumpsys package $Package | Set-Content -Encoding utf8 (Join-Path $Output "package-before.txt")
& $Adb shell cmd appops get $Package | Set-Content -Encoding utf8 (Join-Path $Output "appops-before.txt")
& $Adb shell dumpsys netstats detail | Set-Content -Encoding utf8 (Join-Path $Output "netstats-before.txt")
& $Adb logcat -c
& $Adb shell monkey -p $Package -c android.intent.category.LAUNCHER 1 |
    Set-Content -Encoding utf8 (Join-Path $Output "launch.txt")
Start-Sleep -Seconds $DurationSeconds

$PidValue = (& $Adb shell pidof $Package).Trim()
if ($PidValue) {
    & $Adb logcat -d --pid=$PidValue -v threadtime |
        Set-Content -Encoding utf8 (Join-Path $Output "logcat-app.txt")
}
& $Adb logcat -d -v threadtime | Set-Content -Encoding utf8 (Join-Path $Output "logcat-all.txt")
& $Adb shell dumpsys activity activities | Set-Content -Encoding utf8 (Join-Path $Output "activity.txt")
& $Adb shell dumpsys audio | Set-Content -Encoding utf8 (Join-Path $Output "audio.txt")
& $Adb shell dumpsys media.audio_flinger | Set-Content -Encoding utf8 (Join-Path $Output "audio-flinger.txt")
& $Adb shell dumpsys package $Package | Set-Content -Encoding utf8 (Join-Path $Output "package-after.txt")
& $Adb shell cmd appops get $Package | Set-Content -Encoding utf8 (Join-Path $Output "appops-after.txt")
& $Adb shell dumpsys netstats detail | Set-Content -Encoding utf8 (Join-Path $Output "netstats-after.txt")

Write-Output "Đã ghi bằng chứng runtime vào $Output"
