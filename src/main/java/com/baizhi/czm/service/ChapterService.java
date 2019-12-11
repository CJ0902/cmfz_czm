package com.baizhi.czm.service;

import com.baizhi.czm.entity.Album;
import com.baizhi.czm.entity.Chapter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public interface ChapterService {
    //查询所有                   (页号)          (每页显示的条数)
    public HashMap<String,Object> showAll(Integer page, Integer rows,String album_id);

    //添加
    public String add(Chapter chapter);
    //修改
    public void updat(Chapter chapter);
    //删除
    public void delet(String id);

    //音频上传
    public void uploadChapter(MultipartFile src, String id, HttpServletRequest request);

    //下载
    public void auDownloag(String dname, HttpServletRequest request, HttpServletResponse response);
}
