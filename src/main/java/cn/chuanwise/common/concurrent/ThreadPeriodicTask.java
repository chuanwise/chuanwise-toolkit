package cn.chuanwise.common.concurrent;

import cn.chuanwise.common.api.ExceptionRunnable;
import cn.chuanwise.common.util.Preconditions;

/**
 * @author Chuanwise
 */
public class ThreadPeriodicTask
    extends AbstractThreadPeriodicTask {
    
    private final ExceptionRunnable action;
    
    public ThreadPeriodicTask(ExceptionRunnable action) {
        Preconditions.objectNonNull(action, "action");
        
        this.action = action;
    }
    
    public ThreadPeriodicTask(Runnable action) {
        Preconditions.objectNonNull(action, "action");
        
        this.action = action::run;
    }
    
    
    @Override
    protected void run0() throws Exception {
        action.exceptRun();
    }
}
