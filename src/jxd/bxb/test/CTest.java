package jxd.bxb.test;

import jxd.bxb.test.All.BaseUtils;
import jxd.bxb.test.All.EntityUtils.EntityUtil;
import jxd.bxb.test.Connect.Conn.DbConnect;
import jxd.bxb.test.utils.BeanUtils;
import jxd.bxb.test.utils.StringUtil;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * @author baixinbao
 * @create 2022/8/1
 */
public class CTest {

    @Test
    public void model() throws Exception {
        String tableName = "CardRateCull";
        List<String> fieldList = DbConnect.getFieldList(tableName);
        List<String> fieldTypeList = DbConnect.getFieldTypeList(tableName);
        List<String> fieldDescList = DbConnect.getFieldDescList(tableName);
        String start = "public virtual ";
        String getAndSet = " { get; set; }";
        for (int i = 0; i < fieldList.size(); i++) {
//            System.out.println("/// <summary>\n" +
//                    "/// " + fieldDescList.get(i)+ "\n" +
//                    "/// </summary>");
            if (fieldTypeList.get(i).indexOf("int") > -1) {
                System.out.println(start + "int " + fieldList.get(i) + getAndSet);
            } else {
                System.out.println(start + "String " + fieldList.get(i) + getAndSet);
            }
        }
    }

    @Test
    public void xml() throws SQLException {
        String tableName = "T_PROCESS_CARD_ACCOUNT";
        List<String> fieldList = DbConnect.getFieldList(tableName);
        List<String> fieldTypeList = DbConnect.getFieldTypeList(tableName);
        List<String> fieldDescList = DbConnect.getFieldDescList(tableName);
        for (int i = 0; i < fieldList.size(); i++) {
            System.out.println("<!--" + fieldDescList.get(i) + "-->");
            if (i == 0) {
                System.out.println("<id name=\"" + StringUtil.strToStr(fieldList.get(i)) + "\" type=\"string\">");
                System.out.println("<column name=\"" + fieldList.get(i) + "\" length=\"32\" not-null=\"true\"/>");
                System.out.println("<generator class=\"native\" />");
                System.out.println("</id>");
            } else {
                System.out.println("<property name=\"" + StringUtil.strToStr(fieldList.get(i)) + "\" type=\"string\">");
                System.out.println("<column name=\"" + fieldList.get(i) + "\" length=\"100\" not-null=\"false\"/>");
                System.out.println("</property>");
            }
        }
    }

    @Test
    public void saveField() throws SQLException {
        String tableName = "T_PROCESS_CARD_ACCOUNT";
        List<String> fieldList = DbConnect.getFieldList(tableName);
        List<String> fieldTypeList = DbConnect.getFieldTypeList(tableName);
        fieldList = EntityUtil.initField(fieldList);
        for (int i = 0; i < fieldList.size() -1; i++) {
            if (fieldTypeList.get(i).indexOf("int") > -1) {
                System.out.print("int " + fieldList.get(i));
            } else {
                System.out.print("string " + fieldList.get(i));
            }
        }
        int i = fieldList.size() -1;
        if (fieldTypeList.get(i).indexOf("int") > -1) {
            System.out.print("int " + fieldList.get(i));
        } else {
            System.out.print("string " + fieldList.get(i));
        }
    }

    @Test
    public void updateField() throws SQLException {
        String tableName = "TB_SYS_PUSH_USER";
        List<String> fieldList = DbConnect.getFieldList(tableName);
        List<String> fieldTypeList = DbConnect.getFieldTypeList(tableName);
        List<String> fieldDescList = DbConnect.getFieldDescList(tableName);
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE " + tableName)
                .append(" set ");
        for (int i = 0; i < fieldList.size(); i++) {
            sql.append(fieldList.get(i) + " = @" + fieldList.get(i) + ",");

        }
        System.out.println(sql.toString());

    }
}
