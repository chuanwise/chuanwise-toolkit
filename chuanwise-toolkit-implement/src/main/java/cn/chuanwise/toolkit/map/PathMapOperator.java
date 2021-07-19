package cn.chuanwise.toolkit.map;

import cn.chuanwise.annotation.Uncomplete;
import cn.chuanwise.toolkit.map.getter.MultipleTypePathGetter;
import cn.chuanwise.toolkit.map.getter.PathGetterConfiguration;
import cn.chuanwise.toolkit.map.setter.PathSetter;
import cn.chuanwise.toolkit.map.setter.PathSetterConfiguration;
import cn.chuanwise.toolkit.map.getter.SimplePathGetterConfiguration;
import cn.chuanwise.toolkit.map.setter.SimplePathSetterConfiguration;
import cn.chuanwise.utility.ArrayUtility;
import cn.chuanwise.utility.CheckUtility;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

@Getter
@Setter
public class PathMapOperator implements MultipleTypePathGetter, PathSetter {
    final Map<String, Object> values;

    PathGetterConfiguration pathGetterConfiguration = new SimplePathGetterConfiguration();

    PathSetterConfiguration pathSetterConfiguration = new SimplePathSetterConfiguration();

    public PathMapOperator(Map<String, Object> values) {
        this.values = values;
    }

    public PathMapOperator() {
        this(new HashMap<>());
    }

    public int size() {
        return values.size();
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    public boolean containsKey(Object key) {
        return values.containsKey(key);
    }

    @Override
    public void set(String path, Object o) {
        String[] paths = path.split(Pattern.quote("."));

        CheckUtility.checkArgument(paths.length > 0, "empty path");

        Map<String, Object> object = values;
        boolean found = false;

        for (int i = 0; i < paths.length - 1; i++) {
            final String currentKey = paths[i];

            Object next = object.get(currentKey);
            if (next instanceof Map) {
                object = ((Map<String, Object>) next);
            } else if (object.containsKey(currentKey)) {
                final String currentPath = ArrayUtility.rangeToString(paths, 0, i + 1, ".");
                throw new NoSuchElementException("element in path: " + currentPath + " is not a map");
            } else if (getPathSetterConfiguration().isCreatePathIfNoSuchPath()) {
                final Map<String, Object> son = new HashMap<>();
                object.put(currentKey, son);
                object = son;
            } else {
                final String currentPath = ArrayUtility.rangeToString(paths, 0, i + 1, ".");
                throw new NoSuchElementException("element: " + currentPath + " is null, and can not create map " +
                        "(use getPathSetterConfiguration().setCreatePathIfNoSuchPath(true) to change it)");
            }
        }

        object.put(paths[paths.length - 1], o);
    }

    @Override
    public Object get(String path) {
        String[] paths = path.split(Pattern.quote("."));
        Object object = values;
        boolean found;

        for (int i = 0; i < paths.length; i++) {
            final String currentKey = paths[i];
            if (object instanceof Map) {
                final Map<String, Object> map = (Map) object;
                object = map.get(currentKey);

                // 一般情况下应该都 get 到了，如果是 null，还得再检查一下
                found = !Objects.isNull(object) || map.containsKey(currentKey);
            } else {
                found = false;
            }

            if (found) {
                continue;
            }

            if (getPathGetterConfiguration().isReturnNullIfFail()) {
                return null;
            } else {
                final String currentPath = ArrayUtility.rangeToString(paths, 0, i, ".");
                if (Objects.isNull(object)) {
                    throw new NoSuchElementException("no such element: " + currentPath + " in path: " + ArrayUtility.toString(paths, "."));
                } else {
                    throw new NoSuchElementException("element: " + currentPath + " is null, can not access the remain part in path: " + ArrayUtility.toString(paths, "."));
                }
            }
        }
        return object;
    }

    @Override
    public Object remove(String path) {
        String[] paths = path.split(Pattern.quote("."));

        CheckUtility.checkArgument(paths.length > 0, "empty path");

        Map<String, Object> object = values;
        boolean found = false;

        for (int i = 0; i < paths.length - 1; i++) {
            final String currentKey = paths[i];

            Object next = object.get(currentKey);
            if (next instanceof Map) {
                object = ((Map<String, Object>) next);
            } else if (object.containsKey(currentKey)) {
                final String currentPath = ArrayUtility.rangeToString(paths, 0, i + 1, ".");
                throw new NoSuchElementException("element in path: " + currentPath + " is not a map");
            } else if (getPathSetterConfiguration().isCreatePathIfNoSuchPath()) {
                final Map<String, Object> son = new HashMap<>();
                object.put(currentKey, son);
                object = son;
            } else {
                final String currentPath = ArrayUtility.rangeToString(paths, 0, i + 1, ".");
                throw new NoSuchElementException("element: " + currentPath + " is null, and can not create map " +
                        "(use getPathSetterConfiguration().setCreatePathIfNoSuchPath(true) to change it)");
            }
        }

        return object.remove(paths[paths.length - 1]);
    }

    public void clear() {
        values.clear();
    }

    public Set<String> keySet() {
        return values.keySet();
    }

    public Collection<Object> values() {
        return values.values();
    }

    public Set<Map.Entry<String, Object>> entrySet() {
        return values.entrySet();
    }
}
