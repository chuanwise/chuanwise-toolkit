package cn.chuanwise.common.space;

/**
 * 可以修改的键值对
 *
 * @author Chuanwise
 *
 * @param <K> 键类型
 * @param <V> 值类型
 *
 * @see cn.chuanwise.common.space.Pair
 */
public class ModifiablePair<K, V>
    implements Pair<K, V> {
    
    private final K key;
    
    private V value;
    
    public ModifiablePair(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    @Override
    public K getKey() {
        return key;
    }
    
    @Override
    public V getValue() {
        return value;
    }
    
    @Override
    public V setValue(V value) {
        final V elderValue = this.value;
        this.value = value;
        return elderValue;
    }
}
