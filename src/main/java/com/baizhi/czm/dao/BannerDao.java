package com.baizhi.czm.dao;

import com.baizhi.czm.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDao {
    //展示所有
    public List<Banner> selectAll(@Param("page") Integer page, @Param("rows") Integer rows);
    //求总条数
    public  Integer  totalcount();

    //添加
    public void add(Banner banner);
    //修改
    public  void  updateByID(Banner banner);
    //根据id删除
    public void deletByID(String id);
}
