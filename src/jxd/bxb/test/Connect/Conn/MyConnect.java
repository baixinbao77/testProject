package jxd.bxb.test.Connect.Conn;

import jxd.bxb.test.utils.Constant;
import jxd.bxb.test.utils.StringUtil;

import java.sql.*;
import java.util.*;

/**
 * @author baixinbao
 * @create 2022/6/16
 */
public class MyConnect {
    private static Connection conn = null;
    public static List<String> getFieldList(String tableName) throws SQLException {
        if (conn == null) {
            conn = getConnection();
        }
        String sql = "select * from " + tableName;
        PreparedStatement stmt;
        List<String> list = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(sql);
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
        if (conn == null) {
            conn = getConnection();
        }
        String sql = "select * from " + tableName;
        PreparedStatement stmt;
        List<String> list = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(sql);
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
        if (conn == null) {
            conn = getConnection();
        }
        DatabaseMetaData metaData = conn.getMetaData();
        List<String> list = new ArrayList<>();
        ResultSet rs = metaData.getColumns(conn.getCatalog(), "%", tableName, "%");
        while (rs.next()) {
            list.add(rs.getString(ResultSetColumnKeys.REMARKS.name()));
        }
        return list;
    }

    public static String getFieldDesc(String tableName , String labelName) throws SQLException {
        if (conn == null) {
            conn = getConnection();
        }
        DatabaseMetaData metaData = conn.getMetaData();
        String result = "";
        ResultSet rs = metaData.getColumns(conn.getCatalog(), "%", tableName, labelName);
        while (rs.next()) {
            result = rs.getString(ResultSetColumnKeys.REMARKS.name());
        }
        return result;
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Map<String , Object> map = StringUtil.getPropertiesMap(Constant.CONNECT_FILE_PATH);
            String url = map.get("MyUrl").toString();
            String passWord = map.get("MyPassWord").toString();
            String userName = map.get("MyUserName").toString();
            conn = DriverManager.getConnection(url, userName, passWord);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
