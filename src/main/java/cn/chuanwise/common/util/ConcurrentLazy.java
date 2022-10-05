package cn.chuanwise.common.util;

import java.util.concurrent.CancellationException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

/**
 * 线程安全的懒加载值。
 * 
 * @author Chuanwise
 */
public final class ConcurrentLazy<T>
    implements Lazy<T> {
    
    private final Supplier<T> initializer;
    
    private final Lock lock = new ReentrantLock();
    
    private boolean initialized;
    
    private volatile T value;
    
    public ConcurrentLazy(Supplier<T> initializer) {
        Preconditions.objectNonNull(initializer, "initializer");
        
        this.initializer = initializer;
    }
    
    @Override
    public T get() {
        if (initialized) {
            return value;
        }
        try {
            lock.lockInterruptibly();
            
            if (!initialized) {
                value = initializer.get();
            }
        } catch (InterruptedException e) {
            throw new CancellationException("lock cancelled caused by interrupting");
        } finally {
            lock.unlock();
        }
        return value;
    }
    
    @Override
    public T get(T defaultValue) {
        return initialized ? value : defaultValue;
    }
    
    @Override
    public boolean isInitialized() {
        return initialized;
    }
    
    @Override
    public String toString() {
        return "Lazy{initialized=" + initialized + ", value=" + value + "}";
    }
}
