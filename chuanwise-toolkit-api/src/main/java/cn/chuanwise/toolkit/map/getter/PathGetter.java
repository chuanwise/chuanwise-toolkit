package cn.chuanwise.toolkit.map.getter;

import java.util.function.Supplier;

public interface PathGetter {
    Object get(String path);

    <T> T get(String path, Class<T> clazz);

    default  <T> T getOrDefault(String path, T defaultValue) {
        return getOrSupplie(path, () -> defaultValue);
    }

    <T> T getOrSupplie(String path, Supplier<T> supplier);

    PathGetterConfiguration getPathGetterConfiguration();

    void setPathGetterConfiguration(PathGetterConfiguration configuration);
}
