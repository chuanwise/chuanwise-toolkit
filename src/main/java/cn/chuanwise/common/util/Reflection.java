package cn.chuanwise.common.util;

import lombok.Data;

/**
 * 反射操作类
 *
 * @author Chuanwise
 * @param <T> 目标类型
 */
@Data
public class Reflection<T> {
    
    protected final T value;
    
    public Reflection(T value) {
        Preconditions.objectNonNull(value, "value");
        
        this.value = value;
    }
    
    /**
     * 获取可访问属性值
     *
     * @param fieldName 属性名
     * @return 当找不到该属性时返回 null，否则返回属性值
     */
    public Object getAccessibleField(String fieldName) {
        Preconditions.objectArgumentNonEmpty(fieldName, "field name");
    
        return Reflections.getAccessibleFieldValue(value, fieldName);
    }
    
    /**
     * 设置可访问属性值
     *
     * @param fieldName 属性名
     * @return 当找不到该属性时返回 false
     */
    public boolean setAccessibleField(String fieldName, Object value) {
        Preconditions.objectArgumentNonEmpty(fieldName, "field name");
    
        return Reflections.setAccessibleFieldValue(this.value, fieldName, value);
    }
    
    /**
     * 获取存在的属性值
     *
     * @param fieldName 属性名
     * @return 当找不到该属性时返回 null，否则返回属性值
     */
    public Object getExistField(String fieldName) {
        Preconditions.objectArgumentNonEmpty(fieldName, "field name");
    
        return Reflections.getExistFieldValue(value, fieldName);
    }
    
    /**
     * 设置存在的属性值
     *
     * @param fieldName 属性名
     * @return 当找不到该属性时返回 false
     */
    public boolean setExistField(String fieldName, Object value) {
        Preconditions.objectArgumentNonEmpty(fieldName, "field name");
    
        return Reflections.setExistFieldValue(this.value, fieldName, value);
    }
    
    /**
     * 获取定义的属性值
     *
     * @param fieldName 属性名
     * @return 当找不到该属性时返回 null，否则返回属性值
     */
    public Object getDeclaredField(String fieldName) {
        Preconditions.objectArgumentNonEmpty(fieldName, "field name");
    
        return Reflections.getDeclaredFieldValue(value, fieldName);
    }
    
    /**
     * 设置定义的属性值
     *
     * @param fieldName 属性名
     * @return 当找不到该属性时返回 false
     */
    public boolean setDeclaredField(String fieldName, Object value) {
        Preconditions.objectArgumentNonEmpty(fieldName, "field name");
    
        return Reflections.setDeclaredFieldValue(this.value, fieldName, value);
    }
}
