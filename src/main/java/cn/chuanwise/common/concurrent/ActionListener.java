package cn.chuanwise.common.concurrent;

/**
 * 行动监听器
 *
 * @author Chuanwise
 */
public interface ActionListener<T extends Task> {
    
    /**
     * 操作完成回调方法
     *
     * @param t 动作本身
     */
    void actionDone(T t);
}
