package jxd.bxb.test.Connect.Conn.type;

/**
 * @ClassName Column
 * @Description TODO
 * @Author 白新报
 * @Date 2022/10/29 20:46
 * @Version 1.0
 **/
public abstract class Column {
    private String columnType;
    private Object columnName;

    public Column(String columnType, Object columnName) {
        this.columnType = columnType;
        this.columnName = columnName;
    }

    public abstract void handleValue();

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public Object getColumnName() {
        return columnName;
    }

    public void setColumnName(Object columnName) {
        this.columnName = columnName;
    }
}
