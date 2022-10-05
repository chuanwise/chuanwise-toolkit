package cn.chuanwise.common.grid;

import cn.chuanwise.common.util.Preconditions;

/**
 * 不可编辑的网格
 *
 * @author Chuanwise
 * @param <T> 网格元素类型
 */
public class UnmodifiableGird<T>
    implements Grid<T> {
    
    protected final Grid<T> grid;
    
    public UnmodifiableGird(Grid<T> grid) {
        Preconditions.objectNonNull(grid, "grid");
        
        this.grid = grid;
    }
    
    @Override
    public int getColumnCount() {
        return grid.getColumnCount();
    }
    
    @Override
    public int getRowCount() {
        return grid.getRowCount();
    }
    
    @Override
    public void set(int index, T t) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public T get(int index) {
        return grid.get(index);
    }
    
    @Override
    public int indexOf(T t) {
        return grid.indexOf(t);
    }
}