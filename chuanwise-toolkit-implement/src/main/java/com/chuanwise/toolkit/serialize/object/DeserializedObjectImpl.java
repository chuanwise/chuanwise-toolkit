package com.chuanwise.toolkit.serialize.object;

import com.chuanwise.toolkit.serialize.serializer.object.DeserializedObject;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public abstract class DeserializedObjectImpl implements DeserializedObject {
    protected boolean returnNullIfFail = false;

    @Override
    public String toString() {
        return Objects.toString(getValues());
    }
}
