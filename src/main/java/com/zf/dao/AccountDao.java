package com.zf.dao;

import com.zf.domain.Account;

import java.util.List;

/**
 * @author zzz
 * @date 2021/3/29
 */
public interface AccountDao {
    /*@Select("")
    @Results(value = {
            @Result(column = "",property = ""),
            @Result(column = "",property = "",many = @Many(select = "",fetchType = FetchType.LAZY))

    }
    )*/
    List<Account> findAll();
}
