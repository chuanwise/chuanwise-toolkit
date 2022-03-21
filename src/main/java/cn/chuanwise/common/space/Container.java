package cn.chuanwise.common.space;

import cn.chuanwise.common.util.Preconditions;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 一个存放值的只读容器。
 *
 * 是为了完善 JDK 自带的 {@link Optional} 而创建的。
 *
 * @param <T> 容器内的值类型
 * @author Chuanwise
 */
public final class Container<T> {
    
    /**
     * 容器存放的值
     */
    private final T value;
    
    /**
     * 全局唯一空容器
     */
    private static final Container<?> EMPTY = new Container<>();
    
    /**
     * 全局唯一存放 null 的容器
     */
    private static final Container<?> NULL = new Container<>();
    
    
    /**
     * 通过 JDK 自带的 {@link Optional} 构造一个容器
     *
     * @param optional JDK 自带的 Optional
     * @param <U>      Optional 内含变量的类型
     * @return 当 Optional 非空，返回装有 Optional 的值的容器，否则返回空容器
     * @see #toOptional() 与当前方法互逆
     */
    @SuppressWarnings("all")
    public static <U> Container<U> ofOptional(Optional<U> optional) {
        Preconditions.namedArgumentNonNull(optional, "optional");
    
        return optional.map(Container::of).orElse(empty());
    }
    
    /**
     * 由特定值构造一个容器
     *
     * @param value 容器内的值
     * @param <U>   容器内的值类型
     * @return 当 value 为 null，返回 null 值容器，否则返回某一特定容器
     * @see #ofNull() 当 value 为 null 时，当前方法与之等价
     */
    @SuppressWarnings("all")
    public static <U> Container<U> of(U value) {
        return Objects.isNull(value) ? (Container<U>) NULL : new Container<>(value);
    }
    
    /**
     * 获得全局唯一的 null 值容器
     *
     * @param <U> 需要将 null 转化到的类型
     * @return 全局唯一的 null 值容器
     */
    @SuppressWarnings("unchecked")
    public static <U> Container<U> ofNull() {
        return (Container<U>) NULL;
    }
    
    /**
     * 由特定值创建容器，或返回空容器
     *
     * @param value 容器内的值
     * @param <U>   容器内的值类型
     * @return 当 value 为 null，返回空容器，否则返回装有其值的容器
     * @deprecated 请使用 {@link #ofNonNull(Object)} 代替
     */
    @Deprecated
    public static <U> Container<U> ofNotNull(U value) {
        if (Objects.isNull(value)) {
            return empty();
        } else {
            return of(value);
        }
    }
    
    /**
     * 由特定值创建容器，或返回空容器
     *
     * @param value 容器内的值
     * @param <U>   容器内的值类型
     * @return 当 value 为 null，返回空容器，否则返回装有其值的容器
     */
    public static <U> Container<U> ofNonNull(U value) {
        return ofNotNull(value);
    }
    
    /**
     * 获取全局唯一空容器
     *
     * @param <U> 需要将空容器转化到的类型
     * @return 全局唯一空容器
     */
    @SuppressWarnings("unchecked")
    public static <U> Container<U> empty() {
        return (Container<U>) EMPTY;
    }
    
    /**
     * 通过某个值构造装有特定值的容器。
     *
     * @param value 容器内的值
     * @throws IllegalArgumentException value 为 null 时
     */
    private Container(T value) {
        Preconditions.namedArgumentNonNull(value, "若要构造装有 null 值的容器，请使用 Container.ofNull()");
    
        this.value = value;
    }
    
    /**
     * 构造一个特殊容器，仅限空容器和 null 值容器使用。
     */
    private Container() {
        this.value = null;
    }
    
    /**
     * 获取容器内的值。
     *
     * @return 容器内的值
     * @throws NoSuchElementException 容器为空时
     */
    public T get() {
        return orElseThrow();
    }
    
