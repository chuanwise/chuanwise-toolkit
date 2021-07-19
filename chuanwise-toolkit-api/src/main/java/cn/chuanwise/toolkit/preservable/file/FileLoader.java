package cn.chuanwise.toolkit.preservable.file;

import cn.chuanwise.toolkit.preservable.Preservable;
import cn.chuanwise.toolkit.preservable.PreservableLoaderImpl;
import cn.chuanwise.toolkit.serialize.serializer.Serializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public abstract class FileLoader extends PreservableLoaderImpl<File> {
    public FileLoader(Serializer defaultSerializer) {
        super(defaultSerializer);
    }

    @Override
    public <T extends Preservable<File>> T load(Serializer serializer, Class<T> clazz, File medium) throws IOException {
        if (!medium.isFile()) {
            return null;
        }
        T deserialize = null;
        try (InputStream inputStream = new FileInputStream(medium)) {
            deserialize = serializer.deserialize(inputStream, clazz);
        } finally {
            if (Objects.nonNull(deserialize)) {
                deserialize.setSerializer(serializer);
                deserialize.setMedium(medium);
            }
        }
        return deserialize;
    }
}
