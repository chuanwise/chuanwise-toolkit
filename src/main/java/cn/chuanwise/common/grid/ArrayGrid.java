package cn.chuanwise.common.grid;

import cn.chuanwise.common.util.Preconditions;

import java.util.Arrays;
import java.util.Objects;

/**
 * 使用一维数组实现的网格
 *
 * @param <T> 网格元素类型
 * @author Chuanwise
 */
@SuppressWarnings("all")
public class ArrayGrid<T>
    implements Grid<T> {
    
    protected final int columnCount, rowCount;
    
    protected final Object[] array;

    public ArrayGrid(int rowCount, int columnCount) {
        Preconditions.argument(columnCount > 0, "column number must bigger than 0!");
        Preconditions.argument(rowCount > 0, "row number must bigger than 0!");

        this.columnCount = columnCount;
        this.rowCount = rowCount;
        this.array = new Object[columnCount * rowCount];
    }
    
    @Override
    public int getColumnCount() {
        return columnCount;
    }
    
    @Override
    public int getRowCount() {
        return rowCount;
    }
    
    @Override
    public void set(int index, T t) {
        Preconditions.index(index, array.length, "originalIndex");
        array[index] = t;
    }

    @Override
    public T get(int index) {
        Preconditions.index(index, array.length, "originalIndex");
        return (T) array[index];
    }

    @Override
    public int indexOf(T t) {
        final T[] array = (T[]) this.array;
        for (int i = 0; i < array.length; i++) {
            final T element = array[i];
            if (Objects.equals(element, t)) {
                return i;
            }
        }
        return -1;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayGrid<?> arrayGrid = (ArrayGrid<?>) o;
        return columnCount == arrayGrid.columnCount
            && rowCount == arrayGrid.rowCount
            && Arrays.equals(array, arrayGrid.array);
    }
    
    @Override
    public int hashCode() {
        int result = Objects.hash(columnCount, rowCount);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }
}