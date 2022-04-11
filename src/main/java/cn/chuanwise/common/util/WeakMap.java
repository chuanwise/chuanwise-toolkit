package cn.chuanwise.common.util;

import lombok.Data;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Map;

/**
 * 使用软引用的哈希表
 *
 * @author Chuanwise
 */
public class WeakMap<K, V>
    extends NestedMap<K, V, WeakMap.WeakValue<K, V>> {
    
    public WeakMap(Map<K, WeakValue<K, V>> map) {
        super(map);
    }
    
    @Override
    public V put(K key, V value) {
        final WeakValue<K, V> weakValue = map.put(key, new WeakValue<>(this, key, value));
        return weakValue == null ? null : weakValue.get();
    }
    
    @Data
    @SuppressWarnings("all")
    public static class WeakValue<K, V>
        extends WeakReference<V> {
    
        private final K key;
        
        private final NestedMap<K, V, ?> map;
    
        protected WeakValue(NestedMap<K, V, ?> map, K key, V value) {
            super(value);
        
            this.map = map;
            this.key = key;
        }
    
        @Override
        protected void finalize() {
            map.remove(key);
        }
    }
}
