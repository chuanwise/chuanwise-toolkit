package cn.chuanwise.toolkit.serialize.object;

import cn.chuanwise.toolkit.serialize.serializer.object.JsonObject;
import cn.chuanwise.utility.ArrayUtility;
import cn.chuanwise.toolkit.utility.del.JsonUtility;
import cn.chuanwise.toolkit.serialize.serializer.object.DeserializedObject;
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
        final Object object = get(path);
        if (object instanceof Map) {
            return new MapDeserializedObject(((Map<String, Object>) object));
        } else if (this.getPathGetterConfiguration().isReturnNullIfFail()) {
            return null;
        } else {
            throw new NoSuchElementException("no such element: " + path);
        }
    }

    @Override
    public <T> T toObject(Class<T> clazz) {
        return JsonUtility.SERIALIZER.convert(values, clazz);
    }

    @Override
    public Object get(String path) {
        String[] paths = path.split(Pattern.quote("."));
        Object object = values;
        boolean found;

        for (int i = 0; i < paths.length; i++) {
            final String currentKey = paths[i];
            if (object instanceof Map) {
                final Map<String, Object> map = (Map) object;
                object = map.get(currentKey);

                // 一般情况下应该都 get 到了，如果是 null，还得再检查一下
                found = !Objects.isNull(object) || map.containsKey(currentKey);
            } else {
                found = false;
            }

            if (found) {
                continue;
            }

            if (this.getPathGetterConfiguration().isReturnNullIfFail()) {
                return null;
            } else {
                final String currentPath = ArrayUtility.rangeToString(paths, 0, i, ".");
                if (Objects.isNull(object)) {
                    throw new NoSuchElementException("no such element: " + currentPath + " in path: " + ArrayUtility.toString(paths, "."));
                } else {
                    throw new NoSuchElementException("element: " + currentPath + " is null, can not access the remain part in path: " + ArrayUtility.toString(paths, "."));
                }
            }
        }
        return object;
    }

    @Override
    public List<DeserializedObject> getDeserializedObjectList(String path) {
        final Object object = get(path);
        if (object instanceof List) {
            final List<DeserializedObject> result = new ArrayList<>(((List<?>) object).size());
            for (Object element : ((List<?>) object)) {
                if (element instanceof Map) {
                    result.add(new MapDeserializedObject(((Map<String, Object>) element)));
                } else if (this.getPathGetterConfiguration().isReturnNullIfFail() || Objects.isNull(element)) {
                    result.add(null);
                } else {
                    throw new IllegalArgumentException("element: " + element + " in path: " + path + " can not be regard as a serialized object");
                }
            }
            return result;
        } else if (this.getPathGetterConfiguration().isReturnNullIfFail() || Objects.isNull(object)) {
            return null;
        } else {
            throw new IllegalArgumentException("object: " + object + " in path: " + path + " can not be regard as an array of serialized object");
        }
    }
}
