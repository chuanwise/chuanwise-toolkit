package cn.chuanwise.toolkit.utility.del;

import cn.chuanwise.utility.StaticUtility;
import cn.chuanwise.toolkit.preservable.file.FileLoader;
import cn.chuanwise.toolkit.preservable.file.loader.YamlFileLoader;
import cn.chuanwise.toolkit.serialize.serializer.Serializer;
import cn.chuanwise.toolkit.serialize.serializer.yaml.SnakeYamlSerializer;

public class YamlUtility extends StaticUtility {
    public static final Serializer SERIALIZER = new SnakeYamlSerializer();

    public static final FileLoader FILE_LOADER = new YamlFileLoader();
}