    /**
     * 判断容器是否有值。
     *
     * @return 容器是否有值
     */
    public boolean isSet() {
        return this != EMPTY;
    }
    
    /**
     * 判断容器是否有非空值。
     *
     * @return 容器是否有非空值
     */
    public boolean isPresent() {
        return isSet() && value != null;
    }
    
    /**
     * 判断容器是否为空。
     *
     * @return 容器是否为空
     */
    public boolean isEmpty() {
        return !isSet();
    }
    
    /**
     * 判断容器是否为 null 值容器。
     *
     * @return 容器是否为 null 值容器
     */
    public boolean isNull() {
        return this == NULL;
    }
    
    /**
     * 当容器有非空值时，进行一些需要容器内的值的操作。
     *
     * @param action 需要容器内的值的操作
     * @return 容器本身
     * @throws IllegalArgumentException action 为 null 时
     */
    public Container<T> ifPresent(Consumer<T> action) {
        Preconditions.namedArgumentNonNull(action, "action");
    
        if (isPresent()) {
            action.accept(get());
        }
        return this;
    }
    
    /**
     * 当容器有非空值时，进行一些不需要容器内的值的操作。
     *
     * @param action 不需要容器内的值的操作
     * @return 容器本身
     * @throws IllegalArgumentException action 为 null 时
     */
    public Container<T> ifPresent(Runnable action) {
        Preconditions.namedArgumentNonNull(action, "action");
    
        if (isPresent()) {
            action.run();
        }
        return this;
    }
    
    /**
     * 当容器有值时，进行一些需要容器内的值的操作。
     *
     * @param action 需要容器内的值的操作
     * @return 容器本身
     * @throws IllegalArgumentException action 为 null 时
     */
    public Container<T> ifSet(Consumer<T> action) {
        Preconditions.namedArgumentNonNull(action, "action");
    
        if (isSet()) {
            action.accept(value);
        }
        return this;
    }
    
    /**
     * 当容器有值时，进行一些不需要容器内的值的操作。
     *
     * @param action 不需要容器内的值的操作
     * @return 容器本身
     * @throws IllegalArgumentException action 为 null 时
     */
    public Container<T> ifSet(Runnable action) {
        Preconditions.namedArgumentNonNull(action, "action");
    
        if (isSet()) {
            action.run();
        }
        return this;
    }
    
    /**
     * 当容器无值时，进行一些操作。
     *
     * @param action 要进行的操作
     * @return 容器本身
     * @throws IllegalArgumentException action 为 null 时
     */
    public Container<T> ifEmpty(Runnable action) {
        Preconditions.namedArgumentNonNull(action, "action");
    
        if (isEmpty()) {
            action.run();
        }
        return this;
    }
    
    /**
     * 当容器有非空值，进行某需要容器内的值的操作，否则进行另一操作。
     *
     * @param action1 当容器内有非空值时需要进行的需要容器内的值的操作
     * @param action2 当容器内无非空值时需要进行的另一操作
     * @return 容器本身
     * @throws IllegalArgumentException action1 或 action2 为 null 时
     * @deprecated 该方法指令参数较多，较不美观，请使用 ifPresent(...).ifEmpty(...) 替代
     */
    @Deprecated
    public Container<T> ifPresentOrEmpty(Consumer<T> action1, Runnable action2) {
        Preconditions.namedArgumentNonNull(action1, "action when the container is present");
        Preconditions.namedArgumentNonNull(action2, "action when the container isn't present");
    
        if (isPresent()) {
            action1.accept(get());
        } else {
            action2.run();
        }
        return this;
    }
    
    /**
     * 当容器内有值，通过某方式将其值映射为另一值，并返回其容器。
     *
     * @param mapper 映射方式
     * @param <U>    映射结果值类型
     * @return 当容器内有值，返回映射后的容器，否则返回空容器
     * @throws IllegalArgumentException mapper 为 null 时
     */
    public <U> Container<U> map(Function<T, U> mapper) {
        Preconditions.namedArgumentNonNull(mapper, "mapper");
    
        if (isSet()) {
            return of(mapper.apply(get()));
        } else {
            return empty();
        }
    }
    
