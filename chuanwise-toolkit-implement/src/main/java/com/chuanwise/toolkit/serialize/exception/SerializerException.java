package com.chuanwise.toolkit.serialize.exception;

import com.chuanwise.exception.ChuanwiseRuntimeException;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SerializerException extends ChuanwiseRuntimeException {
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