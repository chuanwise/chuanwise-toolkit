package cn.chuanwise.utility;

import cn.chuanwise.toolkit.functional.ThrowableFunction;
import cn.chuanwise.toolkit.functional.ThrowableSupplier;

import java.io.*;
import java.util.Objects;

public class FileUtility extends StaticUtility {
    public static <T> T loadFileOrSupplie(File file, ThrowableFunction<IOException, File, T> translator, ThrowableSupplier<IOException, T> supplier) throws IOException {
        T result = null;
        if (file.isFile()) {
            try {
                result = translator.throwableApply(file);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return Objects.isNull(result) ? supplier.get() : result;
    }

    public static <T> T loadFileOrDefault(File file, ThrowableFunction<IOException, File, T> translator, T defaultValue) throws IOException {
        T result = null;
        if (file.isFile()) {
            try {
                result = translator.throwableApply(file);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return Objects.isNull(result) ? defaultValue : result;
    }

    public static boolean copyOrMoveFile(File from, File to, boolean copy, boolean replace) throws IOException {
        if (!from.isFile()) {
            return false;
        }
        if (to.isFile() && !replace) {
            return false;
        }
        if (!to.isFile()) {
            to.createNewFile();
        }

        try (OutputStream outputStream = new FileOutputStream(to);
             InputStream inputStream = new FileInputStream(from)) {
            StreamUtility.dump(inputStream, outputStream);
        }

        return !copy || from.delete();
    }

    public static boolean copyFile(File from, File to, boolean replace) throws IOException {
        return copyOrMoveFile(from, to, true, replace);
    }

    public static boolean copyFile(File from, File to) throws IOException {
        return copyOrMoveFile(from, to, true, true);
    }

    public static boolean moveFile(File from, File to, boolean replace) throws IOException {
        return copyOrMoveFile(from, to, false, replace);
    }

    public static boolean moveFile(File from, File to) throws IOException {
        return copyOrMoveFile(from, to, false, true);
    }
}
