package cn.chuanwise.common.util;

/**
 * 字符工具类
 *
 * @author Chuanwise
 */
public class Characters
    extends StaticUtilities {
    
    /**
     * 最大的半角字符
     */
    public static final int MAX_HALF_WIDTH_CHAR = 0xff;
    
    /**
     * 最小的半角字符
     */
    public static final int MIN_HALF_WIDTH_CHAR = 0x00;
    
    /**
     * 判断字符是否是半角字符
     *
     * @param ch 字符
     * @return 字符是否是半角字符
     */
    public static boolean isHalfWidth(int ch) {
        return ch >= MIN_HALF_WIDTH_CHAR && ch < MAX_HALF_WIDTH_CHAR;
    }
    
    /**
     * 判断字符是否是全角字符
     *
     * @param ch 字符
     * @return 字符是否是全角字符
     */
    public static boolean isFullWidth(int ch) {
        return !isHalfWidth(ch);
    }
}
