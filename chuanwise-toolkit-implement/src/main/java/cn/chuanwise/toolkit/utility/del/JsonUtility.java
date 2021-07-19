package cn.chuanwise.toolkit.utility.del;

import cn.chuanwise.toolkit.preservable.file.loader.JsonFileLoader;
import cn.chuanwise.toolkit.serialize.serializer.json.JackJsonSerializer;
import cn.chuanwise.utility.StaticUtility;
import cn.chuanwise.toolkit.preservable.file.FileLoader;
import cn.chuanwise.toolkit.serialize.serializer.Serializer;

public class JsonUtility extends StaticUtility {
    public static final Serializer SERIALIZER = new JackJsonSerializer();

    public static final FileLoader FILE_LOADER = new JsonFileLoader();
}