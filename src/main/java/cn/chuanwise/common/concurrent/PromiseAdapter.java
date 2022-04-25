package cn.chuanwise.common.concurrent;

import lombok.Data;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Data
public class PromiseAdapter<T>
    implements Promise<T> {
    
    private final Promise<T> promise;
    
    @Override
    public T get(long timeout) throws InterruptedException, ExecutionException, TimeoutException {
        return promise.get(timeout);
    }
    
    @Override
    public boolean isDone() {
        return promise.isDone();
    }
    
    @Override
    public T get() throws InterruptedException, ExecutionException {
        return promise.get();
    }
    
    @Override
    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return promise.get(timeout, unit);
    }
    
    @Override
    public boolean isSucceed() {
        return promise.isSucceed();
    }
    
    @Override
    public boolean isExecuting() {
        return promise.isExecuting();
    }
    
    @Override
    public boolean isFailed() {
        return promise.isFailed();
    }
    
    @Override
    public Throwable getCause() {
        return promise.getCause();
    }
    
    @Override
    public void awaitDone() throws InterruptedException {
        promise.awaitDone();
    }
    
    @Override
    public void awaitDoneUninterruptibly() {
        promise.awaitDoneUninterruptibly();
    }
    
    @Override
    public boolean awaitDone(long timeout) throws InterruptedException {
        return promise.awaitDone(timeout);
    }
    
    @Override
    public boolean awaitDone(long timeout, TimeUnit timeUnit) throws InterruptedException {
        return promise.awaitDone(timeout, timeUnit);
    }
    
    @Override
    public boolean awaitDoneUninterruptibly(long timeout) {
        return promise.awaitDoneUninterruptibly(timeout);
    }
    
    @Override
    public boolean awaitDoneUninterruptibly(long timeout, TimeUnit timeUnit) {
        return promise.awaitDoneUninterruptibly(timeout, timeUnit);
    }
    
    @Override
    public boolean isCancelled() {
        return promise.isCancelled();
    }
    
    @Override
    public boolean cancel(boolean interrupt) {
        return promise.cancel(interrupt);
    }
    
    @Override
    public Task registerDoneListener(ActionListener listener) {
        return promise.registerDoneListener(listener);
    }
    
    @Override
    public Task registerDoneListeners(ActionListener... listeners) {
        return promise.registerDoneListeners(listeners);
    }
    
    @Override
    public Task registerDoneListeners(Iterable<? extends ActionListener> listeners) {
        return promise.registerDoneListeners(listeners);
    }
    
    @Override
    public Task unregisterDoneListener(ActionListener listener) {
        return promise.unregisterDoneListener(listener);
    }
    
    @Override
    public Task unregisterDoneListeners(ActionListener... listeners) {
        return promise.unregisterDoneListeners(listeners);
    }
    
    @Override
    public Task unregisterDoneListeners(Iterable<? extends ActionListener> listeners) {
        return promise.unregisterDoneListeners(listeners);
    }
}
