package cn.chuanwise.toolkit.preservable.file;

import cn.chuanwise.toolkit.preservable.Preservable;
import cn.chuanwise.toolkit.serialize.serializer.Serializer;

import java.io.File;
import java.io.IOException;

public interface FilePreservable extends Preservable<File> {
    @Override
    default void save(Serializer serializer, File file, boolean replace) throws IOException {
        serializer.serialize(this, file, replace);
    }
}
