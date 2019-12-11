package com.baizhi.czm.service;

import com.baizhi.czm.entity.Album;
import com.baizhi.czm.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserService {
    //查询所有                   (页号)          (每页显示的条数)
    public HashMap<String,Object> showAll(Integer page, Integer rows);
    //修改状态
    public void updat(User user);

    //查所有导出
    public List<User> quers();
    //用户统计
    public List<User> stat();
}

