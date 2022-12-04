package jxd.bxb.test.Connect.Conn;

import jxd.bxb.test.utils.Constant;
import jxd.bxb.test.utils.StringUtil;

import java.sql.*;
import java.util.*;

/**
 * @author baixinbao
 * @create 2022/6/16
 */
public class PgConnect {
    public static List<String> getFieldList(String tableName) throws SQLException {
        Connection connection = getConnection();
        String sql = "select * from \"" + tableName + "\"";
        PreparedStatement stmt;
        List<String> list = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData data = rs.getMetaData();
            for (int i = 1; i <= data.getColumnCount(); i++) {
                String columnName = data.getColumnName(i);
                list.add(columnName);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        }
    }

    public static List<String> getFieldTypeList(String tableName) throws SQLException {
        Connection connection = getConnection();
        String sql = "select * from \"" + tableName + "\"";
        PreparedStatement stmt;
        List<String> list = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData data = rs.getMetaData();
            for (int i = 1; i <= data.getColumnCount(); i++) {
                String columnTypeName = data.getColumnTypeName(i);
                list.add(columnTypeName);

            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        }
    }

    public static List<String> getFieldDescList(String tableName) throws SQLException {
        Connection connection = getConnection();
        String sql = "SELECT C.relname,A.attname AS NAME,A.attnotnull AS NOTNULL,format_type ( A.atttypid, A.atttypmod ) AS TYPE,col_description ( A.attrelid, A.attnum ) AS COMMENT " +
                "FROM pg_class AS C, pg_attribute AS A WHERE C.relname = '" + tableName + "' AND A.attrelid = C.oid AND A.attnum > 0 and format_type ( A.atttypid, A.atttypmod )  != '-'";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<String> list = new ArrayList<>();
            while (rs.next()) {
                String comment = rs.getString("COMMENT");
                list.add(comment);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getFieldDesc(String tableName , String labelName) throws SQLException {
        Connection connection = getConnection();
        String sql = "SELECT C.relname,A.attname AS NAME,A.attnotnull AS NOTNULL,format_type ( A.atttypid, A.atttypmod ) AS TYPE,col_description ( A.attrelid, A.attnum ) AS COMMENT " +
                "FROM pg_class AS C, pg_attribute AS A WHERE C.relname = '" + tableName + "' AND A.attrelid = C.oid AND A.attnum > 0 and A.attname = '" + labelName + "'";
        PreparedStatement stmt;
        String comment = "";
        try {
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                comment = rs.getString("COMMENT");
            }
            return comment;
        } catch (Exception e) {
            e.printStackTrace();
            return comment;
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            Map<String , Object> map = StringUtil.getPropertiesMap(Constant.CONNECT_FILE_PATH);
            String url = map.get("PgUrl").toString();
            String passWord = map.get("PgPassWord").toString();
            String userName = map.get("PgUserName").toString();
            conn = DriverManager.getConnection(url, userName, passWord);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
