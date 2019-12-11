package com.baizhi.czm.controller;

import com.baizhi.czm.entity.User;
import com.baizhi.czm.service.GuruService;
import com.baizhi.czm.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("/guru")
public class GuruController {
    //注入
    @Resource
    private GuruService guruService;

    @RequestMapping("/showAll")
    public HashMap<String,Object> showAll(Integer page, Integer rows){
        HashMap<String, Object> map = guruService.showAll(page, rows);
        return map;
    }
}
