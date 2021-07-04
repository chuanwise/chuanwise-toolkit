package com.chuanwise.toolkit.preservable.file;

import com.chuanwise.toolkit.preservable.Preservable;
import lombok.Setter;

import java.beans.Transient;
import java.io.File;

public abstract class FilePreservable implements Preservable<File> {
    @Setter
    transient File medium;

    @Override
    @Transient
    public File getMedium() {
        return medium;
    }
}