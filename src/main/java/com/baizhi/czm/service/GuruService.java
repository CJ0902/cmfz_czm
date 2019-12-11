package com.baizhi.czm.service;

import com.baizhi.czm.entity.Guru;
import com.baizhi.czm.entity.User;

import java.util.HashMap;

public interface GuruService {
    //查询所有                   (页号)          (每页显示的条数)
    public HashMap<String,Object> showAll(Integer page, Integer rows);

    //根据id查
    public Guru queryId(String id);
}

