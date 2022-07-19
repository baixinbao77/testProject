package jxd.bxb.test.Connect.Controller;

import jxd.bxb.test.Connect.Conn.Table;
import jxd.bxb.test.Connect.annotation.TableName;
import jxd.bxb.test.utils.BeanUtils;
import jxd.bxb.test.utils.StringUtil;

import java.sql.SQLException;

/**
 * @author baixinbao
 * @create 2022/7/19
 */
public final class SQL<T> {
    private StringBuilder select = new StringBuilder();
    private StringBuilder insert = new StringBuilder();
    private StringBuilder update = new StringBuilder();
    private StringBuilder delete = new StringBuilder();
    private Class entityClass;
    private Table table;

    public SQL(Class entityClass) {
        this.entityClass = entityClass;
        this.select = this.select.append("SELECT ");
        this.insert = this.insert.append("INSERT ");
        this.update = this.update.append("UPDATE ");
        this.delete = this.delete.append("DALETE ");
        setTable();
    }

    public StringBuilder getSelect() {
        return select;
    }

    public void setSelect(StringBuilder select) {
        this.select = select;
    }

    public StringBuilder getInsert() {
        return insert;
    }

    public void setInsert(StringBuilder insert) {
        this.insert = insert;
    }

    public StringBuilder getUpdate() {
        return update;
    }

    public void setUpdate(StringBuilder update) {
        this.update = update;
    }

    public StringBuilder getDelete() {
        return delete;
    }

    public void setDelete(StringBuilder delete) {
        this.delete = delete;
    }

    public SQL select (String... colums) {
        if (checkFrom(this.select)) {
            int fromPosition = getFromPosition(this.select);
            if (StringUtil.isNotEmpty(colums)) {
                for (String colum : colums) {
                    select(colum);
                }
            }
        } else {
            if (StringUtil.isNotEmpty(colums)) {
                for (String colum : colums) {
                    select(colum);
                }
            }
        }
        return this;
    }

    public SQL select (String colum) {
        if (checkFrom(this.select)){
            int fromPosition = getFromPosition(this.select);
            if (this.select.toString().endsWith("SELECT ")) {
                this.select.insert(fromPosition , colum + " ");
            } else {
                this.select.insert(fromPosition , "," + colum + " ");
            }
        } else {
            if (this.select.toString().endsWith("SELECT ")) {
                this.select.append(colum + " ");
            } else {
                this.select.append("," + colum + " ");
            }
            setFrom(this.select);
        }

        return this;
    }

    private boolean checkFrom (StringBuilder builder) {
        if (builder.toString().contains("FROM")) {
            return true;
        }
        return false;
    }

    private int getFromPosition (StringBuilder builder) {
        return builder.toString().indexOf("FROM");
    }

    private void setFrom (StringBuilder builder) {
        String tableName = table.getTableName();
        if (builder.toString().startsWith("SELECT")) {
            builder.append("FROM ");
        }
        if (builder.toString().startsWith("INSERT ")) {
            builder.append(" ");
        }
        if (builder.toString().startsWith("UPDATE ")) {
            builder.append(" ");
        }
        if (builder.toString().startsWith("DELETE ")) {
            builder.append("FROM ");
        }
        builder.append("\"" + tableName + "\"");
    }

    private void setTable () {
        if (entityClass.isAnnotationPresent(TableName.class)) {
            TableName tableName = (TableName) entityClass.getDeclaredAnnotation(TableName.class);
            String value = tableName.value();
            try {
                table = new Table(value);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public SQL where (String colum) {

    }

}
