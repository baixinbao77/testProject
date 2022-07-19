package jxd.bxb.test.result;

import jxd.bxb.test.Connect.Conn.DataBase;
import jxd.bxb.test.Connect.annotation.DS;
import jxd.bxb.test.Connect.annotation.TableField;
import jxd.bxb.test.Connect.annotation.TableName;

/**
 * @author BXBstart
 * @create 2022-06-25 19:39
 */
@DS(DataBase.MYSQL)
@TableName(value = "employees")
public class Result {
    @TableField("employeeID")
    private int id ;
    @TableField("employeeName")
    private String name ;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
