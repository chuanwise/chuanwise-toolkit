package cn.chuanwise.common.space;

import cn.chuanwise.common.util.Preconditions;

/**
 * 不可编辑键值对装饰器
 *
 * @author Chuanwise
 *
 * @param <K> 键类型
 * @param <V> 值类型
 *
 * @see cn.chuanwise.common.space.Pair
 */
public class UnmodifiablePairDecorator<K, V>
    implements Pair<K, V> {
    
    private final Pair<K, V> pair;
    
    public UnmodifiablePairDecorator(Pair<K, V> pair) {
        Preconditions.objectNonNull(pair, "pair");
        
        this.pair = pair;
    }
    
    @Override
    public K getKey() {
        return pair.getKey();
    }
    
    @Override
    public V getValue() {
        return pair.getValue();
    }
    
    @Override
    public V setValue(V value) {
        throw new UnsupportedOperationException();
    }
}
