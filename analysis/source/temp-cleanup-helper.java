    public static void p(File file, boolean z, long j) {
        File[] fileArrListFiles;
        try {
            if (file.exists() && file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
                for (File file2 : fileArrListFiles) {
                    try {
                        if (file2.isDirectory()) {
                            p(file2, false, j);
                        } else if (file2.lastModified() < j) {
                            file2.delete();
                        }
                    } catch (Throwable unused) {
                    }
                }
                if (z) {
                    return;
                }
                File[] fileArrListFiles2 = file.listFiles();
                if (fileArrListFiles2 == null || fileArrListFiles2.length == 0) {
                    file.delete();
                }
            }
        } catch (Throwable unused2) {
        }
    }
