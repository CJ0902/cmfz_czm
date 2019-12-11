package com.baizhi.czm.service;

import com.baizhi.czm.dao.ChapterDao;
import com.baizhi.czm.entity.Album;
import com.baizhi.czm.entity.Banner;
import com.baizhi.czm.entity.Chapter;
import com.baizhi.czm.util.UUIDUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.spi.http.HttpContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class ChapterServiceImp implements ChapterService {

    @Resource
    private ChapterDao chapterDao;
    @Resource
    private HttpSession session;

    //查所有
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap<String, Object> showAll(Integer page, Integer rows,String album_id) {
        HashMap<String, Object> map = new HashMap<>();
        //1.总条数  records
        Integer records = chapterDao.totalcount();
        map.put("records",records);
        //2.总页数  total
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        map.put("total",total);
        //3.当前页  page
        map.put("page",page);
        //4.数据   rows
        List<Banner> banners = chapterDao.selectAll(album_id);
        map.put("rows",banners);

        return map;
    }

    //添加
    @Override
    public String add(Chapter chapter) {
        String s1 =(String) session.getAttribute("s1");

        String uuid = UUIDUtil.getUUID();
        chapter.setId(uuid);
        chapter.setUpload_time(new Date());
        session.setAttribute("chapter",chapter);

        chapterDao.add(chapter);
        return uuid;
    }

    //修改
    @Override
    public void updat(Chapter chapter) {
        chapterDao.update(chapter);
    }

    //删除
    @Override
    public void delet(String id) {
        chapterDao.delet(id);
    }

    //音频上传
    @Override
    public void uploadChapter(MultipartFile src, String id, HttpServletRequest request) {

        //1.相对路径获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/audio");
        //2.判断文件夹是否存在
        File file = new File(realPath);
        if(file.exists()){
            file.mkdir();
        }

        //3.获取上传额文件名
        String filename = src.getOriginalFilename();

        //4.给文件添加事件戳
        String newName = new Date().getTime()+"-"+filename;

        //5.文件上传
        try {
            src.transferTo(new File(realPath,newName));

            //获取文件大小
           long size=src.getSize();
            //String s = String.valueOf(size);
            Double aDouble=Double.valueOf(size)/1024/1024;
            DecimalFormat format = new DecimalFormat("0.00");
            String s1 = format.format(aDouble)+"MB";



            Chapter chapter =(Chapter) session.getAttribute("chapter");

            System.out.println("audioFile="+new File(realPath,newName));
            /*//获取文件时长
            AudioFile audioFile = AudioFileIO.read(new File(realPath,newName));
            AudioHeader audioHeader = audioFile.getAudioHeader();
            int length = audioHeader.getTrackLength();
            String duration=length/60+"分"+length%60+"秒";
            System.out.println("时长="+duration);*/

            System.out.println("src="+chapter.getSrc());

            chapter.setSize(s1);
            chapter.setDuration("3分");



            chapterDao.update(chapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //下载
    @Override
    public void auDownloag(String dname, HttpServletRequest request, HttpServletResponse response) {
        //1.相对路径获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/audio");

        try {
            //2.创建读入流
            FileInputStream inputStream = new FileInputStream(new File(realPath, dname));
            //3.设置响应头       attachment:以复检形式下载  inline:在在线打开
            response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode(dname,"UTF-8"));
            //4.文件下载
            IOUtils.copy(inputStream,response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
