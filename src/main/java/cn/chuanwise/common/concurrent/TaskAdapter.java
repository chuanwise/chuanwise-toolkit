package cn.chuanwise.common.concurrent;

import lombok.Data;

import java.util.concurrent.TimeUnit;

@Data
public class TaskAdapter
    implements Task {
    
    private final Task task;
    
    @Override
    public boolean isDone() {
        return task.isDone();
    }
    
    @Override
    public boolean isSucceed() {
        return task.isSucceed();
    }
    
    @Override
    public boolean isFailed() {
        return task.isFailed();
    }
    
    @Override
    public boolean isExecuting() {
        return task.isExecuting();
    }
    
    @Override
    public Throwable getCause() {
        return task.getCause();
    }
    
    @Override
    public void awaitDone() throws InterruptedException {
        task.awaitDone();
    }
    
    @Override
    public void awaitDoneUninterruptibly() {
        task.awaitDoneUninterruptibly();
    }
    
    @Override
    public boolean awaitDone(long timeout) throws InterruptedException {
        return task.awaitDone(timeout);
    }
    
    @Override
    public boolean awaitDone(long timeout, TimeUnit timeUnit) throws InterruptedException {
        return task.awaitDone(timeout, timeUnit);
    }
    
    @Override
    public boolean awaitDoneUninterruptibly(long timeout) {
        return task.awaitDoneUninterruptibly(timeout);
    }
    
    @Override
    public boolean awaitDoneUninterruptibly(long timeout, TimeUnit timeUnit) {
        return task.awaitDoneUninterruptibly(timeout, timeUnit);
    }
    
    @Override
    public boolean isCancelled() {
        return task.isCancelled();
    }
    
    @Override
    public boolean cancel(boolean interrupt) {
        return task.cancel(interrupt);
    }
    
    @Override
    public Task registerDoneListener(ActionListener listener) {
        return task.registerDoneListener(listener);
    }
    
    @Override
    public Task registerDoneListeners(ActionListener... listeners) {
        return task.registerDoneListeners(listeners);
    }
    
    @Override
    public Task registerDoneListeners(Iterable<? extends ActionListener> listeners) {
        return task.registerDoneListeners(listeners);
    }
    
    @Override
    public Task unregisterDoneListener(ActionListener listener) {
        return task.unregisterDoneListener(listener);
    }
    
    @Override
    public Task unregisterDoneListeners(ActionListener... listeners) {
        return task.unregisterDoneListeners(listeners);
    }
    
    @Override
    public Task unregisterDoneListeners(Iterable<? extends ActionListener> listeners) {
        return task.unregisterDoneListeners(listeners);
    }
}
