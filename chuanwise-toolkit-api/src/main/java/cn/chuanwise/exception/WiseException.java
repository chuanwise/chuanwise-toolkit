package cn.chuanwise.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class WiseException extends Exception {
    public WiseException(Throwable cause) {
        super(cause);
    }

    public WiseException(String message, Throwable cause) {
        super(message, cause);
    }

    public WiseException(String message) {
        super(message);
    }
}
