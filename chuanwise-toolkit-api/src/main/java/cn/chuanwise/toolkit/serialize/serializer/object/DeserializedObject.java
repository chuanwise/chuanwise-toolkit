package cn.chuanwise.toolkit.serialize.serializer.object;

import cn.chuanwise.toolkit.map.getter.MultipleTypePathGetter;

import java.util.List;
import java.util.Objects;

public interface DeserializedObject extends MultipleTypePathGetter {
    DeserializedObject getDeserializedObject(String path);

    default DeserializedObject[] getDeserializedObjectArray(String path) {
        final List<DeserializedObject> list = getDeserializedObjectList(path);
        return (Objects.isNull(list) && getPathGetterConfiguration().isReturnNullIfFail()) ? null : list.toArray(new DeserializedObject[0]);
    }

    List<DeserializedObject> getDeserializedObjectList(String path);

    Object getValues();

    <T> T toObject(Class<T> clazz);
}