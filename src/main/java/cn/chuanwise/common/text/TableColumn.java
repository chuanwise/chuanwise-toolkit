package cn.chuanwise.common.text;

import cn.chuanwise.common.util.Preconditions;
import lombok.Data;

import java.util.Objects;

/**
 * 表示列表中的列
 *
 * @author Chuanwise
 */
@Data
public class TableColumn<T> {
    
    /**
     * 对齐方式
     */
    private Alignment alignment = Alignment.LEFT;
    
    /**
     * 列名
     */
    private String name;
    
    /**
     * 显示前，对对象进行操作
     */
    private ElementFormat<T> format = Objects::toString;
    
    /**
     * 构造指定列名的列
     *
     * @param name 列名
     */
    public TableColumn(String name) {
        Preconditions.objectNonNull(name, "name");
        
        this.name = name;
    }
    
    /**
     * 构造指定列名和格式的列
     *
     * @param name   列名
     * @param format 格式
     */
    public TableColumn(String name, ElementFormat<T> format) {
        Preconditions.objectNonNull(name, "name");
        Preconditions.objectNonNull(format, "format");
        
        this.name = name;
        this.format = format;
    }
    
    /**
     * 构造指定列名和对齐方式的列
     *
     * @param name      列名
     * @param alignment 对齐方式
     */
    public TableColumn(String name, Alignment alignment) {
        Preconditions.objectNonNull(name, "name");
        Preconditions.objectNonNull(alignment, "alignment");
    
        this.name = name;
        this.alignment = alignment;
    }
    
    /**
     * 构造指定列名、格式和对齐方式的列
     *
     * @param name      列名
     * @param format    格式
     * @param alignment 对齐方式
     */
    public TableColumn(String name, ElementFormat<T> format, Alignment alignment) {
        Preconditions.objectNonNull(name, "name");
        Preconditions.objectNonNull(format, "format");
        Preconditions.objectNonNull(alignment, "alignment");
    
        this.name = name;
        this.format = format;
        this.alignment = alignment;
    }
}