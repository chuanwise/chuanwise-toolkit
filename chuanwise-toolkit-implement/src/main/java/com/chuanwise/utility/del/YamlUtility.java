package com.chuanwise.utility.del;

import com.chuanwise.toolkit.preservable.file.FileLoader;
import com.chuanwise.toolkit.preservable.loader.PreservableLoader;
import com.chuanwise.toolkit.preservable.file.loader.YamlFileLoader;
import com.chuanwise.toolkit.serialize.serializer.Serializer;
import com.chuanwise.toolkit.serialize.serializer.yaml.SnakeYamlSerializer;
import com.chuanwise.utility.SingleUtility;
import com.chuanwise.utility.StaticUtility;

import java.io.File;

public class YamlUtility extends StaticUtility {
    public static final Serializer SERIALIZER = new SnakeYamlSerializer();
    public static final FileLoader FILE_LOADER = new YamlFileLoader();
}
