package com.chuanwise.toolkit.serialize.object.json;

import com.alibaba.fastjson.JSONObject;
import com.chuanwise.toolkit.serialize.serializer.object.JsonObject;
import com.chuanwise.toolkit.serialize.serializer.object.DeserializedObject;
import com.chuanwise.toolkit.serialize.object.DeserializedObjectImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class FastJsonObject extends DeserializedObjectImpl implements JsonObject {
    final JSONObject values;

    @Override
    public DeserializedObject getDeserializedObject(String path) {
        final Object o = values.get(path);
        if (o instanceof JSONObject) {
            return new FastJsonObject(((JSONObject) o));
        } else if (returnNullIfFail) {
            return null;
        } else {
            throw new NoSuchElementException("no element: " + path + " in json object: " + values);
        }
    }

    @Override
    public List<DeserializedObject> getDeserializedObjectList(String path) {
        final Object object = getObject(path);
        if (object instanceof List) {
            final List<DeserializedObject> result = new ArrayList<>(((List<?>) object).size());
            for (Object element : ((List<?>) object)) {
                if (element instanceof JSONObject) {
                    result.add(new FastJsonObject(((JSONObject) element)));
                } else if (returnNullIfFail || Objects.isNull(element)) {
                    result.add(null);
                } else {
                    throw new IllegalArgumentException("element: " + element + " in path: " + path + " can not be regard as a serialized object");
                }
            }
            return result;
        } else if (returnNullIfFail || Objects.isNull(object)) {
            return null;
        } else {
            throw new IllegalArgumentException("object: " + object + " in path: " + path + " can not be regard as an array of serialized object");
        }
    }

    @Override
    public Object getObject(String path) {
        return values.get(path);
    }

    @Override
    public <T> T toObject(Class<T> clazz) {
        return values.toJavaObject(clazz);
    }
}
