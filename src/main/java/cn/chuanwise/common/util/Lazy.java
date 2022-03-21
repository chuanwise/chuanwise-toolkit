package cn.chuanwise.common.util;

import java.util.function.Supplier;

/**
 * 懒加载值
 *
 * @param <T> 懒加载值
 * @author Chuanwise
 */
public class Lazy<T>
    implements Supplier<T> {
    
    private boolean initialized;
    
    private final Supplier<T> initializer;
    
    private T value;
    
    protected Lazy(Supplier<T> initializer) {
        Preconditions.namedArgumentNonNull(initializer, "initializer");
        
        this.initializer = initializer;
    }
    
    /**
     * 构造一个懒加载值
     *
     * @param initializer 初始化器
     * @param <T> 懒加载值类型
     * @return 懒加载值
     */
    public static <T> Lazy<T> of(Supplier<T> initializer) {
        Preconditions.namedArgumentNonNull(initializer, "initializer");
        
        return new Lazy<>(initializer);
    }
    
    /**
     * 获取值
     *
     * @return 懒加载值
     */
    @Override
    public synchronized T get() {
        if (!initialized) {
            value = initializer.get();
            initialized = true;
        }
        return value;
    }
}
