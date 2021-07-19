package cn.chuanwise.utility;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class CollectionUtility extends StaticUtility {
    /**
     * 将可迭代对象中的元素根据默认顺序，逐次使用 translator 函数处理后放入新的集合中
     * @param fromIterable 来源集合
     * @param toCollection 转化到的集合类型
     * @param translator 转化者
     * @param <F> 来源类型
     * @param <FI> 来源可迭代类型
     * @param <T> 转化类型
     * @param <TC> 转化集合类型
     * @return
     */
    public static <F, FI extends Iterable<F>, T, TC extends Collection<T>> TC addTo(FI fromIterable, TC toCollection, Function<F, T> translator) {
        fromIterable.forEach(value -> toCollection.add(translator.apply(value)));
        return toCollection;
    }

    public static boolean isEmpty(Collection<?> collection) {
        return Objects.isNull(collection) || collection.isEmpty();
    }

    public static <T> String toString(Iterable<T> iterable, Function<T, String> translator, String spiltter) {
        Iterator<T> iterator = iterable.iterator();
        if (iterator.hasNext()) {
            StringBuffer stringBuffer = new StringBuffer(translator.apply(iterator.next()));

            iterator.forEachRemaining(element -> {
                stringBuffer.append(spiltter + translator.apply(element));
            });

            return stringBuffer.toString();
        } else {
            return null;
        }
    }

    public static <T> String toIndexString(Iterable<T> iterable, Function<Integer, String> indexTranslator, Function<T, String> elementTranslator, String spiltter) {
        Iterator<T> iterator = iterable.iterator();
        if (iterator.hasNext()) {
            StringBuffer stringBuffer = new StringBuffer(indexTranslator.apply(0) + elementTranslator.apply(iterator.next()));

            int index = 1;
            while (iterator.hasNext()) {
                stringBuffer.append(spiltter + indexTranslator.apply(index++) + elementTranslator.apply(iterator.next()));
            }

            return stringBuffer.toString();
        } else {
            return null;
        }
    }

    public static <T> String toIndexString(Iterable<T> iterable, Function<T, String> elementTranslator, String spiltter) {
        return toIndexString(iterable, number -> ((number + 1 ) + "、"), elementTranslator, spiltter);
    }

    public static <T> String toString(Iterable<T> iterable, Function<T, String> translator) {
        return toString(iterable, translator, "，");
    }

    public static <T> String toString(Iterable<T> iterable) {
        return toString(iterable, Objects::toString);
    }

    public static <T> String toString(Iterable<T> iterable, String spiltter) {
        return toString(iterable, Objects::toString, spiltter);
    }

    public static <T> String toIndexString(Iterable<T> iterable, Function<T, String> consumer) {
        return toIndexString(iterable, consumer, "\n");
    }

    public static <T> String toIndexString(Iterable<T> iterable) {
        return toIndexString(iterable, Objects::toString);
    }

    public static <T> Set<T> asSet(T... elements) {
        return addTo(Arrays.asList(elements), new HashSet<>(elements.length), element -> element);
    }

    public static <K, V> V getOrSupplie(Map<K, V> map, K key, Supplier<V> supplier) {
        V v = map.get(key);
        if (Objects.isNull(v)) {
            v = supplier.get();
            map.put(key, v);
        }
        return v;
    }

    public static <K, V> V getOrPut(Map<K, V> map, K key, V value) {
        V v = map.get(key);
        if (Objects.isNull(v)) {
            v = value;
            map.put(key, v);
        }
        return v;
    }
}
