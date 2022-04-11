package cn.chuanwise.common.api;

import java.util.function.Supplier;

/**
 * 可以抛出异常的 {@link Supplier}
 *
 * @author Chuanwise
 */
@FunctionalInterface
public interface ExceptionSupplier<T>
    extends Supplier<T> {
    
    /**
     * 调用 {@link #exceptGet()}
     * 如果抛出异常，包装为 {@link RuntimeException} 再次抛出。
     *
     * @return 返回值
     * @see Supplier#get()
     */
    @Override
    default T get() {
        try {
            return exceptGet();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 接收一个值，可以抛出异常。
     *
     * @return 返回值
     * @throws Exception 执行时出现异常
     * @see Supplier#get()
     */
    T exceptGet() throws Exception;
}