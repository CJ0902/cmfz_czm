package com.baizhi.czm.service;

import com.baizhi.czm.entity.Album;
import com.baizhi.czm.entity.Banner;

import java.util.HashMap;

public interface AlbumService {
    //查询所有                   (页号)          (每页显示的条数)
    public HashMap<String,Object> showAll(Integer page, Integer rows);

    //添加
    public String add(Album album);
    //修改
    public void updat(Album album);
    //删除
    public void delet(String id);
}
