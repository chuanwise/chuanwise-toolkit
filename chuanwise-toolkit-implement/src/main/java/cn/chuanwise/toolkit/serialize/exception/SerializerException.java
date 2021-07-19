package cn.chuanwise.toolkit.serialize.exception;

import cn.chuanwise.exception.WiseRuntimeException;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SerializerException extends WiseRuntimeException {
    public SerializerException(Throwable cause) {
        super(cause);
    }

    public SerializerException(String message, Throwable cause) {
        super(message, cause);
    }

    public SerializerException(String message) {
        super(message);
    }
}