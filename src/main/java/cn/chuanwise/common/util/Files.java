package cn.chuanwise.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * 和文件相关的操作
 *
 * @author Chuanwise
 */
public class Files
    extends StaticUtilities {
    
    /**
     * 从文件中读取字节数组
     *
     * @param file 文件
     * @return 文件中的字节数组
     * @throws IOException 读取时抛出的异常
     */
    @SuppressWarnings("all")
    public static byte[] readBytes(File file) throws IOException {
        Preconditions.objectNonNull(file, "file");
        
        try (InputStream inputStream = new FileInputStream(file)) {
            return ByteStreams.read(inputStream);
        }
    }
    
    /**
     * 从文件中读取字符串
     *
     * @param file    文件
     * @param charset 编码
     * @return 文件中的字符串
     * @throws IOException 读取时抛出的异常
     */
    public static String readString(File file, Charset charset) throws IOException {
        Preconditions.objectNonNull(file, "file");
        Preconditions.objectNonNull(charset, "charset");
        
        return new String(readBytes(file), charset);
    }
    
    /**
     * 从文件中读取字符串
     *
     * @param file 文件
     * @return 文件中的字符串
     * @throws IOException 读取时抛出的异常
     */
    public static String readString(File file) throws IOException {
        return readString(file, Charset.defaultCharset());
    }
}
