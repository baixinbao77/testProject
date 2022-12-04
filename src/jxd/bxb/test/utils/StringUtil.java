package jxd.bxb.test.utils;

import java.io.*;
import java.lang.reflect.Field;
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
        str = str.trim();
        String s = "";
        String[] list = str.split("_");
        s += list[0].toLowerCase(Locale.ROOT);
        for (int i = 1; i < list.length; i++) {
            s += list[i].substring(0, 1).toUpperCase(Locale.ROOT);
            s += list[i].toLowerCase(Locale.ROOT).substring(1);
        }
        return s;
    }

    public static String strToPsms(String str) {
        str = str.trim();
        String s = "";
        String[] list = str.split("_");
        s += list[0].trim().substring(0 , 1).toLowerCase(Locale.ROOT);
        s += list[0].trim().substring(1);
        for (int i = 1; i < list.length; i++) {
            s += list[i].trim().substring(0, 1).toUpperCase(Locale.ROOT);
            s += list[i].trim().toLowerCase(Locale.ROOT).substring(1);
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
        return obj == null || obj.toString().trim().equals("");
    }

    public static boolean isEmpty(Object... obj) {
        for (Object object : obj) {
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

    public static <T> List<T> move(List<T> list , int param , int position) {
        if (isNotEmpty(list)) {
            int size = list.size();
            if (size < position || size < param) {
                return list;
            } else {
                T start = list.get(param - 1);
                list.remove(start);
                list.add(position - 1, start);
            }
        }
        return list;
    }

    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.size() == 0;
    }

    public static <T> boolean isNotEmpty(List<T> list) {
        return list != null && list.size() != 0;
    }

    public static boolean isNotEmpty(Object obj) {
        return obj != null && !(obj.toString().trim().equals(""));
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


    public static Boolean have(Object source , Object... targets) {
        for (Object target : targets) {
            if (source.equals(target)) {
                return true;
            }
        }
        return false;
    }

    public static Boolean haveTextIgnoreCase(String source , String... targets) {
        for (String target : targets) {
            if (source.equalsIgnoreCase(target)) {
                return true;
            }
        }
        return false;
    }
    public static Boolean haveText(String source , String... targets) {
        for (String target : targets) {
            if (source.equals(target)) {
                return true;
            }
        }
        return false;
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

    public static String scannerStr() {
        Scanner scanner = new Scanner(System.in);
        String result = "";
        while (scanner.hasNextLine()) {
            result += scanner.nextLine();
        }
        return result;
    }

    public static LinkedList<String> methodFieldToList(String str) {
        if (str.contains("(") || str.contains(")")) {
            str = str.substring(str.indexOf("(") + 1 , str.indexOf(")"));
        }
        LinkedList<String> result = new LinkedList<>();
        String[] split = str.split(",");
        for (String s : split) {
            result.add(checkField(s).trim());
        }
        return result;
    }



    public static String checkField(String str) {
        if (str.contains("=")) {
            str = str.substring(0 , str.indexOf("="));
        }
        String s = new String(str).trim();
        str = str.toLowerCase().trim();
        if (str.contains("int") || str.contains("integer")) {
            return str.contains("integer") ? s.substring(str.indexOf("integer") + 7) : s.substring(str.indexOf("int") + 3).trim();
        }
        if (str.contains("string")) {
            return s.substring(str.indexOf("string") + 6).trim();
        }
        if (str.contains("decimal")) {
            return s.substring(str.indexOf("decimal") + 7).trim();
        }
        if (str.contains("datetime")) {
            return s.substring(str.indexOf("datetime") + 8).trim();
        }
        return "";
    }

    public static Integer getFieldPosition(String str) {
        String s = new String(str).trim();
        str = str.toLowerCase().trim();
        if (str.contains("int") || str.contains("integer")) {
            return str.contains("int") ? str.indexOf("int") + 4 : str.indexOf("integer") + 8;
        }
        if (str.contains("string")) {
            return str.indexOf("string") + 7;
        }
        if (str.contains("decimal")) {
            return str.indexOf("decimal") + 8;
        }
        if (str.contains("datetime")) {
            return str.indexOf("datetime") + 9;
        }
        return null;
    }

    public static StringBuilder addHuan(StringBuilder builder , int len) {
        String s = builder.toString();
        String[] split = s.split(",");
        builder = builder.delete(0 , builder.length());
        int j = 0;
        int k = 0;
        for (String s1 : split) {
            j++;
            k++;
            if (k == split.length) {
            } else if (j == len - 1) {
                s1 = s1 + " , \n";
                j = 0;
            } else {
                s1 = s1 +" , ";
            }
            builder.append(s1);
        }
        return builder;
    }

    public static String getMethod(Field field) {
        if (isEmpty(field)) return "";
        String fieldName = field.getName();
        return new StringBuffer().append(field.getType().equals(boolean.class) ? "is" : "get")
                .append(fieldName.substring(0,1).toUpperCase())
                .append(fieldName.substring(1)).toString();
    }

    public static String setMethod(String fieldName) {
        if (isEmpty(fieldName)) return "";
        return new StringBuffer().append("set")
                .append(fieldName.substring(0,1).toUpperCase())
                .append(fieldName.substring(1)).toString();
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
