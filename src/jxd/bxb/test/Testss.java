package jxd.bxb.test;

import com.mysql.cj.xdevapi.WarningImpl;
import com.sun.org.slf4j.internal.LoggerFactory;
import jxd.bxb.test.All.EntityUtils.Path;
import jxd.bxb.test.Connect.Controller.BaseController;
import jxd.bxb.test.result.Result;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author baixinbao
 * @create 2022/6/27
 */
public class Testss extends BaseController<Result> {

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
        Integer a = null;
    System.out.println(a.equals(Integer.valueOf(1)));
}



    @Test
    public void test () {
        Result result = new Result();
        super.insert(result);
    }

    @Test
    public void testsas () {
        Field[] fields = Path.class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i].getName());
        }
    }

}
