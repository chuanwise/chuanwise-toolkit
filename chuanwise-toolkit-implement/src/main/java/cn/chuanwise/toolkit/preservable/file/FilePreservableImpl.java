package cn.chuanwise.toolkit.preservable.file;

import cn.chuanwise.toolkit.utility.del.JsonUtility;
import cn.chuanwise.toolkit.serialize.serializer.Serializer;
import lombok.Getter;
import lombok.Setter;

import java.beans.Transient;
import java.io.File;

@Setter
@Getter
public abstract class FilePreservableImpl implements FilePreservable {
    transient File medium;

    transient Serializer serializer = JsonUtility.SERIALIZER;

    @Override
    @Transient
    public File getMedium() {
        return medium;
    }

    @Override
    @Transient
    public Serializer getSerializer() {
        return serializer;
    }
}