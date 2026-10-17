import java.io.File;
import jadx.api.JadxArgs;
import jadx.api.JadxDecompiler;

/** Reproducible full APK export using the installed JADX library. */
public class DecodeApk {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) throw new IllegalArgumentException("APK output-directory");
        JadxArgs options = new JadxArgs();
        options.setInputFile(new File(args[0]));
        options.setOutDir(new File(args[1]));
        options.setThreadsCount(4);
        options.setShowInconsistentCode(true);
        options.setDeobfuscationOn(false);
        try (JadxDecompiler decoder = new JadxDecompiler(options)) {
            decoder.load();
            System.out.println("Loaded classes=" + decoder.getClassesWithInners().size()
                + " resources=" + decoder.getResources().size());
            decoder.save();
            System.out.println("Export finished; decompiler errors=" + decoder.getErrorsCount());
        }
    }
}
