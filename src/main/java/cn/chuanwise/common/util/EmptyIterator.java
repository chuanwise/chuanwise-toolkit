package cn.chuanwise.common.util;

import java.util.Iterator;

/**
 * 空迭代器
 *
 * @author Chuanwise
 */
public class EmptyIterator<T>
    implements Iterator<T> {
    
    private static final EmptyIterator<?> INSTANCE = new EmptyIterator<>();
    
    private EmptyIterator() {
    }
    
    @SuppressWarnings("unchecked")
    public static <U> EmptyIterator<U> getInstance() {
        return (EmptyIterator<U>) INSTANCE;
    }
    
    @Override
    public boolean hasNext() {
        return false;
    }
    
    @Override
    public T next() {
        throw new IndexOutOfBoundsException();
    }
}
