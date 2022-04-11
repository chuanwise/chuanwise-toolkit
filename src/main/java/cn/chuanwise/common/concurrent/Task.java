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
    void sync() throws InterruptedException;
    
    /**
     * 不可中断地同步，直到 {@link #isDone()} 返回 {@code true}。
     */
    void syncUninterruptibly();
    
    /**
     * 等待直到 {@link #isDone()} 返回 {@code true}，或超时退出。
     *
     * @param timeout 超时时长毫秒数
     * @return 等待是否是因为结束而退出的
     * @throws InterruptedException 中断异常
     */
    boolean await(long timeout) throws InterruptedException;
    
    /**
     * 等待直到 {@link #isDone()} 返回 {@code true}，或超时退出。
     *
     * @param timeout  超时时长
     * @param timeUnit 时间单位
     * @return 等待是否是因为结束而退出的
     * @throws InterruptedException 中断异常
     */
    boolean await(long timeout, TimeUnit timeUnit) throws InterruptedException;
    
    /**
     * 不可中断地等待直到 {@link #isDone()} 返回 {@code true}。
     *
     * @param timeout 超时时长毫秒数
     * @return 等待是否是因为结束而退出的
     */
    boolean awaitUninterruptibly(long timeout);
    
    /**
     * 不可中断地等待直到 {@link #isDone()} 返回 {@code true}。
     *
     * @param timeout  超时时长
     * @param timeUnit 时间单位
     * @return 等待是否是因为结束而退出的
     */
    boolean awaitUninterruptibly(long timeout, TimeUnit timeUnit);
    
    /**
     * 注册一个监听器
     *
     * @param listener 监听器
     * @return 当前对象，方便链式调用
     */
    Task registerListener(ActionListener<? extends Task> listener);
    
    /**
     * 注册一些监听器
     *
     * @param listeners 一些监听器
     * @return 当前对象，方便链式调用
     */
    Task registerListeners(ActionListener<? extends Task>... listeners);
    
    /**
     * 注册一些监听器
     *
     * @param listeners 一些监听器
     * @return 当前对象，方便链式调用
     */
    Task registerListeners(Iterable<? extends ActionListener<? extends Task>> listeners);
    
    /**
     * 取消注册一个监听器
     *
     * @param listener 监听器
     * @return 当前对象，方便链式调用
     */
    Task unregisterListener(ActionListener<? extends Task> listener);
    
    /**
     * 取消注册一些监听器
     *
     * @param listeners 一些监听器
     * @return 当前对象，方便链式调用
     */
    Task unregisterListeners(ActionListener<? extends Task>... listeners);
    
    /**
     * 取消注册一些监听器
     *
     * @param listeners 一些监听器
     * @return 当前对象，方便链式调用
     */
    Task unregisterListeners(Iterable<? extends ActionListener<? extends Task>> listeners);
}