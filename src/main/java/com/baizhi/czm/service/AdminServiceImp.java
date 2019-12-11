package com.baizhi.czm.service;

import com.baizhi.czm.dao.AdminDao;
import com.baizhi.czm.entity.Admin;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Service
@Transactional  //添加事务
public class AdminServiceImp implements AdminService {

    //注入
    @Resource
    private AdminDao adminDao;

    //登录
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap<String,Object> logins(String username, String password,String enCode,HttpSession session) {

        HashMap<String, Object> map = new HashMap<>();
        //获取验证码
        String code =(String) session.getAttribute("code");
        //调业务
        Admin admin = adminDao.queryuser(username, password);
        //判断验证码
        if(code.equals(enCode)){
            //判断用户
            if(admin!=null){
                //判断密码
                if(admin.getPassword()!=password){
                    //登录成功,存储信息
                    session.setAttribute("login",admin);
                    map.put("success","200");
                    map.put("message","登录成功");
                }else{
                    map.put("success","400");
                    map.put("message","密码错误");
                }
            }else{
                map.put("success","400");
                map.put("message","用户不存在或密码错误");
            }
        }else{
            map.put("success","400");
            map.put("message","验证码错误");
        }
        return map;
    }



    /*//查所有
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Admin> query() {
        List<Admin> admins = adminDao.queryAll();
        return admins;
    }*/
}
