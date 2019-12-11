package com.baizhi.czm.dao;

import com.baizhi.czm.entity.Admin;
import com.baizhi.czm.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserDao {
    //查所有
    public List<User> queryAll(@Param("page") Integer page, @Param("rows") Integer rows);
    //求总条数
    public  Integer  totalcount();

    //修改状态
    public void updat(User user);

    //查所有
    public List<User> queryAlls();
}
