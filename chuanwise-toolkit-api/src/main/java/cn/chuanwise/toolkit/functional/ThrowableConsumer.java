package cn.chuanwise.toolkit.functional;

import java.util.function.Consumer;

public interface ThrowableConsumer<T extends Throwable, R> extends WiseFunctional, Consumer<R> {
    void throwableAccept(R argument) throws T;

    @Override
    default void accept(R r) {
        try {
            throwableAccept(r);
        } catch (Throwable throwable) {
            throw new FunctionalRuntimeException(throwable);
        }
    }
}