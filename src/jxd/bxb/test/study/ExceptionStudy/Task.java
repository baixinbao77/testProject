package jxd.bxb.test.study.ExceptionStudy;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @ClassName Task
 * @Description TODO
 * @Author 白新报
 * @Date 2022/11/11 19:54
 * @Version 1.0
 **/
public interface Task {

    void run() throws Exception;
    @SuppressWarnings("unchecked")
    static <T extends Exception> void throwAs(Throwable t) throws T{
        throw (T) t;
    }


    static Runnable asRunnable(Task task) {

        return () -> {
            try {
                task.run();
            } catch (Exception e) {
                Task.<RuntimeException>throwAs(e);
            }
        };
    }

    static Consumer asConsumer(Task task) {
        return (start) -> {
            System.out.println(start);
        };
    }

    static BiConsumer asBiConsumer(Task task) {
        return (start , end) -> {
            try {
            } catch (Exception e) {
                Task.throwAs(e);
            }
            System.out.println(start);
            System.out.println(end);
        };
    }

}
