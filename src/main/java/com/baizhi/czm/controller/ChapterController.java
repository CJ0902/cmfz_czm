package com.baizhi.czm.controller;

import com.baizhi.czm.entity.Album;
import com.baizhi.czm.entity.Chapter;
import com.baizhi.czm.service.AlbumService;
import com.baizhi.czm.service.ChapterService;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/chapter")
public class ChapterController {
    //注入
    @Resource
    private ChapterService chapterService;

    @RequestMapping("/showAll")
    public HashMap<String,Object> showAll(Integer page,Integer rows,String rowId,HttpSession session){
        HashMap<String, Object> map = chapterService.showAll(page, rows,rowId);
        session.setAttribute("ros",rowId);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/edit")
    public String edit(Chapter chapter, String oper,HttpSession session){
        System.out.println(chapter);
        String ros =(String) session.getAttribute("ros");

        String id=null;

        //添加
        if (oper.equals("add")){
            if (chapter.getAlbum_id()==null){
                chapter.setAlbum_id(ros);
                id = chapterService.add(chapter);
            }
        }
        //修改
        if(oper.equals("edit")){
            chapterService.updat(chapter);
        }
        //删除
        if(oper.equals("del")){
            chapterService.delet(chapter.getId());
        }
        return id;
    }

    //音频上传
    @RequestMapping("uploadChapter")
    public void uploadChapter(MultipartFile src,String id,HttpServletRequest request){

        chapterService.uploadChapter(src,id,request);
    }

    //下载
    @RequestMapping("auDownloag")
    public void auDownloag(String dname, HttpServletRequest request, HttpServletResponse response){
        chapterService.auDownloag(dname,request,response);

    }
}
