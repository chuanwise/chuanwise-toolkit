package cn.chuanwise.common.util;

import java.io.Flushable;
import java.lang.ref.Reference;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Chuanwise
 */
public abstract class NestedMap<K, V, R extends Reference<V>>
    implements Map<K, V>, Flushable {
    
    protected final Map<K, R> map;
    
    public NestedMap(Map<K, R> map) {
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
    @SuppressWarnings("all")
    public boolean containsKey(Object key) {
        final R r = map.get(key);
        return r != null && r.get() != null;
    }
    
    @Override
    public boolean containsValue(Object value) {
        return Collections.containsIf(map.values(), x -> Objects.equals(x.get(), value));
    }
    
    @Override
    public V get(Object key) {
        final R r = map.get(key);
        return r == null ? null : r.get();
    }
    
    @Override
    public V remove(Object key) {
        final R r = map.remove(key);
        return r == null ? null : r.get();
    }
    
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
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
        return new SetAdapter<V>(
            map.values()
                .stream()
                .map(this::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet())
        ) {
            @Override
            public boolean remove(Object o) {
                final boolean removed = super.remove(o);
                if (removed) {
                    map.entrySet().removeIf(x -> Objects.equals(x.getValue(), o));
                }
                return removed;
            }
        };
    }
    
    @Override
    @SuppressWarnings("all")
    public Set<Map.Entry<K, V>> entrySet() {
        return (Set) new SetAdapter<R>(new HashSet<>(map.values())) {
            @Override
            public boolean remove(Object o) {
                final boolean removed = super.remove(o);
                if (removed) {
                    map.values().remove(o);
                }
                return removed;
            }
        };
    }
    
    @Override
    public void flush() {
        map.values().removeIf(Objects::isNull);
    }
}
