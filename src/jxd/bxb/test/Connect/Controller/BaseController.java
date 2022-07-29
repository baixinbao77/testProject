package jxd.bxb.test.Connect.Controller;

import jxd.bxb.test.All.BaseUtils;
import jxd.bxb.test.Connect.Conn.DataBase;
import jxd.bxb.test.Connect.Conn.MyConnect;
import jxd.bxb.test.Connect.Conn.PgConnect;
import jxd.bxb.test.Connect.Conn.Table;
import jxd.bxb.test.utils.BeanUtils;
import jxd.bxb.test.utils.StringUtil;
import jxd.bxb.test.Connect.annotation.DS;
import jxd.bxb.test.Connect.annotation.TableField;
import jxd.bxb.test.Connect.annotation.TableName;
import jxd.bxb.test.utils.annotation.TableId;

import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author baixinbao
 * @create 2022/7/6
 */
public class BaseController<T> extends BaseUtils {

    public Class entityClass;
    private Table table;
    private Logger logger;
    private Connection conn;
    private Map<String , Object> map = new HashMap<>();
    private Map<String , Object> fieldMap = new HashMap<>();
    private List<String> fieldList = new ArrayList<>();
    private List<Object> columList = new ArrayList<>();

    public int insert (T entity) {
        getConnect();
        int result = 0 ;
        setValue(entity);
        String sql = getInsert().toString();
        try {
            PreparedStatement psmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            setParams(psmt, fieldList.toArray());
            result = psmt.executeUpdate();
            close(null , psmt , conn);
            if (result > 0) {
                logger.info("插入成功！");
            } else {
                logger.info("插入失败！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<T> queryList () {

        return null;
    }

    public int updateById (T entity) {
        getConnect ();
        int result = 0 ;
        setValue(entity);
        String sql = getUpdate().toString();
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            setParams(psmt, StringUtil.move(fieldList , 1 , fieldList.size() ).toArray());
            result = psmt.executeUpdate();
            if (result > 0) {
                logger.info("更新成功！");
            } else {
                logger.info("更新失败！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete (T entity) {
        getConnect ();
        int result = 0 ;
        setValue(entity);
        String sql = getDelete().toString();
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            setParams(psmt, fieldList.get(0));
            result = psmt.executeUpdate();
            if (result > 0) {
                logger.info("删除成功！");
            } else {
                logger.info("删除失败！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    private StringBuilder getUpdate () {
        StringBuilder update = new StringBuilder();
        update.append("UPDATE " + table.getTableName());
        update.append(" SET ");
        setUpdate(update, columList, "?");
        return update;
    }

    private StringBuilder getDelete () {
        StringBuilder delete = new StringBuilder();
        delete.append("DELETE FROM " + table.getTableName());
        delete.append(" WHERE ");
        delete.append(columList.get(0) + "=" + "?");
        return delete;
    }

    private void setUpdate (StringBuilder builder , List<Object> list , String sign) {
        if (StringUtil.isNotEmpty(list)) {
            for (int i = 1; i < list.size() -1 ; i++) {
                builder.append(list.get(i).toString() + "=" + sign + ",");
            }
            builder.append(list.get(list.size() - 1).toString() + "=" + sign);
            builder.append(" WHERE " + list.get(0) + "= ?");
        }
    }

    private void setParams (PreparedStatement psmt , Object... params) {
        if (StringUtil.isNotEmpty(params)) {
            for (int i = 0; i < params.length; i++) {
                try {
                    psmt.setObject(i+1 , map.get(params[i].toString()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private StringBuilder getInsert () {
        StringBuilder insert = new StringBuilder();
        insert.append("INSERT INTO " + table.getTableName() + " (");
        add(columList , insert , ", ");
        insert.append("VALUES (");
        add(StringUtil.initList(columList, "?") , insert , "," );
        insert.append(";");
        return insert;
    }

    private void add (List<Object> list , StringBuilder builder , String sign) {
        for (int i = 0; i < list.size() - 1; i++) {
            builder.append(list.get(i) + sign);
        }
        builder.append(list.get(list.size() - 1) + ")");
    }

    public BaseController () {
        entityClass = BeanUtils.getEntityClass(this.getClass());
        logger = Logger.getLogger(entityClass.getName());

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

    private void setField () {
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(TableField.class)) {
                TableField tableField = field.getAnnotation(TableField.class);
                String value = tableField.value();
                if (!table.getColums().contains(value)) {
                    logger.info(getClass().getName() + "TableField的值错误");
                    break;
                } else {
                    fieldList.add(field.getName().toString());
                    fieldMap.put(field.getName(), value);
                    columList.add(value);
                }
            } else if (field.isAnnotationPresent(TableId.class)) {
                TableId tableId = field.getAnnotation(TableId.class);
                String value = tableId.value();
                fieldMap.put(field.getName(), value);
                fieldList.add(0, field.getName());
                columList.add(0, value);
            }
        }
    }

    private void setValue (Method method , Object value) {
        for (String s : fieldList) {
            if (s.equalsIgnoreCase(method.getName().substring(method.getName().indexOf("get") + 3))) {
                map.put(s , value);
            }
        }
    }

    private void setValue (T entity) {
        Method[] methods = entityClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("get")) {
                try {
                    Object value = method.invoke(entity);
                    setValue(method, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void setConn () {
        if (entityClass.isAnnotationPresent(DS.class)) {
            DS ds = (DS) entityClass.getDeclaredAnnotation(DS.class);
            DataBase value = ds.value();
            if (value.equals(DataBase.MYSQL)) {
                conn = MyConnect.getConnection();
            } else if (value.equals(DataBase.PGSQL)) {
                conn = PgConnect.getConnection();
            } else {
                logger.info("" +
                        "");
            }
        } else {
            logger.info("数据库DS未加判断");
        }
    }

    private void getConnect () {
        if (conn == null) {
            setConn();
        }
        if (table.isNull()) {
            setTable();
        }
        if (StringUtil.isEmpty(columList , fieldList) || fieldMap.isEmpty()) {
            setField();
        }
    }




    protected void close(ResultSet rs , PreparedStatement psmt , Connection conn){
        try {
            if (rs != null) {
                rs.close();
            }
            if(psmt!=null){
                psmt.close();
            }
            if(conn!=null && !conn.isClosed()){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