    /**
     * 筛选容器内的值。
     *
     * @param filter 筛选方式
     * @return 当容器内有值，其值满足条件，则返回容器本身，否则返回空容器
     * @throws IllegalArgumentException filter 为 null 时
     */
    public Container<T> filter(Predicate<T> filter) {
        Preconditions.namedArgumentNonNull(filter, "filter");
    
        if (isSet() && filter.test(value)) {
            return this;
        } else {
            return empty();
        }
    }
    
    /**
     * 当容器内有值，通过某方式将其值映射为另一容器，并返回其容器。
     *
     * @param mapper 将容器内的值映射为另一容器的方法
     * @param <U>    映射目标的容器参数类型
     * @return 映射后的容器，或空容器
     * @throws IllegalArgumentException mapper 为 null 时
     */
    public <U> Container<U> flatMap(Function<T, Container<U>> mapper) {
        if (isSet()) {
            return mapper.apply(value);
        } else {
            return empty();
        }
    }
    
    /**
     * 当容器非空，返回容器本身，否则返回指定的另一容器
     *
     * @param container 指定的另一容器
     * @return 本容器或指定的另一容器
     * @throws IllegalArgumentException container 为 null 时
     */
    public Container<T> or(Container<T> container) {
        Preconditions.namedArgumentNonNull(container, "container");
    
        if (isEmpty()) {
            return container;
        } else {
            return this;
        }
    }
    
    /**
     * 将容器转化为 JDK 内含的 {@link Optional}
     *
     * @return 代表当前容器值的 Optional 变量
     */
    public Optional<T> toOptional() {
        return map(Optional::ofNullable).orElseGet(Optional::empty);
    }
    
    /**
     * 获取容器内的值，或返回默认值
     *
     * @param defaultValue 默认值
     * @return 当容器非空，返回容器内的值，否则返回默认值
     */
    public T orElse(T defaultValue) {
        return orElseGet(() -> defaultValue);
    }
    
    /**
     * 获取容器内的值，或通过指定方式返回默认值
     *
     * @param supplier 获取默认值的方式
     * @return 当容器非空，返回容器内的值，否则通过指定方式获取默认值并返回
     * @throws IllegalArgumentException supplier 为 null 时
     */
    public T orElseGet(Supplier<T> supplier) {
        Preconditions.namedArgumentNonNull(supplier, "supplier");
    
        if (isSet()) {
            return get();
        } else {
            return supplier.get();
        }
    }
    
    /**
     * 获取容器内的值，或抛出异常
     *
     * @return 容器内的值
     * @throws IllegalArgumentException 容器为空时
     * @see #orElseThrow(Supplier) 与当前方法类似
     */
    public T orElseThrow() {
        return orElseThrow(NoSuchElementException::new);
    }
    
    /**
     * 获取容器内的值，或抛出异常
     *
     * @param message 异常信息
     * @return 容器内的值
     * @throws IllegalArgumentException 容器为空，或 message 为空时
     * @see #orElseThrow(Supplier) 与当前方法类似
     */
    public T orElseThrow(String message) {
        Preconditions.namedArgumentNonNull(message, "message");
    
        return orElseThrow(() -> new NoSuchElementException(message));
    }
    
    /**
     * 获取容器内的值，或抛出异常
     *
     * @param supplier 获得异常的方式
     * @param <E>      异常类型
     * @return 容器内的值
     * @throws E 容器为空时
     */
    public <E extends Throwable> T orElseThrow(Supplier<E> supplier) throws E {
        Preconditions.namedArgumentNonNull(supplier, "supplier");
    
        if (isSet()) {
            return value;
        } else {
            throw supplier.get();
        }
    }
}