package jxd.bxb.test.study.ReflectStudy;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * @ClassName TypeLiteral
 * @Description TODO
 * @Author 白新报
 * @Date 2022/11/25 21:37
 * @Version 1.0
 **/
public class TypeLiteral<T> {

    private Type type;

    public String sasaa;

    public TypeLiteral(Type type) {
        this.type = type;
    }

    public TypeLiteral() {
        Type parentType = getClass().getGenericSuperclass();
        if (parentType instanceof ParameterizedType) {
            type = ((ParameterizedType)parentType).getActualTypeArguments()[0];
        } else {
            throw new UnsupportedOperationException("Construct as new TypeLiteral<...>(){}");
        }
    }

    public static TypeLiteral<?> of(Type type) {
        return new TypeLiteral<Object>(type);
    }

    @Override
    public String toString() {
        if (type instanceof Class) return ((Class<?>) type).getName();
        else return type.toString();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof TypeLiteral
                && type.equals(((TypeLiteral<?>) o).type);
    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }
}
