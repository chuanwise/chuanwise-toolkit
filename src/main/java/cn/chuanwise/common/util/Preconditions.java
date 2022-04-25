package cn.chuanwise.common.util;

import cn.chuanwise.common.api.Emptiable;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * 断言工具类
 *
 * @author Chuanwise
 */
public class Preconditions
        extends StaticUtilities {
    
    /**
     * 非空断言
     *
     * @param reference 不能为空的引用
     * @throws NullPointerException reference 为 null
     */
    public static void nonNull(Object reference) {
        if (Objects.isNull(reference)) {
            throw new NullPointerException();
        }
    }
    
    /**
     * 非空断言
     *
     * @param reference 不能为空的引用
     * @param message   异常信息
     * @throws NullPointerException reference 或 message 为 null
     */
    public static void nonNull(Object reference, String message) {
        if (Objects.isNull(message)) {
            throw new NullPointerException("message is null");
        }
        
        if (Objects.isNull(reference)) {
            throw new NullPointerException(message);
        }
    }
    
    /**
     * 非空断言
     *
     * @param object     引用名
     * @param objectName 引用名
     * @throws NullPointerException object 或 objectName 为 null
     */
    @SuppressWarnings("all")
    public static void objectNonNull(Object object, String objectName) {
        if (Objects.isNull(objectName)) {
            throw new NullPointerException("object name is null");
        }
        
        nonNull(object, objectName + " is null");
    }
    
    /**
     * 断言方法参数
     *
     * @param legal 方法参数是否合法
     * @throws IllegalArgumentException legal 为 false
     */
    public static void argument(boolean legal) {
        if (!legal) {
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * 断言方法参数
     *
     * @param legal   方法参数是否合法
     * @param message 异常信息
     * @throws IllegalArgumentException legal 为 false
     * @throws NullPointerException     message 为 null
     */
    public static void argument(boolean legal, String message) {
        objectNonNull(message, "message");
        
        if (!legal) {
            throw new IllegalArgumentException(message);
        }
    }
    
    /**
     * 非空参数断言
     *
     * @param argument 参数
     * @throws IllegalArgumentException argument 为 null
     */
    public static void argumentNonEmpty(String argument) {
        argument(Strings.nonEmpty(argument));
    }
    
    /**
     * 非空参数断言
     *
     * @param argument 参数
     * @param message  异常信息
     * @throws IllegalArgumentException argument 为 null
     * @throws NullPointerException     message 为 null
     */
    public static void argumentNonEmpty(String argument, String message) {
        argument(Strings.nonEmpty(argument), message);
    }
    
    /**
     * 非空参数断言
     *
     * @param argument 参数
     * @throws NullPointerException     argument 为 null
     * @throws IllegalArgumentException argument 为空
     */
    public static void argumentNonEmpty(byte[] argument) {
        argument(Arrays.nonEmpty(argument));
    }
    
    /**
     * 非空参数断言
     *
     * @param argument 参数
     * @param message  异常信息
     * @throws NullPointerException     argument 或 message 为 null
     * @throws IllegalArgumentException argument 为空
     */
    public static void argumentNonEmpty(byte[] argument, String message) {
        argument(Arrays.nonEmpty(argument), message);
    }
    
    /**
     * 非空参数断言
     *
     * @param argument   参数
     * @param objectName 对象名
     * @throws IllegalArgumentException argument 为 null
     * @throws NullPointerException     message 为 null
     */
    public static void objectArgumentNonEmpty(byte[] argument, String objectName) {
        argumentNonEmpty(argument, objectName + " is empty");
    }
    
    /**
     * 非空参数断言
     *
     * @param argument 参数
     * @throws NullPointerException     argument 为 null
     * @throws IllegalArgumentException argument 为空
     */
    public static void argumentNonEmpty(char[] argument) {
        argument(Arrays.nonEmpty(argument));
    }
    
    /**
     * 非空参数断言
     *
     * @param argument 参数
     * @param message  异常信息
     * @throws NullPointerException     argument 或 message 为 null
     * @throws IllegalArgumentException argument 为空
     */
    public static void argumentNonEmpty(char[] argument, String message) {
        argument(Arrays.nonEmpty(argument), message);
    }
    
    /**
     * 非空参数断言
     *
     * @param argument   参数
     * @param objectName 对象名
     * @throws IllegalArgumentException argument 为 null
     * @throws NullPointerException     message 为 null
     */
    public static void objectArgumentNonEmpty(char[] argument, String objectName) {
        argumentNonEmpty(argument, objectName + " is empty");
    }
    
    /**
     * 非空参数断言
     *
     * @param argument 参数
     * @throws NullPointerException     argument 为 null
     * @throws IllegalArgumentException argument 为空
     */
    public static void argumentNonEmpty(short[] argument) {
        argument(Arrays.nonEmpty(argument));
    }
    
    /**
     * 非空参数断言
     *
     * @param argument 参数
     * @param message  异常信息
     * @throws NullPointerException     argument 或 message 为 null
     * @throws IllegalArgumentException argument 为空
     */
    public static void argumentNonEmpty(short[] argument, String message) {
        argument(Arrays.nonEmpty(argument), message);
    }
    
    /**
     * 非空参数断言
     *
     * @param argument   参数
     * @param objectName 对象名
     * @throws IllegalArgumentException argument 为 null
     * @throws NullPointerException     message 为 null
     */
    public static void objectArgumentNonEmpty(short[] argument, String objectName) {
        argumentNonEmpty(argument, objectName + " is empty");
    }
    
    /**
     * 非空参数断言
     *
     * @param argument 参数
     * @throws NullPointerException     argument 为 null
     * @throws IllegalArgumentException argument 为空
     */
    public static void argumentNonEmpty(int[] argument) {
        argument(Arrays.nonEmpty(argument));
    }
    
    /**
     * 非空参数断言
     *
     * @param argument 参数
     * @param message  异常信息
     * @throws NullPointerException     argument 或 message 为 null
     * @throws IllegalArgumentException argument 为空
     */
    public static void argumentNonEmpty(int[] argument, String message) {
        argument(Arrays.nonEmpty(argument), message);
    }
    
    /**
     * 非空参数断言
     *
     * @param argument   参数
     * @param objectName 对象名
     * @throws IllegalArgumentException argument 为 null
     * @throws NullPointerException     message 为 null
     */
    public static void objectArgumentNonEmpty(int[] argument, String objectName) {
        argumentNonEmpty(argument, objectName + " is empty");
    }
    
    /**
     * 非空参数断言
     *
     * @param argument 参数
     * @throws NullPointerException     argument 为 null
     * @throws IllegalArgumentException argument 为空
     */
    public static void argumentNonEmpty(long[] argument) {
        argument(Arrays.nonEmpty(argument));
    }
    
    /**
     * 非空参数断言
     *
     * @param argument 参数
     * @param message  异常信息
     * @throws NullPointerException     argument 或 message 为 null
     * @throws IllegalArgumentException argument 为空
     */
    public static void argumentNonEmpty(long[] argument, String message) {
        argument(Arrays.nonEmpty(argument), message);
    }
    
    /**
     * 非空参数断言
     *
     * @param argument   参数
     * @param objectName 对象名
     * @throws IllegalArgumentException argument 为 null
     * @throws NullPointerException     message 为 null
     */
    public static void objectArgumentNonEmpty(long[] argument, String objectName) {
        argumentNonEmpty(argument, objectName + " is empty");
    }
    
    /**
     * 非空参数断言
     *
     * @param argument 参数
     * @throws NullPointerException     argument 为 null
     * @throws IllegalArgumentException argument 为空
     */
    public static void argumentNonEmpty(float[] argument) {
        argument(Arrays.nonEmpty(argument));
    }
    
    /**
     * 非空参数断言
     *
     * @param argument 参数
     * @param message  异常信息
     * @throws NullPointerException     argument 或 message 为 null
     * @throws IllegalArgumentException argument 为空
     */
    public static void argumentNonEmpty(float[] argument, String message) {
        argument(Arrays.nonEmpty(argument), message);
    }
    
    /**
     * 非空参数断言
     *
     * @param argument   参数
     * @param objectName 对象名
     * @throws IllegalArgumentException argument 为 null
     * @throws NullPointerException     message 为 null
     */
    public static void objectArgumentNonEmpty(float[] argument, String objectName) {
        argumentNonEmpty(argument, objectName + " is empty");
    }
    
    /**
     * 非空参数断言
     *
     * @param argument 参数
     * @throws NullPointerException     argument 为 null
     * @throws IllegalArgumentException argument 为空
     */
    public static void argumentNonEmpty(double[] argument) {
        argument(Arrays.nonEmpty(argument));
    }
    
    /**
     * 非空参数断言
     *
     * @param argument 参数
     * @param message  异常信息
     * @throws NullPointerException     argument 或 message 为 null
     * @throws IllegalArgumentException argument 为空
     */
    public static void argumentNonEmpty(double[] argument, String message) {
        argument(Arrays.nonEmpty(argument), message);
    }
    
    /**
     * 非空参数断言
     *
     * @param argument   参数
     * @param objectName 对象名
     * @throws IllegalArgumentException argument 为 null
     * @throws NullPointerException     message 为 null
     */
    public static void objectArgumentNonEmpty(double[] argument, String objectName) {
        argumentNonEmpty(argument, objectName + " is empty");
    }
    
    /**
     * 非空参数断言
     *
     * @param argument 参数
     * @param <T>      数组类型
     * @throws NullPointerException     argument 为 null
     * @throws IllegalArgumentException argument 为空
     */
    public static <T> void argumentNonEmpty(T[] argument) {
        argument(Arrays.nonEmpty(argument));
    }
    
    /**
     * 非空参数断言
     *
     * @param argument 参数
     * @param message  异常信息
     * @param <T>      数组类型
     * @throws NullPointerException     argument 或 message 为 null
     * @throws IllegalArgumentException argument 为空
     */
    public static <T> void argumentNonEmpty(T[] argument, String message) {
        argument(Arrays.nonEmpty(argument), message);
    }
    
    /**
     * 非空参数断言
     *
     * @param argument   参数
     * @param objectName 对象名
     * @param <T>        数组类型
     * @throws IllegalArgumentException argument 为 null
     * @throws NullPointerException     message 为 null
     */
    public static <T> void objectArgumentNonEmpty(T[] argument, String objectName) {
        argumentNonEmpty(argument, objectName + " is empty");
    }
    
    
    /**
     * 非空参数断言
     *
     * @param argument 参数
     * @throws IllegalArgumentException argument 为 null
     */
    public static void argumentNonEmpty(Collection<?> argument) {
        argument(Collections.nonEmpty(argument));
    }
    
    /**
     * 非空参数断言
     *
     * @param argument 参数
     * @param message  异常信息
     * @throws IllegalArgumentException argument 为 null
     * @throws NullPointerException     message 为 null
     */
    public static void argumentNonEmpty(Collection<?> argument, String message) {
        argument(Collections.nonEmpty(argument), message);
    }
    
    /**
     * 非空参数断言
     *
     * @param argument 参数
     * @throws IllegalArgumentException argument 为 null
     */
    public static void argumentNonEmpty(Emptiable argument) {
        argument(Emptiable.nonEmpty(argument));
    }
    
    /**
     * 非空参数断言
     *
     * @param argument 参数
     * @param message  异常信息
     * @throws IllegalArgumentException argument 为 null
     * @throws NullPointerException     message 为 null
     */
    public static void argumentNonEmpty(Emptiable argument, String message) {
        argument(Emptiable.nonEmpty(argument), message);
    }
    
    /**
     * 非空参数断言
     *
     * @param argument 参数
     * @param name     参数名
     * @throws IllegalArgumentException argument 为空
     * @throws NullPointerException     name 为 null
     */
    public static void objectArgumentNonEmpty(String argument, String name) {
        objectNonNull(name, "name");
        argumentNonEmpty(argument, name + " is empty");
    }
    
    /**
     * 非空参数断言
     *
     * @param argument 参数
     * @param name     参数名
     * @throws IllegalArgumentException argument 为空
     * @throws NullPointerException     name 为 null
     */
    public static void objectArgumentNonEmpty(Collection<?> argument, String name) {
        objectNonNull(name, "name");
        argumentNonEmpty(argument, name + " is empty");
    }
    
    /**
     * 非空参数断言
     *
     * @param argument 参数
     * @param name     参数名
     * @throws IllegalArgumentException argument 为空
     * @throws NullPointerException     name 为 null
     */
    public static void objectArgumentNonEmpty(Emptiable argument, String name) {
        objectNonNull(name, "name");
        argumentNonEmpty(argument, name + " is empty");
    }
    
    /**
     * 索引断言
     *
     * @param legal 索引是否合法
     * @throws IndexOutOfBoundsException legal 为 false
     */
    public static void index(boolean legal) {
        if (!legal) {
            throw new IndexOutOfBoundsException();
        }
    }
    
    /**
     * 索引断言
     *
     * @param legal   索引是否合法
     * @param message 异常信息
     * @throws IndexOutOfBoundsException legal 为 false
     */
    public static void index(boolean legal, String message) {
        objectNonNull(message, "message");
        
        if (!legal) {
            throw new IndexOutOfBoundsException(message);
        }
    }
    
    /**
     * 索引断言
     *
     * @param index 索引值
     * @param bound 索引值上限
     * @throws IndexOutOfBoundsException index >= 0 且 index < bound
     * @throws IllegalArgumentException  bound < 0
     */
    public static void index(int index, int bound) {
        argument(bound >= 0, "bound must be bigger than or equals to 0!");
        index(index >= 0 && index < bound);
    }
    
    /**
     * 索引断言
     *
     * @param index   索引值
     * @param bound   索引值上限
     * @param message 异常信息
     * @throws IndexOutOfBoundsException index >= 0 且 index < bound
     * @throws IllegalArgumentException  bound < 0
     */
    public static void index(int index, int bound, String message) {
        objectNonNull(message, "message");
        index(index >= 0 && index < bound, message + " index out of bound, position: " + index + " should be restricted in [0, " + bound + ")");
    }
    
    /**
     * 索引断言
     *
     * @param legal 索引是否合法
     * @param name  索引名
     * @throws IndexOutOfBoundsException legal 为 false
     * @throws IllegalArgumentException  name 为 null
     */
    public static void objectIndex(boolean legal, String name) {
        objectNonNull(name, "name");
        index(legal, name + " index out of bound!");
    }
    
    /**
     * 索引断言
     *
     * @param index 索引值
     * @param bound 索引值上限
     * @param name  索引名
     * @throws IndexOutOfBoundsException index >= 0 且 index < bound
     * @throws IllegalArgumentException  name 为 null
     */
    public static void objectIndex(int index, int bound, String name) {
        objectNonNull(name, "name");
        index(index, bound, name + " index out of bound!");
    }
    
    /**
     * 位置断言
     *
     * @param legal 位置是否合法
     * @throws IndexOutOfBoundsException legal 为 false
     */
    public static void position(boolean legal) {
        if (!legal) {
            throw new IndexOutOfBoundsException();
        }
    }
    
    /**
     * 位置断言
     *
     * @param legal   位置是否合法
     * @param message 异常信息
     * @throws IndexOutOfBoundsException legal 为 false
     */
    public static void position(boolean legal, String message) {
        objectNonNull(message, "message");
        
        if (!legal) {
            throw new IndexOutOfBoundsException(message);
        }
    }
    
    /**
     * 位置断言
     *
     * @param position 位置值
     * @param bound    位置值上限
     * @throws IndexOutOfBoundsException position >= 0 且 position < bound
     * @throws IllegalArgumentException  bound < 0
     */
    public static void position(int position, int bound) {
        argument(bound >= 0, "bound must be bigger than or equals to 0!");
        position(position >= 0 && position < bound);
    }
    
    /**
     * 位置断言
     *
     * @param position 位置值
     * @param bound    位置值上限
     * @param message  异常信息
     * @throws IndexOutOfBoundsException position >= 0 且 position <= bound
     * @throws IllegalArgumentException  bound < 0
     */
    public static void position(int position, int bound, String message) {
        objectNonNull(message, "message");
        position(position >= 0 && position <= bound, message + " position out of bound, position: " + position + " should be restricted in [0, " + bound + "]");
    }
    
    /**
     * 位置断言
     *
     * @param legal 位置是否合法
     * @param name  位置名
     * @throws IndexOutOfBoundsException legal 为 false
     * @throws IllegalArgumentException  name 为 null
     */
    public static void objectPosition(boolean legal, String name) {
        objectNonNull(name, "name");
        position(legal, name + " position out of bound!");
    }
    
    /**
     * 位置断言
     *
     * @param position 位置值
     * @param bound    位置值上限
     * @param name     位置名
     * @throws IndexOutOfBoundsException position >= 0 且 position < bound
     * @throws IllegalArgumentException  name 为 null
     */
    public static void objectPosition(int position, int bound, String name) {
        objectNonNull(name, "name");
        position(position, bound, name + " position out of bound!");
    }
    
    /**
     * 操作断言
     *
     * @param legal 操作是否合法
     * @throws UnsupportedOperationException legal 为 false
     */
    public static void operation(boolean legal) {
        if (!legal) {
            throw new UnsupportedOperationException();
        }
    }
    
    /**
     * 操作断言
     *
     * @param legal   操作是否合法
     * @param message 异常信息
     * @throws UnsupportedOperationException legal 为 false
     * @throws IllegalArgumentException      message 为 null
     */
    public static void operation(boolean legal, String message) {
        objectNonNull(message, "message");
        
        if (!legal) {
            throw new UnsupportedOperationException(message);
        }
    }
    
    /**
     * 操作为空断言
     *
     * @param reference 对象
     * @throws UnsupportedOperationException reference 非 null
     */
    public static void operationIsNull(Object reference) {
        operation(Objects.isNull(reference));
    }
    
    /**
     * 操作为空断言
     *
     * @param reference 对象
     * @param message   异常信息
     * @throws UnsupportedOperationException reference 非 null
     * @throws IllegalArgumentException      message 为 null
     */
    public static void operationIsNull(Object reference, String message) {
        objectNonNull(message, "message");
        
        operation(Objects.isNull(reference), message);
    }
    
    /**
     * 操作非空断言
     *
     * @param reference 对象
     * @throws UnsupportedOperationException reference 为 null
     */
    public static void operationNonNull(Object reference) {
        operation(Objects.nonNull(reference));
    }
    
    /**
     * 操作非空断言
     *
     * @param reference 对象
     * @param message   异常信息
     * @throws UnsupportedOperationException reference 为 null
     * @throws IllegalArgumentException      message 为 null
     */
    public static void operationNonNull(Object reference, String message) {
        objectNonNull(message, "message");
        
        operation(Objects.nonNull(reference), message);
    }
    
    /**
     * 元素断言
     *
     * @param legal 元素是否存在
     * @throws NoSuchElementException legal 为 false
     */
    public static void element(boolean legal) {
        if (!legal) {
            throw new NoSuchElementException();
        }
    }
    
    /**
     * 元素断言
     *
     * @param legal   元素是否存在
     * @param message 异常信息
     * @throws NoSuchElementException   legal 为 false
     * @throws IllegalArgumentException message 为 null
     */
    public static void element(boolean legal, String message) {
        objectNonNull(message, "message");
        
        if (!legal) {
            throw new NoSuchElementException(message);
        }
    }
    
    /**
     * 元素非空断言
     *
     * @param reference 元素
     * @throws NoSuchElementException reference 为 null
     */
    public static void elementIsNull(Object reference) {
        element(Objects.isNull(reference));
    }
    
    /**
     * 元素非空断言
     *
     * @param reference 元素
     * @param message   异常信息
     * @throws NoSuchElementException   reference 为 null
     * @throws IllegalArgumentException message 为 null
     */
    public static void elementIsNull(Object reference, String message) {
        objectNonNull(message, "message");
        
        element(Objects.isNull(reference), message);
    }
    
    /**
     * 元素非空断言
     *
     * @param reference 元素
     * @throws NoSuchElementException reference 为 null
     */
    public static void elementNonNull(Object reference) {
        element(Objects.nonNull(reference));
    }
    
    /**
     * 元素非空断言
     *
     * @param reference 元素
     * @param message   异常信息
     * @throws NoSuchElementException   reference 为 null
     * @throws IllegalArgumentException message 为 null
     */
    public static void elementNonNull(Object reference, String message) {
        objectNonNull(message, "message");
        
        element(Objects.nonNull(reference), message);
    }
    
    /**
     * 状态断言
     *
     * @param legal 状态是否合法
     * @throws IllegalStateException legal 为 false
     */
    public static void state(boolean legal) {
        if (!legal) {
            throw new IllegalStateException();
        }
    }
    
    /**
     * 状态断言
     *
     * @param legal   状态是否合法
     * @param message 异常信息
     * @throws IllegalStateException legal 为 false
     */
    public static void state(boolean legal, String message) {
        if (!legal) {
            throw new IllegalStateException(message);
        }
    }
    
    /**
     * 状态断言
     *
     * @param reference 元素
     * @throws IllegalStateException reference != null
     */
    public static void stateIsNull(Object reference) {
        state(Objects.isNull(reference));
    }
    
    /**
     * 状态断言
     *
     * @param reference 元素
     * @param message   异常信息
     * @throws IllegalStateException reference != null
     */
    public static void stateIsNull(Object reference, String message) {
        state(Objects.isNull(reference), message);
    }
    
    /**
     * 状态断言
     *
     * @param reference 元素
     * @throws IllegalStateException reference == null
     */
    public static void stateNonNull(Object reference) {
        state(Objects.nonNull(reference));
    }
    
    /**
     * 状态断言
     *
     * @param reference 元素
     * @param message   异常信息
     * @throws IllegalStateException reference == null
     */
    public static void stateNonNull(Object reference, String message) {
        state(Objects.nonNull(reference), message);
    }
}