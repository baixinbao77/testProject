package jxd.bxb.test.study.collectionStudy;


import java.util.*;

/**
 * @ClassName EnumerationTest
 * @Description TODO
 * @Author 白新报
 * @Date 2022/11/26 8:58
 * @Version 1.0
 **/
public class EnumerationTest extends AbstractQueue {

    @Override
    public Iterator iterator() {
        ArrayList<Integer> objects = new ArrayList<Integer>(100) {
            {
                for (int i = 0; i < 100; i++) {
                    add(i);
                }
            }
        };
        objects.iterator().forEachRemaining(
                (item) -> {
                    System.out.println(item);
                }
        );

        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean offer(Object o) {
        return false;
    }

    @Override
    public Object poll() {
        return null;
    }

    @Override
    public Object peek() {
        return null;
    }
}
