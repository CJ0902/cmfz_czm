package com.baizhi.czm.service;

import com.baizhi.czm.entity.Article;
import com.baizhi.czm.entity.User;

import java.util.HashMap;

public interface ArticleService {
    //查询所有                   (页号)          (每页显示的条数)
    public HashMap<String,Object> showAll(Integer page, Integer rows);

    //添加
    public void add(Article article);
    //修改
    public void update(Article article);
    //删除
    public void delete(String id);

}

