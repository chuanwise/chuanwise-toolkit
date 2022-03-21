package cn.chuanwise.common.util;

import java.util.Map;
import java.util.function.Supplier;

/**
 * 映射表相关工具类
 *
 * @author Chuanwise
 */
public class Maps
        extends StaticUtilities {
    
    /**
     * 从映射表中获取某元素，或将其添加后获取
     *
     * @param map   映射表
     * @param key   键
     * @param value 值
     * @param <K>   键类型
     * @param <V>   值类型
     * @return 值
     */
    public static <K, V> V getOrPut(Map<K, V> map, K key, V value) {
        Preconditions.namedArgumentNonNull(map, "map");
    
        if (map.containsKey(key)) {
            return map.get(key);
        } else {
            map.put(key, value);
            return value;
        }
    }
    
    /**
     * 从映射表中获取某元素，或将其添加后获取
     *
     * @param map      映射表
     * @param key      键
     * @param supplier 值的访问器
     * @param <K>      键类型
     * @param <V>      值类型
     * @return 值
     */
    public static <K, V> V getOrPutGet(Map<K, V> map, K key, Supplier<V> supplier) {
        Preconditions.namedArgumentNonNull(map, "map");
        Preconditions.namedArgumentNonNull(supplier, "supplier");
        
        if (map.containsKey(key)) {
            return map.get(key);
        } else {
            final V v = supplier.get();
            map.put(key, v);
            return v;
        }
    }
}