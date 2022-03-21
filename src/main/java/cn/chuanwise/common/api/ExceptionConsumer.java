package cn.chuanwise.common.api;

import java.util.function.Consumer;

/**
 * 可以抛出异常的 {@link Consumer}
 *
 * @author Chuanwise
 */
@FunctionalInterface
public interface ExceptionConsumer<T>
    extends Consumer<T> {
    
    /**
     * 调用 {@link #exceptAccept(Object)}
     * 如果抛出异常，包装为 {@link RuntimeException} 再次抛出。
     *
     * @param t 参数值
     * @see Consumer#accept(Object)
     */
    @Override
    default void accept(T t) {
        try {
            exceptAccept(t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 接收一个值，可以抛出异常。
     *
     * @param t 参数值
     * @throws Exception 执行时出现异常
     * @see Consumer#accept(Object)
     */
    void exceptAccept(T t) throws Exception;
}