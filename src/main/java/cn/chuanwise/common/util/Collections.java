package cn.chuanwise.common.util;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Predicate;

/**
 * 集合工具类
 *
 * @author Chuanwise
 */
public class Collections
        extends StaticUtilities {
    
    /**
     * 判断集合是否为空
     *
     * @param collection 集合
     * @return 当 collection == null 或没有元素时返回 true
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
    
    /**
     * 判断集合是否非空
     *
     * @param collection 集合
     * @return 当 collection 不为 null 且有至少一个元素时返回 true
     */
    public static boolean nonEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }
    
    /**
     * 将迭代器元素复制到列表中
     *
     * @param iterable 迭代器
     * @param <T>      集合元素类型
     * @return 复制后的新列表
     */
    public static <T> List<T> asList(Iterable<T> iterable) {
        Preconditions.namedArgumentNonNull(iterable, "iterable");
    
        final List<T> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }
    
    /**
     * 将迭代器元素复制到列表中
     *
     * @param iterable 迭代器
     * @param <T>      集合元素类型
     * @return 复制后的新列表
     */
    public static <T> List<T> asConcurrentList(Iterable<T> iterable) {
        Preconditions.namedArgumentNonNull(iterable, "iterable");
    
        final List<T> list = new CopyOnWriteArrayList<>();
        iterable.forEach(list::add);
        return list;
    }
    
    /**
     * 将迭代器元素复制到列表中
     *
     * @param iterable 迭代器
     * @param <T>      集合元素类型
     * @return 复制后的新列表
     */
    public static <T> List<T> asUnmodifiableList(Iterable<T> iterable) {
        Preconditions.namedArgumentNonNull(iterable, "iterable");
    
        final List<T> list = new ArrayList<>();
        iterable.forEach(list::add);
        return java.util.Collections.unmodifiableList(list);
    }
    
    /**
     * 将迭代器元素复制到列表中
     *
     * @param iterable 迭代器
     * @param <T>      集合元素类型
     * @return 复制后的新列表
     */
    public static <T> Set<T> asSet(Iterable<T> iterable) {
        Preconditions.namedArgumentNonNull(iterable, "iterable");
        
        final Set<T> set = new HashSet<>();
        iterable.forEach(set::add);
        return set;
    }
    
    /**
     * 将迭代器元素复制到列表中
     *
     * @param iterable 迭代器
     * @param <T>      集合元素类型
     * @return 复制后的新列表
     */
    public static <T> Set<T> asConcurrentSet(Iterable<T> iterable) {
        Preconditions.namedArgumentNonNull(iterable, "iterable");
        
        final Set<T> set = new CopyOnWriteArraySet<>();
        iterable.forEach(set::add);
        return set;
    }
    
    /**
     * 将迭代器元素复制到列表中
     *
     * @param iterable 迭代器
     * @param <T>      集合元素类型
     * @return 复制后的新列表
     */
    public static <T> Set<T> asUnmodifiableSet(Iterable<T> iterable) {
        Preconditions.namedArgumentNonNull(iterable, "iterable");
        
        final Set<T> set = new HashSet<>();
        iterable.forEach(set::add);
        return java.util.Collections.unmodifiableSet(set);
    }
    
    /**
     * 寻找起始索引之后的某个值
     *
     * @param iterable   集合
     * @param value      目标值
     * @param beginIndex 起始索引
     * @param <T>        集合元素类型
     * @return 当在起始索引后找到目标值后返回其索引，否则返回 -1
     */
    public static <T> int indexOf(Iterable<T> iterable, T value, int beginIndex) {
        Preconditions.namedArgumentNonNull(iterable, "iterable");
        Preconditions.argument(beginIndex >= 0, "begin index must be bigger than or equals to 0!");
        
        int index = -1;
        for (T t : iterable) {
            index++;
    
            if (index < beginIndex) {
                continue;
            }
            
            if (Objects.equals(t, value)) {
                return index;
            }
        }
        
        Preconditions.namedIndex(index >= beginIndex, "begin index");
        
        return -1;
    }
    
    /**
     * 从头寻找某个值
     *
     * @param iterable 集合
     * @param value    目标值
     * @param <T>      集合元素类型
     * @return 当在找到目标值后返回其索引，否则返回 -1
     */
    public static <T> int indexOf(Iterable<T> iterable, T value) {
        return indexOf(iterable, value, 0);
    }
    
    /**
     * 寻找起始索引之后的某个值
     *
     * @param iterable 集合
     * @param filter   筛选器
     * @param <T>      集合元素类型
     * @return 当在起始索引后找到目标值后返回其索引，否则返回 -1
     */
    public static <T> int indexIf(Iterable<T> iterable, Predicate<T> filter, int beginIndex) {
        Preconditions.namedArgumentNonNull(iterable, "iterable");
        Preconditions.namedArgumentNonNull(filter, "filter");
        Preconditions.argument(beginIndex >= 0, "begin index must be bigger than or equals to 0!");
    
        int index = -1;
        for (T t : iterable) {
            index++;
    
            if (index < beginIndex) {
                continue;
            }
    
            if (filter.test(t)) {
                return index;
            }
        }
    
        Preconditions.namedIndex(index >= beginIndex, "begin index");
    
        return -1;
    }
    
    /**
     * 从头寻找某个值
     *
     * @param iterable 集合
     * @param filter   筛选器
     * @param <T>      集合元素类型
     * @return 当在找到目标值后返回其索引，否则返回 -1
     */
    public static <T> int indexIf(Iterable<T> iterable, Predicate<T> filter) {
        return indexIf(iterable, filter, 0);
    }
    
    /**
     * 寻找起始索引之后的某个值
     *
     * @param list       集合
     * @param value      目标值
     * @param beginIndex 起始索引
     * @param <T>        集合元素类型
     * @return 当在起始索引后找到目标值后返回其索引，否则返回 -1
     */
    public static <T> int indexOf(List<T> list, T value, int beginIndex) {
        Preconditions.namedArgumentNonNull(list, "list");
        Preconditions.index(beginIndex, list.size(), "begin index must be bigger than or equals to 0!");
    
        final int size = list.size();
        for (int i = beginIndex; i < size; i++) {
            final T t = list.get(i);
            if (Objects.equals(value, t)) {
                return i;
            }
        }
        
        return -1;
    }
    
    /**
     * 从头寻找某个值
     *
     * @param list  集合
     * @param value 目标值
     * @param <T>   集合元素类型
     * @return 当找到目标值后返回其索引，否则返回 -1
     */
    public static <T> int indexOf(List<T> list, T value) {
        return indexOf(list, value, 0);
    }
    
    /**
     * 寻找起始索引之后的某个值
     *
     * @param list       集合
     * @param filter     筛选器
     * @param beginIndex 起始索引
     * @param <T>        集合元素类型
     * @return 当在起始索引后找到目标值后返回其索引，否则返回 -1
     */
    public static <T> int indexIf(List<T> list, Predicate<T> filter, int beginIndex) {
        Preconditions.namedArgumentNonNull(list, "list");
        Preconditions.namedArgumentNonNull(filter, "filter");
        Preconditions.index(beginIndex, list.size(), "begin index must be bigger than or equals to 0!");
    
        final int size = list.size();
        for (int i = beginIndex; i < size; i++) {
            final T t = list.get(i);
            if (filter.test(t)) {
                return i;
            }
        }
        
        return -1;
    }
    
    /**
     * 从头寻找某个值
     *
     * @param list   集合
     * @param filter 筛选器
     * @param <T>    集合元素类型
     * @return 当找到目标值后返回其索引，否则返回 -1
     */
    public static <T> int indexIf(List<T> list, Predicate<T> filter) {
        return indexIf(list, filter, 0);
    }
    
    /**
     * 在集合中添加一个新的元素
     *
     * @param collection 集合
     * @param value      元素
     * @param <T>        元素类型
     * @return 如果集合本包含该元素，返回 false，否则添加并返回 true
     */
    public static <T> boolean addDistinctly(Collection<T> collection, T value) {
        Preconditions.namedArgumentNonNull(collection, "collection");
    
        if (collection.contains(value)) {
            return false;
        } else {
            return collection.add(value);
        }
    }
    
    /**
     * 寻找集合中第一个满足要求的对象
     *
     * @param iterable     集合
     * @param filter       筛选器
     * @param defaultValue 默认值
     * @param <T>          集合元素类型
     * @return 当在集合中找到满足筛选器的元素时返回元素，否则返回默认值
     */
    public static <T> T firstIf(Iterable<T> iterable, Predicate<T> filter, T defaultValue) {
        Preconditions.namedArgumentNonNull(iterable, "iterable");
        Preconditions.namedArgumentNonNull(filter, "filter");
    
        for (T t : iterable) {
            if (filter.test(t)) {
                return t;
            }
        }
        
        return defaultValue;
    }
    
    /**
     * 寻找集合中第一个满足要求的对象
     *
     * @param iterable 集合
     * @param filter   筛选器
     * @param <T>      集合元素类型
     * @return 当在集合中找到满足筛选器的元素时返回元素，否则返回 null
     */
    public static <T> T firstIf(Iterable<T> iterable, Predicate<T> filter) {
        return firstIf(iterable, filter, null);
    }
    
    /**
     * 在列表中从后往前查找第一个满足要求的对象
     *
     * @param list         列表
     * @param filter       筛选器
     * @param beginIndex   最早找到哪个元素
     * @param defaultValue 默认值
     * @param <T>          列表元素类型
     * @return 当在列表中从后往前找到满足筛选器的元素时返回元素，否则返回默认值
     */
    public static <T> T lastOf(List<T> list, Predicate<T> filter, int beginIndex, T defaultValue) {
        Preconditions.namedArgumentNonNull(list, "list");
        Preconditions.namedArgumentNonNull(filter, "filter");
        Preconditions.namedPosition(beginIndex, list.size(), "begin");
    
        final int size = list.size();
        for (int i = size - 1; i >= beginIndex; i--) {
            final T t = list.get(i);
            if (filter.test(t)) {
                return t;
            }
        }
        
        return defaultValue;
    }
    
    /**
     * 在列表中从后往前查找第一个满足要求的对象
     *
     * @param list       列表
     * @param filter     筛选器
     * @param beginIndex 最早找到哪个元素
     * @param <T>        列表元素类型
     * @return 当在列表中从后往前找到满足筛选器的元素时返回元素，否则返回 null
     */
    public static <T> T lastOf(List<T> list, Predicate<T> filter, int beginIndex) {
        return lastOf(list, filter, beginIndex, null);
    }
    
    /**
     * 在列表中从后往前查找第一个满足要求的对象
     *
     * @param list   列表
     * @param filter 筛选器
     * @param <T>    列表元素类型
     * @return 当在列表中从后往前找到满足筛选器的元素时返回元素，否则返回 null
     */
    public static <T> T lastOf(List<T> list, Predicate<T> filter) {
        return lastOf(list, filter, 0, null);
    }
    
    /**
     * 检查集合中是否有满足某条件的元素
     *
     * @param collection 集合
     * @param filter 筛选器
     * @param <T> 集合元素类型
     * @return 集合中是否有满足该条件的元素
     */
    public static <T> boolean containsIf(Collection<T> collection, Predicate<T> filter) {
        Preconditions.namedArgumentNonNull(collection, "collection");
        Preconditions.namedArgumentNonNull(filter, "filter");
    
        for (T t : collection) {
            if (filter.test(t)) {
                return true;
            }
        }
        
        return false;
    }
}