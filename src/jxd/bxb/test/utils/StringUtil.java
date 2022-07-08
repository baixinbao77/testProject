package jxd.bxb.test.utils;

import com.sun.javafx.binding.StringFormatter;
import sun.nio.cs.FastCharsetProvider;

import java.io.*;
import java.util.*;

/**
 * @author BXBstart
 * @create 2022-06-16 21:01
 */
public class StringUtil {

    /**
     * 获取字符串里面的大写字符的位置的列表
     * @param str
     * @return
     */
    public static List<Integer> getStrUpperPositionList(String str) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt <=90 && charAt >= 65) {
                list.add(Integer.valueOf(i));
            }
        }
        list.add(str.length());
        return list;
    }

    public static String strToStr(String str) {
        String s = "";
        String[] list = str.split("_");
        s += list[0].toLowerCase(Locale.ROOT);
        for (int i = 1; i < list.length; i++) {
            s += list[i].substring(0, 1).toUpperCase(Locale.ROOT);
            s += list[i].toLowerCase(Locale.ROOT).substring(1);
        }
        return s;
    }

   

    public static String setName(String str) {
        String s = "";
        s += str.substring(0, 1).toUpperCase(Locale.ROOT);
        s += str.substring(1);
        return s;
    }

    public static boolean isEmpty(Object obj) {
        return obj == null || obj.toString().equals("");
    }

    public static boolean isEmpty(Object... obj) {
        Object[] objects = obj;
        for (Object object : objects) {
            if (isEmpty(object)) {
                return true;
            }
        }
        return false;
    }


    public static <T> boolean isEmpty(List<T>... lists) {
        if (lists == null || lists.length == 0) {
            return true;
        }
        for (List<T> t: lists) {
            if (isEmpty(t)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.size() == 0;
    }

    public static <T> boolean isNotEmpty(List<T> list) {
        return list != null && list.size() != 0;
    }

    public static boolean isNotEmpty(Object obj) {
        return obj != null && !(obj.toString().equals(""));
    }

    public static boolean isNotEmpty(Object... objects) {
        if (objects == null || objects.length == 0) {
            return false;
        }
        for (Object object : objects) {
            if (isEmpty(object)) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean isNotEmpty(List<T>... lists) {
        if (lists == null || lists.length == 0) {
            return false;
        }
        for (List list : lists) {
            if (isEmpty(list)) {
                return false;
            }
        }
        return true;
    }


    public static Map<String , Object> getPropertiesMap(String filePath) {
        InputStream inputStream = null;
        Map<String , Object> map = new HashMap<>();
        try {
            inputStream = new FileInputStream(new File(filePath));
            Properties properties = new Properties();
            properties.load(inputStream);
            Set<String> set = properties.stringPropertyNames();
            for (String s : set) {
                map.put(s , properties.getProperty(s));
            }
            return map;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static <T , R> Map<String , Object> listToMap (List<T> keys , List<R> values) {
        Map<String , Object> map = new HashMap<>();
        if (isEmpty(keys , values)) {
            return map;
        }
        if (equal(keys, values)) {
            return map;
        }
        for (int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i).toString(), values.get(i));
        }
        return map;

    }

    private static <T , R> boolean equal(List<T> keys , List<R> values) {
        return keys == values;
    }


    public static boolean isEmpty (Map... maps) {
        if (maps == null || maps.length == 0) {
            return true;
        }
        for (Map map : maps) {
            if (map.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static <T ,R> List<R> mapValueToList (Map<T , R> map) {
        List<R> list = new ArrayList<>();
        for (T t : map.keySet()) {
            list.add(map.get(t));
        }
        return list;
    }

    public static <T ,R> List<T> mapKeyToList (Map<T , R> map) {
        List<T> list = new ArrayList<>();
        for (T t : map.keySet()) {
            list.add(t);
        }
        return list;
    }

    public static <T> List<T> initList (List<T> list , T t) {
        List<T> result = new ArrayList<>();
        for (T t1 : list) {
            result.add(t);
        }
        return result;
    }

//    public static List<Map<String , Object>> strToMap(String str) throws InstantiationException, IllegalAccessException {
//        str = str.substring(1 , str.length() -1);
//        if (str.indexOf("{") > -1) {
//
//        }
//        List<Map<String , Object>> result = new ArrayList<>();
//        String[] list = str.split(",");
//
//        for (String s : list) {
//            String[] oneList = s.split(":");
//            String key = oneList[0].substring(oneList[1].indexOf("\""), oneList[0].indexOf("\""));
//            String value = oneList[1];
//            result.add(setValue(key , value));
//        }
//        return result;
//    }
//
//    public static List<String> getScannerList() {
//        List<String> list = new ArrayList<>();
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNextLine()) {
//            String next = scanner.nextLine();
//            list.add(next);
//            if (next.equals("") || next == null) {
//                break;
//            }
//        }
//        return list;
//    }
//
//    public static String parseString(String str) {
//        if (str.indexOf("{") > -1) {
//            if (str.indexOf("[") > -1) {
//                if (str.indexOf("{") < str.indexOf("[")) {
//                }
//            }
//        }
//        return "";
//    }
//
//
//    private static Map<String , Object> setValue(String key, Object value) {
//        Map<String , Object> result = new HashMap<>();
//        result.put(key, value);
//        return result;
//    }

}
