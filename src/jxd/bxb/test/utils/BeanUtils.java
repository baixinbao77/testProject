package jxd.bxb.test.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author baixinbao
 * @create 2022/7/12
 */
public class BeanUtils {

    public static void copyProperties(Object source, Object target , List<String> params)  {
        if (StringUtil.isNotEmpty(source , target) && StringUtil.isNotEmpty(params)) {
            Arrays.stream(target.getClass().getDeclaredFields()).forEach(
                    ta -> {
                        try {
                            ta.set(ta.getName(), source.getClass().getDeclaredField(ta.getName()).get(source));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
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
                        fd.set(fd , source.getClass().getDeclaredField(pa).get(source));
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
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

}
