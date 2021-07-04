package com.chuanwise.toolkit.serialize.serializer.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chuanwise.toolkit.serialize.object.json.FastJsonObject;
import com.chuanwise.toolkit.serialize.serializer.object.DeserializedObject;
import lombok.Data;

import java.util.Objects;

@Data
public class FastJsonSerializer implements JsonSerializer {
    ClassLoader classLoader = getClass().getClassLoader();

    @Override
    public String serialize(Object object) {
        return JSON.toJSONString(object);
    }

    @Override
    public <T> T deserialize(String string, Class<T> clazz) {
        return JSON.parseObject(string, clazz);
    }

    @Override
    public DeserializedObject deserialize(String string) {
        final JSONObject values = JSON.parseObject(string);
        return Objects.nonNull(values) ? new FastJsonObject(values) : null;
    }

    @Override
    public void setClassLoader(ClassLoader classLoader) {
    }
}
