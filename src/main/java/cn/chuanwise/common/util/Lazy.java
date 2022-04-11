package cn.chuanwise.common.util;

import java.util.function.Supplier;

/**
 * 懒加载值
 *
 * @param <T> 懒加载值
 * @author Chuanwise
 */
public interface Lazy<T>
    extends Supplier<T> {
    
    /**
     * 获取懒加载值的值。
     * 如果该值尚未加载，则首先初始化再返回值。
     *
     * @return 懒加载的值
     */
    @Override
    T get();
    
    /**
     * 获取懒加载的值，或默认值。
     * 如果该值尚未加载，直接返回默认值，不会初始化。
     *
     * @param defaultValue 默认值
     * @return 懒加载的值，或默认值
     */
    T get(T defaultValue);
    
    /**
     * 询问值是否已经加载
     *
     * @return 值是否已经加载
     */
    boolean isInitialized();
    
    /**
     * 构造一个线程安全的懒加载器值。
     *
     * @param getter 初始化器
     * @param <U>    懒加载器值类型
     * @return 懒加载器
     */
    static <U> Lazy<U> ofConcurrent(Supplier<U> getter) {
        Preconditions.objectNonNull(getter, "getter");
        
        return new ConcurrentLazy<>(getter);
    }
    
    /**
     * 构造一个普通的懒加载器值。
     *
     * @param getter 初始化器
     * @param <U>    懒加载器值类型
     * @return 懒加载器
     */
    static <U> Lazy<U> of(Supplier<U> getter) {
        Preconditions.objectNonNull(getter, "getter");
        
        return new SimpleLazy<>(getter);
    }
    
    /**
     * 获取一个已经加载过的懒加载器值。
     *
     * @param value 懒加载器值
     * @param <U>   懒加载器值类型
     * @return 懒加载器
     */
    static <U> Lazy<U> ofInitialized(U value) {
        return InitializedLazy.of(value);
    }
}