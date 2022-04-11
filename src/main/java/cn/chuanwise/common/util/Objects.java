package cn.chuanwise.common.util;

import cn.chuanwise.common.api.Emptiable;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * 类基础 API
 *
 * @author Chuanwise
 */
public class Objects
    extends StaticUtilities {
    
    /**
     * 判断对象是否为 null
     *
     * @param object 对象
     * @return 对象是否为 null
     */
    public static boolean isNull(Object object) {
        return object == null;
    }
    
    /**
     * 判断对象是否非 null
     *
     * @param object 对象
     * @return 对象是否非 null
     */
    public static boolean nonNull(Object object) {
        return object != null;
    }
    
    /**
     * 判断对象是否为空
     *
     * @param object 对象
     * @return 当对象是 null，返回 true。
     * 当对象是 {@link Emptiable} 的实例，返回 {@link Emptiable#isEmpty()}。
     * 当对象是各类数组，返回 {@link Arrays#isEmpty(Object[])}。
     * 否则返回 false
     */
    public static boolean isEmpty(Object object) {
        if (isNull(object)) {
            return true;
        }
    
        if (object instanceof Collection) {
            final Collection<?> objects = (Collection<?>) object;
            return Collections.isEmpty(objects);
        }
    
        if (object instanceof Emptiable) {
            final Emptiable emptiable = (Emptiable) object;
            return emptiable.isEmpty();
        }
    
        final Class<?> componentType = object.getClass().getComponentType();
        if (Objects.nonNull(componentType)) {
            return Array.getLength(object) == 0;
        }
        
        return false;
    }
    
    /**
     * 判断对象是否相等
     *
     * @param object1 对象 1
     * @param object2 对象 2
     * @return 对象 1 和 2 是否相等
     */
    public static boolean equals(Object object1, Object object2) {
        return java.util.Objects.equals(object1, object2);
    }
    
    /**
     * 在某个对象上同步
     *
     * @param object 同步对象
     * @throws InterruptedException 中断异常
     */
    @SuppressWarnings("all")
    public static void await(Object object) throws InterruptedException {
        Preconditions.objectNonNull(object, "object");
        
        synchronized (object) {
            object.wait();
        }
    }
    
    /**
     * 在某个对象上同步
     *
     * @param object     同步对象
     * @param timeout 超时时间戳
     * @return 是否有其他线程在该对象上 notify
     * @throws InterruptedException 中断异常
     */
    @SuppressWarnings("all")
    public static boolean await(Object object, long timeout) throws InterruptedException {
        Preconditions.objectNonNull(object, "object");
        Preconditions.argument(timeout >= 0, "time millis must be bigger than or equals to 0!");
        
        if (timeout == 0) {
            await(object);
            return true;
        }
        
        final long deadline = System.currentTimeMillis() + timeout;
        
        synchronized (object) {
            object.wait(timeout);
        }
        
        return System.currentTimeMillis() < deadline;
    }
    
    /**
     * 在某个对象上同步
     *
     * @param object   同步对象
     * @param timeout 超时时长
     * @param timeUnit 时间单位
     * @return 是否有其他线程在该对象上 notify
     * @throws InterruptedException 中断异常
     */
    public static boolean await(Object object, long timeout, TimeUnit timeUnit) throws InterruptedException {
        Preconditions.objectNonNull(object, "object");
        Preconditions.argument(timeout >= 0, "duration must be bigger than or equals to 0!");
        Preconditions.objectNonNull(timeUnit, "time unit");
        
        return await(object, timeUnit.toMillis(timeout));
    }
    
    /**
     * 不可打断地在某个对象上同步
     *
     * @param object 同步对象
     */
    @SuppressWarnings("all")
    public static void awaitUninterruptibly(Object object) {
        Preconditions.objectNonNull(object, "object");
        
        while (true) {
            try {
                await(object);
                return;
            } catch (InterruptedException ignored) {
            }
        }
    }
    
    /**
     * 不可打断地在某个对象上同步
     *
     * @param object   同步对象
     * @param timeout 超时时长
     * @param timeUnit 时间单位
     * @return 是否有其他线程在该对象上 notify
     */
    public static boolean awaitUninterruptibly(Object object, long timeout, TimeUnit timeUnit) {
        Preconditions.objectNonNull(object, "object");
        Preconditions.argument(timeout >= 0, "duration must be bigger than or equals to 0!");
        Preconditions.objectNonNull(timeUnit, "time unit");
        
        return awaitUninterruptibly(object, timeout, TimeUnit.MILLISECONDS);
    }
    
    /**
     * 不可打断地在某个对象上同步
     *
     * @param object     同步对象
     * @param timeout 超时时间戳
     * @return 是否有其他线程在该对象上 notify
     */
    public static boolean awaitUninterruptibly(Object object, long timeout) {
        Preconditions.objectNonNull(object, "object");
        Preconditions.argument(timeout >= 0, "time millis must be bigger than or equals to 0!");
        
        if (timeout == 0) {
            awaitUninterruptibly(object);
            return true;
        }
        
        final long deadline = System.currentTimeMillis() + timeout;
        
        while (true) {
            try {
                final long remain = deadline - System.currentTimeMillis();
                
                if (remain <= 0) {
                    return false;
                } else {
                    return await(object, remain);
                }
            } catch (InterruptedException ignored) {
            }
        }
    }
}
