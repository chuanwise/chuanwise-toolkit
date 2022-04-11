package cn.chuanwise.common.util;

import lombok.EqualsAndHashCode;

import java.util.function.Supplier;

/**
 * 线程安全的懒加载值。
 * 
 * @author Chuanwise
 */
@EqualsAndHashCode
public final class ConcurrentLazy<T>
    implements Lazy<T> {
    
    private final Supplier<T> getter;
    
    private final Object mutex = new Object();
    
    private boolean initialized;
    
    private volatile T value;
    
    public ConcurrentLazy(Supplier<T> getter) {
        Preconditions.objectNonNull(getter, "getter");
        
        this.getter = getter;
    }
    
    @Override
    public T get() {
        if (initialized) {
            return value;
        }
        synchronized (mutex) {
            value = getter.get();
        }
        return value;
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
