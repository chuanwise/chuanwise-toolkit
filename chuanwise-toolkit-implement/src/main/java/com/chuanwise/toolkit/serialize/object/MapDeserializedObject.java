package com.chuanwise.toolkit.serialize.object;

import com.chuanwise.toolkit.serialize.serializer.object.JsonObject;
import com.chuanwise.toolkit.serialize.serializer.object.DeserializedObject;
import com.chuanwise.utility.ArrayUtility;
import com.chuanwise.utility.del.JsonUtility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.regex.Pattern;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MapDeserializedObject extends DeserializedObjectImpl implements JsonObject {
    protected Map<String, Object> values;

    @Override
    public DeserializedObject getDeserializedObject(String path) {
        final Object object = forPath(path);
        if (object instanceof Map) {
            return new MapDeserializedObject(((Map<String, Object>) object));
        } else if (returnNullIfFail) {
            return null;
        } else {
            throw new NoSuchElementException("no such element: " + path);
        }
    }

    @Override
    public <T> T toObject(Class<T> clazz) {
        return JsonUtility.SERIALIZER.convert(values, clazz);
    }

    protected Object forPath(String[] path) {
        Object object = values;
        for (int i = 0; i < path.length; i++) {
            if (object instanceof Map) {
                object = ((Map<?, ?>) object).get(path[i]);
            } else if (returnNullIfFail) {
                return null;
            } else {
                final String currentPath = ArrayUtility.rangeToString(path, 0, i, ".");
                throw new NoSuchElementException("no such element: " + currentPath + " in path: " + ArrayUtility.toString(path, "."));
            }
        }
        return object;
    }

    protected Object forPath(String path) {
        return forPath(path.split(Pattern.quote(".")));
    }

    @Override
    public Object getObject(String path) {
        return forPath(path);
    }

    @Override
    public List<DeserializedObject> getDeserializedObjectList(String path) {
        final Object object = getObject(path);
        if (object instanceof List) {
            final List<DeserializedObject> result = new ArrayList<>(((List<?>) object).size());
            for (Object element : ((List<?>) object)) {
                if (element instanceof Map) {
                    result.add(new MapDeserializedObject(((Map<String, Object>) element)));
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
}
