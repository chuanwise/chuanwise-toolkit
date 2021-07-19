package cn.chuanwise.utility;

import cn.chuanwise.exception.IllegalOperationException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SingleUtility extends Utility {
    private static final Map<Class<? extends SingleUtility>, SingleUtility> INSTANCES = new ConcurrentHashMap<>();

    protected SingleUtility() {
        final Class<? extends SingleUtility> clazz = getClass();
        final SingleUtility instance = INSTANCES.getOrDefault(clazz, this);
        if (instance != this) {
            throw new IllegalOperationException("single utility class " + getClass().getName() + " already have a instance: " + instance.hashCode());
        } else {
            INSTANCES.put(clazz, this);
        }
    }

    public static <T extends SingleUtility> T getInstance(Class<T> clazz) {
        return (T) INSTANCES.get(clazz);
    }
}