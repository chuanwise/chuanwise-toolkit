package cn.chuanwise.common.util;


import java.util.Objects;
import java.util.function.Function;

/**
 * 用于拼接字符串的 joiner
 *
 * @author Chuanwise
 */
public class Joiner {
    
    protected String prefix;
    
    protected String suffix;
    
    protected String delimiter;
    
    protected String emptyString;
    
    protected StringBuilder stringBuilder = new StringBuilder();
    
    protected boolean nullable;
    
    public static JoinerBuilder builder() {
        return new JoinerBuilder();
    }
    
    @SuppressWarnings("all")
    public static Joiner newInstance() {
        return builder().build();
    }
    
    Joiner(String prefix,
           String suffix,
           String delimiter,
           String emptyString,
           boolean nullable) {
        
        this.prefix = prefix;
        this.suffix = suffix;
        this.delimiter = delimiter;
        this.emptyString = emptyString;
        this.nullable = nullable;
    }
    
    /**
     * 添加一个字符串
     *
     * @param string 字符串
     * @return this
     */
    public Joiner plus(String string) {
        Preconditions.argument(nullable || Strings.nonEmpty(string), "string");
    
        if (stringBuilder.length() != 0) {
            stringBuilder.append(delimiter);
        }
        stringBuilder.append(string);
        
        return this;
    }
    
    /**
     * 添加一个元素
     *
     * @param element 元素
     * @param serializer 序列化器
     * @return this
     */
    public <T> Joiner plus(T element, Function<T, String> serializer) {
        Preconditions.argument(nullable || Objects.nonNull(element), "string");
        Preconditions.objectNonNull(serializer, "serializer");
    
        if (stringBuilder.length() != 0) {
            stringBuilder.append(delimiter);
        }
        stringBuilder.append(serializer.apply(element));
    
        return this;
    }
    
    /**
     * 添加若干元素
     *
     * @param elements 元素
     * @param serializer 序列化器
     * @return this
     */
    public <T> Joiner withAll(T[] elements, Function<T, String> serializer) {
        Preconditions.objectNonNull(elements, "strings");
        Preconditions.objectNonNull(serializer, "serializer");

        for (T element : elements) {
            plus(element, serializer);
        }
        return this;
    }
    
    /**
     * 添加若干元素
     *
     * @param strings 元素
     * @param serializer 序列化器
     * @return this
     */
    public Joiner plus(char[] strings, Function<Character, String> serializer) {
        Preconditions.objectNonNull(strings, "strings");
        Preconditions.objectNonNull(serializer, "serializer");

        for (char element : strings) {
            plus(element, serializer);
        }
        return this;
    }
    /**
     * 添加若干元素
     *
     * @param strings 元素
     * @param serializer 序列化器
     * @return this
     */
    public Joiner plus(byte[] strings, Function<Byte, String> serializer) {
        Preconditions.objectNonNull(strings, "strings");
        Preconditions.objectNonNull(serializer, "serializer");

        for (byte element : strings) {
            plus(element, serializer);
        }
        return this;
    }
    
    /**
     * 添加若干元素
     *
     * @param strings 元素
     * @param serializer 序列化器
     * @return this
     */
    public Joiner plus(boolean[] strings, Function<Boolean, String> serializer) {
        Preconditions.objectNonNull(strings, "strings");
        Preconditions.objectNonNull(serializer, "serializer");

        for (boolean element : strings) {
            plus(element, serializer);
        }
        return this;
    }
    
    /**
     * 添加若干元素
     *
     * @param strings 元素
     * @param serializer 序列化器
     * @return this
     */
    public Joiner plus(short[] strings, Function<Short, String> serializer) {
        Preconditions.objectNonNull(strings, "strings");
        Preconditions.objectNonNull(serializer, "serializer");

        for (short element : strings) {
            plus(element, serializer);
        }
        return this;
    }
    
    /**
     * 添加若干元素
     *
     * @param strings 元素
     * @param serializer 序列化器
     * @return this
     */
    public Joiner plus(int[] strings, Function<Integer, String> serializer) {
        Preconditions.objectNonNull(strings, "strings");
        Preconditions.objectNonNull(serializer, "serializer");

        for (int element : strings) {
            plus(element, serializer);
        }
        return this;
    }
    
    /**
     * 添加若干元素
     *
     * @param strings 元素
     * @param serializer 序列化器
     * @return this
     */
    public Joiner plus(long[] strings, Function<Long, String> serializer) {
        Preconditions.objectNonNull(strings, "strings");
        Preconditions.objectNonNull(serializer, "serializer");

        for (long element : strings) {
            plus(element, serializer);
        }
        return this;
    }
    
    /**
     * 添加若干元素
     *
     * @param strings 元素
     * @param serializer 序列化器
     * @return this
     */
    public Joiner plus(float[] strings, Function<Float, String> serializer) {
        Preconditions.objectNonNull(strings, "strings");
        Preconditions.objectNonNull(serializer, "serializer");

        for (float element : strings) {
            plus(element, serializer);
        }
        return this;
    }
    
    /**
     * 添加若干元素
     *
     * @param strings 元素
     * @param serializer 序列化器
     * @return this
     */
    public Joiner plus(double[] strings, Function<Double, String> serializer) {
        Preconditions.objectNonNull(strings, "strings");
        Preconditions.objectNonNull(serializer, "serializer");

        for (double element : strings) {
            plus(element, serializer);
        }
        return this;
    }
    
    /**
     * 添加若干元素
     *
     * @param iterable 元素
     * @param serializer 序列化器
     * @return this
     */
    public <T> Joiner withAll(Iterable<T> iterable, Function<T, String> serializer) {
        Preconditions.objectNonNull(iterable, "iterable");
        Preconditions.objectNonNull(serializer, "serializer");
    
        iterable.forEach(x -> plus(x, serializer));
        return this;
    }
    
    /**
     * 添加若干元素
     *
     * @param objects 元素
     * @return this
     */
    public Joiner plus(Object[] objects) {
        Preconditions.objectNonNull(objects, "objects");

        return withAll(objects, Objects::toString);
    }
    
    /**
     * 添加若干元素
     *
     * @param iterable 元素
     * @return this
     */
    public Joiner plus(Iterable<?> iterable) {
        Preconditions.objectNonNull(iterable, "iterable");
    
        return withAll(iterable, Objects::toString);
    }
    
    /**
     * 添加若干元素
     *
     * @param iterable 元素
     * @param serializer 序列化器
     * @return this
     */
    public <T> Joiner plus(Iterable<T> iterable, Function<T, String> serializer) {
        Preconditions.objectNonNull(iterable, "iterable");
    
        return withAll(iterable, serializer);
    }
    
    /**
     * 合并结果
     *
     * @return 合并后的结果
     */
    public String join(String emptyString) {
        Preconditions.objectNonNull(emptyString, "empty string");
    
        if (stringBuilder.length() == 0) {
            return emptyString;
        }
        
        return prefix + stringBuilder + suffix;
    }
    
    /**
     * 合并结果
     *
     * @return 合并后的结果
     */
    public String join() {
        return join(emptyString);
    }
}
