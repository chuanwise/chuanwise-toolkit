package cn.chuanwise.toolkit.grid;

import cn.chuanwise.utility.CheckUtility;
import lombok.Getter;

public class ArrayGrid<T> implements Grid<T> {
    @Getter
    final int columnNumber, rowNumber;

    final Object[] array;

    public ArrayGrid(int rowNumber, int columnNumber) {
        CheckUtility.checkArgument(columnNumber > 0, "column number must bigger than 0!");
        CheckUtility.checkArgument(rowNumber > 0, "row number must bigger than 0!");

        this.columnNumber = columnNumber;
        this.rowNumber = rowNumber;
        array = new Object[columnNumber * rowNumber];
    }

    @Override
    public void set(int index, T t) {
        CheckUtility.checkIndex(index, array.length, "originalIndex");
        array[index] = t;
    }

    @Override
    public T get(int index) {
        CheckUtility.checkIndex(index, array.length, "originalIndex");
        return (T) array[index];
    }
}