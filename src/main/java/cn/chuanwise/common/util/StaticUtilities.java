package cn.chuanwise.common.util;

/**
 * 静态工具类的父类
 *
 * @author Chuanwise
 */
public abstract class StaticUtilities
    extends Utilities {
    
    protected StaticUtilities() {
        throw new UnsupportedOperationException("can not call the constructor of the static util class: " + getClass().getName());
    }
}
