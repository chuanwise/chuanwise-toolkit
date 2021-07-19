package cn.chuanwise.utility;

import cn.chuanwise.toolkit.functional.ThrowableFunction;
import cn.chuanwise.toolkit.functional.ThrowableSupplier;

import java.io.*;
import java.util.Objects;

public class ResourceUtility extends StaticUtility {
    public static final int COPY_PACK_SIZE = 1024;
    public static final ClassLoader DEFAULT_CLASSLOADER = Thread.class.getClassLoader();

    /**
     * 用指定的类加载器加载资源，再复制到指定的位置
     * @param classLoader 类加载器
     * @param path 资源路径
     * @param dest 文件位置
     * @param replace 文件存在时是否替换
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
            StreamUtility.dump(inputStream, outputStream);
        }
        return true;
    }

    /**
     * 用默认的类加载器加载资源，再复制到指定的位置
     * @param path 资源路径
     * @param dest 文件位置
     * @param replace 文件存在时是否替换
     * @return 资源文件是否复制成功
     * @throws IOException 复制过程中的异常
     */
    public static boolean copyResource(String path, File dest, boolean replace) throws IOException {
        return copyResource(DEFAULT_CLASSLOADER, path, dest, replace);
    }

    /**
     * 用指定的类加载器加载资源，再复制到指定的位置
     * @param classLoader 类加载器
     * @param path 资源路径
     * @param dest 文件位置
     * @return 资源文件是否复制成功
     * @throws IOException 复制过程中的异常
     */
    public static boolean copyResource(ClassLoader classLoader, String path, File dest) throws IOException {
        return copyResource(classLoader, path, dest, true);
    }

    /**
     * 用默认的类加载器加载资源，再复制到指定的位置
     * @param path 资源路径
     * @param dest 文件位置
     * @return 资源文件是否复制成功
     * @throws IOException 复制过程中的异常
     */
    public static boolean copyResource(String path, File dest) throws IOException {
        return copyResource(path, dest, true);
    }

    /**
     * 使用默认的类加载器加载一个资源，并用特地的处理方法处理为一个所需对象。
     * 如果没有找到该资源，则返回一个默认的所需对象。
     * @param path 资源路径
     * @param translator 处理方法
     * @param supplier 默认对象的提供者
     * @param <T> 默认所需对象类型
     * @return 加载处理过，或默认的对象
     * @throws IOException 加载过程中的异常
     */
    public static <T> T loadResourceOrSupplie(String path,
                                              ThrowableFunction<IOException, InputStream, T> translator,
                                              ThrowableSupplier<IOException, T> supplier) throws IOException {
        return loadResourceOrSupplie(DEFAULT_CLASSLOADER, path, translator, supplier);
    }

    /**
     * 使用指定的类加载器加载一个资源，并用特地的处理方法处理为一个所需对象。
     * 如果没有找到该资源，则返回一个默认的所需对象。
     * @param classLoader 类加载器
     * @param path 资源路径
     * @param translator 处理方法
     * @param supplier 默认对象的提供者
     * @param <T> 默认所需对象类型
     * @return 加载处理过，或默认的对象
     * @throws IOException 加载过程中的异常
     */
    public static <T> T loadResourceOrSupplie(ClassLoader classLoader,
                                              String path,
                                              ThrowableFunction<IOException, InputStream, T> translator,
                                              ThrowableSupplier<IOException, T> supplier) throws IOException {
        final InputStream inputStream = classLoader.getResourceAsStream(path);
        return Objects.nonNull(inputStream) ? translator.throwableApply(inputStream) : supplier.get();
    }

    /**
     * 使用默认的类加载器加载一个资源，并用特地的处理方法处理为一个所需对象。
     * 如果没有找到该资源，则返回一个默认的所需对象。
     * @param path 资源路径
     * @param translator 处理方法
     * @param defaultValue 默认所需对象
     * @param <T> 默认所需对象类型
     * @return 加载处理过，或默认的对象
     * @throws IOException 加载过程中的异常
     */
    public static <T> T loadResourceOrDefault(String path,
                                              ThrowableFunction<IOException, InputStream, T> translator,
                                              T defaultValue) throws IOException {
        return loadResourceOrDefault(DEFAULT_CLASSLOADER, path, translator, defaultValue);
    }

    /**
     * 使用指定的类加载器加载一个资源，并用特地的处理方法处理为一个所需对象。
     * 如果没有找到该资源，则返回一个默认的所需对象。
     * @param classLoader 类加载器
     * @param path 资源路径
     * @param translator 处理方法
     * @param defaultValue 默认所需对象
     * @param <T> 默认所需对象类型
     * @return 加载处理过，或默认的对象
     * @throws IOException 加载过程中的异常
     */
    public static <T> T loadResourceOrDefault(ClassLoader classLoader,
                                              String path,
                                              ThrowableFunction<IOException, InputStream, T> translator,
                                              T defaultValue) throws IOException {
        final InputStream inputStream = classLoader.getResourceAsStream(path);
        return Objects.nonNull(inputStream) ? translator.throwableApply(inputStream) : defaultValue;
    }
}
