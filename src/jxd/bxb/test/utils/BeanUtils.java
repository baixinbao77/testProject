package jxd.bxb.test.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author baixinbao
 * @create 2022/7/12
 */
public class BeanUtils {

    public static void copyProperties(Object source, Object target , List<String> params)  {
        if (StringUtil.isNotEmpty(source , target) && StringUtil.isEmpty(params)) {
            Arrays.stream(target.getClass().getDeclaredFields()).forEach(
                    ta -> {
                        try {
                            ta.setAccessible(true);
                            setValue(ta , getFieldValue(source.getClass().getDeclaredField(ta.getName()) , source) , target);
                            ta.setAccessible(false);
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                        }
                    }
            );
        } else {
            params.stream().forEach(pa -> {
                try {
                    Field fd = target.getClass().getDeclaredField(pa);
                    if (fd != null) {
                        setValue(fd  ,getFieldValue(source.getClass().getDeclaredField(pa) , source) , target);
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static <T , R> List<R> convertListToList (List<T> source , R target) {
        List<R> list = new ArrayList<>();
        if (StringUtil.isNotEmpty(source , target)) {
            source.stream().forEach(so -> list.add(convertProperties(so , target)));
        }
        return list;
    }

    public static void copyProperties (Object source , Object target) {
        copyProperties(source, target, null);
    }

    public static <T> T convertProperties (Object source , T target) {
        copyProperties(source, target);
        return target;
    }

    private static String getFieldValue (Field field , Object obj) {
        field.setAccessible(true);
        String result = "";
        try {
            result = field.get(obj) == null ? "" : field.get(obj).toString();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static void setValue (Field field , String value , Object obj) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
        field.setAccessible(true);
        try {
            String typeName = field.getGenericType().getTypeName();
            if (typeName.indexOf("Integer") > -1) {
                    field.set(obj , StringUtil.isEmpty(value) ? null : Integer.valueOf(value));
            }
            else if (typeName.indexOf("String") > -1) {
                field.set(obj , StringUtil.isEmpty(value) ? null : value);
            }
            else if (typeName.indexOf("Date") > -1) {
                field.set(obj, StringUtil.isEmpty(value) ? null : sdf.parse(value));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    private Object getValueByField (Field field , Object obj) {
        field.setAccessible(true);
        String fieldName = field.getName();
        String firstCard = fieldName.substring(0,1).toUpperCase();
        Object result = new Object();
        try {
            Method me = obj.getClass().getDeclaredMethod("get" + firstCard + fieldName.substring(1));
            result = me.invoke(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        field.setAccessible(false);
        return result;
    }

    public static Class getEntityClass (Class clazz) {
        Class entityClass = null;
        Type genericSuperclass = clazz.getGenericSuperclass();
        Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
        Type actualType = actualTypeArguments[0];
        try {
            entityClass = Class.forName(actualType.getTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return entityClass;
    }



}
