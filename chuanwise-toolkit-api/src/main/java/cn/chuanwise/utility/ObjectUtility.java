package cn.chuanwise.utility;

import cn.chuanwise.toolkit.enumerate.WeekReason;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ObjectUtility extends StaticUtility {
    /**
     * 令当前线程在指定的对象上等待
     * @param object 指定的对象
     * @param timeout 最长等待时长
     * @return 被唤醒的方式
     */
    public static WeekReason wait(Object object, long timeout) {
        CheckUtility.checkArgument(timeout > 0, "timeout value must bigger than or equal to 0!");

        final long latestTime = System.currentTimeMillis() + timeout;
        synchronized (object) {
            try {
                object.wait(timeout);
                if (latestTime >= System.currentTimeMillis() && timeout > 0) {
                    return WeekReason.TIMEOUT;
                } else {
                    return WeekReason.NOTIFY;
                }
            } catch (InterruptedException exception) {
                return WeekReason.INTERRUPT;
            }
        }
    }

    /**
     * 令当前线程在指定的对象上等待，直到天荒地老
     * @param object 指定的对象
     * @return 被唤醒的方式
     */
    public static WeekReason wait(Object object) {
        return wait(object, 0);
    }

    public static <T> T getOrDefault(T t, boolean replace, T defaultValue) {
        return replace ? t : defaultValue;
    }

    public static <T> T getOrSupplie(T t, boolean replace, Supplier<T> defaultValueSupplier) {
        return replace ? t : defaultValueSupplier.get();
    }

    public static <T> T getOrDefault(T t, T defaultValue) {
        return Objects.isNull(t) ? defaultValue : t;
    }

    public static <T> T getOrDefault(T t, Supplier<T> defaultValueSupplier) {
        return Objects.isNull(t) ? defaultValueSupplier.get() : t;
    }

    public static <T> T chooseFirstOrDefault(Predicate<T> chooser, T defaultValue, T... array) {
        return chooseFirstOrSupplie(chooser, () -> defaultValue, array);
    }

    public static <T> T chooseFirstNonNullOrDefault(T defaultValue, T... array) {
        return chooseFirstOrDefault(Objects::nonNull, defaultValue, array);
    }

    public static <T> T chooseFirstOrSupplie(Predicate<T> chooser, Supplier<T> defaultValueSupplier, T... array) {
        for (T t : array) {
            if (chooser.test(t)) {
                return t;
            }
        }
        return defaultValueSupplier.get();
    }

    public static <T> T chooseFirstNonNullOrSupplie(Supplier<T> defaultValueSupplier, T... array) {
        return chooseFirstOrSupplie(Objects::nonNull, defaultValueSupplier, array);
    }
}