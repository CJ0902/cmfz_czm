package com.baizhi.czm.controller;

import com.baizhi.czm.dao.UserDao;
import com.baizhi.czm.entity.*;
import com.baizhi.czm.service.AlbumService;
import com.baizhi.czm.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    //注入
    @Resource
    private UserService userService;

    @RequestMapping("/showAll")
    public HashMap<String,Object> showAll(Integer page, Integer rows, HttpSession session){
        HashMap<String, Object> map = userService.showAll(page, rows);
        return map;
    }

    //用户统计
    @RequestMapping("/stat")
    public HashMap<String,Object> stat(){
        HashMap<String, Object> map = new HashMap<>();
        List<User> stat1 = userService.stat();
        Integer boys=0;
        Integer girls=0;
        for (User user:stat1){
            if (user.getSex().equals("1")){
                boys++;
            }else{
                girls++;
            }
            map.put("boys", Arrays.asList(boys));
            map.put("girls", Arrays.asList(girls));
        }
        map.put("month", Arrays.asList("男女比例"));
        return map;
    }

    //用户分布
    @RequestMapping("/distr")
    public ArrayList<Object> distr(){
        ArrayList<Object> list = new ArrayList<>();

        ArrayList<City> citiesBoy = new ArrayList<>();
        citiesBoy.add(new City( "北京","300"));
        citiesBoy.add(new City( "上海","500"));
        citiesBoy.add(new City( "天津","600"));
        citiesBoy.add(new City( "浙江","200"));
        citiesBoy.add(new City( "深圳","100"));
        citiesBoy.add(new City( "广州","200"));

        ArrayList<City> citiesGirls = new ArrayList<>();
        citiesGirls.add(new City( "山西","200"));
        citiesGirls.add(new City( "山东","300"));
        citiesGirls.add(new City( "河南","600"));
        citiesGirls.add(new City( "四川","200"));
        citiesGirls.add(new City( "广东","500"));
        citiesGirls.add(new City( "广西","200"));

        China Boy = new China("男",citiesBoy);
        China Grils = new China("女",citiesGirls);
        list.add(Boy);
        list.add(Grils);

        return list;
    }




    @RequestMapping("edit")
    public void edit(User user,String status, String oper,HttpSession session){
        //修改状态
        if (oper.equals("edit")){
            userService.updat(user);
        }
    }

    //查所有导出
    @RequestMapping("/derive")
    public List<User> quer(){
        List<User> quers = userService.quers();
        return quers;
    }
}
