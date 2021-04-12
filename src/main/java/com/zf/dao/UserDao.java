package com.zf.dao;

import com.zf.domain.QueryVo;
import com.zf.domain.User;

import java.util.List;

/**
 * @author zzz
 * @date 2021/3/27
 */
public interface UserDao {

    List<User> findAll();

    void insertUser(User user);

    void updateUser(User user);

    List<User> findByTagName(String name);

    User findByVo(QueryVo vo);

    List<User> findUserByCondition(User user);

    //List<User> findUserByIds(int[] ids);
    //List<User> findUserByIds(List<Integer> ids);
    List<User> findUserByIds(QueryVo ids);
}
