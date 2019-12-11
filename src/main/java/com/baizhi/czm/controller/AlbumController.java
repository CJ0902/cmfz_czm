package com.baizhi.czm.controller;

import com.baizhi.czm.entity.Album;
import com.baizhi.czm.entity.Banner;
import com.baizhi.czm.entity.Chapter;
import com.baizhi.czm.service.AlbumService;
import com.baizhi.czm.service.BannerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/album")
public class AlbumController {
    //注入
    @Resource
    private AlbumService albumService;

    @RequestMapping("/showAll")
    public HashMap<String,Object> showAll(Integer page, Integer rows, HttpSession session){
        HashMap<String, Object> map = albumService.showAll(page, rows);
        return map;
    }

    @RequestMapping("/edit")
    public String edit(Album album, String oper, Chapter chapter){
        String id=null;
        System.out.println(album);
        System.out.println("chapter="+chapter);

        //添加
        if(oper.equals("add")){
            id = albumService.add(album);
        }
        //修改
        if(oper.equals("edit")){
            albumService.updat(album);
        }
        //删除
        if(oper.equals("del")){
            if(chapter.getAlbum_id()==null){
                albumService.delet(album.getId());
            }else{
                return "/main/login";
            }
        }
        return "id";
    }

    //文件上传
    @RequestMapping("/uploadAlbum")
    public void uploadAlbum(MultipartFile cover_img, String id, HttpServletRequest request){
        //1.根据相对路径获取绝对路径
        String realPath = request.getServletContext().getRealPath("/upload/image1");
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdir();
        }
        //2.获取文件名
        String filename = cover_img.getOriginalFilename();
        //重命名图片名
        String newName=new Date().getTime()+"-"+filename;
        //3.文件上传
        try {
            cover_img.transferTo(new File(realPath,newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
