package cn.chuanwise.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 字节流相关操作
 *
 * @author Chuanwise
 */
public class ByteStreams
    extends StaticUtilities {
    
    /**
     * 默认转移包大小
     */
    private static final int DEFAULT_PACK_SIZE = 1024;
    
    /**
     * 读取输入流剩余所有内容
     *
     * @param inputStream 输入流
     * @return 字节流剩余的字节
     * @throws IOException 读取时出现异常
     */
    public static byte[] read(InputStream inputStream) throws IOException {
        Preconditions.objectNonNull(inputStream, "input stream");
        
        final byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        return bytes;
    }
    
    /**
     * 将输入流剩余内容转移到输出流里
     *
     * @param inputStream 输入流
     * @param outputStream 输出流
     * @throws IOException 转移时出现异常
     */
    public static void dump(InputStream inputStream, OutputStream outputStream) throws IOException {
        Preconditions.objectNonNull(inputStream, "input stream");
        Preconditions.objectNonNull(outputStream, "out stream");
        
        final byte[] bytes = new byte[DEFAULT_PACK_SIZE];
        int len;
        while ((len = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
        }
    }
}