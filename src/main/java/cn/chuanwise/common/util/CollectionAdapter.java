package cn.chuanwise.common.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

/**
 * 集合适配器
 *
 * @param <T> 集合元素类型
 * @author Chuanwise
 */
public class CollectionAdapter<T>
        implements Collection<T> {
    
    protected Collection<T> collection;
    
    public CollectionAdapter(Collection<T> collection) {
        Preconditions.objectNonNull(collection, "collection");
        
        this.collection = collection;
    }
    
    @Override
    public int size() {
        return collection.size();
    }
    
    @Override
    public boolean isEmpty() {
        return collection.isEmpty();
    }
    
    @Override
    public boolean contains(Object o) {
        return collection.contains(o);
    }
    
    @Override
    public Iterator<T> iterator() {
        return collection.iterator();
    }
    
    @Override
    public Object[] toArray() {
        return collection.toArray();
    }
    
    @Override
    public <T1> T1[] toArray(T1[] a) {
        return collection.toArray(a);
    }
    
    @Override
    public boolean add(T t) {
        return collection.add(t);
    }
    
    @Override
    public boolean remove(Object o) {
        return collection.remove(o);
    }
    
    @Override
    public boolean containsAll(Collection<?> c) {
        return collection.containsAll(c);
    }
    
    @Override
    public boolean addAll(Collection<? extends T> c) {
        return collection.addAll(c);
    }
    
    @Override
    public boolean removeAll(Collection<?> c) {
        return collection.removeAll(c);
    }
    
    @Override
    public boolean retainAll(Collection<?> c) {
        return collection.retainAll(c);
    }
    
    @Override
    public void clear() {
        collection.clear();
    }
    
    protected boolean canEqual(Object o) {
        return o instanceof CollectionAdapter;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CollectionAdapter<?> that = (CollectionAdapter<?>) o;
        return Objects.equals(collection, that.collection);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(collection);
    }
}