package com.atguigu.mybatis.dao;

import com.atguigu.mybatis.bean.Employee;

/**
 * @author baixinbao
 * @create 2022/7/29
 */
public interface EmployeeDao {
    Employee getEmployeeById(Integer id);

}
