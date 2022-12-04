package jxd.bxb.test.study.ReflectStudy;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @ClassName Formatter
 * @Description TODO
 * @Author 白新报
 * @Date 2022/11/25 21:48
 * @Version 1.0
 **/
public class Formatter {

    private Map<TypeLiteral<?> , Function<? , String>> rules = new HashMap<>();

    public String sa ;

    public <T> void forType(TypeLiteral<T> type, Function<T , String> formatterForType) {
        rules.put(type , formatterForType);
    }

    public String formatFields(Object obj) {
        StringBuilder result = new StringBuilder();
        for (Field field : obj.getClass().getDeclaredFields()) {
            result.append(field.getName());
            result.append("=");
            field.setAccessible(true);
            Function<?, String> formatterForType = rules.get(TypeLiteral.of(field.getGenericType()));
            if (formatterForType != null) {
                @SuppressWarnings("unchecked")
                Function<Object, String> objectFormatter = (Function<Object, String>) formatterForType;
                try {
                    result.append(objectFormatter.apply(field.get(obj)));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    result.append(field.get(obj).toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        result.append("\n");
        return result.toString();
    }

}
