package jxd.bxb.test.study.ReflectStudy;

import java.util.ArrayList;

/**
 * @ClassName TypeLiterals
 * @Description TODO
 * @Author 白新报
 * @Date 2022/11/25 21:58
 * @Version 1.0
 **/
public class TypeLiterals {
    public static class Sample{
        ArrayList<Integer> nums;
        ArrayList<Character> chars;
        ArrayList<String> strings;

        public Sample() {
            nums = new ArrayList<>();
            nums.add(42);nums.add(1728);
            chars = new ArrayList<>();
            chars.add('H');chars.add('i');
            strings = new ArrayList<>();
            strings.add("hello"); strings.add(" World");
        }
    }

    private static <T> String join(String separtor , ArrayList<T> elements) {
        StringBuilder result = new StringBuilder();
        for (T e : elements) {
            if (result.length() > 0) {
                result.append(separtor);
            }
            result.append(e.toString());
        }
        return result.toString();
    }

    public static void main(String[] args) throws Exception{
        Formatter formatter = new Formatter();
        formatter.forType(new TypeLiteral<ArrayList<Character>>(){}, lst -> "\"" + join("" , lst) + "\"");
        formatter.forType(new TypeLiteral<ArrayList<Integer>>() {}, lst -> join(" ", lst));
        System.out.println(formatter.formatFields(new Sample()));

    }
}
