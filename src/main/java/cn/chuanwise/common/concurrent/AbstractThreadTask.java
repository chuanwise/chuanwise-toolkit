package cn.chuanwise.common.concurrent;

import cn.chuanwise.common.util.Objects;

import java.util.concurrent.TimeUnit;

/**
 * 抽象线程任务
 *
 * @author Chuanwise
 */
public abstract class AbstractThreadTask
    extends AbstractTask
    implements Runnable, Task {
    
    /**
     * 任务执行状态
     */
    protected enum State {
        
        /**
         * 初始化状态，任务正在等待被执行
         */
        INITIALIZED,
        
        /**
         * 正在执行状态
         */
        EXECUTING,
        
        /**
         * 执行成功状态
         */
        SUCCEED,
        
        /**
         * 执行失败状态
         */
        FAILED,
        
        /**
         * 执行被取消状态
         */
        CANCELLED,
    }
    
    /**
     * 任务执行状态
     */
    protected volatile State state = State.INITIALIZED;
    
    /**
     * 执行任务的线程，只有在 {@link #state} 为 {@link State#EXECUTING} 时非空
     */
    protected volatile Thread thread;
    
    /**
     * 设置最终状态时的信号量
     */
    protected final Object mutex = new Object();
    
    @SuppressWarnings("all")
    private void setDoneState(State state) {
        this.state = state;
    
        synchronized (mutex) {
            mutex.notifyAll();
        }
        
        for (ActionListener listener : doneListeners) {
            listener.listen(this);
        }
    }
    
    @Override
    public boolean isSucceed() {
        return state == State.SUCCEED;
    }
    
    @Override
    public boolean isExecuting() {
        return state == State.EXECUTING;
    }
    
    @Override
    public boolean isFailed() {
        return state == State.FAILED;
    }
    
    @Override
    public boolean isCancelled() {
        return state == State.CANCELLED;
    }
    
    @Override
    public boolean isDone() {
        return state == State.SUCCEED
            || state == State.FAILED
            || state == State.CANCELLED;
    }
    
    @Override
    public boolean cancel(boolean interrupt) {
        switch (state) {
            case INITIALIZED:
                setDoneState(State.CANCELLED);
                return true;
            case EXECUTING:
                setDoneState(State.CANCELLED);
                if (interrupt) {
                    thread.interrupt();
                }
                return true;
            case SUCCEED:
            case FAILED:
            case CANCELLED:
                return false;
            default:
                throw new IllegalStateException();
        }
    }
    
    @Override
    @SuppressWarnings("all")
    public final void run() {
        switch (state) {
            case INITIALIZED:
                break;
            case EXECUTING:
                throw new IllegalStateException("can not run single task in parallel");
            case SUCCEED:
            case FAILED:
            case CANCELLED:
                return;
            default:
                throw new IllegalStateException();
        }
        
        try {
            // set state value
            thread = Thread.currentThread();
            state = State.EXECUTING;
            
            // do run
            run0();
            
            // if it's canceled without interrupting,
            // the state will be cancelled
            // or set state to succeed
            if (state == State.EXECUTING) {
                setDoneState(State.SUCCEED);
            }
        } catch (Throwable throwable) {
            // if it's cancelling in interrupt
            // and the exception is caused by interrupting
            // ignore it
            if (state == State.CANCELLED && throwable instanceof InterruptedException) {
                return;
            }
            
            // set state to failed
            // and record the exception
            cause = throwable;
            setDoneState(State.FAILED);
        } finally {
            thread = null;
        }
    }
    
    /**
     * 真正执行相关操作
     *
     * @throws Exception 执行操作时抛出的异常
     */
    protected abstract void run0() throws Exception;
    
    @Override
    public void awaitDone() throws InterruptedException {
        switch (state) {
            case INITIALIZED:
            case EXECUTING:
                Objects.await(mutex);
                break;
            case SUCCEED:
            case FAILED:
            case CANCELLED:
                return;
            default:
                throw new IllegalStateException();
        }
    }
    
    @Override
    public void awaitDoneUninterruptibly() {
        switch (state) {
            case INITIALIZED:
            case EXECUTING:
                Objects.awaitUninterruptibly(mutex);
                break;
            case SUCCEED:
            case FAILED:
            case CANCELLED:
                return;
            default:
                throw new IllegalStateException();
        }
    }
    
    @Override
    public boolean awaitDone(long timeout) throws InterruptedException {
        switch (state) {
            case INITIALIZED:
            case EXECUTING:
                return Objects.await(mutex, timeout);
            case SUCCEED:
            case FAILED:
            case CANCELLED:
                return false;
            default:
                throw new IllegalStateException();
        }
    }
    
    @Override
    public boolean awaitDone(long timeout, TimeUnit timeUnit) throws InterruptedException {
        switch (state) {
            case INITIALIZED:
            case EXECUTING:
                return Objects.await(mutex, timeout, timeUnit);
            case SUCCEED:
            case FAILED:
            case CANCELLED:
                return false;
            default:
                throw new IllegalStateException();
        }
    }
    
    @Override
    public boolean awaitDoneUninterruptibly(long timeout) {
        switch (state) {
            case INITIALIZED:
            case EXECUTING:
                return Objects.awaitUninterruptibly(mutex, timeout);
            case SUCCEED:
            case FAILED:
            case CANCELLED:
                return false;
            default:
                throw new IllegalStateException();
        }
    }
    
    @Override
    public boolean awaitDoneUninterruptibly(long timeout, TimeUnit timeUnit) {
        switch (state) {
            case INITIALIZED:
            case EXECUTING:
                return Objects.awaitUninterruptibly(mutex, timeout, timeUnit);
            case SUCCEED:
            case FAILED:
            case CANCELLED:
                return false;
            default:
                throw new IllegalStateException();
        }
    }
}