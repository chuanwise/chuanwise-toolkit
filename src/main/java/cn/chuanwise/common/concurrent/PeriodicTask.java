package cn.chuanwise.common.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 周期性任务
 *
 * @author Chuanwise
 */
public interface PeriodicTask
    extends Task {
    
    /**
     * 跳过下一次执行
     *
     * @return 是否成功设置
     */
    boolean skipNext();
    
    /**
     * 跳过接下来若干次执行
     *
     * @param count 跳过次数
     * @return 是否成功设置
     */
    boolean skipNext(int count);
    
    /**
     * 询问是否正在跳过下一次执行
     *
     * @return 是否正在跳过下一次执行
     */
    boolean isSkipping();
    
    /**
     * 询问是否正在等待下一次执行
     *
     * @return 是否正在等待下一次执行
     */
    boolean isWaiting();
    
    /**
     * 同步到任务结束
     *
     * @throws InterruptedException 中断异常
     */
    void awaitPeriod() throws InterruptedException;
    
    /**
     * 不可中断地同步到任务结束
     */
    void awaitPeriodUninterruptibly();
    
    /**
     * 等待直到 {@link #isWaiting()} 返回 {@code true}，或超时退出。
     *
     * @param timeout 超时时长毫秒数
     * @return 等待是否是因为结束而退出的
     * @throws InterruptedException 中断异常
     */
    boolean awaitPeriod(long timeout) throws InterruptedException;
    
    /**
     * 等待直到 {@link #isWaiting()} 返回 {@code true}，或超时退出。
     *
     * @param timeout  超时时长
     * @param timeUnit 时间单位
     * @return 等待是否是因为结束而退出的
     * @throws InterruptedException 中断异常
     */
    boolean awaitPeriod(long timeout, TimeUnit timeUnit) throws InterruptedException;
    
    /**
     * 不可中断地等待直到 {@link #isWaiting()} 返回 {@code true}。
     *
     * @param timeout 超时时长毫秒数
     * @return 等待是否是因为结束而退出的
     */
    boolean awaitPeriodUninterruptibly(long timeout);
    
    /**
     * 不可中断地等待直到 {@link #isWaiting()} 返回 {@code true}。
     *
     * @param timeout  超时时长
     * @param timeUnit 时间单位
     * @return 等待是否是因为结束而退出的
     */
    boolean awaitPeriodUninterruptibly(long timeout, TimeUnit timeUnit);
    
    /**
     * 注册一个监听器
     *
     * @param listener 监听器
     * @return 当前对象，方便链式调用
     */
    PeriodicTask registerPeriodicListener(ActionListener listener);
    
    /**
     * 注册一些监听器
     *
     * @param listeners 一些监听器
     * @return 当前对象，方便链式调用
     */
    PeriodicTask registerPeriodicListeners(ActionListener... listeners);
    
    /**
     * 注册一些监听器
     *
     * @param listeners 一些监听器
     * @return 当前对象，方便链式调用
     */
    PeriodicTask registerPeriodicListeners(Iterable<? extends ActionListener> listeners);
    
    /**
     * 取消注册一个监听器
     *
     * @param listener 监听器
     * @return 当前对象，方便链式调用
     */
    PeriodicTask unregisterPeriodicListener(ActionListener listener);
    
    /**
     * 取消注册一些监听器
     *
     * @param listeners 一些监听器
     * @return 当前对象，方便链式调用
     */
    PeriodicTask unregisterPeriodicListeners(ActionListener... listeners);
    
    /**
     * 取消注册一些监听器
     *
     * @param listeners 一些监听器
     * @return 当前对象，方便链式调用
     */
    PeriodicTask unregisterPeriodicListeners(Iterable<? extends ActionListener> listeners);
    
    /**
     * 注册一个监听器
     *
     * @param listener 监听器
     * @return 当前对象，方便链式调用
     */
    @Override
    PeriodicTask registerDoneListener(ActionListener listener);
    
    /**
     * 注册一些监听器
     *
     * @param listeners 一些监听器
     * @return 当前对象，方便链式调用
     */
    @Override
    PeriodicTask registerDoneListeners(ActionListener... listeners);
    
    /**
     * 注册一些监听器
     *
     * @param listeners 一些监听器
     * @return 当前对象，方便链式调用
     */
    @Override
    PeriodicTask registerDoneListeners(Iterable<? extends ActionListener> listeners);
    
    /**
     * 取消注册一个监听器
     *
     * @param listener 监听器
     * @return 当前对象，方便链式调用
     */
    @Override
    PeriodicTask unregisterDoneListener(ActionListener listener);
    
    /**
     * 取消注册一些监听器
     *
     * @param listeners 一些监听器
     * @return 当前对象，方便链式调用
     */
    @Override
    PeriodicTask unregisterDoneListeners(ActionListener... listeners);
    
    /**
     * 取消注册一些监听器
     *
     * @param listeners 一些监听器
     * @return 当前对象，方便链式调用
     */
    @Override
    PeriodicTask unregisterDoneListeners(Iterable<? extends ActionListener> listeners);
}