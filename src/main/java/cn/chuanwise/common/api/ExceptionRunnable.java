package cn.chuanwise.common.api;

/**
 * 可以抛出异常的 {@link Runnable}
 *
 * @author Chuanwise
 */
@FunctionalInterface
public interface ExceptionRunnable
        extends Runnable {
    
    /**
     * 调用 {@link #exceptRun()}
     * 如果抛出异常，包装为 {@link RuntimeException} 再次抛出。
     *
     * @see Runnable#run()
     */
    @Override
    default void run() {
        try {
            exceptRun();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 动作内容，可以抛出异常。
     *
     * @throws Exception 执行时出现异常
     * @see Runnable#run()
     */
    void exceptRun() throws Exception;
}
