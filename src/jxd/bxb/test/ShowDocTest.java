package jxd.bxb.test;

import jxd.bxb.test.Connect.Conn.DbConnect;
import jxd.bxb.test.Connect.Conn.PgConnect;
import jxd.bxb.test.Connect.Conn.type.DbTableType;
import jxd.bxb.test.utils.StringUtil;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author baixinbao
 * @create 2022/6/20
 */
public class ShowDocTest {

    @Test
    public void save() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("tableName:");
            String tableName = scanner.nextLine();
            List<String> fieldList = PgConnect.getFieldList(tableName);
            List<String> fieldTypeList = PgConnect.getFieldTypeList(tableName);
            int i = 0 ;
            for (String labelName : fieldList) {
                String fieldDesc = PgConnect.getFieldDesc(tableName, labelName);
                if (fieldTypeList.get(i).indexOf("int") > -1) {
                    System.out.println("| " + StringUtil.strToStr(labelName) + "| 是 | number | " + fieldDesc + "|");
                } else {
                    System.out.println("| " + StringUtil.strToStr(labelName) + "| 是 | string | " + fieldDesc + "|");
                }
                i++;
            }
        }
    }

    @Test
    public void query() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String tableName = scanner.nextLine();
            List<String> fieldList  = PgConnect.getFieldList(tableName);
            List<String> fieldTypeList = PgConnect.getFieldTypeList(tableName);
            List<String> fieldDesc = PgConnect.getFieldDescList(tableName);
            int i = 0 ;
            for (String labelName : fieldList) {
                if (fieldTypeList.get(i).indexOf("int") > -1) {
                    System.out.println("| " + StringUtil.strToStr(labelName) + "| number | " + fieldDesc.get(i) + "|");
                } else {
                    System.out.println("| " + StringUtil.strToStr(labelName) + "| string | " + fieldDesc.get(i) + "|");
                }
                i++;
            }
        }
    }



//    @Test
//    public void test(){
//        List<String> list = StringUtil.getScannerList();
//        List<Map<String , Object>> mapList = new ArrayList<>();
//        Map<String , Object> map = new HashMap<>();
//        for (int i = 0; i < list.size(); i++) {
//
//            String key = "";
//            if (list.get(i).indexOf(":") > -1) {
//                key = list.get(i).substring(list.get(i).indexOf("\""), list.get(i).length() - 1);
//            }
//            if (list.get(i).indexOf("{") > -1) {
//                StringUtil.parseString(list.get(i));
//            }
//        }
//    }

    @Test
    public void testsa() throws SQLException {
        List<DbTableType> dbTableTypes = Arrays.asList(DbTableType.U, DbTableType.X);
        String type = dbTableTypes.stream().map(m -> m.name()).collect(Collectors.joining("','"));
        System.out.println(type);
    }
}
