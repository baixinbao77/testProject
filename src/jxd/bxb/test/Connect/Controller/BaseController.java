package jxd.bxb.test.Connect.Controller;

import jxd.bxb.test.Connect.Table;
import jxd.bxb.test.utils.annotation.TableField;
import jxd.bxb.test.utils.annotation.TableName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;

/**
 * @author baixinbao
 * @create 2022/7/6
 */
public class BaseController<T>{

    private Class entityClass;
    private Table table;
    private Logger logger;

    public int insert (T entity) {

        return 0;
    }

    public BaseController () {
        logger = LoggerFactory.getLogger(getClass().getName());
        Type genericSuperclass = getClass().getGenericSuperclass();
        Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
        Type actualType = actualTypeArguments[0];
        try {
            entityClass = Class.forName(actualType.getTypeName());
            setTable();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
                    logger.error(getClass().getName() + "Table_Field的值错误");
                    break;
                }
            }
        }
    }

    private void setValue (Field field , Object obj) {
    }


}
