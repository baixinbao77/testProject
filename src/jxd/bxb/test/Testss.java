package jxd.bxb.test;

import jxd.bxb.test.All.EntityUtils.EntityUtil;
import jxd.bxb.test.All.EntityUtils.PoUtils;
import jxd.bxb.test.Connect.Controller.BaseController;
import jxd.bxb.test.Connect.Conn.MyConnect;
import jxd.bxb.test.result.employees.model.po.EmployeesPo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author baixinbao
 * @create 2022/6/27
 */
public class Testss extends BaseController<EmployeesPo> {

    public class ListNode {
        int val;
        ListNode next;


        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            StringBuffer s1 = new StringBuffer();
            StringBuffer s2 = new StringBuffer();
            ListNode listNode = new ListNode();
            ListNode ln = new ListNode();
            while (l1 != null) {
                s1.append(l1.val);
                l1 = l1.next;

            }
            while (l2 != null) {
                s2.append(l2.val);
                l2 = l2.next;
            }
            String i1 = String.valueOf(Integer.valueOf(s1.reverse().toString()) + Integer.valueOf(s2.reverse().toString()));
            String[] split = i1.split("");
            int i = split.length - 1;
            while(i >= 0){//从头指针开始遍历
                listNode.val = Integer.valueOf(split[i]);
                i--;
                for (int k = 0; k < split.length - i; k++) {
                    listNode.next = listNode;
                }

            //将尾指针和后面连接起来
        }
            return listNode;
        }
    }

@Test
public void testsh(){
        Integer a = 999;
    System.out.println(a.toString().length());
}



    @Test
    public void test () {
        EmployeesPo employeesPo = new EmployeesPo();
        employeesPo.setEmployeeid(1000);
        employeesPo.setEmployeename("zgk");
        employeesPo.setAge(10);
        employeesPo.setIdcard("saasa ");
        employeesPo.setEmployeeid(1000);
        employeesPo.setPassword("saas");
        employeesPo.setPhonenumber("saas");
        employeesPo.setSalary(12);
        employeesPo.setHiredate("2022-02-02");

        super.insert(employeesPo);
    }

    @Test
    public void testsas () {
       String a = null;
        a.length();
        int integer = 10;
    }

    @Test
    public void testyyy() throws SQLException, IOException {
        String tableName = "employees";
        String fileName = "employees";
        String name = "employees";
        List<String> fieldList = MyConnect.getFieldList(tableName);
        List<String> fieldDescList = EntityUtil.initFieldDesc(MyConnect.getFieldDescList(tableName));
        List<String> fieldTypeListOld = MyConnect.getFieldTypeList(tableName);
        List<String> fieldTypeListNew = EntityUtil.initFieldTypeList(fieldTypeListOld);
        String poPath = PoUtils.createPoFile(fieldList, fieldTypeListNew, fieldDescList, fileName, name , tableName);
    }





}
