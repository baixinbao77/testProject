package jxd.bxb.test.study.ThreadStudy;

import jxd.bxb.test.utils.BeanUtils;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ThreadTest
 * @Description TODO
 * @Author 白新报
 * @Date 2022/11/28 19:14
 * @Version 1.0
 **/
public class ThreadTest {

    public static final int DELAY = 10;
    public static final int STEPS = 100;
    public static final int MAX_AMOUNT = 1000;

    public static final ThreadLocal<SimpleDateFormat> sdf = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    @Test
    public void test01() {
        Thread.State state = Thread.currentThread().getState();
        System.out.println(state);
        Thread thread = Thread.currentThread();
        System.out.println(thread.isInterrupted());
        thread.interrupt();
        System.out.println(thread.isInterrupted());
        Thread.setDefaultUncaughtExceptionHandler(new ThreadExceptionHandle());
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        reentrantLock.unlock();
        Condition condition = reentrantLock.newCondition();

        new HashSet<String>();


    }


}
