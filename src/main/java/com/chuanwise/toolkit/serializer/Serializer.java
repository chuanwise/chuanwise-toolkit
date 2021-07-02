package com.chuanwise.toolkit.serializer;

import java.io.*;
import java.nio.charset.Charset;

/**
 * 序列化工具类
 * @author Chuanwise
 */
public interface Serializer {
    /**
     * 序列化一个对象为字符串
     * @param object 需要序列化的对象
     * @return 序列化后的字符串
     */
    String serialize(Object object);

    /**
     * 序列化一个对象为默认编码的字符串，并将序列化后的结果存入文件中
     * @param object 需要序列化的对象
     * @param file 需要保存到的文件
     * @param createFile 当该文件不存在时，是否创建文件
     * @throws IOException 写入序列化结果时抛出的异常
     */
    default void serialize(Object object, File file, boolean createFile) throws IOException {
        if (createFile && !file.isFile()) {
            file.createNewFile();
        }
        try (final OutputStream outputStream = new FileOutputStream(file)) {
            serialize(object, Charset.defaultCharset().name(), outputStream);
        }
    }

    /**
     * 序列化一个对象为默认编码的字符串，并将序列化后的结果存入文件中
     * 当该文件不存在时，会自动创建
     * @param object 需要序列化的对象
     * @param file 需要保存到的文件
     * @throws IOException 写入序列化结果时抛出的异常
     */
    default void serialize(Object object, File file) throws IOException {
        serialize(object, file, true);
    }

    /**
     * 序列化一个对象为指定编码的字符串，并将序列化后的结果存入输出流中
     * @param object 需要序列化的对象
     * @param encoding 选用的编码类型
     * @param outputStream 需要保存到的输出流
     * @throws IOException 写入序列化结果时抛出的异常
     */
    default void serialize(Object object, String encoding, OutputStream outputStream) throws IOException {
        outputStream.write(serialize(object).getBytes(encoding));
    }

    /**
     * 序列化一个对象为默认编码的字符串，并将序列化后的结果存入输出流中
     * @param object 需要序列化的对象
     * @param outputStream 需要保存到的输出流
     * @throws IOException 写入序列化结果时抛出的异常
     */
    default void serialize(Object object, OutputStream outputStream) throws IOException {
        outputStream.write(serialize(object).getBytes());
    }

    /**
     * 反序列化字符串为指定类型的对象
     * @param string 数据来源
     * @param clazz 反序列化目标类型的类对象
     * @param <T> 反序列化目标类型
     * @return 反序列化结果
     */
    <T> T deserialize(String string, Class<T> clazz);

    /**
     * 从输入流中读取内容，并反序列化为指定的类型的对象
     * @param inputStream 数据来源
     * @param clazz 反序列化目标类型的类对象
     * @param <T> 反序列化目标类型
     * @return 反序列化结果
     */
    default <T> T deserialize(InputStream inputStream, Class<T> clazz) {
        return null;
    }

    /**
     * 按照默认编码读取文件的内容，将其反序列化为指定类型的对象
     * @param file 数据来源
     * @param clazz 反序列化目标类型的类对象
     * @param <T> 反序列化目标类型
     * @return 反序列化结果
     * @throws IOException 读取文件内容时抛出的异常
     */
    default <T> T deserialize(File file, Class<T> clazz) throws IOException {
        try (final InputStream inputStream = new FileInputStream(file)) {
            return deserialize(inputStream, clazz);
        }
    }

    /**
     * 按照指定编码读取文件的内容，将其反序列化为指定类型的对象
     * @param file 数据来源
     * @param encoding 指定的编码
     * @param clazz 反序列化目标类型的类对象
     * @param <T> 反序列化目标类型
     * @return 反序列化结果
     * @throws IOException 读取文件内容时抛出的异常
     */
    default <T> T deserialize(File file, String encoding, Class<T> clazz) throws IOException {
        byte[] bytes;
        try (final InputStream inputStream = new FileInputStream(file)) {
            bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
        }
        return deserialize(bytes, encoding, clazz);
    }

    /**
     * 按照指定编码反序列化字节数组的内容为指定类型的对象
     * @param bytes 数据来源
     * @param encoding 指定的编码
     * @param clazz 反序列化目标类型的类对象
     * @param <T> 反序列化目标类型
     * @return 反序列化结果
     * @throws UnsupportedEncodingException 当编码不被支持时抛出
     */
    default <T> T deserialize(byte[] bytes, String encoding, Class<T> clazz) throws UnsupportedEncodingException {
        return deserialize(new String(bytes, encoding), clazz);
    }

    /**
     * 按照默认编码反序列化字节数组的内容为指定类型的对象
     * @param bytes 数据来源
     * @param clazz 反序列化目标类型的类对象
     * @param <T> 反序列化目标类型
     * @return 反序列化结果
     */
    default <T> T deserialize(byte[] bytes, Class<T> clazz) {
        return deserialize(new String(bytes), clazz);
    }
}
