package com.baizhi.czm.dao;

import com.baizhi.czm.entity.Album;
import com.baizhi.czm.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDao {
    //展示所有
    public List<Album> selectAll(@Param("page") Integer page, @Param("rows") Integer rows);
    //求总条数
    public  Integer  totalcount();

    //添加
    public void add(Album album);
    //修改
    public void updat(Album album);
    //删除
    public void delet(String id);
}
