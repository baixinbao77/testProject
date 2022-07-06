package jxd.bxb.test.Connect.inter;

/**
 * @author baixinbao
 * @create 2022/7/6
 */
public interface BaseMapper<T> {
    int insert (T entity);
    int update (T entity);
    int delete (T entity);
}
