package com.zf.test;


import com.zf.dao.UserDao;
import com.zf.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @author zzz
 * @date 2021/3/27
 */
public class MybatisTest {
    public static void main(String[] args) throws Exception {
        //1.获取mybatis配置文件的字节输入流对象
        //Resources: mybatis中用于简化通过类加载器对资源的访问的类。底层为ClassLoader.getSystemClassLoader();
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2.调用SqlSessionFactoryBuilder中的build(InputStream is)方法，通过XMLConfigBuilder解析xml文件或注解信息，
        //获取连接数据库和mapper映射的配置信息，并转化为Configuration对象(用于存放mybatis配置文件的对象)，通过传入Configuration对象
        //来构建出SqlSessionFactory对象，实际返回的是SqlSessionFactory的实现类DefaultSqlSessionFactory
        // public SqlSessionFactory build(Configuration config) {
        //    return new DefaultSqlSessionFactory(config);
        //  }
        //SqlSessionFactoryBuilder: 用于创建SqlSession实例的工厂(SqlSessionFactory) 使用了构建者模式，隐藏对象创建繁琐的细节。
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        /*
         Connection 对象是在 Transaction 中，由 Connection 和 数据库事务 组成了 Transaction 对象。
         Transaction 对象是在 BaseExecutor 中， Transaction 在 Executor 中提供了事务的功能。

         DataSource 初始化是在 XMLConfigBuilder 解析 mybatis-config.xml 配置的 dataSource 节点中。
         同时也创建了 TransactionFactory 对象。并把 DataSource 和 TransactionFactory 封装进了 Environment，
         然后写入了 Configuration 对象。

         Transaction 对象初始化
         在 DefaultSqlSessionFactory 中的 openSessionFromDataSource(),方法中获取项目初始化的 TransactionFactory，
         然后由 TransactionFactory 创建 Transaction 对象，然后再由 Transaction 对象创建 Executor 对象，
         因为 Transaction 对象中包含了 Connection 对象，所以 Executor 中也有了 Connection 对象。

         */
        //3. 从连接或数据源中创建一个SqlSession。使用了工厂模式，解耦。
        //SqlSession为MyBatis的主要Java接口。 通过此接口获取映射器和管理事务。
        SqlSession session = sessionFactory.openSession();

        //4.调用SqlSession的getMapper(Class<T> type)方法，获取UserDao接口的代理对象
        //getMapper()方法底层使用Proxy.newProxyInstance()方法创建代理对象，通过Executor实现对方法的增强，执行sql语句，并对结果集进行封装。
        //使用了代理模式
        UserDao userDao = session.getMapper(UserDao.class);
        User user = userDao.findOne(41);


        System.out.println(user);

        is.close();
        session.close();
    }
}
