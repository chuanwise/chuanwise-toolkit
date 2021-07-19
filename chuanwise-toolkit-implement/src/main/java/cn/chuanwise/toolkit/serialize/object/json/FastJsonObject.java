package cn.chuanwise.toolkit.serialize.object.json;

import cn.chuanwise.toolkit.serialize.serializer.object.JsonObject;
import com.alibaba.fastjson.JSONObject;
import cn.chuanwise.toolkit.serialize.serializer.object.DeserializedObject;
import cn.chuanwise.toolkit.serialize.object.DeserializedObjectImpl;
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
        } else if (this.getPathGetterConfiguration().isReturnNullIfFail()) {
            return null;
        } else {
            throw new NoSuchElementException("no element: " + path + " in json object: " + values);
        }
    }

    @Override
    public List<DeserializedObject> getDeserializedObjectList(String path) {
        final Object object = get(path);
        if (object instanceof List) {
            final List<DeserializedObject> result = new ArrayList<>(((List<?>) object).size());
            for (Object element : ((List<?>) object)) {
                if (element instanceof JSONObject) {
                    result.add(new FastJsonObject(((JSONObject) element)));
                } else if (getPathGetterConfiguration().isReturnNullIfFail() || Objects.isNull(element)) {
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

    @Override
    public Object get(String path) {
        return values.get(path);
    }

    @Override
    public <T> T toObject(Class<T> clazz) {
        return values.toJavaObject(clazz);
    }
}
