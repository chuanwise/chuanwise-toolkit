package cn.chuanwise.common.util;

import lombok.EqualsAndHashCode;

import java.util.function.Supplier;

/**
 * 普通懒加载值，是线程不安全的实现。
 * 
 * @author Chuanwise
 */
@EqualsAndHashCode
public final class SimpleLazy<T> 
    implements Lazy<T> {
    
    private final Supplier<T> getter;
    
    private boolean initialized;
    
    private T value;
    
    public SimpleLazy(Supplier<T> getter) {
        Preconditions.objectNonNull(getter, "getter");
        
        this.getter = getter;
    }
    
    @Override
    public T get() {
        return initialized
            ? value
            : (value = getter.get());
    }
    
    @Override
    public T get(T defaultValue) {
        return initialized
            ? value
            : defaultValue;
    }
    
    @Override
    public boolean isInitialized() {
        return initialized;
    }
    
    @Override
    public String toString() {
        return "Lazy[initialized=" + initialized + ", value=" + value + "]";
    }
}
