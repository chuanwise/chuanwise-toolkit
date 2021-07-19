package cn.chuanwise.toolkit.serialize.object;

import cn.chuanwise.toolkit.map.getter.PathGetterConfiguration;
import cn.chuanwise.toolkit.map.getter.SimplePathGetterConfiguration;
import cn.chuanwise.toolkit.serialize.serializer.object.DeserializedObject;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public abstract class DeserializedObjectImpl implements DeserializedObject {
    PathGetterConfiguration pathGetterConfiguration = new SimplePathGetterConfiguration();

    @Override
    public String toString() {
        return Objects.toString(getValues());
    }
}
