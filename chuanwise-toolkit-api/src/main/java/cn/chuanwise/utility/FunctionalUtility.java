package cn.chuanwise.utility;

import cn.chuanwise.toolkit.functional.ThrowableSupplier;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalUtility extends StaticUtility {
    public static void runIfNonNull(Runnable runnable) {
        if (Objects.nonNull(runnable)) {
            runnable.run();
        }
    }

    public static <T> void runIfNonNull(Consumer<T> consumer, T argument) {
        if (Objects.nonNull(consumer)) {
            consumer.accept(argument);
        }
    }

    public static <T> T runIfNonNull(Supplier<T> supplier, T defaultValue) {
        if (Objects.nonNull(supplier)) {
            return supplier.get();
        } else {
            return defaultValue;
        }
    }

    public static <T> T returnOrDefault(Supplier<T> supplier, Predicate<T> replaceJudger, T defaultValue) {
        return returnOrSupplie(supplier, replaceJudger, () -> defaultValue);
    }

    public static <T> T returnOrSupplie(Supplier<T> supplier, Predicate<T> replaceJudger, Supplier<T> defaultValueSupplier) {
        T t = null;
        try {
            t = supplier.get();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        if (replaceJudger.test(t)) {
            t = defaultValueSupplier.get();
        }
        return t;
    }
}
