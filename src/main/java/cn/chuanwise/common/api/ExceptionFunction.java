package cn.chuanwise.common.api;

import java.util.function.Function;

/**
 * 可以抛出异常的 {@link Function}
 *
 * @author Chuanwise
 */
@FunctionalInterface
public interface ExceptionFunction<T, R>
    extends Function<T, R> {
    
    /**
     * 调用 {@link #exceptApply(Object)}
     * 如果抛出异常，包装为 {@link RuntimeException} 再次抛出。
     *
     * @param t 参数值
     * @return 返回值
     * @see Function#apply(Object)
     */
    @Override
    default R apply(T t) {
        try {
            return exceptApply(t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 接收一个值，可以抛出异常。
     *
     * @param t 参数值
     * @return 返回值
     * @throws Exception 执行时出现异常
     * @see Function#apply(Object)
     */
    R exceptApply(T t) throws Exception;
}
