package cn.chuanwise.common.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 表示某种任务
 *
 * @author Chuanwise
 */
public interface Task {
    
    /**
     * 询问任务是否完成。
     * 任务取消、成功或失败都应该算任务完成。
     *
     * @return 任务是否完成
     */
    boolean isDone();
    
    /**
     * 询问任务是否成功执行。
     * 当该函数返回 {@code true}，应该保证 {@link #getCause()} 返回 {@code null}
     *
     * @return 务是否成功执行
     */
    boolean isSucceed();
    
    /**
     * 询问任务是否正在执行
     *
     * @return 任务是否正在执行
     */
    boolean isExecuting();
    
    /**
     * 询问任务是否执行失败。
     * 当该函数返回 {@code true}，应该保证 {@link #getCause()} 返回非空值。
     *
     * @return 任务是否执行失败
     */
    boolean isFailed();
    
    /**
     * 询问任务失败的异常。
     *
     * @return 如果返回非空值，应该保证 {@link #isFailed()} 返回 {@code true}。
     */
    Throwable getCause();
    
    /**
     * 同步直到 {@link #isDone()} 返回 {@code true}。
     *
     * @throws InterruptedException 中断异常
     */
    void awaitDone() throws InterruptedException;
    
    /**
     * 不可中断地同步，直到 {@link #isDone()} 返回 {@code true}。
     */
    void awaitDoneUninterruptibly();
    
    /**
     * 等待直到 {@link #isDone()} 返回 {@code true}，或超时退出。
     *
     * @param timeout 超时时长毫秒数
     * @return 等待是否是因为结束而退出的
     * @throws InterruptedException 中断异常
     */
    boolean awaitDone(long timeout) throws InterruptedException;
    
    /**
     * 等待直到 {@link #isDone()} 返回 {@code true}，或超时退出。
     *
     * @param timeout  超时时长
     * @param timeUnit 时间单位
     * @return 等待是否是因为结束而退出的
     * @throws InterruptedException 中断异常
     */
    boolean awaitDone(long timeout, TimeUnit timeUnit) throws InterruptedException;
    
    /**
     * 不可中断地等待直到 {@link #isDone()} 返回 {@code true}。
     *
     * @param timeout 超时时长毫秒数
     * @return 等待是否是因为结束而退出的
     */
    boolean awaitDoneUninterruptibly(long timeout);
    
    /**
     * 不可中断地等待直到 {@link #isDone()} 返回 {@code true}。
     *
     * @param timeout  超时时长
     * @param timeUnit 时间单位
     * @return 等待是否是因为结束而退出的
     */
    boolean awaitDoneUninterruptibly(long timeout, TimeUnit timeUnit);
    
    /**
     * 询问任务是否被取消
     *
     * @return 任务是否被取消
     */
    boolean isCancelled();
    
    /**
     * 取消执行任务
     *
     * @param interrupt 是否打断正在进行的任务。
     *                  如果任务已经执行，则该参数没有意义。
     *                  如果任务正在执行，当该参数为 {@code true} 时将中断正在
     *                  执行该任务的线程。
     *                  如果任务尚未执行，则任务将永远不会执行，该参数没有意义。
     * @return 是否成功取消执行任务
     */
    boolean cancel(boolean interrupt);
    
    /**
     * 注册一个监听器
     *
     * @param listener 监听器
     * @return 当前对象，方便链式调用
     */
    Task registerDoneListener(ActionListener listener);
    
    /**
     * 注册一些监听器
     *
     * @param listeners 一些监听器
     * @return 当前对象，方便链式调用
     */
    Task registerDoneListeners(ActionListener... listeners);
    
    /**
     * 注册一些监听器
     *
     * @param listeners 一些监听器
     * @return 当前对象，方便链式调用
     */
    Task registerDoneListeners(Iterable<? extends ActionListener> listeners);
    
    /**
     * 取消注册一个监听器
     *
     * @param listener 监听器
     * @return 当前对象，方便链式调用
     */
    Task unregisterDoneListener(ActionListener listener);
    
    /**
     * 取消注册一些监听器
     *
     * @param listeners 一些监听器
     * @return 当前对象，方便链式调用
     */
    Task unregisterDoneListeners(ActionListener... listeners);
    
    /**
     * 取消注册一些监听器
     *
     * @param listeners 一些监听器
     * @return 当前对象，方便链式调用
     */
    Task unregisterDoneListeners(Iterable<? extends ActionListener> listeners);
}