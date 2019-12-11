package com.baizhi.czm.controller;

import com.baizhi.czm.service.AdminService;
import com.baizhi.czm.util.ImageCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

@Controller
@RequestMapping("/admin")
public class AdminController {

    //注入
    @Resource
    private AdminService adminService;

    //验证码
    @RequestMapping("/getImageCode")
    public void GetImageCode(HttpSession session, HttpServletResponse response){
        //1.获取验证码
        String securityCode = ImageCodeUtil.getSecurityCode();
        System.out.println("验证码: "+securityCode);
        //2.将随机字符存入作用域
        session.setAttribute("code",securityCode);
        //3.将验证码字符生成图片
        BufferedImage image = ImageCodeUtil.createImage(securityCode);
        //4.设置响应格式
        response.setContentType("image/png");
        try {
            //5.将验证码响应到前台
            ImageIO.write(image,"png",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //登录
    @RequestMapping("/log")
    @ResponseBody
    public HashMap<String, Object> log(String enCode, String username, String password, HttpSession session) throws Exception {
        HashMap<String, Object> map = adminService.logins(username, password, enCode, session);
        session.setAttribute("map",map);
        return map;
    }
    //退出登录
    @RequestMapping("/logs")
    public String logs(HttpSession session){
        HashMap map =(HashMap) session.getAttribute("map");
        map.remove(map);
        return "login/login";
    }

    /*//查所有
    @RequestMapping("/showAll")
    public List<Admin>  query(Model model)throws Exception{
        List<Admin> admins = adminService.query();
        return admins;
    }*/
}
