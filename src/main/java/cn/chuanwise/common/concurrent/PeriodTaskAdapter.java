package cn.chuanwise.common.concurrent;

import lombok.Data;

import java.util.concurrent.TimeUnit;

@Data
public class PeriodTaskAdapter
    implements PeriodicTask {
    
    private final PeriodicTask task;
    
    @Override
    public boolean skipNext() {
        return task.skipNext();
    }
    
    @Override
    public boolean skipNext(int count) {
        return task.skipNext(count);
    }
    
    @Override
    public boolean isSkipping() {
        return task.isSkipping();
    }
    
    @Override
    public boolean isWaiting() {
        return task.isWaiting();
    }
    
    @Override
    public void awaitPeriod() throws InterruptedException {
        task.awaitPeriod();
    }
    
    @Override
    public void awaitPeriodUninterruptibly() {
        task.awaitPeriodUninterruptibly();
    }
    
    @Override
    public boolean awaitPeriod(long timeout) throws InterruptedException {
        return task.awaitPeriod(timeout);
    }
    
    @Override
    public boolean awaitPeriod(long timeout, TimeUnit timeUnit) throws InterruptedException {
        return task.awaitPeriod(timeout, timeUnit);
    }
    
    @Override
    public boolean awaitPeriodUninterruptibly(long timeout) {
        return task.awaitPeriodUninterruptibly(timeout);
    }
    
    @Override
    public boolean awaitPeriodUninterruptibly(long timeout, TimeUnit timeUnit) {
        return false;
    }
    
    @Override
    public PeriodicTask registerPeriodicListener(ActionListener listener) {
        return task.registerPeriodicListener(listener);
    }
    
    @Override
    public PeriodicTask registerPeriodicListeners(ActionListener... listeners) {
        return task.registerPeriodicListeners(listeners);
    }
    
    @Override
    public PeriodicTask registerPeriodicListeners(Iterable<? extends ActionListener> listeners) {
        return task.registerPeriodicListeners(listeners);
    }
    
    @Override
    public PeriodicTask unregisterPeriodicListener(ActionListener listener) {
        return task.unregisterPeriodicListener(listener);
    }
    
    @Override
    public PeriodicTask unregisterPeriodicListeners(ActionListener... listeners) {
        return task.unregisterPeriodicListeners(listeners);
    }
    
    @Override
    public PeriodicTask unregisterPeriodicListeners(Iterable<? extends ActionListener> listeners) {
        return task.unregisterPeriodicListeners(listeners);
    }
    
    @Override
    public boolean isDone() {
        return task.isDone();
    }
    
    @Override
    public boolean isSucceed() {
        return task.isSucceed();
    }
    
    @Override
    public boolean isExecuting() {
        return task.isExecuting();
    }
    
    @Override
    public boolean isFailed() {
        return task.isFailed();
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
    public PeriodicTask registerDoneListener(ActionListener listener) {
        return task.registerDoneListener(listener);
    }
    
    @Override
    public PeriodicTask registerDoneListeners(ActionListener... listeners) {
        return task.registerDoneListeners(listeners);
    }
    
    @Override
    public PeriodicTask registerDoneListeners(Iterable<? extends ActionListener> listeners) {
        return task.registerDoneListeners(listeners);
    }
    
    @Override
    public PeriodicTask unregisterDoneListener(ActionListener listener) {
        return task.unregisterDoneListener(listener);
    }
    
    @Override
    public PeriodicTask unregisterDoneListeners(ActionListener... listeners) {
        return task.unregisterDoneListeners(listeners);
    }
    
    @Override
    public PeriodicTask unregisterDoneListeners(Iterable<? extends ActionListener> listeners) {
        return task.unregisterDoneListeners(listeners);
    }
}
