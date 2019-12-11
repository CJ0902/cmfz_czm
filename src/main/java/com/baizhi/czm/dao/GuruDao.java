package com.baizhi.czm.dao;

import com.baizhi.czm.entity.Guru;
import com.baizhi.czm.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface GuruDao {
    //查所有
    public List<Guru> queryAll(@Param("page") Integer page, @Param("rows") Integer rows);
    //求总条数
    public  Integer  totalcount();

    //根据id查
    public Guru queryId(String id);

}
