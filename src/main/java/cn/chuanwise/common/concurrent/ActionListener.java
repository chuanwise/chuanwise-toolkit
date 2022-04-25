package cn.chuanwise.common.concurrent;

/**
 * 行动监听器
 *
 * @author Chuanwise
 */
public interface ActionListener {
    
    /**
     * 如果失败，打印失败堆栈信息
     */
    ActionListener PRINT_STACK_TRACE_IF_FAILED = task -> {
        if (task.isFailed()) {
            task.getCause().printStackTrace();
        }
    };
    
    /**
     * 操作完成回调方法
     *
     * @param task 动作本身
     */
    void listen(Task task);
}