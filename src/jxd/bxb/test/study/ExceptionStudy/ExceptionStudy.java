package jxd.bxb.test.study.ExceptionStudy;

import jxd.bxb.test.result.Result;
import jxd.bxb.test.result.employees.model.po.EmployeesPo;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @ClassName ExceptionStudy
 * @Description TODO
 * @Author 白新报
 * @Date 2022/11/11 19:47
 * @Version 1.0
 **/
public class ExceptionStudy {

    public static void main(String[] args) {
        int start = 0;
        int end = 0;
        Thread thread = new Thread(Task.asRunnable(() -> {
            System.out.println("hello");
        }));
        thread.start();
    }

    public static <T , R> R test(Function<T , R> function , T t) {
        return function.apply(t);
    }

    @Test
    public void ssa() {
        String ds = test((start) -> {
            System.out.println(start);
            return start;
        }, "ds");
        System.out.println(ds);
        EmployeesPo employeesPo = new EmployeesPo();
        employeesPo.setEmployeename("saaassasasa");
        String test = test((employeesPo1) -> {
            System.out.println(employeesPo1);
            employeesPo1.setPassword("12333333333");
            return employeesPo1.getPassword();
        }, employeesPo);
        System.out.println(test);
        EmployeesPo employeesPo1 = new EmployeesPo();
        employeesPo1.setId(12);
        Class<EmployeesPo> employeesPoClass = EmployeesPo.class;
        Result result = new Result();
        Result cast = employeesPoClass.cast(employeesPo1);
        System.out.println(cast);
    }

}
