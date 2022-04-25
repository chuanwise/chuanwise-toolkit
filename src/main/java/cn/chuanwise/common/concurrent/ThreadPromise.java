package cn.chuanwise.common.concurrent;

import cn.chuanwise.common.api.ExceptionSupplier;
import cn.chuanwise.common.util.Preconditions;

import java.util.concurrent.Callable;

/**
 * 承诺
 *
 * @author Chuanwise
 */
public class ThreadPromise<T>
    extends AbstractThreadPromise<T> {
    
    private final ExceptionSupplier<T> action;
    
    public ThreadPromise(ExceptionSupplier<T> action) {
        Preconditions.objectNonNull(action, "action");
        
        this.action = action;
    }
    
    public ThreadPromise(Callable<T> action) {
        Preconditions.objectNonNull(action, "action");
    
        this.action = action::call;
    }
    
    @Override
    protected T call0() throws Exception {
        return action.get();
    }
}
