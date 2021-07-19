package cn.chuanwise.toolkit.functional;

import java.util.function.Supplier;

public interface ThrowableSupplier<T extends Throwable, R> extends WiseFunctional, Supplier<R> {
    R throwableGet() throws T;

    @Override
    default R get() {
        try {
            return throwableGet();
        } catch (Throwable throwable) {
            throw new FunctionalRuntimeException(throwable);
        }
    }
}
