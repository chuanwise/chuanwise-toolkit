package cn.chuanwise.common.concurrent;

import java.util.concurrent.*;

/**
 * @author Chuanwise
 *
 * @see cn.chuanwise.common.concurrent.Promise
 */
public abstract class AbstractThreadPromise<T>
    extends AbstractThreadTask
    implements Promise<T>, Callable<T>, Future<T> {
    
    private volatile T result;
    
    @Override
    public final T call() throws Exception {
        run();
        return result;
    }
    
    @Override
    @SuppressWarnings("all")
    protected final void run0() throws Exception {
        result = call0();
    }
    
    @Override
    public T get() throws InterruptedException, ExecutionException {
        awaitDone();
        switch (state) {
            case SUCCEED:
                return result;
            case FAILED:
                throw new ExecutionException(cause);
            case CANCELLED:
                throw new CancellationException();
            default:
                throw new IllegalStateException();
        }
    }
    
    @Override
    public final T get(long timeout) throws InterruptedException, ExecutionException, TimeoutException {
        awaitDone(timeout);
        switch (state) {
            case SUCCEED:
                return result;
            case FAILED:
                throw new ExecutionException(cause);
            case CANCELLED:
                throw new CancellationException();
            default:
                throw new IllegalStateException();
        }
    }
    
    @Override
    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        awaitDone(timeout, unit);
        switch (state) {
            case SUCCEED:
                return result;
            case FAILED:
                throw new ExecutionException(cause);
            case CANCELLED:
                throw new CancellationException();
            default:
                throw new IllegalStateException();
        }
    }
    
    /**
     * 计算相关值
     *
     * @return 相关值
     * @throws Exception 计算时出现异常
     */
    protected abstract T call0() throws Exception;
}