package com.chuanwise.toolkit.preservable.file.loader;

import com.chuanwise.toolkit.preservable.Preservable;
import com.chuanwise.toolkit.preservable.file.FileLoader;
import com.chuanwise.utility.del.JsonUtility;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;

@Getter
@Setter
public class JsonFileLoader implements FileLoader {
    @Override
    public <T extends Preservable<File>> T load(Class<T> clazz, File medium) throws IOException {
        return load(JsonUtility.SERIALIZER, clazz, medium);
    }
}
