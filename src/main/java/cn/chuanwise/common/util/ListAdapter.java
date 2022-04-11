package cn.chuanwise.common.util;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/**
 * 列表适配器
 *
 * @author Chuanwise
 */
public class ListAdapter<T>
        extends CollectionAdapter<T>
        implements List<T> {
    
    public ListAdapter(List<T> list) {
        super(list);
    }
    
    protected List<T> getList() {
        return (List<T>) collection;
    }
    
    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return getList().addAll(c);
    }
    
    @Override
    public T get(int index) {
        return getList().get(index);
    }
    
    @Override
    public T set(int index, T element) {
        return getList().set(index, element);
    }
    
    @Override
    public void add(int index, T element) {
        getList().add(index, element);
    }
    
    @Override
    public T remove(int index) {
        return getList().remove(index);
    }
    
    @Override
    public int indexOf(Object o) {
        return getList().indexOf(o);
    }
    
    @Override
    public int lastIndexOf(Object o) {
        return getList().lastIndexOf(o);
    }
    
    @Override
    public ListIterator<T> listIterator() {
        return getList().listIterator();
    }
    
    @Override
    public ListIterator<T> listIterator(int index) {
        return getList().listIterator(index);
    }
    
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return getList().subList(fromIndex, toIndex);
    }
}
