package jxd.bxb.test.study;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName MyMap
 * @Description TODO
 * @Author 白新报
 * @Date 2022/11/25 16:54
 * @Version 1.0
 **/
public class MyMap<String,V> extends HashMap<String , V> {
    @Override
    public boolean containsKey(Object key) {
        return super.containsKey(key);
    }

    @Override
    public V put(String key, V value) {
        return super.put(key, value);
    }
}
