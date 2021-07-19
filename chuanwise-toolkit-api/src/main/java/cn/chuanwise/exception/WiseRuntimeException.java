package cn.chuanwise.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class WiseRuntimeException extends RuntimeException {
    public WiseRuntimeException(Throwable cause) {
        super(cause);
    }

    public WiseRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public WiseRuntimeException(String message) {
        super(message);
    }
}
