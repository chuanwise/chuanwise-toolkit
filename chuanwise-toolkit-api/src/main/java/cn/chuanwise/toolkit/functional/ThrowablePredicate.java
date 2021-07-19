package cn.chuanwise.toolkit.functional;

import java.util.function.Predicate;

public interface ThrowablePredicate<T extends Throwable, P> extends WiseFunctional, Predicate<P> {
    boolean throwableTest(P parameter) throws T;

    @Override
    default boolean test(P p) {
        try {
            return throwableTest(p);
        } catch (Throwable throwable) {
            throw new FunctionalRuntimeException(throwable);
        }
    }
}