package cn.chuanwise.common.util;

import java.util.Collections;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Predicate;

/**
 * 数组工具类
 *
 * @author Chuanwise
 */
@SuppressWarnings("all")
public class Arrays
        extends StaticUtilities {
    
    /**
     * 对数组拆箱
     *
     * @param array 包装类型数组
     * @return 拆箱后的数组
     * @throws NullPointerException     包装类型数组中有 null
     * @throws IllegalArgumentException 包装类型数组是 null
     */
    public static boolean[] unbox(Boolean[] array) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        final boolean[] newArray = new boolean[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
    
    /**
     * 对数组装箱
     *
     * @param array 基本类型数组
     * @return 装箱后的数组
     * @throws IllegalArgumentException 包装类型数组是 null
     */
    public static Boolean[] box(boolean[] array) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        final Boolean[] newArray = new Boolean[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param value        值
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static int indexOf(boolean[] array, boolean value, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = beginIndex; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param value      值
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static int indexOf(boolean[] array, boolean value, int beginIndex) {
        return indexOf(array, value, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array 数组
     * @param value 值
     * @return 找到时返回其索引，否则返回 -1
     */
    public static int indexOf(boolean[] array, boolean value) {
        return indexOf(array, value, 0, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param value        值
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static int lastIndexOf(boolean[] array, boolean value, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = array.length - 1; i >= beginIndex; i--) {
            if (array[i] == value) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param value      值
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static int lastIndexOf(boolean[] array, boolean value, int beginIndex) {
        return lastIndexOf(array, value, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array 数组
     * @param value 值
     * @return 找到时返回其索引，否则返回 -1
     */
    public static int lastIndexOf(boolean[] array, boolean value) {
        return lastIndexOf(array, value, 0, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param filter       筛选器
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static int indexIf(boolean[] array, Predicate<Boolean> filter, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        Preconditions.namedArgumentNonNull(filter, "filter");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = beginIndex; i < array.length; i++) {
            if (filter.test(array[i])) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param filter     筛选器
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static int indexIf(boolean[] array, Predicate<Boolean> filter, int beginIndex) {
        return indexIf(array, filter, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array  数组
     * @param filter 筛选器
     * @return 找到时返回其索引，否则返回 -1
     */
    public static int indexIf(boolean[] array, Predicate<Boolean> filter) {
        return indexIf(array, filter, 0, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param filter       筛选器
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static int lastIndexIf(boolean[] array, Predicate<Boolean> filter, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        Preconditions.namedArgumentNonNull(filter, "filter");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = array.length - 1; i >= beginIndex; i--) {
            if (filter.test(array[i])) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param filter     筛选器
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static int lastIndexIf(boolean[] array, Predicate<Boolean> filter, int beginIndex) {
        return lastIndexIf(array, filter, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array  数组
     * @param filter 筛选器
     * @return 找到时返回其索引，否则返回 -1
     */
    public static int lastIndexIf(boolean[] array, Predicate<Boolean> filter) {
        return lastIndexIf(array, filter, 0, -1);
    }
    
    /**
     * 判断数组是否为空
     *
     * @param array 数组
     * @return 如果数组为 null 或 length 为 0，返回 false，否则返回 true
     */
    public static boolean isEmpty(boolean[] array) {
        return array != null && array.length != 0;
    }
    
    /**
     * 判断数组是否为空
     *
     * @param array 数组
     * @return 如果数组为 null 或 length 为 0，返回 false，否则返回 true
     */
    public static boolean isEmpty(byte[] array) {
        return array != null && array.length != 0;
    }
    
    /**
     * 判断数组是否非空
     *
     * @param array 数组
     * @return 如果数组为 null 或 length 为 0，返回 true，否则返回 false
     */
    public static boolean nonEmpty(boolean[] array) {
        return array == null || array.length == 0;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array      数组
     * @param beginIndex 起始索引
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static boolean contains(boolean[] array, boolean value, int beginIndex) {
        return indexOf(array, value, beginIndex) != -1;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array 数组
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static boolean contains(boolean[] array, boolean value) {
        return indexOf(array, value) != -1;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array      数组
     * @param beginIndex 起始索引
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static boolean containsIf(boolean[] array, Predicate<Boolean> filter, int beginIndex) {
        return indexIf(array, filter, beginIndex) != -1;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array 数组
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static boolean containsIf(boolean[] array, Predicate<Boolean> filter) {
        return indexIf(array, filter) != -1;
    }
    
    /**
     * 对数组拆箱
     *
     * @param array 包装类型数组
     * @return 拆箱后的数组
     * @throws NullPointerException     包装类型数组中有 null
     * @throws IllegalArgumentException 包装类型数组是 null
     */
    public static char[] unbox(Character[] array) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        final char[] newArray = new char[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
    
    /**
     * 对数组装箱
     *
     * @param array 基本类型数组
     * @return 装箱后的数组
     * @throws IllegalArgumentException 包装类型数组是 null
     */
    public static Character[] box(char[] array) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        final Character[] newArray = new Character[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param value        值
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static int indexOf(char[] array, char value, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = beginIndex; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param value      值
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static int indexOf(char[] array, char value, int beginIndex) {
        return indexOf(array, value, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array 数组
     * @param value 值
     * @return 找到时返回其索引，否则返回 -1
     */
    public static int indexOf(char[] array, char value) {
        return indexOf(array, value, 0, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param value        值
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static int lastIndexOf(char[] array, char value, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = array.length - 1; i >= beginIndex; i--) {
            if (array[i] == value) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param value      值
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static int lastIndexOf(char[] array, char value, int beginIndex) {
        return lastIndexOf(array, value, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array 数组
     * @param value 值
     * @return 找到时返回其索引，否则返回 -1
     */
    public static int lastIndexOf(char[] array, char value) {
        return lastIndexOf(array, value, 0, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param filter       筛选器
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static int indexIf(char[] array, Predicate<Character> filter, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        Preconditions.namedArgumentNonNull(filter, "filter");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = beginIndex; i < array.length; i++) {
            if (filter.test(array[i])) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param filter     筛选器
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static int indexIf(char[] array, Predicate<Character> filter, int beginIndex) {
        return indexIf(array, filter, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array  数组
     * @param filter 筛选器
     * @return 找到时返回其索引，否则返回 -1
     */
    public static int indexIf(char[] array, Predicate<Character> filter) {
        return indexIf(array, filter, 0, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param filter       筛选器
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static int lastIndexIf(char[] array, Predicate<Character> filter, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        Preconditions.namedArgumentNonNull(filter, "filter");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = array.length - 1; i >= beginIndex; i--) {
            if (filter.test(array[i])) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param filter     筛选器
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static int lastIndexIf(char[] array, Predicate<Character> filter, int beginIndex) {
        return lastIndexIf(array, filter, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array  数组
     * @param filter 筛选器
     * @return 找到时返回其索引，否则返回 -1
     */
    public static int lastIndexIf(char[] array, Predicate<Character> filter) {
        return lastIndexIf(array, filter, 0, -1);
    }
    
    /**
     * 判断数组是否为空
     *
     * @param array 数组
     * @return 如果数组为 null 或 length 为 0，返回 false，否则返回 true
     */
    public static boolean isEmpty(char[] array) {
        return array != null && array.length != 0;
    }
    
    /**
     * 判断数组是否非空
     *
     * @param array 数组
     * @return 如果数组为 null 或 length 为 0，返回 true，否则返回 false
     */
    public static boolean nonEmpty(char[] array) {
        return array == null || array.length == 0;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array      数组
     * @param beginIndex 起始索引
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static boolean contains(char[] array, char value, int beginIndex) {
        return indexOf(array, value, beginIndex) != -1;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array 数组
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static boolean contains(char[] array, char value) {
        return indexOf(array, value) != -1;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array      数组
     * @param beginIndex 起始索引
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static boolean containsIf(char[] array, Predicate<Character> filter, int beginIndex) {
        return indexIf(array, filter, beginIndex) != -1;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array 数组
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static boolean containsIf(char[] array, Predicate<Character> filter) {
        return indexIf(array, filter) != -1;
    }
    
    /**
     * 对数组拆箱
     *
     * @param array 包装类型数组
     * @return 拆箱后的数组
     * @throws NullPointerException     包装类型数组中有 null
     * @throws IllegalArgumentException 包装类型数组是 null
     */
    public static short[] unbox(Short[] array) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        final short[] newArray = new short[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
    
    /**
     * 对数组装箱
     *
     * @param array 基本类型数组
     * @return 装箱后的数组
     * @throws IllegalArgumentException 包装类型数组是 null
     */
    public static Short[] box(short[] array) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        final Short[] newArray = new Short[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param value        值
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static int indexOf(short[] array, short value, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = beginIndex; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param value      值
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static int indexOf(short[] array, short value, int beginIndex) {
        return indexOf(array, value, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array 数组
     * @param value 值
     * @return 找到时返回其索引，否则返回 -1
     */
    public static int indexOf(short[] array, short value) {
        return indexOf(array, value, 0, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param value        值
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static int lastIndexOf(short[] array, short value, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = array.length - 1; i >= beginIndex; i--) {
            if (array[i] == value) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param value      值
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static int lastIndexOf(short[] array, short value, int beginIndex) {
        return lastIndexOf(array, value, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array 数组
     * @param value 值
     * @return 找到时返回其索引，否则返回 -1
     */
    public static int lastIndexOf(short[] array, short value) {
        return lastIndexOf(array, value, 0, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param filter       筛选器
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static int indexIf(short[] array, Predicate<Short> filter, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        Preconditions.namedArgumentNonNull(filter, "filter");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = beginIndex; i < array.length; i++) {
            if (filter.test(array[i])) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param filter     筛选器
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static int indexIf(short[] array, Predicate<Short> filter, int beginIndex) {
        return indexIf(array, filter, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array  数组
     * @param filter 筛选器
     * @return 找到时返回其索引，否则返回 -1
     */
    public static int indexIf(short[] array, Predicate<Short> filter) {
        return indexIf(array, filter, 0, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param filter       筛选器
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static int lastIndexIf(short[] array, Predicate<Short> filter, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        Preconditions.namedArgumentNonNull(filter, "filter");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = array.length - 1; i >= beginIndex; i--) {
            if (filter.test(array[i])) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param filter     筛选器
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static int lastIndexIf(short[] array, Predicate<Short> filter, int beginIndex) {
        return lastIndexIf(array, filter, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array  数组
     * @param filter 筛选器
     * @return 找到时返回其索引，否则返回 -1
     */
    public static int lastIndexIf(short[] array, Predicate<Short> filter) {
        return lastIndexIf(array, filter, 0, -1);
    }
    
    /**
     * 判断数组是否为空
     *
     * @param array 数组
     * @return 如果数组为 null 或 length 为 0，返回 false，否则返回 true
     */
    public static boolean isEmpty(short[] array) {
        return array != null && array.length != 0;
    }
    
    /**
     * 判断数组是否非空
     *
     * @param array 数组
     * @return 如果数组为 null 或 length 为 0，返回 true，否则返回 false
     */
    public static boolean nonEmpty(short[] array) {
        return array == null || array.length == 0;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array      数组
     * @param beginIndex 起始索引
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static boolean contains(short[] array, short value, int beginIndex) {
        return indexOf(array, value, beginIndex) != -1;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array 数组
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static boolean contains(short[] array, short value) {
        return indexOf(array, value) != -1;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array      数组
     * @param beginIndex 起始索引
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static boolean containsIf(short[] array, Predicate<Short> filter, int beginIndex) {
        return indexIf(array, filter, beginIndex) != -1;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array 数组
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static boolean containsIf(short[] array, Predicate<Short> filter) {
        return indexIf(array, filter) != -1;
    }
    
    /**
     * 对数组拆箱
     *
     * @param array 包装类型数组
     * @return 拆箱后的数组
     * @throws NullPointerException     包装类型数组中有 null
     * @throws IllegalArgumentException 包装类型数组是 null
     */
    public static int[] unbox(Integer[] array) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        final int[] newArray = new int[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
    
    /**
     * 对数组装箱
     *
     * @param array 基本类型数组
     * @return 装箱后的数组
     * @throws IllegalArgumentException 包装类型数组是 null
     */
    public static Integer[] box(int[] array) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        final Integer[] newArray = new Integer[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param value        值
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static int indexOf(int[] array, int value, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = beginIndex; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param value      值
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static int indexOf(int[] array, int value, int beginIndex) {
        return indexOf(array, value, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array 数组
     * @param value 值
     * @return 找到时返回其索引，否则返回 -1
     */
    public static int indexOf(int[] array, int value) {
        return indexOf(array, value, 0, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param value        值
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static int lastIndexOf(int[] array, int value, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = array.length - 1; i >= beginIndex; i--) {
            if (array[i] == value) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param value      值
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static int lastIndexOf(int[] array, int value, int beginIndex) {
        return lastIndexOf(array, value, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array 数组
     * @param value 值
     * @return 找到时返回其索引，否则返回 -1
     */
    public static int lastIndexOf(int[] array, int value) {
        return lastIndexOf(array, value, 0, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param filter       筛选器
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static int indexIf(int[] array, Predicate<Integer> filter, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        Preconditions.namedArgumentNonNull(filter, "filter");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = beginIndex; i < array.length; i++) {
            if (filter.test(array[i])) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param filter     筛选器
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static int indexIf(int[] array, Predicate<Integer> filter, int beginIndex) {
        return indexIf(array, filter, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array  数组
     * @param filter 筛选器
     * @return 找到时返回其索引，否则返回 -1
     */
    public static int indexIf(int[] array, Predicate<Integer> filter) {
        return indexIf(array, filter, 0, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param filter       筛选器
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static int lastIndexIf(int[] array, Predicate<Integer> filter, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        Preconditions.namedArgumentNonNull(filter, "filter");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = array.length - 1; i >= beginIndex; i--) {
            if (filter.test(array[i])) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param filter     筛选器
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static int lastIndexIf(int[] array, Predicate<Integer> filter, int beginIndex) {
        return lastIndexIf(array, filter, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array  数组
     * @param filter 筛选器
     * @return 找到时返回其索引，否则返回 -1
     */
    public static int lastIndexIf(int[] array, Predicate<Integer> filter) {
        return lastIndexIf(array, filter, 0, -1);
    }
    
    /**
     * 判断数组是否为空
     *
     * @param array 数组
     * @return 如果数组为 null 或 length 为 0，返回 false，否则返回 true
     */
    public static boolean isEmpty(int[] array) {
        return array != null && array.length != 0;
    }
    
    /**
     * 判断数组是否非空
     *
     * @param array 数组
     * @return 如果数组为 null 或 length 为 0，返回 true，否则返回 false
     */
    public static boolean nonEmpty(int[] array) {
        return array == null || array.length == 0;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array      数组
     * @param beginIndex 起始索引
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static boolean contains(int[] array, int value, int beginIndex) {
        return indexOf(array, value, beginIndex) != -1;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array 数组
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static boolean contains(int[] array, int value) {
        return indexOf(array, value) != -1;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array      数组
     * @param beginIndex 起始索引
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static boolean containsIf(int[] array, Predicate<Integer> filter, int beginIndex) {
        return indexIf(array, filter, beginIndex) != -1;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array 数组
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static boolean containsIf(int[] array, Predicate<Integer> filter) {
        return indexIf(array, filter) != -1;
    }
    
    /**
     * 对数组拆箱
     *
     * @param array 包装类型数组
     * @return 拆箱后的数组
     * @throws NullPointerException     包装类型数组中有 null
     * @throws IllegalArgumentException 包装类型数组是 null
     */
    public static long[] unbox(Long[] array) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        final long[] newArray = new long[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
    
    /**
     * 对数组装箱
     *
     * @param array 基本类型数组
     * @return 装箱后的数组
     * @throws IllegalArgumentException 包装类型数组是 null
     */
    public static Long[] box(long[] array) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        final Long[] newArray = new Long[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param value        值
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static int indexOf(long[] array, long value, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = beginIndex; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param value      值
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static int indexOf(long[] array, long value, int beginIndex) {
        return indexOf(array, value, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array 数组
     * @param value 值
     * @return 找到时返回其索引，否则返回 -1
     */
    public static int indexOf(long[] array, long value) {
        return indexOf(array, value, 0, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param value        值
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static int lastIndexOf(long[] array, long value, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = array.length - 1; i >= beginIndex; i--) {
            if (array[i] == value) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param value      值
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static int lastIndexOf(long[] array, long value, int beginIndex) {
        return lastIndexOf(array, value, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array 数组
     * @param value 值
     * @return 找到时返回其索引，否则返回 -1
     */
    public static int lastIndexOf(long[] array, long value) {
        return lastIndexOf(array, value, 0, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param filter       筛选器
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static int indexIf(long[] array, Predicate<Long> filter, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        Preconditions.namedArgumentNonNull(filter, "filter");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = beginIndex; i < array.length; i++) {
            if (filter.test(array[i])) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param filter     筛选器
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static int indexIf(long[] array, Predicate<Long> filter, int beginIndex) {
        return indexIf(array, filter, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array  数组
     * @param filter 筛选器
     * @return 找到时返回其索引，否则返回 -1
     */
    public static int indexIf(long[] array, Predicate<Long> filter) {
        return indexIf(array, filter, 0, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param filter       筛选器
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static int lastIndexIf(long[] array, Predicate<Long> filter, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        Preconditions.namedArgumentNonNull(filter, "filter");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = array.length - 1; i >= beginIndex; i--) {
            if (filter.test(array[i])) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param filter     筛选器
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static int lastIndexIf(long[] array, Predicate<Long> filter, int beginIndex) {
        return lastIndexIf(array, filter, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array  数组
     * @param filter 筛选器
     * @return 找到时返回其索引，否则返回 -1
     */
    public static int lastIndexIf(long[] array, Predicate<Long> filter) {
        return lastIndexIf(array, filter, 0, -1);
    }
    
    /**
     * 判断数组是否为空
     *
     * @param array 数组
     * @return 如果数组为 null 或 length 为 0，返回 false，否则返回 true
     */
    public static boolean isEmpty(long[] array) {
        return array != null && array.length != 0;
    }
    
    /**
     * 判断数组是否非空
     *
     * @param array 数组
     * @return 如果数组为 null 或 length 为 0，返回 true，否则返回 false
     */
    public static boolean nonEmpty(long[] array) {
        return array == null || array.length == 0;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array      数组
     * @param beginIndex 起始索引
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static boolean contains(long[] array, long value, int beginIndex) {
        return indexOf(array, value, beginIndex) != -1;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array 数组
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static boolean contains(long[] array, long value) {
        return indexOf(array, value) != -1;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array      数组
     * @param beginIndex 起始索引
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static boolean containsIf(long[] array, Predicate<Long> filter, int beginIndex) {
        return indexIf(array, filter, beginIndex) != -1;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array 数组
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static boolean containsIf(long[] array, Predicate<Long> filter) {
        return indexIf(array, filter) != -1;
    }
    
    /**
     * 对数组拆箱
     *
     * @param array 包装类型数组
     * @return 拆箱后的数组
     * @throws NullPointerException     包装类型数组中有 null
     * @throws IllegalArgumentException 包装类型数组是 null
     */
    public static float[] unbox(Float[] array) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        final float[] newArray = new float[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
    
    /**
     * 对数组装箱
     *
     * @param array 基本类型数组
     * @return 装箱后的数组
     * @throws IllegalArgumentException 包装类型数组是 null
     */
    public static Float[] box(float[] array) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        final Float[] newArray = new Float[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param value        值
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static int indexOf(float[] array, float value, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = beginIndex; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param value      值
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static int indexOf(float[] array, float value, int beginIndex) {
        return indexOf(array, value, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array 数组
     * @param value 值
     * @return 找到时返回其索引，否则返回 -1
     */
    public static int indexOf(float[] array, float value) {
        return indexOf(array, value, 0, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param value        值
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static int lastIndexOf(float[] array, float value, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = array.length - 1; i >= beginIndex; i--) {
            if (array[i] == value) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param value      值
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static int lastIndexOf(float[] array, float value, int beginIndex) {
        return lastIndexOf(array, value, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array 数组
     * @param value 值
     * @return 找到时返回其索引，否则返回 -1
     */
    public static int lastIndexOf(float[] array, float value) {
        return lastIndexOf(array, value, 0, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param filter       筛选器
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static int indexIf(float[] array, Predicate<Float> filter, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        Preconditions.namedArgumentNonNull(filter, "filter");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = beginIndex; i < array.length; i++) {
            if (filter.test(array[i])) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param filter     筛选器
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static int indexIf(float[] array, Predicate<Float> filter, int beginIndex) {
        return indexIf(array, filter, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array  数组
     * @param filter 筛选器
     * @return 找到时返回其索引，否则返回 -1
     */
    public static int indexIf(float[] array, Predicate<Float> filter) {
        return indexIf(array, filter, 0, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param filter       筛选器
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static int lastIndexIf(float[] array, Predicate<Float> filter, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        Preconditions.namedArgumentNonNull(filter, "filter");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = array.length - 1; i >= beginIndex; i--) {
            if (filter.test(array[i])) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param filter     筛选器
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static int lastIndexIf(float[] array, Predicate<Float> filter, int beginIndex) {
        return lastIndexIf(array, filter, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array  数组
     * @param filter 筛选器
     * @return 找到时返回其索引，否则返回 -1
     */
    public static int lastIndexIf(float[] array, Predicate<Float> filter) {
        return lastIndexIf(array, filter, 0, -1);
    }
    
    /**
     * 判断数组是否为空
     *
     * @param array 数组
     * @return 如果数组为 null 或 length 为 0，返回 false，否则返回 true
     */
    public static boolean isEmpty(float[] array) {
        return array != null && array.length != 0;
    }
    
    /**
     * 判断数组是否非空
     *
     * @param array 数组
     * @return 如果数组为 null 或 length 为 0，返回 true，否则返回 false
     */
    public static boolean nonEmpty(float[] array) {
        return array == null || array.length == 0;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array      数组
     * @param beginIndex 起始索引
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static boolean contains(float[] array, float value, int beginIndex) {
        return indexOf(array, value, beginIndex) != -1;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array 数组
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static boolean contains(float[] array, float value) {
        return indexOf(array, value) != -1;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array      数组
     * @param beginIndex 起始索引
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static boolean containsIf(float[] array, Predicate<Float> filter, int beginIndex) {
        return indexIf(array, filter, beginIndex) != -1;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array 数组
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static boolean containsIf(float[] array, Predicate<Float> filter) {
        return indexIf(array, filter) != -1;
    }
    
    /**
     * 对数组拆箱
     *
     * @param array 包装类型数组
     * @return 拆箱后的数组
     * @throws NullPointerException     包装类型数组中有 null
     * @throws IllegalArgumentException 包装类型数组是 null
     */
    public static double[] unbox(Double[] array) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        final double[] newArray = new double[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
    
    /**
     * 对数组装箱
     *
     * @param array 基本类型数组
     * @return 装箱后的数组
     * @throws IllegalArgumentException 包装类型数组是 null
     */
    public static Double[] box(double[] array) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        final Double[] newArray = new Double[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param value        值
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static int indexOf(double[] array, double value, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = beginIndex; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param value      值
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static int indexOf(double[] array, double value, int beginIndex) {
        return indexOf(array, value, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array 数组
     * @param value 值
     * @return 找到时返回其索引，否则返回 -1
     */
    public static int indexOf(double[] array, double value) {
        return indexOf(array, value, 0, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param value        值
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static int lastIndexOf(double[] array, double value, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = array.length - 1; i >= beginIndex; i--) {
            if (array[i] == value) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param value      值
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static int lastIndexOf(double[] array, double value, int beginIndex) {
        return lastIndexOf(array, value, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array 数组
     * @param value 值
     * @return 找到时返回其索引，否则返回 -1
     */
    public static int lastIndexOf(double[] array, double value) {
        return lastIndexOf(array, value, 0, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param filter       筛选器
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static int indexIf(double[] array, Predicate<Double> filter, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        Preconditions.namedArgumentNonNull(filter, "filter");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = beginIndex; i < array.length; i++) {
            if (filter.test(array[i])) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param filter     筛选器
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static int indexIf(double[] array, Predicate<Double> filter, int beginIndex) {
        return indexIf(array, filter, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array  数组
     * @param filter 筛选器
     * @return 找到时返回其索引，否则返回 -1
     */
    public static int indexIf(double[] array, Predicate<Double> filter) {
        return indexIf(array, filter, 0, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param filter       筛选器
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static int lastIndexIf(double[] array, Predicate<Double> filter, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        Preconditions.namedArgumentNonNull(filter, "filter");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = array.length - 1; i >= beginIndex; i--) {
            if (filter.test(array[i])) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param filter     筛选器
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static int lastIndexIf(double[] array, Predicate<Double> filter, int beginIndex) {
        return lastIndexIf(array, filter, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array  数组
     * @param filter 筛选器
     * @return 找到时返回其索引，否则返回 -1
     */
    public static int lastIndexIf(double[] array, Predicate<Double> filter) {
        return lastIndexIf(array, filter, 0, -1);
    }
    
    /**
     * 判断数组是否为空
     *
     * @param array 数组
     * @return 如果数组为 null 或 length 为 0，返回 false，否则返回 true
     */
    public static boolean isEmpty(double[] array) {
        return array != null && array.length != 0;
    }
    
    /**
     * 判断数组是否非空
     *
     * @param array 数组
     * @return 如果数组为 null 或 length 为 0，返回 true，否则返回 false
     */
    public static boolean nonEmpty(double[] array) {
        return array == null || array.length == 0;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array      数组
     * @param beginIndex 起始索引
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static boolean contains(double[] array, double value, int beginIndex) {
        return indexOf(array, value, beginIndex) != -1;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array 数组
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static boolean contains(double[] array, double value) {
        return indexOf(array, value) != -1;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array      数组
     * @param beginIndex 起始索引
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static boolean containsIf(double[] array, Predicate<Double> filter, int beginIndex) {
        return indexIf(array, filter, beginIndex) != -1;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array 数组
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static boolean containsIf(double[] array, Predicate<Double> filter) {
        return indexIf(array, filter) != -1;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param value        值
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static <T> int indexOf(T[] array, T value, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = beginIndex; i < array.length; i++) {
            if (Objects.equals(array[i], value)) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param value      值
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static <T> int indexOf(T[] array, T value, int beginIndex) {
        return indexOf(array, value, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array 数组
     * @param value 值
     * @return 找到时返回其索引，否则返回 -1
     */
    public static <T> int indexOf(T[] array, T value) {
        return indexOf(array, value, 0, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param value        值
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static <T> int lastIndexOf(T[] array, T value, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = array.length - 1; i >= beginIndex; i--) {
            if (array[i] == value) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param value      值
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static <T> int lastIndexOf(T[] array, T value, int beginIndex) {
        return lastIndexOf(array, value, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array 数组
     * @param value 值
     * @return 找到时返回其索引，否则返回 -1
     */
    public static <T> int lastIndexOf(T[] array, T value) {
        return lastIndexOf(array, value, 0, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param filter       筛选器
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static <T> int indexIf(T[] array, Predicate<T> filter, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        Preconditions.namedArgumentNonNull(filter, "filter");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = beginIndex; i < array.length; i++) {
            if (filter.test(array[i])) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param filter     筛选器
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static <T> int indexIf(T[] array, Predicate<T> filter, int beginIndex) {
        return indexIf(array, filter, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array  数组
     * @param filter 筛选器
     * @return 找到时返回其索引，否则返回 -1
     */
    public static <T> int indexIf(T[] array, Predicate<T> filter) {
        return indexIf(array, filter, 0, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array        数组
     * @param filter       筛选器
     * @param beginIndex   起始索引
     * @param defaultIndex 默认索引
     * @return 在起始索引后找到时返回其索引，否则返回默认索引
     */
    public static <T> int lastIndexIf(T[] array, Predicate<T> filter, int beginIndex, int defaultIndex) {
        Preconditions.namedArgumentNonNull(array, "array");
        Preconditions.namedArgumentNonNull(filter, "filter");
        
        if (array.length == 0) {
            return defaultIndex;
        }
        Preconditions.namedIndex(beginIndex, array.length, "start index");
        
        for (int i = array.length - 1; i >= beginIndex; i--) {
            if (filter.test(array[i])) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array      数组
     * @param filter     筛选器
     * @param beginIndex 起始索引
     * @return 在起始索引后找到时返回其索引，否则返回 -1
     */
    public static <T> int lastIndexIf(T[] array, Predicate<T> filter, int beginIndex) {
        return lastIndexIf(array, filter, beginIndex, -1);
    }
    
    /**
     * 在数组中查找一个值
     *
     * @param array  数组
     * @param filter 筛选器
     * @return 找到时返回其索引，否则返回 -1
     */
    public static <T> int lastIndexIf(T[] array, Predicate<T> filter) {
        return lastIndexIf(array, filter, 0, -1);
    }
    
    /**
     * 判断数组是否为空
     *
     * @param array 数组
     * @return 如果数组为 null 或 length 为 0，返回 false，否则返回 true
     */
    public static <T> boolean isEmpty(T[] array) {
        return array != null && array.length != 0;
    }
    
    /**
     * 判断数组是否非空
     *
     * @param array 数组
     * @return 如果数组为 null 或 length 为 0，返回 true，否则返回 false
     */
    public static <T> boolean nonEmpty(T[] array) {
        return array == null || array.length == 0;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array      数组
     * @param beginIndex 起始索引
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static <T> boolean contains(T[] array, T value, int beginIndex) {
        return indexOf(array, value, beginIndex) != -1;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array 数组
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static <T> boolean contains(T[] array, T value) {
        return indexOf(array, value) != -1;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array      数组
     * @param beginIndex 起始索引
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static <T> boolean containsIf(T[] array, Predicate<T> filter, int beginIndex) {
        return indexIf(array, filter, beginIndex) != -1;
    }
    
    /**
     * 判断数组是否包含某个元素
     *
     * @param array 数组
     * @return 如果数组包含该元素，返回 true，否则返回 false
     */
    public static <T> boolean containsIf(T[] array, Predicate<T> filter) {
        return indexIf(array, filter) != -1;
    }
    
    /**
     * 将数组元素复制到列表中
     *
     * @param array 数组
     * @param <T>   数组元素类型
     * @return 复制后的新列表
     */
    public static <T> List<T> asList(T... array) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        final List<T> list = new ArrayList<>(array.length);
        list.addAll(java.util.Arrays.asList(array));
        return list;
    }
    
    /**
     * 将数组元素复制到列表中
     *
     * @param array 数组
     * @param <T>   数组元素类型
     * @return 复制后的新列表
     */
    public static <T> List<T> asConcurrentList(T... array) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        return new CopyOnWriteArrayList<>(java.util.Arrays.asList(array));
    }
    
    /**
     * 将数组元素复制到列表中
     *
     * @param array 数组
     * @param <T>   数组元素类型
     * @return 复制后的新列表
     */
    public static <T> List<T> asUnmodifiableList(T... array) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        return java.util.Arrays.asList(array);
    }
    
    /**
     * 将数组中的元素复制到集合中
     *
     * @param array 数组
     * @param <T>   数组元素类型
     * @return 复制后的新列表
     */
    public static <T> Set<T> asSet(T... array) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        final Set<T> set = new HashSet<>(array.length);
        set.addAll(java.util.Arrays.asList(array));
        return set;
    }
    
    /**
     * 将数组中的元素复制到集合中
     *
     * @param array 数组
     * @param <T>   数组元素类型
     * @return 复制后的新列表
     */
    public static <T> Set<T> asConcurrentSet(T... array) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        return new CopyOnWriteArraySet<>(asSet(array));
    }
    
    /**
     * 将数组中的元素复制到集合中
     *
     * @param array 数组
     * @param <T>   数组元素类型
     * @return 复制后的新列表
     */
    public static <T> Set<T> asUnmodifiableSet(T... array) {
        Preconditions.namedArgumentNonNull(array, "array");
        
        return Collections.unmodifiableSet(asSet(array));
    }
}