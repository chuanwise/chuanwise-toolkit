package cn.chuanwise.common.concurrent;

import cn.chuanwise.common.api.ExceptionRunnable;
import cn.chuanwise.common.util.Preconditions;

/**
 * 线程任务执行器
 *
 * @author Chuanwise
 */
public class ThreadTask
    extends AbstractThreadTask {
    
    private final ExceptionRunnable action;
    
    public ThreadTask(ExceptionRunnable action) {
        Preconditions.objectNonNull(action, "action");
        
        this.action = action;
    }
    
    public ThreadTask(Runnable action) {
        Preconditions.objectNonNull(action, "action");
        
        this.action = action::run;
    }
    
    @Override
    protected final void run0() throws Throwable {
        action.exceptRun();
    }
}
