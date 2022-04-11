package cn.chuanwise.common.util;

import lombok.EqualsAndHashCode;

import java.util.function.Supplier;

/**
 * 已经加载过的懒加载值，只是一个装饰器。
 * 
 * @author Chuanwise
 */
@EqualsAndHashCode
public final class InitializedLazy<T>
    implements Lazy<T> {
    
    /**
     * 加载后的值
     */
    private final T value;
    
    /**
     * 表示 null 值的已加载值
     */
    private static final InitializedLazy<?> NULL = new InitializedLazy<>(null);
    
    private InitializedLazy(T value) {
        this.value = value;
    }
    
    @SuppressWarnings("all")
    public static <U> Lazy<U> of(U value) {
        return Objects.isNull(value)
            ? (Lazy<U>) NULL
            : new InitializedLazy<>(value);
    }
    
    @SuppressWarnings("all")
    public static <U> Lazy<U> ofNull() {
        return (Lazy<U>) NULL;
    }
    
    @Override
    public T get() {
        return value;
    }
    
    @Override
    public T get(T defaultValue) {
        return value;
    }
    
    @Override
    public boolean isInitialized() {
        return true;
    }
    
    @Override
    public String toString() {
        return "Lazy[initialized=true, value=" + value + "]";
    }
}
