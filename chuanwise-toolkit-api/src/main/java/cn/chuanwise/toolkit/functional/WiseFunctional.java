package cn.chuanwise.toolkit.functional;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public interface WiseFunctional {
    static <T extends Throwable, R> ThrowableSupplier<T, R> asThrowable(Supplier<R> supplier) {
        return supplier::get;
    }

    static <T extends Throwable, P, R> ThrowableFunction<T, P, R> asThrowable(Function<P, R> function) {
        return function::apply;
    }

    static <T extends Throwable, P, R> ThrowableConsumer<T, P> asThrowable(Consumer<P> consumer) {
        return consumer::accept;
    }

    static <T extends Throwable, P> ThrowablePredicate<T, P> asThrowable(Predicate<P> predicate) {
        return predicate::test;
    }

    static <T extends Throwable, R> Supplier<R> asNormal(Class<T> throwableClass, ThrowableSupplier<T, R> throwableSupplier) {
        return throwableSupplier::get;
    }

    static <T extends Throwable, P> Consumer<P> asNormal(Class<T> throwableClass, ThrowableConsumer<T, P> throwableConsumer) {
        return throwableConsumer::accept;
    }

    static <T extends Throwable, P, R> Function<P, R> asNormal(Class<T> throwableClass, ThrowableFunction<T, P, R> throwableFunction) {
        return throwableFunction::apply;
    }

    static <T extends Throwable, P> Predicate<P> asNormal(Class<T> throwableClass, ThrowablePredicate<T, P> throwablePredicate) {
        return throwablePredicate::test;
    }
}