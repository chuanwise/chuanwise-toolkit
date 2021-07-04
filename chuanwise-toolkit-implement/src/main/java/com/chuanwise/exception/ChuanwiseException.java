package com.chuanwise.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ChuanwiseException extends Exception {
    public ChuanwiseException(Throwable cause) {
        super(cause);
    }

    public ChuanwiseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChuanwiseException(String message) {
        super(message);
    }
}
