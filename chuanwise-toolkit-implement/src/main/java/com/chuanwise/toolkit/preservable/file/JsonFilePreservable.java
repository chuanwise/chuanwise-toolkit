package com.chuanwise.toolkit.preservable.file;

import com.chuanwise.utility.del.JsonUtility;
import lombok.Data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

/**
 * 可以通过 json 文件保存的数据
 * @author Chuanwise
 */
@Data
public class JsonFilePreservable extends FilePreservable {
    @Override
    public boolean saveToThrowsException(File file) throws IOException {
        // 不是文件且创建失败时返回 false
        if (!file.isFile() && !file.createNewFile()) {
            return false;
        }
        // 写入本对象的 json 数据
        try (OutputStream outputStream = new FileOutputStream(file)) {
            JsonUtility.SERIALIZER.serialize(this, outputStream);
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JsonFilePreservable)) {
            return false;
        }
        JsonFilePreservable that = (JsonFilePreservable) o;
        return Objects.equals(medium, that.medium);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medium);
    }
}