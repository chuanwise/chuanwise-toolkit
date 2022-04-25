package cn.chuanwise.common.concurrent;

import cn.chuanwise.common.util.Preconditions;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Chuanwise
 *
 * @see cn.chuanwise.common.concurrent.PeriodicTask
 */
public abstract class AbstractPeriodicTask
    extends AbstractTask
    implements PeriodicTask {
    
    /**
     * 任务执行状态
     */
    protected enum State {
    
        /**
         * 任务正在等待被执行
         */
        INITIALIZED,
    
        /**
         * 正在执行状态
         */
        EXECUTING,
    
        /**
         * 等待下一次执行
         */
        WAITING,
    
        /**
         * 任务执行失败
         */
        FAILED,
    
        /**
         * 任务被取消
         */
        CANCELLED,
    }
    
    /**
     * 任务执行状态
     */
    protected volatile State state = State.INITIALIZED;
    
    /**
     * 监听器
     */
    @SuppressWarnings("all")
    protected final List<ActionListener> periodListeners = new CopyOnWriteArrayList<>();
    
    @Override
    public boolean isDone() {
        return state == State.CANCELLED
            || state == State.FAILED;
    }
    
    @Override
    public boolean isSucceed() {
        return false;
    }
    
    @Override
    public boolean isFailed() {
        return state == State.FAILED;
    }
    
    @Override
    public boolean isExecuting() {
        return state == State.EXECUTING;
    }
    
    @Override
    public PeriodicTask registerPeriodicListener(ActionListener listener) {
        Preconditions.objectNonNull(listener, "listener");
    
        periodListeners.add(listener);
        
        if (isDone()) {
            listener.listen(this);
        }
        
        return this;
    }
    
    @Override
    public PeriodicTask registerPeriodicListeners(ActionListener... listeners) {
        Preconditions.objectNonNull(listeners, "listeners");
    
        final boolean done = isDone();
    
        for (ActionListener listener : listeners) {
            Preconditions.objectNonNull(listener, "listener");
            periodListeners.add(listener);
            if (done) {
                listener.listen(this);
            }
        }
    
        return this;
    }
    
    @Override
    public PeriodicTask registerPeriodicListeners(Iterable<? extends ActionListener> listeners) {
        Preconditions.objectNonNull(listeners, "listeners");
    
        final boolean done = isDone();
    
        for (ActionListener listener : listeners) {
            Preconditions.objectNonNull(listener, "listener");
            periodListeners.add(listener);
            if (done) {
                listener.listen(this);
            }
        }
    
        return this;
    }
    
    @Override
    public PeriodicTask unregisterPeriodicListener(ActionListener listener) {
        Preconditions.objectNonNull(listener, "listener");

        periodListeners.remove(listener);
        
        return this;
    }
    
    @Override
    public PeriodicTask unregisterPeriodicListeners(ActionListener... listeners) {
        Preconditions.objectNonNull(listeners, "listeners");
    
        for (ActionListener listener : listeners) {
            Preconditions.objectNonNull(listener, "listener");
            periodListeners.remove(listener);
        }
        
        return this;
    }
    
    @Override
    public PeriodicTask unregisterPeriodicListeners(Iterable<? extends ActionListener> listeners) {
        Preconditions.objectNonNull(listeners, "listeners");
    
        for (ActionListener listener : listeners) {
            Preconditions.objectNonNull(listener, "listener");
            periodListeners.remove(listener);
        }
    
        return this;
    }
    
    @Override
    public PeriodicTask registerDoneListener(ActionListener listener) {
        return (PeriodicTask) super.registerDoneListener(listener);
    }
    
    @Override
    public PeriodicTask registerDoneListeners(ActionListener... listeners) {
        return (PeriodicTask) super.registerDoneListeners(listeners);
    }
    
    @Override
    public PeriodicTask registerDoneListeners(Iterable<? extends ActionListener> listeners) {
        return (PeriodicTask) super.registerDoneListeners(listeners);
    }
    
    @Override
    public PeriodicTask unregisterDoneListener(ActionListener listener) {
        return (PeriodicTask) super.unregisterDoneListener(listener);
    }
    
    @Override
    public PeriodicTask unregisterDoneListeners(ActionListener... listeners) {
        return (PeriodicTask) super.unregisterDoneListeners(listeners);
    }
    
    @Override
    public PeriodicTask unregisterDoneListeners(Iterable<? extends ActionListener> listeners) {
        return (PeriodicTask) super.unregisterDoneListeners(listeners);
    }
}
