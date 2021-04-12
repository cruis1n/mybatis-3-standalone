package com.zf.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zzz
 * @date 2021/3/27
 */
@Data
public class User implements Serializable{

    private Integer id;
    private String username;
    private Date birthday;
    private String sex;
    private String address;

}
