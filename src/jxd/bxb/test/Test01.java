package jxd.bxb.test;

import jxd.bxb.test.Connect.MyConnect;
import jxd.bxb.test.Connect.PgConnect;
import jxd.bxb.test.utils.StringUtil;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.*;

public class Test01 {

    @Test
    public void field(){
        while(true) {
            String s = "";
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            String[] list = new String[str.length()];
            if (str.indexOf(" ") > -1) {
                list = str.split(" ");
            } else {
                list = str.split("_");
            }
            s += list[0].toLowerCase(Locale.ROOT);
            for (int i = 1; i < list.length; i++) {
                s += list[i].substring(0 , 1).toUpperCase(Locale.ROOT);
                s += list[i].toLowerCase(Locale.ROOT).substring(1);
            }
            System.out.println(s);
        }

    }

    @Test
    public void test(){
        Class<MyConnect> myClass = MyConnect.class;
        Method[] methods = myClass.getMethods();
        for (Method method : methods) {
            String name = method.getName();
        }
    }

    @Test
    public void test1(){
        System.out.println("\\");
    }

    @Test
    public void daxie(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String s = scanner.nextLine();
            System.out.println(s.replaceAll(" ", "_").toUpperCase(Locale.ROOT));
        }
    }


    @Test
    public void zhuanbaoming(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String s = "";
            String str = scanner.nextLine();
            String[] list = str.split(" ");
            if (str.indexOf(" ") > -1) {
                list = str.split(" ");
            } else {
                list = str.split("_");
            }
            for (int i = 0; i < list.length; i++) {
                s += list[i].substring(0 , 1).toUpperCase(Locale.ROOT);
                s += list[i].toLowerCase(Locale.ROOT).substring(1);
            }
            System.out.println(s);
        }

    }

    @Test
    public void test001 () {
        int[] ints = new int[] {3,3,3,4,5,6,7};
        int[] num = new int[2];
        int target = 8;
        Map<Integer , Integer> map = new HashMap<>();
        for (int i = 0; i < ints.length; i++) {
            if (map.containsKey(target - ints[i])) {
                System.out.println(map.get(target - ints[i]));
                System.out.println(i);
            }
            map.put(ints[i] , i);
        }
    }





    @Test
    public void insert() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("TableName：");
            String tableName = scanner.nextLine();
            System.out.print("PoName：");
            String poName = scanner.nextLine();
            if (StringUtil.isEmpty(tableName , poName)) {
                continue;
            }
            List<String> fieldList = PgConnect.getFieldList(tableName);
            List<String> fieldTypeList = PgConnect.getFieldTypeList(tableName);
            if (StringUtil.isEmpty(fieldList , fieldTypeList)) {
                continue;
            }
            System.out.println("INSERT INTO \"" + tableName + "\"");
            System.out.print("(");
            int j = 0;
            for (int i = 0; i < fieldList.size() -1; i++) {
                System.out.print("\"" + fieldList.get(i) + "\", ");
                j++;
                if (j == 3) {
                    System.out.println("");
                    j = 0;
                }
            }
            System.out.println("\"" + fieldList.get(fieldList.size() -1) + "\")");
            System.out.print("VALUES(");
            j=0;
            for (int i = 0; i < fieldList.size() - 1; i++) {
                if (fieldTypeList.get(i).indexOf("date") > -1) {
                    System.out.print("to_date(#{" + poName + "." + StringUtil.strToStr(fieldList.get(i)) + "}, 'YYYY-MM-DD'),");
                } else if (fieldTypeList.get(i).indexOf("timestamp") > -1) {
                    System.out.print("to_timestamp(#{" + poName + "." + StringUtil.strToStr(fieldList.get(i)) + "}, 'YYYY-MM-DD HH24:MI:SS'),");
                } else {
                System.out.print("#{" + poName + "." + StringUtil.strToStr(fieldList.get(i)) + "},");
                }
                j++;
                if (j == 3) {
                    System.out.println("");
                    j = 0;
                }
            }
            System.out.print("#{" + poName + "." + StringUtil.strToStr(fieldList.get(fieldList.size() -1)) + "}");
            System.out.println(");");
        }
    }

    @Test
    public void update() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("TableName：");
            String tableName = scanner.nextLine();
            System.out.print("PoName：");
            String poName = scanner.nextLine();
            if (StringUtil.isEmpty(tableName , poName)) {
                continue;
            }
            List<String> fieldList = PgConnect.getFieldList(tableName);
            List<String> fieldTypeList = PgConnect.getFieldTypeList(tableName);
            if (StringUtil.isEmpty(fieldList , fieldTypeList)) {
                continue;
            }
            System.out.println("UPDATE \"" + tableName + "\" SET");
            for (int i = 1; i < fieldList.size() -1; i++) {
                if (fieldTypeList.get(i).indexOf("date") > -1) {
                    System.out.println("\"" + fieldList.get(i) + "\" = to_date(#{" + poName + "." + StringUtil.strToStr(fieldList.get(i)) + "}, 'YYYY-MM-DD'),");
                } else if (fieldTypeList.get(i).indexOf("timestamp") > -1) {
                    System.out.println("\"" + fieldList.get(i) + "\" = to_timestamp(#{" + poName + "." + StringUtil.strToStr(fieldList.get(i)) + "}, 'YYYY-MM-DD HH24:MI:SS'),");
                } else {
                    System.out.println("\"" + fieldList.get(i) + "\" = #{" + poName + "." + StringUtil.strToStr(fieldList.get(i)) + "},");
                }
            }
            System.out.println("\"" + fieldList.get(fieldList.size() - 1) + "\" = #{" + poName + "." + StringUtil.strToStr(fieldList.get(fieldList.size() - 1)) + "}");
            System.out.println("WHERE \"" + fieldList.get(0) + "\" = #{" + poName + "." + StringUtil.strToStr(fieldList.get(0)) + "} AND \"DATA_STATUS\" = 0");
        }
    }

    @Test
    public void Po() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("TableName：");
            String tableName = scanner.nextLine();
            if (StringUtil.isEmpty(tableName)) {
                continue;
            }
            List<String> fieldList = PgConnect.getFieldList(tableName);
            List<String> fieldTypeList = PgConnect.getFieldTypeList(tableName);
            List<String> fieldDescList = PgConnect.getFieldDescList(tableName);
            if (StringUtil.isEmpty(fieldList , fieldDescList ,fieldTypeList)) {
                continue;
            }
            System.out.println("@TableId(\"" + fieldList.get(0) + "\")");
            String s = fieldDescList.get(0) == null ? "" : " //" + fieldDescList.get(0);
            System.out.println("private String " + StringUtil.strToStr(fieldList.get(0)) + ";" + s);
            for (int i = 1; i < fieldList.size(); i++) {
                s = fieldDescList.get(i) == null ? "" : " //" + fieldDescList.get(i);
                System.out.println("@TableField(\"" + fieldList.get(i) + "\")");
                if (fieldTypeList.get(i).equals("int8")) {
                    System.out.println("private Long " + StringUtil.strToStr(fieldList.get(i)) + ";" + s);
                } else if (fieldTypeList.get(i).indexOf("int") > -1) {
                    System.out.println("private Integer " + StringUtil.strToStr(fieldList.get(i)) + ";" + s);
                } else {
                    System.out.println("private String " + StringUtil.strToStr(fieldList.get(i)) + ";" + s);
                }
            }
        }
    }

    @Test
    public void Dto() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("TableName：");
            String tableName = scanner.nextLine();
            if (StringUtil.isEmpty(tableName)) {
                continue;
            }
            List<String> fieldList = PgConnect.getFieldList(tableName);
            List<String> fieldTypeList = PgConnect.getFieldTypeList(tableName);
            List<String> fieldDescList = PgConnect.getFieldDescList(tableName);
            if (StringUtil.isEmpty(fieldDescList , fieldTypeList ,fieldList)) {
                continue;
            }
            for (int i = 0; i < fieldList.size(); i++) {
                String s = fieldDescList.get(i) == null ? "" : " //" + fieldDescList.get(i);
                if (fieldTypeList.get(i).equals("int8")) {
                    System.out.println("private Long " + StringUtil.strToStr(fieldList.get(i)) + ";" + s);
                } else if (fieldTypeList.get(i).indexOf("int") > -1) {
                    System.out.println("private Integer " + StringUtil.strToStr(fieldList.get(i)) + ";" + s);
                } else {
                    System.out.println("private String " + StringUtil.strToStr(fieldList.get(i)) + ";" + s);
                }
            }
        }
    }

    @Test
    public void resultMap() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String tableName = scanner.nextLine();
            if (StringUtil.isEmpty(tableName)) {
                continue;
            }
            List<String> fieldList = PgConnect.getFieldList(tableName);
            if (StringUtil.isEmpty(fieldList)){
                continue;
            }
            System.out.println("<id property=\"" + StringUtil.strToStr(fieldList.get(0)) + "\" column=\"" + fieldList.get(0) + "\"></id>");
            for (int i = 1; i < fieldList.size(); i++) {
                System.out.println("<result property=\"" + StringUtil.strToStr(fieldList.get(i)) + "\" column=\"" + fieldList.get(i) + "\"></result>");
            }
        }
    }

    @Test
    public void tableName() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            String str = "";
            List<Integer> list = StringUtil.getStrUpperPositionList(s);
            if (s.indexOf(" ") > -1) {
                str = s.replaceAll(" ", "_");
            } else if (list.size() > 1) {
                str += s.substring(0, list.get(0));
                if (!("".equals(str))) {
                    str += "_";
                }
                for (int j = 0; j < list.size() -1; j++) {
                    str += s.substring(list.get(j) , list.get(j + 1)).toLowerCase();
                    if (j + 2 != list.size()) {
                        str+="_";
                    }
                }
            }
            System.out.println("t_" + str.toLowerCase());
        }
    }


    

}
