package jxd.bxb.test.Connect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author baixinbao
 * @create 2022/7/6
 */
@Target({ElementType.TYPE , ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TableField {
    String value();
}
