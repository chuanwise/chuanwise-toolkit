package cn.chuanwise.common.util;

import java.util.Iterator;

/**
 * 连接两个迭代器
 *
 * @author Chuanwise
 */
public class ConnectedIterator<T>
    implements Iterator<T> {
    
    private final Iterator<? extends T> firstIterator;
    
    private final Iterator<? extends T> secondIterator;
    
    public ConnectedIterator(Iterator<? extends T> firstIterator, Iterator<? extends T> secondIterator) {
        Preconditions.objectNonNull(firstIterator, "first iterator");
        Preconditions.objectNonNull(secondIterator, "second iterator");
        
        this.firstIterator = firstIterator;
        this.secondIterator = secondIterator;
    }
    
    @Override
    public boolean hasNext() {
        return firstIterator.hasNext() || secondIterator.hasNext();
    }
    
    @Override
    public T next() {
        if (firstIterator.hasNext()) {
            return firstIterator.next();
        } else if (secondIterator.hasNext()) {
            return secondIterator.next();
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
}
