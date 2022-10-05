package cn.chuanwise.common.space;

/**
 * @author Chuanwise
 *
 * @see cn.chuanwise.common.space.Pair
 * @see java.util.Map.Entry
 */
public class UnmodifiablePair<K, V>
    implements Pair<K, V> {
    
    private final K key;
    
    private final V value;
    
    public UnmodifiablePair(K key, V value) {
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
        throw new UnsupportedOperationException();
    }
}
