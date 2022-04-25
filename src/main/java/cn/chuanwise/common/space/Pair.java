package cn.chuanwise.common.space;

import java.util.Map;

/**
 * 表示一对值
 *
 * @author Chuanwise
 *
 * @param <K> 键类型
 * @param <V> 值类型
 *
 * @see java.util.Map.Entry
 */
public interface Pair<K, V> 
    extends Map.Entry<K, V> {
    
    /**
     * 构造键值对
     * 
     * @param key 键
     * @param value 值
     * @param <K> 键类型
     * @param <V> 值类型
     * @return 键值对
     */
    static <K, V> Pair<K, V> of(K key, V value) {
        return new ModifiablePair<>(key, value);
    }
    
    /**
     * 构造键值对
     *
     * @param key 键
     * @param value 值
     * @param <K> 键类型
     * @param <V> 值类型
     * @return 键值对
     */
    static <K, V> Pair<K, V> ofUnmodifiable(K key, V value) {
        return new UnmodifiablePair<>(key, value);
    }
    
    /**
     * 构造同步键值对
     *
     * @param pair 键值对
     * @param <K> 键类型
     * @param <V> 值类型
     * @return 同步键值对
     * @throws NullPointerException pair 为 null
     */
    static <K, V> Pair<K, V> concurrent(Pair<K, V> pair) {
        return new ConcurrentPairDecorator<>(pair);
    }
    
    /**
     * 构造不可编辑的键值对
     *
     * @param pair 键值对
     * @param <K> 键类型
     * @param <V> 值类型
     * @return 不可编辑的键值对
     * @throws NullPointerException pair 为 null
     */
    static <K, V> Pair<K, V> unmodifiable(Pair<K, V> pair) {
        return new UnmodifiablePairDecorator<>(pair);
    }
}
