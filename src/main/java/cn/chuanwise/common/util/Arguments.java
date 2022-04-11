package cn.chuanwise.common.util;

/**
 * 参数工具类
 *
 * @author Chuanwise
 */
public class Arguments {
    
    /**
     * 序列化参数
     *
     * @param string      参数值
     * @param escapedChar 转义符
     * @return 序列化后的参数值
     */
    public static String serialize(String string, char escapedChar) {
        Preconditions.objectNonNull(string, "string");
    
        // 空字符串序列化为 ""
        if (string.isEmpty()) {
            return "\"\"";
        }
    
        // 不包含敏感字符时不序列化
        if (!string.contains(" ") && !string.contains("\"")) {
            return string;
        }
    
        final int length = string.length();
        final StringBuilder stringBuilder = new StringBuilder(length + 3);
    
        stringBuilder.append("\"");
        for (int i = 0; i < length; i++) {
            final char ch = string.charAt(i);
            final String serialized;
            if (ch == '\"') {
                serialized = escapedChar + "\"";
            } else {
                serialized = String.valueOf(ch);
            }
            stringBuilder.append(serialized);
        }
        stringBuilder.append("\"");
    
        return stringBuilder.toString();
    }
    
    /**
     * 序列化参数
     *
     * @param string      参数值
     * @return 序列化后的参数值
     */
    public static String serialize(String string) {
        return serialize(string, '\\');
    }
    
    /**
     * 反序列化参数
     *
     * @param string 参数值
     * @param escapedChar 转移符
     * @return 反序列化后的参数值
     */
    public static String deserialize(String string, char escapedChar) {
        Preconditions.objectNonNull(string, "string");
    
        // 空字符串反序列化为空
        if (string.isEmpty()) {
            return string;
        }
        
        boolean escaped = false;
        final int length = string.length();
        final StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            final char ch = string.charAt(i);
            
            // 如果是转义字符，直接转义
            if (escaped) {
                stringBuilder.append(ch);
                escaped = false;
            }
            if (ch == escapedChar) {
                escaped = true;
                continue;
            }
            
            stringBuilder.append(ch);
        }
        
        return stringBuilder.toString();
    }
    
    /**
     * 反序列化参数
     *
     * @param string 参数值
     * @return 反序列化后的参数值
     */
    public static String deserialize(String string) {
        return deserialize(string, '\\');
    }
    
    /**
     * 替换所有的 {}
     *
     * @param format 格式串
     * @param arguments 参数
     * @return 替换后的参数
     */
    public static String format(String format, Object... arguments) {
        Preconditions.objectNonNull(format, "format");
        Preconditions.objectNonNull(arguments, "arguments");
    
        final int length = format.length();
        if (length < 2) {
            return format;
        }
        
        final StringBuilder stringBuilder = new StringBuilder(length);
        
        int state = 0;
        final int normalState = 0;
        final int afterLeftState = 1;
        
        int argumentIndex = 0;
    
        for (int i = 0; i < length; i++) {
            final char ch = format.charAt(i);
    
            switch (state) {
                case normalState:
                    if (ch == '{') {
                        state = afterLeftState;
                    } else {
                        stringBuilder.append(ch);
                    }
                    break;
                case afterLeftState:
                    if (ch == '}') {
                        Preconditions.argument(Indexes.isLegal(argumentIndex, arguments.length), "lack argument(s)");
                        final Object argument = arguments[argumentIndex++];
                        stringBuilder.append(argument);
                    } else {
                        stringBuilder.append('{');
                        stringBuilder.append(ch);
                    }
                    state = normalState;
                    break;
                default:
                    throw new IllegalStateException();
            }
        }
    
        switch (state) {
            case normalState:
                break;
            case afterLeftState:
                stringBuilder.append('{');
                break;
            default:
                throw new IllegalStateException();
        }
        Preconditions.argument(argumentIndex == arguments.length, "to many argument(s)");
    
        return stringBuilder.toString();
    }
}