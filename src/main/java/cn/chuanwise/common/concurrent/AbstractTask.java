package cn.chuanwise.common.concurrent;

import cn.chuanwise.common.util.Preconditions;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 抽象任务
 *
 * @author Chuanwise
 */
public abstract class AbstractTask
    implements Task {
    
    /**
     * 任务执行失败时的异常
     */
    protected volatile Throwable cause;
    
    /**
     * 监听器
     */
    @SuppressWarnings("all")
    protected final List<ActionListener<? extends Task>> listeners = new CopyOnWriteArrayList<>();
    
    @Override
    public Throwable getCause() {
        return cause;
    }
    
    @Override
    public Task registerListener(ActionListener<? extends Task> listener) {
        Preconditions.objectNonNull(listener, "listener");
    
        listeners.add(listener);
    
        return this;
    }
    
    @Override
    public Task registerListeners(ActionListener<? extends Task>... listeners) {
        Preconditions.objectNonNull(listeners, "listeners");
    
        for (ActionListener<? extends Task> listener : listeners) {
            Preconditions.objectNonNull(listener, "listener");
            this.listeners.add(listener);
        }
        
        return this;
    }
    
    @Override
    public Task registerListeners(Iterable<? extends ActionListener<? extends Task>> listeners) {
        Preconditions.objectNonNull(listeners, "listeners");
    
        for (ActionListener<? extends Task> listener : listeners) {
            Preconditions.objectNonNull(listener, "listener");
            this.listeners.add(listener);
        }
    
        return this;
    }
    
    @Override
    public Task unregisterListener(ActionListener<? extends Task> listener) {
        Preconditions.objectNonNull(listener, "listener");
    
        listeners.remove(listener);
        
        return this;
    }
    
    @Override
    public Task unregisterListeners(ActionListener<? extends Task>... listeners) {
        Preconditions.objectNonNull(listeners, "listeners");
    
        for (ActionListener<? extends Task> listener : listeners) {
            Preconditions.objectNonNull(listener, "listener");
            this.listeners.remove(listener);
        }
    
        return this;
    }
    
    @Override
    public Task unregisterListeners(Iterable<? extends ActionListener<? extends Task>> listeners) {
        Preconditions.objectNonNull(listeners, "listeners");
    
        for (ActionListener<? extends Task> listener : listeners) {
            Preconditions.objectNonNull(listener, "listener");
            this.listeners.remove(listener);
        }
    
        return this;
    }
}