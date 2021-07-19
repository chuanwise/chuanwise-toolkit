package cn.chuanwise.toolkit.functional;

public interface ThrowableRunnable<T extends Throwable> extends WiseFunctional, Runnable {
    void throwableRun() throws T;

    @Override
    default void run() {
        try {
            throwableRun();
        } catch (Throwable throwable) {
            throw new FunctionalRuntimeException(throwable);
        }
    }
}
