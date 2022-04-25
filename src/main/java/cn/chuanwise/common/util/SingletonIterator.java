package cn.chuanwise.common.util;

import lombok.Data;

import java.util.Iterator;

/**
 * 单个元素的迭代器
 *
 * @author Chuanwise
 * @param <T> 迭代元素类型
 */
@Data
public class SingletonIterator<T>
    implements Iterator<T> {
    
    /**
     * 迭代元素类型
     */
    private final T element;
    
    /**
     * 是否已经调用过 {@link #next()}
     */
    private boolean nextCalled;
    
    public SingletonIterator(T element) {
        this.element = element;
    }
    
    @Override
    public boolean hasNext() {
        return !nextCalled;
    }
    
    @Override
    public T next() {
        if (nextCalled) {
            throw new IndexOutOfBoundsException();
        }
        nextCalled = true;
    
        return element;
    }
}
