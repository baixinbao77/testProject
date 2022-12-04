package com.atguigu.mybatis.dao;

import com.atguigu.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Select;

/**
 * @author baixinbao
 * @create 2022/7/29
 */
public interface EmployeeDaoAnnotation {

    @Select("select * from tbl_employee where id = #{id}")
    Employee getEmployeeById(Integer id);

}
