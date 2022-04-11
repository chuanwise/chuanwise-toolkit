package cn.chuanwise.common.concurrent;

/**
 * 可以取消的某种行为
 *
 * @author Chuanwise
 */
public interface Cancellable
    extends Task {
    
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
}
