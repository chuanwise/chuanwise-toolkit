package cn.chuanwise.common.text;

import cn.chuanwise.common.util.Preconditions;
import cn.chuanwise.common.util.Strings;
import lombok.Data;

import java.util.*;

/**
 * 用于显示某种列表的表格
 *
 * @param <T> 表格元素
 */
@Data
public class TableView<T> {
    
    /**
     * 表中的列
     */
    protected final List<TableColumn<T>> columns = new ArrayList<>();
    
    /**
     * 将一个集合中的元素显示为列表
     *
     * @param collection 集合
     * @return 列表
     */
    public String display(Collection<T> collection) {
        Preconditions.objectNonNull(collection, "collection");
    
        // 如果没有任何列，则返回空
        if (columns.isEmpty()) {
            return "";
        }
        
        final StringBuilder stringBuilder = new StringBuilder();
        
        // 列最大款
        final int[] widths = new int[columns.size()];
        
        // 准备字符串缓冲区
        final String[][] strings = new String[collection.size()][columns.size()];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = new String[columns.size()];
        }
        
        // 设置列宽
        for (int i = 0; i < columns.size(); i++) {
            final TableColumn<T> column = columns.get(i);
    
            final String name = column.getName();
            widths[i] = Strings.width(name);
        }
        
        // 准备表格元素
        int elementIndex = 0;
        for (T element : collection) {
    
            for (int i = 0; i < columns.size(); i++) {
                final TableColumn<T> column = columns.get(i);
    
                final String string = Objects.toString(column.getFormat().format(element));
                strings[elementIndex][i] = string;
                widths[i] = Math.max(widths[i], Strings.width(string));
            }
            
            elementIndex++;
        }
    
        // 构造列名开头
        stringBuilder.append('|');
        for (int i = 0; i < columns.size(); i++) {
            final TableColumn<T> column = columns.get(i);
            final String text = align(column.getAlignment(), column.getName(), widths[i]);
            
            stringBuilder.append(" ").append(text).append(" |");
        }
        stringBuilder.append('\n');
    
        // 构造分隔符
        stringBuilder.append('|');
        for (int i = 0; i < columns.size(); i++) {
            final TableColumn<T> column = columns.get(i);
            final String text;
            switch (column.getAlignment()) {
                case LEFT:
                    text = ":" + Strings.repeat('-', widths[i]) + '-';
                    break;
                case RIGHT:
                    text = "-" + Strings.repeat('-', widths[i]) + ':';
                    break;
                case CENTER:
                    text = ":" + Strings.repeat('-', widths[i]) + ':';
                    break;
                default:
                    throw new NoSuchElementException();
            }
            
            stringBuilder.append(text).append('|');
        }
        stringBuilder.append('\n');
        
        // 填充表元素
        for (int i = 0; i < strings.length; i++) {
            final String[] elementColumns = strings[i];
    
            stringBuilder.append('|');
            for (int j = 0; j < elementColumns.length; j++) {
                final TableColumn<T> column = columns.get(j);
                stringBuilder.append(' ').append(align(column.getAlignment(), strings[i][j], widths[j])).append(' ').append('|');
            }
    
            stringBuilder.append('\n');
        }
        
        return stringBuilder.toString();
    }
    
    private String align(Alignment alignment, String element, int maxWidth) {
        final int width = Strings.width(element);
        final int distance = maxWidth - width;
    
        switch (alignment) {
            case LEFT:
                return element + Strings.repeat(' ', distance);
            case RIGHT:
                return Strings.repeat(' ', distance) + element;
            case CENTER:
                final String halfSpaces = Strings.repeat(' ', distance / 2);
                if ((distance & 1) == 1) {
                    return halfSpaces + element + halfSpaces + ' ';
                } else {
                    return halfSpaces + element + halfSpaces;
                }
            default:
                throw new NoSuchElementException();
        }
    }
}
