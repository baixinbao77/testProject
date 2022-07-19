package jxd.bxb.test.result.employees.model.po;


import jxd.bxb.test.Connect.Conn.DataBase;
import jxd.bxb.test.Connect.annotation.DS;
import jxd.bxb.test.Connect.annotation.TableField;
import jxd.bxb.test.Connect.annotation.TableName;
import jxd.bxb.test.utils.annotation.TableId;

@DS(DataBase.MYSQL)
@TableName("employees")
public class EmployeesPo{
	@TableId("employeeID")
	private Integer employeeid;// 人员ID
	@TableField("employeeName")
	private String employeename;// 人员名字
	@TableField("phoneNumber") 
	private String phonenumber;// 人员电话
	@TableField("hireDate") 
	private String hiredate;// 入职时间
	@TableField("salary") 
	private Integer salary;// 工资
	@TableField("password") 
	private String password;// 登录密码
	@TableField("departmentID") 
	private Integer departmentid;// 部门ID
	@TableField("sex") 
	private Integer sex;// 性别默认是0男修改后1女
	@TableField("age") 
	private Integer age;// 年龄
	@TableField("idCard") 
	private String idcard;// 身份证号

	public void setEmployeeid(Integer employeeid) {
		this.employeeid = employeeid; 
	}
	public Integer getEmployeeid() {
		return employeeid;
	}
	public void setEmployeename(String employeename) {
		this.employeename = employeename; 
	}
	public String getEmployeename() {
		return employeename;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber; 
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate; 
	}
	public String getHiredate() {
		return hiredate;
	}
	public void setSalary(Integer salary) {
		this.salary = salary; 
	}
	public Integer getSalary() {
		return salary;
	}
	public void setPassword(String password) {
		this.password = password; 
	}
	public String getPassword() {
		return password;
	}
	public void setDepartmentid(Integer departmentid) {
		this.departmentid = departmentid; 
	}
	public Integer getDepartmentid() {
		return departmentid;
	}
	public void setSex(Integer sex) {
		this.sex = sex; 
	}
	public Integer getSex() {
		return sex;
	}
	public void setAge(Integer age) {
		this.age = age; 
	}
	public Integer getAge() {
		return age;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard; 
	}
	public String getIdcard() {
		return idcard;
	}


}