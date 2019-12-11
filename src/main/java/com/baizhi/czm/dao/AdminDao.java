package com.baizhi.czm.dao;

import com.baizhi.czm.entity.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AdminDao {
    //登录
    public Admin queryuser(@Param("username") String username, @Param("password")String password);
   ///查所有
    public List<Admin> queryAll();
}
