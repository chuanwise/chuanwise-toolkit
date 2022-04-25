package cn.chuanwise.common.concurrent;

import cn.chuanwise.common.util.Objects;
import cn.chuanwise.common.util.Preconditions;

import java.util.concurrent.TimeUnit;

/**
 * @author Chuanwise
 *
 * @see cn.chuanwise.common.concurrent.PeriodicTask
 */
public abstract class AbstractThreadPeriodicTask
    extends AbstractPeriodicTask
    implements Runnable {
    
    /**
     * 执行任务的线程，只有在 {@link #state} 为 {@link State#EXECUTING} 时非空
     */
    protected volatile Thread thread;
    
    /**
     * 设置最终状态时的信号量
     */
    protected final Object doneMutex = new Object();
    
    /**
     * 周期结束时的信号量
     */
    protected final Object periodMutex = new Object();
    
    /**
     * 需要跳过的次数
     */
    protected volatile int skipping = 0;
    
    
    @SuppressWarnings("all")
    private void setDoneState(State state) {
        this.state = state;
        
        synchronized (periodMutex) {
            periodMutex.notifyAll();
        }
        
        for (ActionListener listener : periodListeners) {
            listener.listen(this);
        }
        
        synchronized (doneMutex) {
            doneMutex.notifyAll();
        }
        
        for (ActionListener listener : doneListeners) {
            listener.listen(this);
        }
    }
    
    @SuppressWarnings("all")
    private void setPeriodState(State state) {
        this.state = state;
        
        synchronized (periodMutex) {
            periodMutex.notifyAll();
        }
        
        for (ActionListener listener : periodListeners) {
            listener.listen(this);
        }
    }
    
    
    @Override
    public void awaitDone() throws InterruptedException {
        switch (state) {
            case INITIALIZED:
            case EXECUTING:
            case WAITING:
                Objects.await(periodMutex);
                break;
            case FAILED:
            case CANCELLED:
                break;
            default:
                throw new IllegalStateException();
        }
    }
    
    @Override
    public void awaitDoneUninterruptibly() {
        switch (state) {
            case INITIALIZED:
            case EXECUTING:
            case WAITING:
                Objects.awaitUninterruptibly(periodMutex);
                break;
            case FAILED:
            case CANCELLED:
                break;
            default:
                throw new IllegalStateException();
        }
    }
    
    @Override
    public boolean awaitDone(long timeout) throws InterruptedException {
        switch (state) {
            case INITIALIZED:
            case EXECUTING:
            case WAITING:
                return Objects.await(periodMutex, timeout);
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
            case WAITING:
                return Objects.await(periodMutex, timeout, timeUnit);
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
            case WAITING:
                return Objects.awaitUninterruptibly(periodMutex, timeout);
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
            case WAITING:
                return Objects.awaitUninterruptibly(periodMutex, timeout, timeUnit);
            case FAILED:
            case CANCELLED:
                return false;
            default:
                throw new IllegalStateException();
        }
    }
    
    @Override
    public boolean isCancelled() {
        return state == State.CANCELLED;
    }
    
    @Override
    public boolean cancel(boolean interrupt) {
        switch (state) {
            case INITIALIZED:
            case WAITING:
                setDoneState(State.CANCELLED);
                return true;
            case EXECUTING:
                setDoneState(State.CANCELLED);
                if (interrupt) {
                    thread.interrupt();
                }
                return true;
            case FAILED:
            case CANCELLED:
                return false;
            default:
                throw new IllegalStateException();
        }
    }
    
    @Override
    public boolean skipNext() {
        return skipNext(1);
    }
    
    @Override
    public boolean skipNext(int count) {
        Preconditions.argument(count >= 0, "skip count must be bigger than or equals to 0!");
        
        switch (state) {
            case INITIALIZED:
            case EXECUTING:
            case WAITING:
                skipping = Math.max(skipping, count);
                return true;
            case FAILED:
            case CANCELLED:
                return false;
            default:
                throw new IllegalStateException();
        }
    }
    
    @Override
    public boolean isSkipping() {
        return state == State.WAITING
            && skipping > 0;
    }
    
    @Override
    public boolean isWaiting() {
        return state == State.WAITING;
    }
    
    @Override
    public void awaitPeriod() throws InterruptedException {
        switch (state) {
            case INITIALIZED:
            case EXECUTING:
            case WAITING:
                Objects.await(periodMutex);
                break;
            case FAILED:
            case CANCELLED:
                break;
            default:
                throw new IllegalStateException();
        }
    }
    
    @Override
    public void awaitPeriodUninterruptibly() {
        switch (state) {
            case INITIALIZED:
            case EXECUTING:
            case WAITING:
                Objects.awaitUninterruptibly(periodMutex);
                break;
            case FAILED:
            case CANCELLED:
                break;
            default:
                throw new IllegalStateException();
        }
    }
    
    @Override
    public boolean awaitPeriod(long timeout) throws InterruptedException {
        switch (state) {
            case INITIALIZED:
            case EXECUTING:
            case WAITING:
                return Objects.await(periodMutex, timeout);
            case FAILED:
            case CANCELLED:
                return false;
            default:
                throw new IllegalStateException();
        }
    }
    
    @Override
    public boolean awaitPeriod(long timeout, TimeUnit timeUnit) throws InterruptedException {
        switch (state) {
            case INITIALIZED:
            case EXECUTING:
            case WAITING:
                return Objects.await(periodMutex, timeout, timeUnit);
            case FAILED:
            case CANCELLED:
                return false;
            default:
                throw new IllegalStateException();
        }
    }
    
    @Override
    public boolean awaitPeriodUninterruptibly(long timeout) {
        switch (state) {
            case INITIALIZED:
            case EXECUTING:
            case WAITING:
                return Objects.awaitUninterruptibly(periodMutex, timeout);
            case FAILED:
            case CANCELLED:
                return false;
            default:
                throw new IllegalStateException();
        }
    }
    
    @Override
    public boolean awaitPeriodUninterruptibly(long timeout, TimeUnit timeUnit) {
        switch (state) {
            case INITIALIZED:
            case EXECUTING:
            case WAITING:
                return Objects.awaitUninterruptibly(periodMutex, timeout, timeUnit);
            case FAILED:
            case CANCELLED:
                return false;
            default:
                throw new IllegalStateException();
        }
    }
    
    @Override
    public final void run() {
        switch (state) {
            case INITIALIZED:
                break;
            case EXECUTING:
                throw new IllegalStateException("can not run single task in parallel");
            case WAITING:
                break;
            case FAILED:
            case CANCELLED:
                return;
            default:
                throw new IllegalStateException();
        }
    
        if (skipping > 0) {
            skipping--;
            return;
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
                setPeriodState(State.WAITING);
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
            setPeriodState(State.FAILED);
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
}