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
    protected final List<ActionListener> doneListeners = new CopyOnWriteArrayList<>();
    
    @Override
    public Throwable getCause() {
        return cause;
    }
    
    @Override
    public Task registerDoneListener(ActionListener listener) {
        Preconditions.objectNonNull(listener, "listener");
    
        doneListeners.add(listener);
        
        if (isDone()) {
            listener.listen(this);
        }
    
        return this;
    }
    
    @Override
    public Task registerDoneListeners(ActionListener... listeners) {
        Preconditions.objectNonNull(listeners, "listeners");
    
        final boolean done = isDone();
        
        for (ActionListener listener : listeners) {
            Preconditions.objectNonNull(listener, "listener");
            this.doneListeners.add(listener);
    
            if (done) {
                listener.listen(this);
            }
        }
        
        return this;
    }
    
    @Override
    public Task registerDoneListeners(Iterable<? extends ActionListener> listeners) {
        Preconditions.objectNonNull(listeners, "listeners");
    
        final boolean done = isDone();
    
        for (ActionListener listener : listeners) {
            Preconditions.objectNonNull(listener, "listener");
            this.doneListeners.add(listener);
        
            if (done) {
                listener.listen(this);
            }
        }
    
        return this;
    }
    
    @Override
    public Task unregisterDoneListener(ActionListener listener) {
        Preconditions.objectNonNull(listener, "listener");
    
        doneListeners.remove(listener);
        
        return this;
    }
    
    @Override
    public Task unregisterDoneListeners(ActionListener... listeners) {
        Preconditions.objectNonNull(listeners, "listeners");
    
        for (ActionListener listener : listeners) {
            Preconditions.objectNonNull(listener, "listener");
            this.doneListeners.remove(listener);
        }
    
        return this;
    }
    
    @Override
    public Task unregisterDoneListeners(Iterable<? extends ActionListener> listeners) {
        Preconditions.objectNonNull(listeners, "listeners");
    
        for (ActionListener listener : listeners) {
            Preconditions.objectNonNull(listener, "listener");
            this.doneListeners.remove(listener);
        }
    
        return this;
    }
}