package cn.chuanwise.common.util;

/**
 * 索引相关工具
 *
 * @author Chuanwise
 */
public class Indexes
        extends StaticUtilities {
    
    /**
     * 将 2D 索引转化为 1D 索引
     *
     * @param index2D 行索引
     * @param index1D 列索引
     * @param base 每一行的元素树
     * @return 1D 索引
     */
    public static int index2DTo1D(int index2D, int index1D, int base) {
        Preconditions.index(index2D, base, "index 2D");
        
        return index2D * base + index1D;
    }
    
    /**
     * 判断索引值是否合法
     *
     * @param index 索引值
     * @param bound 最大值
     * @return 当 index >= 0 且小于 bound 时返回 true
     * @throws IllegalArgumentException bound 小于 0
     */
    public static boolean isLegal(int index, int bound) {
        Preconditions.argument(bound >= 0, "bound must be bigger than or equals to 0!");
        
        return index >= 0 && index < bound;
    }
}
