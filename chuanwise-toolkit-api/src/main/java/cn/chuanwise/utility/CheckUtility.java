package cn.chuanwise.utility;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class CheckUtility extends StaticUtility {
    public static <T> T nonNull(T t, String objectName) {
        return nonNull(t, () -> objectName);
    }

    public static <T> T nonNull(T t, Supplier<String> supplier) {
        if (Objects.isNull(t)) {
            throw new NullPointerException(supplier.get() + " is null!");
        }
        return t;
    }

    public static void checkState(boolean legal, String message) {
        checkState(legal, () -> message);
    }

    public static void checkState(boolean legal, Supplier<String> supplier) {
        if (!legal) {
            throw new IllegalStateException(supplier.get());
        }
    }

    public static void checkArgument(boolean legal, String message) {
        checkArgument(legal, () -> message);
    }

    public static void checkArgument(boolean legal, Supplier<String> supplier) {
        if (!legal) {
            throw new IllegalArgumentException(supplier.get());
        }
    }

    public static int checkIndex(int index, int size, String objectName) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(objectName + " index " + index + " must be restricted in [0, " + size + ")!");
        }
        return index;
    }
}
