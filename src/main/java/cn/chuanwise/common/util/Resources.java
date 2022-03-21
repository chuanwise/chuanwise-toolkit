package cn.chuanwise.common.util;

import java.io.*;

/**
 * 资源相关工具
 *
 * @author Chuanwise
 */
public class Resources
    extends StaticUtilities {
    
    private static final ClassLoader DEFAULT_CLASSLOADER = ClassLoader.getSystemClassLoader();
    
    /**
     * 用指定的类加载器加载资源，再复制到指定的位置
     *
     * @param classLoader 类加载器
     * @param path        资源路径
     * @param dest        文件位置
     * @param replace     文件存在时是否替换
     * @return 资源文件是否复制成功
     * @throws IOException 复制过程中的异常
     */
    public static boolean copyResource(ClassLoader classLoader, String path, File dest, boolean replace) throws IOException {
        if (dest.isFile() && !replace) {
            return false;
        }
        if (!dest.isFile()) {
            dest.createNewFile();
        }
    
        try (final OutputStream outputStream = new FileOutputStream(dest);
             InputStream inputStream = classLoader.getResourceAsStream(path)) {
            ByteStreams.dump(inputStream, outputStream);
        }
        return true;
    }
    
    /**
     * 用默认的类加载器加载资源，再复制到指定的位置
     *
     * @param path    资源路径
     * @param dest    文件位置
     * @param replace 文件存在时是否替换
     * @return 资源文件是否复制成功
     * @throws IOException 复制过程中的异常
     */
    public static boolean copyResource(String path, File dest, boolean replace) throws IOException {
        return copyResource(DEFAULT_CLASSLOADER, path, dest, replace);
    }
    
    /**
     * 用指定的类加载器加载资源，再复制到指定的位置
     *
     * @param classLoader 类加载器
     * @param path        资源路径
     * @param dest        文件位置
     * @return 资源文件是否复制成功
     * @throws IOException 复制过程中的异常
     */
    public static boolean copyResource(ClassLoader classLoader, String path, File dest) throws IOException {
        return copyResource(classLoader, path, dest, true);
    }
    
    /**
     * 用默认的类加载器加载资源，再复制到指定的位置
     *
     * @param path 资源路径
     * @param dest 文件位置
     * @return 资源文件是否复制成功
     * @throws IOException 复制过程中的异常
     */
    public static boolean copyResource(String path, File dest) throws IOException {
        return copyResource(path, dest, true);
    }
}