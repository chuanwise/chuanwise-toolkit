package cn.chuanwise.common.util;

import lombok.Data;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 类型相关工具
 *
 * @author Chuanwise
 */
public class Types
        extends StaticUtilities {
    @Data
    private static class TypeTree {
        protected final TypeTree parent;

        protected final Type currentType;
        protected final Class<?> currentClass;

        protected final Set<TypeTree> sons = new HashSet<>();

        public TypeTree(TypeTree parent, Type currentType) {
            Preconditions.nonNull(currentType, "current type");
            this.currentType = currentType;
            this.parent = parent;

            if (currentType instanceof Class) {
                currentClass = (Class<?>) currentType;
            } else if (currentType instanceof ParameterizedType) {
                final Type rawType = ((ParameterizedType) currentType).getRawType();
                if (rawType instanceof Class) {
                    currentClass = (Class<?>) rawType;
                } else {
                    throw new IllegalStateException();
                }
            } else {
                throw new IllegalStateException();
            }

            final Type genericSuperclass = currentClass.getGenericSuperclass();

            if (Objects.nonNull(genericSuperclass)) {
                sons.add(new TypeTree(this, genericSuperclass));
            }
            for (Type genericInterface : currentClass.getGenericInterfaces()) {
                sons.add(new TypeTree(this, genericInterface));
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            TypeTree typeTree = (TypeTree) o;
            return Objects.equals(currentType, typeTree.currentType);
        }

        @Override
        public int hashCode() {
            return Objects.hash(currentType);
        }

        @Override
        public String toString() {
            return currentType.toString();
        }

        public List<List<TypeTree>> pathOf(Class<?> clazz) {
            final List<List<TypeTree>> results = pathOf0(clazz);
            if (results.isEmpty()) {
                return java.util.Collections.emptyList();
            } else {
                return results.stream()
                        .map(java.util.Collections::unmodifiableList)
                        .collect(Collectors.toList());
            }
        }

        private List<List<TypeTree>> pathOf0(Class<?> clazz) {
            Preconditions.nonNull(clazz, "class");

            if (Objects.equals(currentClass, clazz)) {
                final List<List<TypeTree>> paths = new ArrayList<>();
                final List<TypeTree> singlePath = new ArrayList<>();
                singlePath.add(this);
                paths.add(singlePath);
                return paths;
            }

            final List<List<TypeTree>> lists = sons.stream()
                    .map(x -> x.pathOf0(clazz))
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
            lists.forEach(x -> x.add(0, this));
            return lists;
        }
    }

    /**
     * 获得某类作为某仅具备唯一泛型参数的泛型类的子类，在泛型参数处的实际类型。
     *
     * @param sonType      子类型
     * @param genericClass 仅具备唯一泛型参数的泛型类
     * @return genericClass 的泛型参数实际类型
     * @throws IllegalArgumentException sonType, genericClass 为 null 时，或 sonType 和 genericClass 相等时，
     *                                  或 genericClass 非模板类时，或 sonType 并非 genericClass 的子类时。
     * @throws IllegalStateException    计算出现错误时，或缺少必要信息时
     */
    public static Type getTypeParameterType(Type sonType, Class<?> genericClass) {
        Preconditions.nonNull(sonType, "son class");
        Preconditions.nonNull(genericClass, "generic class");

        final TypeVariable<? extends Class<?>>[] typeParameters = genericClass.getTypeParameters();
        Preconditions.argument(typeParameters.length != 0, "class: " + genericClass.getName() + " is not generic.");
        Preconditions.argument(typeParameters.length == 1, "there are multiple type parameters in generic class: " + genericClass.getName());

        return getTypeParameterType(sonType, genericClass, 0);
    }

    /**
     * 计算类型参数的实际类型
     *
     * @param sonType       子类型
     * @param genericClass  模板类类型
     * @param parameterName 类型参数名
     * @return 对应位置的模板参数实际类型
     * @throws IllegalArgumentException  sonType, genericClass 为 null 时，或 sonType 和 genericClass 相等时，
     *                                   或 genericClass 非模板类时，或 sonType 并非 genericClass 的子类时，
     *                                   或 parameterName 为空时。
     * @throws IndexOutOfBoundsException parameterIndex 非法时
     * @throws IllegalStateException     计算出现错误时，或缺少必要信息时
     */
    public static Type getTypeParameterType(Type sonType, Class<?> genericClass, String parameterName) {
        Preconditions.namedArgumentNonNull(sonType, "son class");
        Preconditions.namedArgumentNonNull(genericClass, "generic class");
        Preconditions.namedArgumentNonEmpty(parameterName, "parameter name");

        // 寻找泛型参数
        final TypeVariable<? extends Class<?>>[] typeParameters = genericClass.getTypeParameters();
        Preconditions.argument(typeParameters.length != 0, "class: " + genericClass.getName() + " is not generic.");

        // 寻找泛型参数的索引
        int parameterIndex = -1;
        for (int i = 0; i < typeParameters.length; i++) {
            if (Objects.equals(typeParameters[i].getName(), parameterName)) {
                parameterIndex = i;
                break;
            }
        }
        Preconditions.argument(parameterIndex != -1, "there is no such type parameter named \"" + parameterName + "\" in class: " + genericClass.getName());

        return getTypeParameterType(sonType, genericClass, parameterIndex);
    }

    /**
     * 计算类型参数的实际类型
     *
     * @param sonType        子类型
     * @param genericClass   模板类类型
     * @param parameterIndex 类型参数索引
     * @return 对应位置的模板参数实际类型
     * @throws IllegalArgumentException  sonType, genericClass 为 null 时，或 sonType 和 genericClass 相等时，
     *                                   或 genericClass 非模板类时，或 sonType 并非 genericClass 的子类时。
     * @throws IndexOutOfBoundsException parameterIndex 非法时
     * @throws IllegalStateException     计算出现错误时，或缺少必要信息时
     */
    public static Type getTypeParameterType(Type sonType, Class<?> genericClass, int parameterIndex) {
        Preconditions.namedArgumentNonNull(sonType, "son type");
        Preconditions.namedArgumentNonNull(genericClass, "generic class");

        Preconditions.argument(!Objects.equals(sonType, genericClass), "can not find the type parameter type of a generic class itself.");

        // 寻找泛型参数
        final TypeVariable<? extends Class<?>>[] typeParameters = genericClass.getTypeParameters();
        Preconditions.argument(typeParameters.length != 0, "class: " + genericClass.getName() + " is not generic.");
        Preconditions.index(parameterIndex, typeParameters.length, "type parameter index");
//        Preconditions.argument(!Objects.equals(sonType, genericClass), "son class can not be generic class itself.");

        // 搜索该类型的继承树
        // 并寻找到指定的泛型类
        final TypeTree typeTree = new TypeTree(null, sonType);
        final List<List<TypeTree>> lists = typeTree.pathOf(genericClass);
        Preconditions.argument(!lists.isEmpty(), "type: " + sonType.getTypeName() + " is not a son class of class: " + genericClass.getName());
        final List<TypeTree> inheritanceChain = lists.get(0);

        // 顺着 path 一路往前找
        // 这里的长度如果大于 1，可以顺着引用链查找
        Preconditions.state(inheritanceChain.size() > 0, "inheritance chain length is not greater than 0!");

        for (int i = inheritanceChain.size() - 1; i > 0; i--) {
            final TypeTree parent = inheritanceChain.get(i);
            final TypeTree son = inheritanceChain.get(i - 1);

            final Type parentType = parent.getCurrentType();
            final Type currentType = son.getCurrentType();

            if (parentType instanceof ParameterizedType) {
                final ParameterizedType parentParameterizedType = (ParameterizedType) parentType;
                final Type parentTypeArgument = parentParameterizedType.getActualTypeArguments()[parameterIndex];

                if (parentTypeArgument instanceof Class) {
                    return parentTypeArgument;
                }
                if (parentTypeArgument instanceof TypeVariable) {
                    // 如果 ArrayList<E> 继承自 AbstractList<I>
                    // 需要找到这个 I 和 E 的对应关系
                    final String name = ((TypeVariable<?>) parentTypeArgument).getName();

                    // 此时子类必须是 ParameterizedType
                    Preconditions.state(currentType instanceof ParameterizedType, "son class of " + parentType.getTypeName() + " is not a parameterized type");
                    final ParameterizedType sonParameterizedType = (ParameterizedType) currentType;

                    // 如果子的模板参数里没有正在寻找的 I
                    // 则应该已经是 Class，已经 return 了
                    // 所以只需要在子的模板参数中寻找
                    final TypeVariable<? extends Class<?>>[] sonTypeParameters = son.getCurrentClass().getTypeParameters();
                    parameterIndex = -1;
                    for (int j = 0; j < sonTypeParameters.length; j++) {
                        final TypeVariable<? extends Class<?>> typeParameter = sonTypeParameters[j];
                        if (Objects.equals(typeParameter.getName(), name)) {
                            parameterIndex = j;
                            break;
                        }
                    }

                    Preconditions.state(parameterIndex != -1,
                            "can not find the type parameter mapper between class: " + sonParameterizedType.getTypeName() + " and " + parentParameterizedType.getTypeName());
                    continue;
                }

                return parentTypeArgument;
            } else {
                throw new IllegalStateException();
            }
        }

        // 如果还没有找到，或者只有一个元素，则第一个元素就是所需的
        if (sonType instanceof ParameterizedType) {
            final ParameterizedType parameterizedType = (ParameterizedType) sonType;
            final Type[] typeArguments = parameterizedType.getActualTypeArguments();
            if (Indexes.isLegal(parameterIndex, typeArguments.length)) {
                return typeArguments[parameterIndex];
            }
        }

        throw new IllegalStateException("can not find the type parameter chain between inheritance chain: " + inheritanceChain);
    }

    /**
     * 计算某类型对应的类对象
     *
     * @param type 类型
     * @return 该类对应的类对象
     * @throws IllegalArgumentException type 为 null，或 type 并不是某种 class 时
     */
    public static Class<?> getTypeClass(Type type) {
        Preconditions.namedArgumentNonNull(type, "type");

        if (type instanceof Class) {
            return ((Class<?>) type);
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = ((ParameterizedType) type);
            do {
                final Type rawType = parameterizedType.getRawType();
                if (rawType instanceof ParameterizedType) {
                    parameterizedType = ((ParameterizedType) rawType);
                    continue;
                }
                if (rawType instanceof Class) {
                    return ((Class<?>) rawType);
                }
            } while (true);
        }

        throw new IllegalArgumentException("can not find the raw class of type: " + type.getTypeName());
    }

    /**
     * 获得某类作为某仅具备唯一泛型参数的泛型类的子类，在泛型参数处的实际类型。
     *
     * @param sonType      子类型
     * @param genericClass 仅具备唯一泛型参数的泛型类
     * @return genericClass 的泛型参数实际类型
     * @throws IllegalArgumentException sonType, genericClass 为 null 时，或 sonType 和 genericClass 相等时，
     *                                  或 genericClass 非模板类时，或 sonType 并非 genericClass 的子类时。
     * @throws IllegalStateException    计算出现错误时，或缺少必要信息时
     */
    public static Class<?> getTypeParameterClass(Type sonType, Class<?> genericClass) {
        return getTypeClass(getTypeParameterType(sonType, genericClass));
    }

    /**
     * 获得某泛型类的子类，在某泛型参数处的实际类型。
     *
     * @param sonType       子类型
     * @param genericClass  仅具备唯一泛型参数的泛型类
     * @param parameterName 泛型参数名
     * @return genericClass 名为 parameterName 的泛型参数实际类型
     * @throws IllegalArgumentException sonType, genericClass 为 null 时，或 sonType 和 genericClass 相等时，
     *                                  或 genericClass 非模板类时，或 sonType 并非 genericClass 的子类时，
     *                                  或 parameterName 为空时。
     * @throws IllegalStateException    计算出现错误时，或缺少必要信息时
     */
    public static Class<?> getTypeParameterClass(Type sonType, Class<?> genericClass, String parameterName) {
        return getTypeClass(getTypeParameterType(sonType, genericClass, parameterName));
    }

    /**
     * 获得某泛型类的子类，在某泛型参数处的实际类型。
     *
     * @param sonType        子类型
     * @param genericClass   仅具备唯一泛型参数的泛型类
     * @param parameterIndex 泛型参数序号
     * @return genericClass 的第 parameterIndex 个泛型参数实际类型
     * @throws IllegalArgumentException  sonType, genericClass 为 null 时，或 sonType 和 genericClass 相等时，
     *                                   或 genericClass 非模板类时，或 sonType 并非 genericClass 的子类时。
     * @throws IndexOutOfBoundsException parameterIndex 非法时
     */
    public static Class<?> getTypeParameterClass(Type sonType, Class<?> genericClass, int parameterIndex) {
        return getTypeClass(getTypeParameterClass(sonType, genericClass, parameterIndex));
    }
}