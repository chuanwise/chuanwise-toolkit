package cn.chuanwise.common.util;

import lombok.Data;

import java.lang.ref.SoftReference;
import java.util.Map;

/**
 * 使用软引用的哈希表
 *
 * @author Chuanwise
 */
public class SoftMap<K, V>
    extends NestedMap<K, V, SoftMap.SoftValue<K, V>> {
    
    public SoftMap(Map<K, SoftValue<K, V>> map) {
        super(map);
    }
    
    @Override
    public V put(K key, V value) {
        final SoftValue<K, V> softValue = map.put(key, new SoftValue<>(this, key, value));
        return softValue == null ? null : softValue.get();
    }
    
    @Data
    @SuppressWarnings("all")
    public static class SoftValue<K, V>
        extends SoftReference<V> {
    
        private final K key;
        
        private final NestedMap<K, V, ?> map;
    
        protected SoftValue(NestedMap<K, V, ?> map, K key, V value) {
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
