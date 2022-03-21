package cn.chuanwise.common.api;

import java.util.function.BiConsumer;

/**
 * 接收两个参数的，可以抛出异常的 {@link BiConsumer}
 *
 * @author Chuanwise
 * @param <T> 第一个参数类型
 * @param <U> 第二个参数类型
 */
@FunctionalInterface
public interface ExceptionBiConsumer<T, U> extends BiConsumer<T, U> {
    
    /**
     * 调用 {@link #exceptAccept(Object, Object)}
     * 如果抛出异常，包装为 {@link RuntimeException} 再次抛出。
     *
     * @param t 第一个参数
     * @param u 第二个参数
     * @see BiConsumer#accept(Object, Object)
     */
    @Override
    default void accept(T t, U u) {
        try {
            exceptAccept(t, u);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 接收两个值，可以抛出异常。
     *
     * @param t 第一个参数
     * @param u 第二个参数
     * @throws Exception 执行时出现异常
     * @see BiConsumer#accept(Object, Object)
     */
    void exceptAccept(T t, U u) throws Exception;
}