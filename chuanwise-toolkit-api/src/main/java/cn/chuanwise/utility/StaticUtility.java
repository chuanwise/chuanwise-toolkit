package cn.chuanwise.utility;

import cn.chuanwise.exception.IllegalOperationException;

public abstract class StaticUtility extends Utility {
    protected StaticUtility() {
        throw new IllegalOperationException("can not call the constructor of the static utility class: " + getClass().getName());
    }
}
