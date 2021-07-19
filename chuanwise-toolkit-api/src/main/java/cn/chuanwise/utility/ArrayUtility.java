package cn.chuanwise.utility;

import java.lang.reflect.Array;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author Chuanwise
 */
public class ArrayUtility extends StaticUtility {
    /**
     * 将一个数组内的所有元素经过一个方法处理后复制进新的数组
     * @param froms 来源数组
     * @param clazz 新数组元素类型
     * @param translator 转换器
     * @param <F> 来源类型
     * @param <T> 新类型
     * @return 新的数组，T[] 类型
     */
    public static <F, T> T[] copyAs(F[] froms, Class<T> clazz, Function<F, T> translator) {
        T[] result = (T[]) Array.newInstance(clazz, froms.length);
        for (int i = 0; i < result.length; i++) {
            result[i] = translator.apply(froms[i]);
        }
        return result;
    }

    public static <T> String toString(T[] array, Function<T, String> translator) {
        return toString(array, translator, ", ");
    }

    public static <T> String toString(T[] array) {
        return toString(array, Objects::toString, ", ");
    }

    public static <T> String toString(T[] array, Function<T, String> translator, String splitter) {
        return rangeToString(array, 0, array.length, translator, splitter);
    }

    public static <T> String toString(T[] array, String splitter) {
        return rangeToString(array, 0, array.length, Objects::toString, splitter);
    }

    public static <T> String rangeToString(T[] array, int begin, int end, Function<T, String> translator, String splitter) {
        if (end <= begin) {
            return "";
        } else {
            final StringBuilder stringBuilder = new StringBuilder(translator.apply(array[begin]));
            for (int i = begin + 1; i < end; i++) {
                stringBuilder.append(splitter).append(translator.apply(array[i]));
            }
            return stringBuilder.toString();
        }
    }

    public static <T> String rangeToString(T[] array, int begin, int end, String splitter) {
        return rangeToString(array, begin, end, Objects::toString, splitter);
    }

    public static String regardAsString(byte[] bytes) {
        final StringBuffer stringBuffer = new StringBuffer(bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            stringBuffer.setCharAt(i, ((char) bytes[i]));
        }
        return stringBuffer.toString();
    }
}
