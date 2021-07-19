package cn.chuanwise.toolkit.preservable.file.loader;

import cn.chuanwise.toolkit.preservable.file.FileLoader;
import cn.chuanwise.toolkit.utility.del.YamlUtility;
import cn.chuanwise.toolkit.serialize.serializer.yaml.YamlSerializer;

public class YamlFileLoader extends FileLoader {
    public YamlFileLoader(YamlSerializer defaultSerializer) {
        super(defaultSerializer);
    }

    public YamlFileLoader() {
        this(((YamlSerializer) YamlUtility.SERIALIZER));
    }
}
