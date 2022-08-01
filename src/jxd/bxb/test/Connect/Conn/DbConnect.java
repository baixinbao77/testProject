package jxd.bxb.test.Connect.Conn;

import jxd.bxb.test.utils.Constant;
import jxd.bxb.test.utils.StringUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author baixinbao
 * @create 2022/6/16
 */
public class DbConnect {
    private static Connection conn = null;
    public static List<String> getFieldList(String tableName) throws SQLException {
        if (conn == null) {
            conn = getConnection();
        }
        String sql = "exec sp_columns " + tableName;
        DatabaseMetaData metaData = conn.getMetaData();
        List<String> list = new ArrayList<>();
        ResultSet rs = metaData.getColumns(conn.getCatalog(), "%", tableName, "%");
        while (rs.next()) {
            list.add(rs.getString(ResultSetColumnKeys.COLUMN_NAME.name()));
        }
        return list;
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

    public static List<String> getLengthList(String tableName) throws SQLException {
        if (conn == null) {
            conn = getConnection();
        }
        tableName = tableName.trim();
        String sql = "exec sp_help " + tableName + ";";
        List<String> list = new ArrayList<>();
        PreparedStatement stmt = null;
        String catalog = conn.getCatalog();

        stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            String length = rs.getString(ResultSetColumnKeys.LENGTH.name()) == null ? "" : rs.getString(ResultSetColumnKeys.LENGTH.name());
            list.add(length);
        }
        return list;
    }

    public static List<String> getFieldDescList(String tableName) throws SQLException {
        if (conn == null) {
            conn = getConnection();
        }
        tableName = tableName.trim();
        String sql = "select a.name as table_name, b.name as column_name, CONVERT(nvarchar(50),ISNULL(c.value, '')) as REMARKS" +
                "    from sys.tables a left join sys.columns b on a.object_id=b.object_id " +
                "    left join sys.extended_properties c on a.object_id=c.major_id " +
                "    where a.name='" + tableName + "' and c.minor_id<>0 and b.column_id=c.minor_id " +
                "    and a.schema_id=(select schema_id from sys.schemas where name='dbo');";

        List<String> list = new ArrayList<>();
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            String remarks = rs.getString(ResultSetColumnKeys.REMARKS.name()) == null ? "" : rs.getString(ResultSetColumnKeys.REMARKS.name());
            list.add(remarks);
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

    private static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("java.sql.Driver");
            Map<String , Object> map = StringUtil.getPropertiesMap(Constant.CONNECT_FILE_PATH);
            String url = map.get("DbUrl").toString();
            String passWord = map.get("DbPassword").toString();
            String userName = map.get("DbUserName").toString();
            conn = DriverManager.getConnection(url, userName, passWord);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }



    public static List<String> getTableList () {
        String sql = "exec sp_tables;";
        if (conn == null) {
            conn = getConnection();
        }
        PreparedStatement stmt;
        List<String> tableList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String tableType = rs.getString(ResultSetColumnKeys.TABLE_TYPE.name());
                if (tableType.equals("TABLE")) {
                    String tableName = rs.getString(ResultSetColumnKeys.TABLE_NAME.name());
                    tableList.add(tableName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableList;
    }

}
