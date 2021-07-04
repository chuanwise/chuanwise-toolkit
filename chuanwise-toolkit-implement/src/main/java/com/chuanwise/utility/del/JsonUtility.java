package com.chuanwise.utility.del;

import com.chuanwise.toolkit.preservable.file.FileLoader;
import com.chuanwise.toolkit.preservable.loader.PreservableLoader;
import com.chuanwise.toolkit.preservable.file.loader.JsonFileLoader;
import com.chuanwise.toolkit.serialize.serializer.json.JackJsonSerializer;
import com.chuanwise.toolkit.serialize.serializer.Serializer;
import com.chuanwise.utility.StaticUtility;

import java.io.File;

public class JsonUtility extends StaticUtility {
    public static final Serializer SERIALIZER = new JackJsonSerializer();

    public static final FileLoader FILE_LOADER = new JsonFileLoader();
}