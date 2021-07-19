package cn.chuanwise.toolkit.preservable;

import cn.chuanwise.toolkit.serialize.serializer.Serializer;

import java.io.IOException;

/**
 * 可以保存的数据
 * @author Chuanwise
 */
public interface Preservable<Medium> {
    /**
     * 保存当前数据到当前存储介质
     * @return 保存是否成功
     */
    default boolean saveOrFail() {
        try {
            save();
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    /**
     * 保存当前数据到当前存储介质
     * @exception IOException 保存失败时的异常
     */
    default void save() throws IOException {
        save(getMedium());
    }

    /**
     * 保存当前数据到当前存储介质
     * @param replace 介质已经存在时是否替换
     * @exception IOException 保存时的异常
     */
    default void save(boolean replace) throws IOException {
        save(getMedium(), replace);
    }

    /**
     * 保存当前数据到指定存储介质。当介质不存在时，自动创建。
     * @param medium 存储介质
     * @exception IOException 保存时的异常
     */
    default void save(Medium medium) throws IOException {
        save(getSerializer(), medium, true);
    }

    /**
     * 保存当前数据到指定存储介质。当介质不存在时，自动创建。
     * @param serializer 序列化器
     * @exception IOException 保存时的异常
     */
    default void save(Serializer serializer) throws IOException {
        save(serializer, getMedium(), true);
    }

    /**
     * 保存当前数据到指定存储介质。当介质不存在时，自动创建。
     * @param medium 存储介质
     * @param replace 介质已经存在时是否替换
     * @return 保存是否成功
     * @exception IOException 保存时的异常
     */
    default void save(Medium medium, boolean replace) throws IOException {
        save(getSerializer(), medium, replace);
    }

    /**
     * 使用指定的序列化器保存当前数据到指定存储介质
     * @param serializer 序列化器
     * @param replace 介质已经存在时是否替换
     * @exception IOException 保存时的异常
     */
    default void save(Serializer serializer, boolean replace) throws IOException {
        save(serializer, getMedium(), replace);
    }

    /**
     * 使用指定的序列化器保存当前数据到指定存储介质。当介质不存在时，自动创建。
     * @param serializer 序列化器
     * @param medium 存储介质
     * @exception IOException 保存时的异常
     */
    default void save(Serializer serializer, Medium medium) throws IOException {
        save(serializer, medium, true);
    }

    /**
     * 使用指定的序列化器保存当前数据到指定存储介质
     * @param serializer 序列化器
     * @param medium 存储介质
     * @param replace 介质已经存在时是否替换
     * @throws IOException 保存时的异常
     */
    void save(Serializer serializer, Medium medium, boolean replace) throws IOException;

    void setSerializer(Serializer serializer);

    Serializer getSerializer();

    Medium getMedium();

    void setMedium(Medium medium);
}
