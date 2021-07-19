package cn.chuanwise.toolkit.preservable.file.loader;

import cn.chuanwise.toolkit.preservable.file.FileLoader;
import cn.chuanwise.toolkit.serialize.serializer.json.JsonSerializer;
import cn.chuanwise.toolkit.utility.del.JsonUtility;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonFileLoader extends FileLoader {
    public JsonFileLoader(JsonSerializer defaultSerializer) {
        super(defaultSerializer);
    }

    public JsonFileLoader() {
        this(((JsonSerializer) JsonUtility.SERIALIZER));
    }
}