package com.chuanwise.utility;

import com.chuanwise.exception.IllegalOperationException;

public abstract class StaticUtility extends Utility {
    protected StaticUtility() {
        throw new IllegalOperationException("can not call the constructor of static utility class: " + getClass().getName());
    }
}
