package jxd.bxb.test.utils;

import jxd.bxb.test.utils.exception.NotFoundException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author BXBstart
 * @create 2022-09-22 20:37
 */
public class Bean {
    private Field field;
    private Method getmethod;
    private Method setMethod;
    private Class clazz;
    private Constructor setConstructor = null;

    private Bean(Field field, Method getmethod, Method setMethod , Class clazz) {
        this.field = field;
        this.getmethod = getmethod;
        this.setMethod = setMethod;
        this.clazz = clazz;
    }
    private Bean(Field field, Method getmethod, Method setMethod) {
        this.field = field;
        this.getmethod = getmethod;
        this.setMethod = setMethod;
    }

    public static Bean getBean(Field field , Class clazz) {
        Method getMethod = null;
        Method setMethod = null;
        try {
            getMethod = clazz.getDeclaredMethod(StringUtil.getMethod(field));
            setMethod = clazz.getDeclaredMethod(StringUtil.setMethod(field.getName()) , getMethod.getReturnType());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return new Bean(field , getMethod , setMethod , clazz);
    }

    public static List<Bean> getBeans(Class clazz) {
        return Arrays.stream(clazz.getDeclaredFields()).map(field1 -> {
            try {
                return new Bean(field1 , clazz.getDeclaredMethod(StringUtil.getMethod(field1)) , clazz.getDeclaredMethod(StringUtil.setMethod(field1.getName()) , field1.getType()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
    }


    public Object invokeGet(Object obj) {
        if (obj == null) {
            throw new NotFoundException(clazz.getName() + "对象未创建");
        }
        Object value = null;
        try {
            value = this.getmethod.invoke(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return value;
    }

    public Bean invokeset(Object obj , Object... values) {
        try {
            if (this.setConstructor == null) {
                this.setConstructor = BeanUtils.getConstructorByValue(this.getmethod, values);
            }
            if (this.setConstructor != null) {
                this.setMethod.invoke(obj , this.setConstructor.newInstance(values));
            }
        } catch (Exception e) {
        }
        return this;
    }




    public Field getField() {
        return field;
    }

    public Method getGetmethod() {
        return getmethod;
    }

    public Method getSetMethod() {
        return setMethod;
    }

    public Class getClazz() {
        return clazz;
    }


}
