package cn.chuanwise.toolkit.functional;

import cn.chuanwise.exception.WiseRuntimeException;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FunctionalRuntimeException extends WiseRuntimeException {
    WiseFunctional functional;

    public FunctionalRuntimeException(WiseFunctional functional) {
        this.functional = functional;
    }

    public FunctionalRuntimeException(WiseFunctional functional, String message) {
        super(message);
        this.functional = functional;
    }

    public FunctionalRuntimeException(WiseFunctional functional, String message, Throwable cause) {
        super(message, cause);
        this.functional = functional;
    }

    public FunctionalRuntimeException(String message) {
        super(message);
    }

    public FunctionalRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FunctionalRuntimeException(Throwable cause) {
        super(cause);
    }
}