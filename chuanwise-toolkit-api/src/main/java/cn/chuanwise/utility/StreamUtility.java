package cn.chuanwise.utility;

import java.io.*;

public class StreamUtility extends StaticUtility {
    public static final int DEFAULT_PACK_SIZE = 1024;

    public static boolean dump(InputStream inputStream, String encoding, String decoding, File dest, boolean replace) throws IOException {
        if (dest.isFile() && !replace) {
            return false;
        }
        if (!dest.isFile()) {
            dest.createNewFile();
        }

        try (OutputStream outputStream = new FileOutputStream(dest)) {
            write(outputStream, read(inputStream, encoding), decoding);
        }
        return true;
    }

    public static boolean dump(InputStream inputStream, File dest, boolean replace) throws IOException {
        if (dest.isFile() && !replace) {
            return false;
        }
        if (!dest.isFile()) {
            dest.createNewFile();
        }

        try (OutputStream outputStream = new FileOutputStream(dest)) {
            dump(inputStream, outputStream);
        }
        return true;
    }

    public static void write(OutputStream outputStream, String data, String encoding) throws IOException {
        outputStream.write(data.getBytes(encoding));
    }

    public static void dump(InputStream inputStream, OutputStream outputStream, int packSize) throws IOException {
        byte[] bytes = new byte[packSize];
        int len;
        while ((len = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
        }
    }

    public static void dump(InputStream inputStream, OutputStream outputStream) throws IOException {
        dump(inputStream, outputStream, DEFAULT_PACK_SIZE);
    }

    public static byte[] read(InputStream inputStream) throws IOException {
        return read(inputStream, inputStream.available());
    }

    public static byte[] read(InputStream inputStream, int maxSize) throws IOException {
        final byte[] data = new byte[maxSize];
        inputStream.read(data, 0, maxSize);
        return data;
    }

    public static String read(InputStream inputStream, String encoding) throws IOException {
        return new String(read(inputStream), encoding);
    }

    public static String read(InputStream inputStream, String encoding, int maxSize) throws IOException {
        return new String(read(inputStream, maxSize), encoding);
    }
}
