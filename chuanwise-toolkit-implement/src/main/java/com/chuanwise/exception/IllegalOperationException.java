package com.chuanwise.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class IllegalOperationException extends ChuanwiseRuntimeException {
    public IllegalOperationException(Throwable cause) {
        super(cause);
    }

    public IllegalOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalOperationException(String message) {
        super(message);
    }
}
