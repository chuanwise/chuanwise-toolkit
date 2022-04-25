package cn.chuanwise.common.text;

/**
 * 格式化列表元素的工具
 *
 * @author Chuanwise
 */
@FunctionalInterface
public interface ElementFormat<T> {
    
    /**
     * 将元素格式化为某种对象
     *
     * @param element 元素
     * @return 对象
     */
    Object format(T element);
}
