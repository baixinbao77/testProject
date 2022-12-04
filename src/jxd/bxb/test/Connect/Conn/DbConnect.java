package jxd.bxb.test.Connect.Conn;

import jxd.bxb.test.Connect.BaseConnect;
import jxd.bxb.test.Connect.Conn.type.DBColunm;
import jxd.bxb.test.Connect.Conn.type.DbObject;
import jxd.bxb.test.Connect.Conn.type.DbTableType;
import jxd.bxb.test.utils.Bean;
import jxd.bxb.test.utils.Constant;
import jxd.bxb.test.utils.StringUtil;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author baixinbao
 * @create 2022/6/16
 */
public class DbConnect implements BaseConnect {
    private static Connection conn = null;

    private DbConnect() {
        this.conn = getConnection();
    }

    public static List<String> getFieldList(String tableName) throws SQLException {
        conn = getConnect();
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
        getConnect();
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
        conn = getConnect();
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
        getConnect();
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
        DatabaseMetaData metaData = conn.getMetaData();
        String result = "";
        ResultSet rs = metaData.getColumns(conn.getCatalog(), "%", tableName, labelName);
        while (rs.next()) {
            result = rs.getString(ResultSetColumnKeys.REMARKS.name());
        }
        return result;
    }

    public Connection getConnection() {
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

    private static Connection getConnect() {
        if (conn == null) {
            return new DbConnect().getConnection();
        }
        try {
            if (conn.isClosed()) {
                return new DbConnect().getConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static List<String> getTableList () {
        String sql = "exec sp_tables;";
        conn = getConnect();
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


    public static List<DbObject> getAllTables(DbTableType... types) {
        List<DbObject> tableList = new ArrayList<>();
        if (StringUtil.isEmpty(types)) {
            return tableList;
        }
        String type = Arrays.stream(types).map(m -> m.name()).collect(Collectors.joining("','"));
        String sql = new StringBuilder().append("select * from sysobjects WHERE xtype in(")
                .append("'").append(type).append("'").append(")").toString();
        conn = getConnect();

        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DbObject dbObject = new DbObject();
                Bean.getBeans(dbObject.getClass()).stream().forEach(bean -> {
                    try {
                        bean.invokeset(dbObject , rs.getObject(bean.getField().getName()));
                    } catch (SQLException e) {
                    }
                });
                tableList.add(dbObject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableList;
    }

    public static List<Map<DBColunm , Object>> queryData(String sql) {
        List<Map<DBColunm , Object>> list = new ArrayList<>();
        if (StringUtil.isEmpty(sql)) {
            return list;
        }
        conn = getConnect();
        Map<DBColunm,Object> map = new HashMap<>();
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                map.clear();
                for (DBColunm meta : metaData(rs.getMetaData())) {
                    map.put(meta, rs.getObject(meta.getColumnName().toString()));
                }
                list.add(map);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static List<DBColunm> metaData(ResultSetMetaData metaData) {
        if (StringUtil.isEmpty(metaData)) {
            return new ArrayList<>();
        }
        List<DBColunm> list = new ArrayList<>();
        try {
            int count = metaData.getColumnCount();
            for (int i = 1; i <= count; i++) {
                String columnName = metaData.getColumnName(i);
                String typeName = metaData.getColumnTypeName(i);
                list.add(new DBColunm(typeName , columnName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public static String insert(Map<DBColunm , Object> map , String tableName) {
        if (StringUtil.isEmpty(map)) {
            return "";
        }
        StringBuilder insert = new StringBuilder();
        StringBuilder parametr = new StringBuilder();
        Iterator<Map.Entry<DBColunm, Object>> iterator = map.entrySet().iterator();
        insert.append("insert into ").append(tableName).append("(");
        while (iterator.hasNext()) {
            Map.Entry<DBColunm, Object> next = iterator.next();
            DBColunm dbColunm = next.getKey();
            insert.append(dbColunm.getColumnName().toString()).append(",");
            if (StringUtil.haveTextIgnoreCase(dbColunm.getColumnType() , "DateTime" , "varchar" , "nvarchar" , "text" , "timeStamp" , "char" , "nchar" , "ntext")) {
                parametr.append("'").append(next.getValue()).append("'").append(",");
            } else {
                parametr.append(next.getValue()).append(",");
            }
        }
        if (insert.toString().endsWith(",")) {
            insert.delete(insert.length() -1, insert.length());
            parametr.delete(parametr.length() -1, parametr.length());
        }
        insert.append(")").append(" values(").append(parametr).append(");");
        return insert.toString();
    }

}
