package cn.chuanwise.toolkit.grid;

import cn.chuanwise.utility.CheckUtility;
import cn.chuanwise.utility.IndexUtility;

import java.util.Objects;
import java.util.function.Supplier;

public interface Grid<T> {
    int getColumnNumber();

    int getRowNumber();

    default int getSize() {
        return getColumnNumber() * getRowNumber();
    }

    void set(int index, T t);

    default void remove(int index) {
        set(index, null);
    }

    default void remove(int x, int y) {
        remove(IndexUtility.twoDimensionToOneDimension(x, y, getColumnNumber(), getRowNumber()));
    }

    default boolean containsIndex(int index) {
        return Objects.nonNull(get(index));
    }

    default boolean containsIndex(int x, int y) {
        return Objects.nonNull(get(x, y));
    }

    T get(int index);

    default T get(int row, int column) {
        return get(IndexUtility.twoDimensionToOneDimension(row, column, getColumnNumber(), getRowNumber()));
    }

    default T getOrDefault(int index, T defaultValue) {
        return getOrSupplie(index, () -> defaultValue);
    }

    default T getOrSupplie(int index, Supplier<T> supplier) {
        if (containsIndex(index)) {
            return get(index);
        } else {
            return supplier.get();
        }
    }

    default void set(int x, int y, T t) {
        set(IndexUtility.twoDimensionToOneDimension(x, y, getColumnNumber(), getRowNumber()), t);
    }
}
