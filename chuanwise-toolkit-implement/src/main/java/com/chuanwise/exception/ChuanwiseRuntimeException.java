package com.chuanwise.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ChuanwiseRuntimeException extends RuntimeException {
    public ChuanwiseRuntimeException(Throwable cause) {
        super(cause);
    }

    public ChuanwiseRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChuanwiseRuntimeException(String message) {
        super(message);
    }
}
