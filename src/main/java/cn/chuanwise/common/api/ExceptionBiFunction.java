package cn.chuanwise.common.api;

import java.util.function.BiFunction;

/**
 * 接收两个参数的，可以抛出异常的 {@link BiFunction}
 *
 * @author Chuanwise
 * @param <T> 第一个参数类型
 * @param <U> 第二个参数类型
 */
@FunctionalInterface
public interface ExceptionBiFunction<T, U, R>
    extends BiFunction<T, U, R> {
    
    /**
     * 调用 {@link #exceptApply(Object, Object)}
     * 如果抛出异常，包装为 {@link RuntimeException} 再次抛出。
     *
     * @param t 第一个参数
     * @param u 第二个参数
     * @return 返回值
     * @see BiFunction#apply(Object, Object)
     */
    @Override
    default R apply(T t, U u) {
        try {
            return exceptApply(t, u);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 接收两个值，可以抛出异常。
     *
     * @param t 第一个参数
     * @param u 第二个参数
     * @return 返回值
     * @throws Exception 执行时出现异常
     * @see BiFunction#apply(Object, Object)
     */
    R exceptApply(T t, U u) throws Exception;
}