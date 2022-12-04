package jxd.bxb.test.utils;




import java.lang.reflect.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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

    public static void mapToBean(Object target , Map<String , Object> source) {
        Bean.getBeans(target.getClass()).stream().forEach(bean -> {
            bean.invokeset(target , source.get(bean.getField().getName()));
        });
    }

    public static void copyProperties (Object source , Object target) {
        copyProperties(source, target, null);
    }

    public static <T> T convertProperties (Object source , T target) {
        copyProperties(source, target);
        return target;
    }


    public static String getFieldValue (Field field , Object obj) {
        if (StringUtil.isEmpty(field , obj)) {
            return "";
        }
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


    public static Object getValueByField (Field field , Object obj) {
        Object result = new Object();
        try {
            result = getGetMethod(field.getDeclaringClass() , field).invoke(obj);
        } catch (Exception e) {
            throw new BeanException(e.getMessage());
        }
        return result;
    }


    public static Boolean allNull(Object obj) {
        Boolean result = true;
        if (StringUtil.isNotEmpty(obj)) {
            result = !Bean.getBeans(obj.getClass()).stream()
                    .anyMatch(bean -> {
                        return StringUtil.isNotEmpty(bean.invokeGet(obj));
                    });
        }
        return result;
    }

    public static Boolean allNotNull(Object obj) {
        Boolean result = false;
        if (StringUtil.isNotEmpty(obj)) {
            result = Bean.getBeans(obj.getClass()).stream()
                    .allMatch(bean -> {
                        return StringUtil.isNotEmpty(bean.invokeGet(obj));
                    });
        }
        return result;
    }



    public static Map<String , Object> beanToMap(Object obj) {
        Map<String , Object> map = new HashMap<>();
        if (StringUtil.isNotEmpty(obj)) {
            map = Bean.getBeans(obj.getClass()).stream()
                    .collect(HashMap::new ,(result , bean) -> {
                        result.put(bean.getField().getName() , bean.invokeGet(obj));
                    }, HashMap::putAll);
        }
        return map;
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




    public static Constructor<?> getConstructorByValue(Method method , Object... values) {
        if (method == null) {
            return null;
        }
        Constructor<?>[] constructors = method.getReturnType().getDeclaredConstructors();
        Object result = null;
        for (int i = 0; i < constructors.length; i++) {
            constructors[i].setAccessible(true);
            try {
                result = constructors[i].newInstance(values);
                if (result != null) {
                    return constructors[i];
                }
            } catch (Exception e) {
                continue;
            }
            constructors[i].setAccessible(false);
        }
        return null;
    }



    public static Object getMethodsByReturnType(Class<?> sourceClass, Class<?> returnTypeClass) {
        if (returnTypeClass == null || sourceClass == null) {
            return null;
        }
        if (returnTypeClass.equals(sourceClass)) {
            return Arrays.stream(sourceClass.getDeclaredConstructors()).collect(Collectors.toList());
        }
        List<Method> methods = Arrays.stream(sourceClass.getDeclaredMethods()).filter(method -> method.getReturnType().equals(returnTypeClass)).collect(Collectors.toList());
        if (methods.size() > 0) {
            return methods;
        }
        return null;
    }

    public static Object getValueByGetMethod(Object source , String fieldName) {
        Method getMethod = getGetMethod(source.getClass(), fieldName);
        try {
            return getMethod.invoke(source);
        } catch (Exception e) {
        }
        return null;
    }


    public static Method getGetMethod(Class<?> clazz, String fieldName) {
        try {
            return getGetMethod(clazz , clazz.getDeclaredField(fieldName));
        } catch (Exception e) {
            return null;
        }
    }

    public static Method getGetMethod(Class<?> clazz, Field field) {
        try {
            return clazz.getDeclaredMethod(StringUtil.getMethod(field));
        } catch (Exception e) {
            return null;
        }
    }



    private static class BeanException extends RuntimeException{
        private BeanException(String message) {
            super(message);
        }
    }


}
