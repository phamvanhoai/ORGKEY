    public static void e(String str) {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
    }
