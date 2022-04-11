package cn.chuanwise.common.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Map 适配器
 *
 * @author Chuanwise
 */
public class MapAdapter<K, V>
        implements Map<K, V> {
    
    protected final Map<K, V> map;
    
    public MapAdapter(Map<K, V> map) {
        Preconditions.objectNonNull(map, "map");
        
        this.map = map;
    }
    
    @Override
    public int size() {
        return map.size();
    }
    
    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }
    
    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }
    
    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }
    
    @Override
    public V get(Object key) {
        return map.get(key);
    }
    
    @Override
    public V put(K key, V value) {
        return map.put(key, value);
    }
    
    @Override
    public V remove(Object key) {
        return map.remove(key);
    }
    
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        map.putAll(m);
    }
    
    @Override
    public void clear() {
        map.clear();
    }
    
    @Override
    public Set<K> keySet() {
        return map.keySet();
    }
    
    @Override
    public Collection<V> values() {
        return map.values();
    }
    
    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }
}
