package cn.chuanwise.common.grid;

import cn.chuanwise.common.util.Preconditions;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;
import java.util.Optional;

/**
 * 使用一维数组实现的网格
 *
 * @param <T> 网格元素类型
 * @author Chuanwise
 */
@SuppressWarnings("all")
@EqualsAndHashCode
public class ArrayGrid<T>
    implements Grid<T> {
    
    @Getter
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
}