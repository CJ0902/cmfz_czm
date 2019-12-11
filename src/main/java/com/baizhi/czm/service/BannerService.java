package com.baizhi.czm.service;

import com.baizhi.czm.entity.Banner;
import com.baizhi.czm.vo.Page;

import java.util.HashMap;
import java.util.List;

public interface BannerService {
    //查询所有                   (页号)          (每页显示的条数)
    public HashMap<String,Object> showAll(Integer page,Integer rows);

    //添加
    public String add(Banner banner);
    //修改
    public void updat(Banner banner);
    //删除
    public void delet(String id);
}
