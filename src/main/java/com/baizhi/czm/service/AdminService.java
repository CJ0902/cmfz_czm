package com.baizhi.czm.service;


import javax.servlet.http.HttpSession;
import java.util.HashMap;

public interface AdminService {
    //登录
    public HashMap<String,Object> logins(String username, String password, String enCode, HttpSession session);
   /* //查询所有
    public List<Admin> query();*/
}
