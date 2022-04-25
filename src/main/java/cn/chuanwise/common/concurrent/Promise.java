package cn.chuanwise.common.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

/**
 * 表示一个异步获取的值。
 *
 * @author Chuanwise
 */
public interface Promise<T>
    extends Task, Future<T> {
    
    /**
     * 阻塞当前线程，直到获取结果
     *
     * @param timeout 超时毫秒数
     * @return 结果
     * @throws InterruptedException 等待被打断
     * @throws ExecutionException   执行时出现异常
     * @throws TimeoutException     等待超时
     */
    T get(long timeout) throws InterruptedException, ExecutionException, TimeoutException;
}
