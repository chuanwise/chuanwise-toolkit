package cn.chuanwise.toolkit.serialize.serializer.json;

import cn.chuanwise.toolkit.serialize.exception.SerializerException;
import cn.chuanwise.toolkit.serialize.object.MapDeserializedObject;
import cn.chuanwise.toolkit.serialize.serializer.object.DeserializedObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.Data;

import java.util.Map;

@Data
public class JackJsonSerializer implements JsonSerializer {
    ClassLoader classLoader = getClass().getClassLoader();

    final ObjectMapper objectMapper;

    public JackJsonSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public JackJsonSerializer() {
        this(new ObjectMapper());
    }

    @Override
    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
        objectMapper.setTypeFactory(TypeFactory.defaultInstance().withClassLoader(classLoader));
    }

    @Override
    public String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException exception) {
            throw new SerializerException(exception);
        }
    }

    @Override
    public <T> T deserialize(String string, Class<T> clazz) {
        try {
            return objectMapper.readValue(string, clazz);
        } catch (JsonProcessingException exception) {
            throw new SerializerException(exception);
        }
    }

    @Override
    public DeserializedObject deserialize(String string) {
        return new MapDeserializedObject(deserialize(string, Map.class));
    }
}
