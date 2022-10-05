package cn.chuanwise.common.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数字工具类
 *
 * @author Chuanwise
 */
@SuppressWarnings("all")
public class Numbers
    extends StaticUtilities {
    
    private static final Pattern OCT_PATTERN = Pattern.compile("[\\+\\-]?0(?<number>[0-7]+)");
    
    private static final Pattern HEX_PATTERN = Pattern.compile("[\\+\\-]?0[Xx](?<number>[0-9A-Fa-f]+)");
    
    private static final Pattern BIN_PATTERN = Pattern.compile("[\\+\\-]?0[Bb](?<number>[0-1]+)");
    
    private static final Pattern INT_PATTERN = Pattern.compile("[\\+\\-]?(?<number>[0-9]+)");
    
    private static final char[] CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
    
    /**
     * 字符串解析为 Integer
     *
     * @param string 字符串
     * @return 如解析成功，返回数字，否则返回 null
     */
    @SuppressWarnings("all")
    public static Integer parseInt(String string) {
        Preconditions.argumentNonEmpty(string, "empty string");
        
        final String number;
        final int base;
        
        do {
            final Matcher hexMatcher = HEX_PATTERN.matcher(string);
            if (hexMatcher.matches()) {
                number = hexMatcher.group("number");
                base = 16;
                break;
            }
            
            final Matcher octMatcher = OCT_PATTERN.matcher(string);
            if (octMatcher.matches()) {
                number = octMatcher.group("number");
                base = 8;
                break;
            }
            
            final Matcher binMatcher = BIN_PATTERN.matcher(string);
            if (binMatcher.matches()) {
                number = binMatcher.group("number");
                base = 2;
                break;
            }
            
            final Matcher intMatcher = INT_PATTERN.matcher(string);
            if (intMatcher.matches()) {
                number = intMatcher.group("number");
                base = 2;
                break;
            }
            
            return null;
        } while (false);
        
        int value = 0;
        for (int i = 0; i < number.length(); i++) {
            final char ch = number.charAt(i);
            value *= base;
            
            final int chIndex = Arrays.indexOf(CHARS, ch);
            Preconditions.element(chIndex != -1, "不支持的进制：" + base);
            value += chIndex;
        }
        
        return value;
    }
    
    /**
     * 字符串解析为 Long
     *
     * @param string 字符串
     * @return 如解析成功，返回数字，否则返回 null
     */
    @SuppressWarnings("all")
    public static Long parseLong(String string) {
        Preconditions.argumentNonEmpty(string, "empty string");
        
        final String number;
        final int base;
        
        do {
            final Matcher hexMatcher = HEX_PATTERN.matcher(string);
            if (hexMatcher.matches()) {
                number = hexMatcher.group("number");
                base = 16;
                break;
            }
            
            final Matcher octMatcher = OCT_PATTERN.matcher(string);
            if (octMatcher.matches()) {
                number = octMatcher.group("number");
                base = 8;
                break;
            }
            
            final Matcher binMatcher = BIN_PATTERN.matcher(string);
            if (binMatcher.matches()) {
                number = binMatcher.group("number");
                base = 2;
                break;
            }
            
            final Matcher intMatcher = INT_PATTERN.matcher(string);
            if (intMatcher.matches()) {
                number = intMatcher.group("number");
                base = 10;
                break;
            }
            
            return null;
        } while (false);
        
        long value = 0;
        for (int i = 0; i < number.length(); i++) {
            final char ch = number.charAt(i);
            value *= base;
            
            final int chIndex = Arrays.indexOf(CHARS, ch);
            Preconditions.element(chIndex != -1, "不支持的进制：" + base);
            value += chIndex;
        }
        
        return value;
    }
    
    /**
     * 计算两个数字之间的差值绝对值
     *
     * @param left 数字1
     * @param right 数字2
     * @return 差值绝对值
     */
    public static int delta(int left, int right) {
        return Math.abs(left - right);
    }
    
    /**
     * 计算两个数字之间的差值绝对值
     *
     * @param left 数字1
     * @param right 数字2
     * @return 差值绝对值
     */
    public static byte delta(byte left, byte right) {
        return (byte) Math.abs(left - right);
    }
    
    /**
     * 计算两个数字之间的差值绝对值
     *
     * @param left 数字1
     * @param right 数字2
     * @return 差值绝对值
     */
    public static short delta(short left, short right) {
        return (short) Math.abs(left - right);
    }
    
    /**
     * 计算两个数字之间的差值绝对值
     *
     * @param left 数字1
     * @param right 数字2
     * @return 差值绝对值
     */
    public static long delta(long left, long right) {
        return Math.abs(left - right);
    }
    
    /**
     * 计算两个数字之间的差值绝对值
     *
     * @param left 数字1
     * @param right 数字2
     * @return 差值绝对值
     */
    public static float delta(float left, float right) {
        return Math.abs(left - right);
    }
    
    /**
     * 计算两个数字之间的差值绝对值
     *
     * @param left 数字1
     * @param right 数字2
     * @return 差值绝对值
     */
    public static double delta(double left, double right) {
        return Math.abs(left - right);
    }
    
    /**
     * 判断一个数字是否是整数
     *
     * @param number 数字
     * @return 数字是否是整数
     */
    public static boolean isIntegerNumber(Number number) {
        return number instanceof Byte
            || number instanceof Short
            || number instanceof Integer
            || number instanceof Long
            || number instanceof BigInteger;
    }
    
    /**
     * 判断一个数字是否是小数
     *
     * @param number 数字
     * @return 数字是否是小数
     */
    public static boolean isFloatingNumber(Number number) {
        return number instanceof Float
            || number instanceof Double
            || number instanceof BigDecimal;
    }
    
    /**
     * 判断一个数字是否是大数字
     *
     * @param number 数字
     * @return 数字是否是大数字
     */
    public static boolean isBigNumber(Number number) {
        return number instanceof BigDecimal
            || number instanceof BigInteger;
    }
    
    /**
     * 比较两个数字的大小
     *
     * @param left
     * @param right
     * @return
     */
    @SuppressWarnings("all")
    public static int compare(Number left, Number right) {
        Preconditions.objectNonNull(left, "left");
        Preconditions.objectNonNull(right, "right");
    
        final boolean leftIsBigNumber = isBigNumber(left);
        final boolean leftIsInteger = isIntegerNumber(left);
        final boolean leftIsBigInteger = left instanceof BigInteger;
        final boolean leftIsBigDecimal = left instanceof BigDecimal;
    
        final boolean rightIsByte = right instanceof Byte;
        final boolean rightIsShort = right instanceof Short;
        final boolean rightIsInteger = right instanceof Integer;
        final boolean rightIsLong = right instanceof Long;
        final boolean rightIsFloat = right instanceof Float;
        final boolean rightIsDouble = right instanceof Double;
        final boolean rightIsBigInteger = right instanceof BigInteger;
        final boolean rightIsBigDecimal = right instanceof BigDecimal;
        
        if (leftIsInteger) {
            
            if (!leftIsBigDecimal) {
                return Long.compare(left.longValue(), right.longValue());
            }
            
            if (rightIsFloat || rightIsDouble) {
                return Double.compare(left.doubleValue(), right.doubleValue());
            }
            
            if (rightIsBigInteger) {
                return BigInteger.valueOf(left.longValue()).compareTo((BigInteger) right);
            }
            if (rightIsBigDecimal) {
                return BigDecimal.valueOf(left.doubleValue()).compareTo((BigDecimal) right);
            }
    
            return Double.compare(left.doubleValue(), right.doubleValue());
        }
    
        // TODO: 2022/9/21 finish it
        return 0;
    }
}
