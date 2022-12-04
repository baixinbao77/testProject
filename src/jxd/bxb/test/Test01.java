package jxd.bxb.test;

import jxd.bxb.test.Connect.Conn.DbConnect;
import jxd.bxb.test.Connect.Conn.MyConnect;
import jxd.bxb.test.Connect.Conn.PgConnect;
import jxd.bxb.test.Connect.Conn.type.DBColunm;
import jxd.bxb.test.Connect.Conn.type.DbTableType;
import jxd.bxb.test.utils.Constant;
import jxd.bxb.test.utils.FileUtils;
import jxd.bxb.test.utils.StringUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
                    System.out.print("convert(datetime ,#{" + poName + "." + StringUtil.strToStr(fieldList.get(i)) + "} , 120),");
                } else if (fieldTypeList.get(i).indexOf("timestamp") > -1) {
                    System.out.print("convert(datetime ,#{" + poName + "." + StringUtil.strToStr(fieldList.get(i)) + "} , 120),");
                } else {
                System.out.print("#{" + poName + "." + StringUtil.strToStr(fieldList.get(i)) + "},");
                }
                j++;
                if (j == 3) {
                    System.out.println("");
                    j = 0;
                }
            }
            if (fieldTypeList.get(fieldList.size() -1).indexOf("date") > -1) {
                System.out.print("to_date(#{" + poName + "." + StringUtil.strToStr(fieldList.get(fieldList.size() -1)) + "}, 'YYYY-MM-DD')");
            } else if (fieldTypeList.get(fieldList.size() -1).indexOf("timestamp") > -1) {
                System.out.print("to_timestamp(#{" + poName + "." + StringUtil.strToStr(fieldList.get(fieldList.size() -1)) + "}, 'YYYY-MM-DD HH24:MI:SS')");
            } else {
                System.out.print("#{" + poName + "." + StringUtil.strToStr(fieldList.get(fieldList.size() -1)) + "}");
            }
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

            if (fieldTypeList.get(fieldList.size() -1).indexOf("date") > -1) {
                System.out.print("\"" + fieldList.get(fieldList.size() - 1) + "\" = to_date(#{" + poName + "." + StringUtil.strToStr(fieldList.get(fieldList.size() -1)) + "}, 'YYYY-MM-DD')");
            } else if (fieldTypeList.get(fieldList.size() -1).indexOf("timestamp") > -1) {
                System.out.print("\"" + fieldList.get(fieldList.size() - 1) + "\" = to_timestamp(#{" + poName + "." + StringUtil.strToStr(fieldList.get(fieldList.size() -1)) + "}, 'YYYY-MM-DD HH24:MI:SS')");
            } else {
                System.out.print("\"" + fieldList.get(fieldList.size() - 1) + "\" = #{" + poName + "." + StringUtil.strToStr(fieldList.get(fieldList.size() -1)) + "}");
            }
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
            List<String> fieldList = DbConnect.getFieldList(tableName);
            List<String> fieldTypeList = DbConnect.getFieldTypeList(tableName);
            List<String> fieldDescList = DbConnect.getFieldDescList(tableName);
            if (StringUtil.isEmpty(fieldList ,fieldTypeList)) {
                continue;
            }
            System.out.println("@TableId(\"" + fieldList.get(0) + "\")");
            String s = "";
            System.out.println("private String " + StringUtil.strToStr(fieldList.get(0)) + ";" + s);
            for (int i = 1; i < fieldList.size(); i++) {
                s = "";
                System.out.println("@TableField(\"" + fieldList.get(i) + "\")");
                if (fieldTypeList.get(i).equals("int8")) {
                    System.out.println("private Long " + StringUtil.strToPsms(fieldList.get(i)) + ";" + s);
                } else if (fieldTypeList.get(i).indexOf("int") > -1) {
                    System.out.println("private Integer " + StringUtil.strToPsms(fieldList.get(i)) + ";" + s);
                } else if (fieldTypeList.get(i).indexOf("numeric") > -1) {
                    System.out.println("private Double " + StringUtil.strToPsms(fieldList.get(i)) + ";" + s);
                } else {
                    System.out.println("private String " + StringUtil.strToPsms(fieldList.get(i)) + ";" + s);
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
            List<String> fieldList = DbConnect.getFieldList(tableName);
            List<String> fieldTypeList = DbConnect.getFieldTypeList(tableName);
            if (StringUtil.isEmpty( fieldTypeList ,fieldList)) {
                continue;
            }
            for (int i = 0; i < fieldList.size(); i++) {
                String s = "";
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
            List<String> fieldList = DbConnect.getFieldList(tableName);
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


    @Test
    public void modifyTest () {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String s = scanner.nextLine();
            System.out.println("                // 将修改记录添加到数据库中");
            System.out.println("systemHisService.saveSysHisByField("+ s + ".getGeneralId() ,DateUtil.getTime() , "+ s + ".getUpdateUserId() ,\n" +
                    "                "+ s + ".getUpdateUserName() , "+ s + ".getUpdateDeptId() , "+ s + ".getUpdateDeptName() ,\n" +
                    "                null);");
        }
    }



    @Test
    public void testttt() {
        try {
            File file = FileUtils.createFile(null, "TableName", "txt");
            StringBuilder builder = new StringBuilder();
            for (String tableName : DbConnect.getTableList()) {
                for (String tableField : DbConnect.getFieldList(tableName)) {
                    builder.append(tableField)
                            .append("\n");
                }

            }
            System.out.println(builder.toString());
            FileUtils.writeFile(file, builder);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void tststts() {
        Date date = new Date(Long.parseLong("1666580279977"));
        String format = DATE_FORMAT.format(date);
        System.out.println(format);
    }




    @Test
    public void tstwstts() {
        DbConnect.getAllTables(DbTableType.U).stream().forEach(table -> {
            List<Map<DBColunm, Object>> maps = DbConnect.queryData("select top 200000* from \"" + table.getName() + "\"");
            System.out.println(DATE_FORMAT.format(new Date()) + "开始：" + table.getName());
            StringBuilder write = new StringBuilder();
            File file = null;
            try {
                file = FileUtils.createFile(StringUtil.getPropertiesMap(Constant.CONNECT_FILE_PATH).get("SqlPath").toString(), table.getName(), "sql");
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (Map<DBColunm, Object> map : maps) {
                write.append(DbConnect.insert(map , table.getName())).append("\n");
                try {
                    if (file != null) {
                        FileUtils.writeFile(file , write);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                write = new StringBuilder();
            }

        });
    }


}
