package com.chuanwise.toolkit.preservable.file.loader;

import com.chuanwise.toolkit.preservable.Preservable;
import com.chuanwise.toolkit.preservable.file.FileLoader;
import com.chuanwise.utility.del.YamlUtility;

import java.io.File;
import java.io.IOException;

public class YamlFileLoader implements FileLoader {
    @Override
    public <T extends Preservable<File>> T load(Class<T> clazz, File medium) throws IOException {
        return load(YamlUtility.SERIALIZER, clazz, medium);
    }
}
