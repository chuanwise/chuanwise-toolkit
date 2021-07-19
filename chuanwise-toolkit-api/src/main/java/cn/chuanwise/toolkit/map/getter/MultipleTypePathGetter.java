package cn.chuanwise.toolkit.map.getter;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Supplier;

public interface MultipleTypePathGetter extends PathGetter {
    default String getString(String path) {
        return get(path, String.class);
    }

    default List<String> getStringList(String path) {
        return get(path, List.class);
    }

    default String[] getStringArray(String path) {
        return get(path, String[].class);
    }

    /** to byte */
    default Byte getByte(String path) {
        return get(path, Byte.class);
    }

    default byte getByteValue(String path) {
        return getByte(path);
    }

    default List<Byte> getByteList(String path) {
        return get(path, List.class);
    }

    default Byte[] getByteArray(String path) {
        final List<Byte> list = getByteList(path);
        return (getPathGetterConfiguration().isReturnNullIfFail() && Objects.isNull(list)) ? null : list.toArray(new Byte[0]);
    }

    default byte[] getByteValueList(String path) {
        final List<Byte> list = getByteList(path);
        if (getPathGetterConfiguration().isReturnNullIfFail() && Objects.isNull(list)) {
            return null;
        } else {
            final byte[] bytes = new byte[list.size()];
            for (int i = 0; i < list.size(); i++) {
                bytes[i] = list.get(i);
            }
            return bytes;
        }
    }

    /** to short */
    default Short getShort(String path) {
        return get(path, Short.class);
    }

    default short getShortValue(String path) {
        return getShort(path);
    }

    default List<Short> getShortList(String path) {
        return get(path, List.class);
    }

    default Short[] getShortArray(String path) {
        final List<Short> list = getShortList(path);
        return (getPathGetterConfiguration().isReturnNullIfFail() && Objects.isNull(list)) ? null : list.toArray(new Short[0]);
    }

    default short[] getShortValueList(String path) {
        final List<Short> list = getShortList(path);
        if (getPathGetterConfiguration().isReturnNullIfFail() && Objects.isNull(list)) {
            return null;
        } else {
            final short[] shorts = new short[list.size()];
            for (int i = 0; i < list.size(); i++) {
                shorts[i] = list.get(i);
            }
            return shorts;
        }
    }

    /** to int */
    default Integer getInteger(String path) {
        return get(path, Integer.class);
    }

    default int getIntegerValue(String path) {
        return getInteger(path);
    }

    default List<Integer> getIntegerList(String path) {
        return get(path, List.class);
    }

    default Integer[] getIntegerArray(String path) {
        final List<Integer> list = getIntegerList(path);
        return (getPathGetterConfiguration().isReturnNullIfFail() && Objects.isNull(list)) ? null : list.toArray(new Integer[0]);
    }

    default int[] getIntegerValueList(String path) {
        final List<Integer> list = getIntegerList(path);
        return (getPathGetterConfiguration().isReturnNullIfFail() && Objects.isNull(list)) ? null : list.stream().mapToInt(Integer::intValue).toArray();
    }

    /** to long */
    default Long getLong(String path) {
        return get(path, Long.class);
    }

    default long getLongValue(String path) {
        return getLong(path);
    }

    default List<Long> getLongList(String path) {
        return get(path, List.class);
    }

    default Long[] getLongArray(String path) {
        final List<Long> list = getLongList(path);
        return (getPathGetterConfiguration().isReturnNullIfFail() && Objects.isNull(list)) ? null : list.toArray(new Long[0]);
    }

    default long[] getLongValueList(String path) {
        final List<Long> list = getLongList(path);
        return (getPathGetterConfiguration().isReturnNullIfFail() && Objects.isNull(list)) ? null : list.stream().mapToLong(Long::longValue).toArray();
    }

    /** to float */
    default Float getFloat(String path) {
        return get(path, Float.class);
    }

    default float getFloatValue(String path) {
        return getFloat(path);
    }

    default List<Float> getFloatList(String path) {
        return get(path, List.class);
    }

    default Float[] getFloatArray(String path) {
        final List<Float> list = getFloatList(path);
        return (getPathGetterConfiguration().isReturnNullIfFail() && Objects.isNull(list)) ? null : list.toArray(new Float[0]);
    }

    default float[] getFloatValueList(String path) {
        final List<Float> list = getFloatList(path);
        if (getPathGetterConfiguration().isReturnNullIfFail() && Objects.isNull(list)) {
            return null;
        } else {
            final float[] floats = new float[list.size()];
            for (int i = 0; i < list.size(); i++) {
                floats[i] = list.get(i);
            }
            return floats;
        }
    }

    /** to double */
    default Double getDouble(String path) {
        return get(path, Double.class);
    }

    default double getDoubleValue(String path) {
        return getDouble(path);
    }

    default List<Double> getDoubleList(String path) {
        return get(path, List.class);
    }

    default Double[] getDoubleArray(String path) {
        final List<Double> list = getDoubleList(path);
        return (getPathGetterConfiguration().isReturnNullIfFail() && Objects.isNull(list)) ? null : list.toArray(new Double[0]);
    }

    default double[] getDoubleValueList(String path) {
        final List<Double> list = getDoubleList(path);
        return (getPathGetterConfiguration().isReturnNullIfFail() && Objects.isNull(list)) ? null : list.stream().mapToDouble(Double::doubleValue).toArray();
    }

    /** to boolean */
    default Boolean getBoolean(String path) {
        return get(path, Boolean.class);
    }

    default boolean getBooleanValue(String path) {
        return getBoolean(path);
    }

    default List<Boolean> getBooleanList(String path) {
        return get(path, List.class);
    }

    default Boolean[] getBooleanArray(String path) {
        final List<Boolean> list = getBooleanList(path);
        return (getPathGetterConfiguration().isReturnNullIfFail() && Objects.isNull(list)) ? null : list.toArray(new Boolean[0]);
    }

    default boolean[] getBooleanValueList(String path) {
        final List<Boolean> list = getBooleanList(path);
        if (getPathGetterConfiguration().isReturnNullIfFail() && Objects.isNull(list)) {
            return null;
        } else {
            final boolean[] booleans = new boolean[list.size()];
            for (int i = 0; i < list.size(); i++) {
                booleans[i] = list.get(i);
            }
            return booleans;
        }
    }

    default Object[] getObjectArray(String path) {
        return get(path, Object[].class);
    }

    default List<Object> getObjectList(String path) {
        return get(path, List.class);
    }

    @Override
    default <T> T get(String path, Class<T> clazz) {
        final Object object = get(path);
        if (Objects.isNull(object)) {
            if (getPathGetterConfiguration().isReturnNullIfFail()) {
                return null;
            } else {
                throw new NoSuchElementException("no such element: " + path);
            }
        } else {
            final Class<?> objectClass = object.getClass();
            if (clazz.isAssignableFrom(objectClass)) {
                return (T) object;
            } else {
                if (getPathGetterConfiguration().isReturnNullIfFail()) {
                    return null;
                } else {
                    throw new IllegalArgumentException("object in path: " + path + " is a instance of " + objectClass.getName() +
                            ", can not be convert to " + clazz.getName());
                }
            }
        }
    }

    @Override
    default  <T> T getOrSupplie(String path, Supplier<T> supplier) {
        try {
            return ((T) get(path));
        } catch (NoSuchElementException | ClassCastException exception) {
            return supplier.get();
        }
    }
}
