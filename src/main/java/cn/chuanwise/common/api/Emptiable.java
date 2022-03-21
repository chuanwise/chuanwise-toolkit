package cn.chuanwise.common.api;

/**
 * 可空的某种类型
 * 
 * @author Chuanwise
 */
public interface Emptiable {
    
    /**
     * 判断对象是否为空
     *
     * @return 对象是否为空
     */
    boolean isEmpty();
    
    /**
     * 判断是否为空
     *
     * @param emptiable 可空对象
     * @return 当 emptiable == null 或 {@link #isEmpty()} 返回 true 时返回 true
     */
    static boolean isEmpty(Emptiable emptiable) {
        return emptiable == null || emptiable.isEmpty();
    }
    
    /**
     * 判断是否非空
     *
     * @param emptiable 可空对象
     * @return 当 emptiable != null 且 {@link #isEmpty()} 返回 false 时返回 true
     */
    static boolean nonEmpty(Emptiable emptiable) {
        return !isEmpty(emptiable);
    }
}
