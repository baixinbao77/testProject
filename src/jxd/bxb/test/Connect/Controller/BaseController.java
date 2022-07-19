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

import java.lang.reflect.*;
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

    private Class entityClass;
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
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
        setTable();
        setField();
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
                logger.info("DS数据库连接不正确");
            }
        } else {
            logger.info("数据库DS未加判断");
        }
    }

    private void getConnect () {
        if (conn == null) {
            setConn();
        }
    }


    public List<T> queryList () {
        return null;
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
