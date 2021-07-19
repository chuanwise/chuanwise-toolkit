package cn.chuanwise.toolkit.serialize.serializer.yaml;

import cn.chuanwise.toolkit.serialize.object.MapDeserializedObject;
import cn.chuanwise.toolkit.serialize.serializer.object.DeserializedObject;
import lombok.Data;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;

import java.io.StringWriter;
import java.util.Map;
import java.util.Objects;

@Data
public class SnakeYamlSerializer implements YamlSerializer {
    ClassLoader classLoader = getClass().getClassLoader();
    Yaml yaml = new Yaml();

    @Override
    public String serialize(Object object) {
        final StringWriter output = new StringWriter();
        yaml.dump(object, output);
        return output.getBuffer().toString();
    }

    @Override
    public <T> T deserialize(String string, Class<T> clazz) {
        return new Yaml(new Constructor(clazz)).loadAs(string, clazz);
    }

    @Override
    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
        yaml = new Yaml(new CustomClassLoaderConstructor(classLoader));
    }

    @Override
    public DeserializedObject deserialize(String string) {
        final Map<String, Object> values = yaml.loadAs(string, Map.class);
        return Objects.nonNull(values) ? new MapDeserializedObject(values) : null;
    }
}