package com.zf.test;

import com.zf.dao.AccountDao;
import com.zf.dao.UserDao;
import com.zf.domain.Account;
import com.zf.domain.QueryVo;
import com.zf.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zzz
 * @date 2021/3/28
 */
public class MybatisTest2 {


    private InputStream is;
    private SqlSession session;
    private UserDao userDao;
    private AccountDao accountDao;
    @Before
    public void init() throws IOException {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);


        session = sessionFactory.openSession();


        userDao = session.getMapper(UserDao.class);
        accountDao = session.getMapper(AccountDao.class);

    }
    @After
    public void destroy() throws IOException {

        is.close();
        session.close();
    }
    @Test
    public void save() throws IOException {
        User user = new User();
        user.setUsername("zhangsan");
        user.setAddress("江西");
        user.setSex("男");
        Date date = new Date();
        user.setBirthday(date);
        System.out.println(user);
        userDao.insertUser(user);

        System.out.println(user);
        session.commit();



    }

    @Test
    public void update() throws IOException {
        User user = new User();
        user.setId(54);
        user.setUsername("wangwu");
        user.setAddress("江西");
        user.setSex("男");
        Date date = new Date();
        user.setBirthday(date);

        userDao.updateUser(user);
        session.commit();

    }


    @Test
    public void findByName(){
        List<User> userList = userDao.findByTagName("王");
        for (User user : userList) {

        System.out.println(user);

        }
    }

    @Test
    public void findByVO(){
        User user = new User();
        user.setUsername("wangwu");


        QueryVo queryVo = new QueryVo();
        queryVo.setUser(user);

        User userVo = userDao.findByVo(queryVo);
        System.out.println(userVo);
    }
    @Test
    public void findByCondition(){
        User u = new User();
        u.setSex("男");
        u.setAddress("京");


        List<User> userList = userDao.findUserByCondition(u);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public  void findByIds(){
        //int[] ids = new int[]{52,54,55};
        List<Integer> list = new ArrayList<>();
        list.add(52);
        list.add(54);
        list.add(55);

        QueryVo queryVo = new QueryVo();
        queryVo.setIds(list);
        List<User> userList = userDao.findUserByIds(queryVo);

        for (User user : userList) {
            System.out.println(user);
        }

    }

    @Test
    public  void findAllAccount(){
        List<Account> all = accountDao.findAll();
        for (Account account : all) {
            System.out.println(account);
        }
    }


}
