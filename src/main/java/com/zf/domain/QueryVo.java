package com.zf.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author zzz
 * @date 2021/3/28
 */
public class QueryVo implements Serializable {

    private User user;
    private List<Integer> ids;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
