package jxd.bxb.test.Connect.Conn;


import java.sql.SQLException;
import java.util.List;

/**
 * @author baixinbao
 * @create 2022/7/4
 */
public class Table {

    private List<String> colums;
    private List<String> types;
    private String tableName;
    private boolean isNull = true;

    public Table (String tableName) throws SQLException {
        this.tableName = tableName;
        this.colums = MyConnect.getFieldList(tableName);
        this.types = MyConnect.getFieldTypeList(tableName);
        this.isNull = false;
    }

    public Table () {

    }

    public boolean isNull() {
        return isNull;
    }

    public void setNull(boolean aNull) {
        isNull = aNull;
    }

    public List<String> getColums() {
        return colums;
    }

    public void setColums(List<String> colums) {
        this.colums = colums;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
