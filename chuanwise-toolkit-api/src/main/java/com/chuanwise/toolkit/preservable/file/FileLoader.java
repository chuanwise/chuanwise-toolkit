package com.chuanwise.toolkit.preservable.file;

import com.chuanwise.toolkit.preservable.Preservable;
import com.chuanwise.toolkit.preservable.loader.PreservableLoader;
import com.chuanwise.toolkit.serialize.serializer.Serializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public interface FileLoader extends PreservableLoader<File> {
    @Override
    default  <T extends Preservable<File>> T load(Serializer serializer, Class<T> clazz, File medium) throws IOException {
        try (InputStream inputStream = new FileInputStream(medium)) {
            return serializer.deserialize(inputStream, clazz);
        }
    }
}
