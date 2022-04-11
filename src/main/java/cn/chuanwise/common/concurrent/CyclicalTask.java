package cn.chuanwise.common.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 周期性任务
 *
 * @author Chuanwise
 */
public interface CyclicalTask
    extends Task {
    
    /**
     * 跳过下一次执行
     *
     * @return 是否成功设置
     */
    boolean skip();
    
    /**
     * 跳过接下来若干次执行
     *
     * @param count 跳过次数
     * @return 是否成功设置
     */
    boolean skip(int count);
    
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
    void syncWaiting() throws InterruptedException;
    
    /**
     * 不可中断地同步到任务结束
     */
    void syncWaitingUninterruptibly();
    
    /**
     * 等待直到 {@link #isWaiting()} 返回 {@code true}，或超时退出。
     *
     * @param timeout 超时时长毫秒数
     * @return 等待是否是因为结束而退出的
     * @throws InterruptedException 中断异常
     * @throws TimeoutException     超时异常
     */
    boolean awaitWaiting(long timeout) throws InterruptedException, TimeoutException;
    
    /**
     * 等待直到 {@link #isWaiting()} 返回 {@code true}，或超时退出。
     *
     * @param timeout  超时时长
     * @param timeUnit 时间单位
     * @return 等待是否是因为结束而退出的
     * @throws InterruptedException 中断异常
     * @throws TimeoutException     超时异常
     */
    boolean awaitWaiting(long timeout, TimeUnit timeUnit) throws InterruptedException, TimeoutException;
    
    /**
     * 不可中断地等待直到 {@link #isWaiting()} 返回 {@code true}。
     *
     * @param timeout 超时时长毫秒数
     * @return 等待是否是因为结束而退出的
     * @throws InterruptedException 中断异常
     */
    boolean awaitWaitingUninterruptibly(long timeout) throws InterruptedException;
    
    /**
     * 不可中断地等待直到 {@link #isWaiting()} 返回 {@code true}。
     *
     * @param timeout  超时时长
     * @param timeUnit 时间单位
     * @return 等待是否是因为结束而退出的
     * @throws InterruptedException 中断异常
     */
    boolean awaitWaitingUninterruptibly(long timeout, TimeUnit timeUnit) throws InterruptedException;
}
