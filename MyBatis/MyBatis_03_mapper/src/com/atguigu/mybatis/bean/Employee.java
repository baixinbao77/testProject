package com.atguigu.mybatis.bean;

import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;

/**
 * @author baixinbao
 * @create 2022/7/29
 */
@Alias("emp")
public class Employee {
    private Integer id;
    private String lastName;
    private String email;
    private String gender;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        int scanle = 1;
        BigDecimal bigDecimal = new BigDecimal(email);
        BigDecimal bigDecimal1 = bigDecimal.setScale(scanle, BigDecimal.ROUND_CEILING);
        this.email = bigDecimal1.toString();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
