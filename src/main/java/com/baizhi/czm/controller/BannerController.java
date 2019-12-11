package com.baizhi.czm.controller;

import com.baizhi.czm.service.BannerService;
import com.baizhi.czm.entity.Banner;
import com.baizhi.czm.vo.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/banner")
public class BannerController {
    //注入
    @Resource
    private BannerService bannerService;

    @RequestMapping("/showAll")
    public HashMap<String,Object> showAll(Integer page,Integer rows){
        HashMap<String, Object> map = bannerService.showAll(page, rows);
        return map;
    }

    @RequestMapping("/edit")
    public String edit(Banner banner,String oper){
        String id=null;
        System.out.println(banner);
        //添加
        if(oper.equals("add")){
              id=bannerService.add(banner);
        }
        //修改
        if(oper.equals("edit")){
            System.out.println("banner==="+banner);
            bannerService.updat(banner);
        }
        //删除
        if(oper.equals("del")){
            bannerService.delet(banner.getId());
        }
        return "id";
    }

    //文件上传
    @RequestMapping("/uploadBanner")
    public void uploadBanner(MultipartFile src_img, String id, HttpServletRequest request){
        //1.根据相对路径获取绝对路径
        String realPath = request.getServletContext().getRealPath("/upload/image");
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdir();
        }
        //2.获取文件名
        String filename = src_img.getOriginalFilename();
        //重命名图片名
        String newName=new Date().getTime()+"-"+filename;
        //3.文件上传
        try {
            src_img.transferTo(new File(realPath,newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
