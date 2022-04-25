package cn.chuanwise.common.util;

import cn.chuanwise.common.space.Pair;

import java.util.*;
import java.util.Collections;

/**
 * 双元素哈希表
 *
 * @author Chuanwise
 */
public class DoubleMap<K, V>
    implements Map<K, V> {
    
    private final K k1;
    private final V v1;
    
    private final K k2;
    private final V v2;
    
    private final Set<K> keySet;
    private final List<V> values;
    
    private final Set<Entry<K, V>> entrySet;
    
    @SuppressWarnings("unchecked")
    protected DoubleMap(K k1, V v1, K k2, V v2) {
        Preconditions.argument(!Objects.equals(k1, k2), "key 1 equals to key 2");
        
        this.k1 = k1;
        this.v1 = v1;
        
        this.k2 = k2;
        this.v2 = v2;
        
        keySet = cn.chuanwise.common.util.Collections.asUnmodifiableSet(k1, k2);
        values = cn.chuanwise.common.util.Collections.asUnmodifiableList(v1, v2);
        
        entrySet = cn.chuanwise.common.util.Collections.asUnmodifiableSet(Pair.ofUnmodifiable(k1, v1), Pair.ofUnmodifiable(k2, v2));
    }
    
    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2) {
        if (Objects.equals(k1, k2)) {
            return Collections.singletonMap(k1, v1);
        } else {
            return new DoubleMap<>(k1, v1, k2, v2);
        }
    }
    
    @Override
    public int size() {
        return 2;
    }
    
    @Override
    public boolean isEmpty() {
        return false;
    }
    
    @Override
    public boolean containsKey(Object key) {
        return Objects.equals(k1, key)
            || Objects.equals(k2, key);
    }
    
    @Override
    public boolean containsValue(Object value) {
        return Objects.equals(v1, value)
            || Objects.equals(v2, value);
    }
    
    @Override
    public V get(Object key) {
        if (Objects.equals(key, k1)) {
            return v1;
        }
        
        if (Objects.equals(key, k2)) {
            return v2;
        }
        
        return null;
    }
    
    @Override
    public V put(K key, V value) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public V remove(Object key) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Set<K> keySet() {
        return keySet;
    }
    
    @Override
    public Collection<V> values() {
        return values;
    }
    
    @Override
    public Set<Entry<K, V>> entrySet() {
        return entrySet;
    }
}
