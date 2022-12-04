package jxd.bxb.test.study.ThreadStudy;

import jxd.bxb.test.study.ExceptionStudy.Task;

/**
 * @ClassName ThreadExceptionHandle
 * @Description TODO
 * @Author 白新报
 * @Date 2022/12/3 15:44
 * @Version 1.0
 **/
public class ThreadExceptionHandle implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Task.<RuntimeException>throwAs(e);
    }
}
