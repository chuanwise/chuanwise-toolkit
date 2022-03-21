package cn.chuanwise.common.grid;

import cn.chuanwise.common.util.Indexes;
import cn.chuanwise.common.util.Preconditions;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * 表示某种网格化的东西
 *
 * @param <T> 网格内的元素类型
 * @author Chuanwise
 */
public interface Grid<T> {
    
    /**
     * 获取列数
     *
     * @return 列数
     */
    int getColumnCount();
    
    /**
     * 获取行数
     *
     * @return 行数
     */
    int getRowCount();
    
    /**
     * 获取总大小
     *
     * @return 总大小
     */
    default int size() {
        return getColumnCount() * getRowCount();
    }
    
    /**
     * 设置某个位置的值
     *
     * @param index 索引
     * @param t     值
     */
    void set(int index, T t);
    
    /**
     * 删除某个位置的值
     *
     * @param index 索引
     */
    default void remove(int index) {
        set(index, null);
    }
    
    /**
     * 删除某个位置的值
     *
     * @param row    行索引
     * @param column 列索引
     */
    default void remove(int row, int column) {
        remove(Indexes.index2DTo1D(row, column, getColumnCount()));
    }
    
    /**
     * 获取某个位置的值
     *
     * @param index 索引
     * @return 如果该处无值则返回 null
     */
    T get(int index);
    
    /**
     * 查找某个元素首次出现的位置
     *
     * @param t 元素
     * @return 索引值
     */
    int indexOf(T t);
    
    /**
     * 获取某个位置的值
     *
     * @param row    行索引
     * @param column 列索引
     * @return 如果该处无值则返回 null
     */
    default T get(int row, int column) {
        return get(Indexes.index2DTo1D(row, column, getColumnCount()));
    }
    
    /**
     * 设置某个位置处的值
     *
     * @param row    行索引
     * @param column 列索引
     * @param t      值
     */
    default void set(int row, int column, T t) {
        set(Indexes.index2DTo1D(row, column, getColumnCount()), t);
    }
    
    /**
     * 行迭代器
     *
     * @param row 行序号
     * @return 迭代器
     */
    default Iterator<T> rowIterator(int row) {
        final int rowCount = getRowCount();
        Preconditions.index(row, rowCount, "row index");
    
        return new Iterator<T>() {
            int cursor;
        
            @Override
            public boolean hasNext() {
                return cursor < rowCount;
            }
        
            @Override
            public T next() {
                return get(row, cursor++);
            }
        };
    }
    
    /**
     * 列迭代器
     *
     * @param column 列序号
     * @return 迭代器
     */
    default Iterator<T> columnIterator(int column) {
        final int columnCount = getColumnCount();
        Preconditions.index(column, columnCount, "column index");
    
        return new Iterator<T>() {
            int cursor;
        
            @Override
            public boolean hasNext() {
                return cursor < columnCount;
            }
        
            @Override
            public T next() {
                return get(cursor++, column);
            }
        };
    }
    
    /**
     * 对某一行进行一些操作
     *
     * @param row    行索引
     * @param action 操作
     */
    default void forRow(int row, Consumer<T> action) {
        Preconditions.namedArgumentNonNull(action, "action");
    
        rowIterator(row).forEachRemaining(action);
    }
    
    /**
     * 对某一列进行一些操作
     *
     * @param column 列索引
     * @param action 操作
     */
    default void forColumn(int column, Consumer<T> action) {
        Preconditions.namedArgumentNonNull(action, "action");
        
        columnIterator(column).forEachRemaining(action);
    }
    
    /**
     * 获得一个不可编辑的网格
     *
     * @param grid 网格
     * @param <T>  网格内容类型
     * @return 不可编辑的网格
     */
    static <T> Grid<T> unmodifiable(Grid<T> grid) {
        return new UnmodifiableGird<>(grid);
    }
}