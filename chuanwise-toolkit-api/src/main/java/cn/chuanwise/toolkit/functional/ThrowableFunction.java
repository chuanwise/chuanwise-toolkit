package cn.chuanwise.toolkit.functional;

import java.util.function.Function;

public interface ThrowableFunction<T extends Throwable, P, R> extends WiseFunctional, Function<P, R> {
    R throwableApply(P parameter) throws T;

    @Override
    default R apply(P p) {
        try {
            return throwableApply(p);
        } catch (Throwable throwable) {
            throw new FunctionalRuntimeException(throwable);
        }
    }
}
