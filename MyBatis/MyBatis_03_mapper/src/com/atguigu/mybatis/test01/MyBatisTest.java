package com.atguigu.mybatis.test01;
import com.atguigu.mybatis.bean.Employee;
import com.atguigu.mybatis.dao.EmployeeDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * 1.接口式编程
 *      原生：             Dao         ===>        DaoImpl
 *      mybatis:          Mapper      ===>         xxxMapper.xml
 * 2. SqlSession 代表和数据库的一次会话，用完必须关闭
 * 3. SqlSession 和connection一样都是非线程安全的，每次使用都应该获取新的对象
 * 4. mapper接口没有实现类，但是Mybatis会为这个接口生成一个代理对象
 *          将接口和xml绑定
 *          EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);
 * 5. 两个重要的配置文件：
 *      Mybatis的全局配置文件：包含数据库连接池信息，事务管理器等...系统运行环境信息
 *      sql映射文件：保存了每一个sql语句的映射信息
 *                  将SQL抽取出来
 * @author baixinbao
 * @create 2022/7/29
 */
public class MyBatisTest {


    /**
     * 1.根据xml配置文件（全局配置）创建一个SqlSessionFactory对象
     *      有数据源一些运行环境信息
     * 2.SQL映射文件，配置了每一个SQL以及SQL的封装规则等
     * 3.将SQL映射文件注册在全局配置文件中
     * 4.写代码：
     *      1).根据全局配置文件得到SqlSessionFactory
     *      2).使用SqlSessionFactory工厂，获取得到SqlSession对象使用它来进行增删改查
     *          一个SqlSession就是代表和数据库的一次会话，用完关闭
     *      3).使用SQL的唯一标识来告诉mybatis执行那个sql，SQl都是保存在SQL映射文件中的
     */
    @Test
    public void testss() {
        String resource = "resource/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取SqlSession实例，能直接执行已经映射的SQL语句
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // (1) s: SQL的唯一标识符 (2) o: 执行SQL要用的参数
        Employee employee = sqlSession.selectOne("com.atguigu.mybatis.bean.Employee.selectEmp", 1);
        System.out.println(employee);
        sqlSession.close();
    }

    @Test
    public void test01() {
        SqlSession sqlSession = getSqlSession();
        // 获取接口的实现类对象
        //      会为接口自动的 创建一个代理对象，代理对象去执行增删改查
        EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);
        System.out.println(mapper.getClass());
        try {
            Employee employeeById = mapper.getEmployeeById(4);
            System.out.println(employeeById);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 测试增删改
     *      1.mybatis允许增删改查直接定义一下类型返回值
     *              Integer  Long   Boolean  void
     *      2.需要我们手动提交
     */
    @Test
    public void test03 () {
        SqlSession sqlSession = getSqlSession();
        EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);
        Employee employeeById = mapper.getEmployeeById(4);
        employeeById.setLastName("zgk");
        employeeById.setGender("0");
        mapper.insert(employeeById);
        System.out.println(employeeById.getId());
//        employeeById.setId(1);
//        mapper.update(employeeById);
        mapper.deleteById(1);

        commitAndClose(sqlSession);
    }



    private void commitAndClose(SqlSession sqlSession) {
        sqlSession.commit();
        sqlSession.close();
    }


    private SqlSession getSqlSession () {
        String resource = "resource/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取SqlSession实例，能直接执行已经映射的SQL语句
        SqlSession sqlSession = sqlSessionFactory.openSession();

        return sqlSession;
    }



}
