package com.baizhi.czm.controller;

import com.baizhi.czm.entity.Article;
import com.baizhi.czm.entity.Guru;
import com.baizhi.czm.entity.User;
import com.baizhi.czm.service.ArticleService;
import com.baizhi.czm.service.GuruService;
import com.baizhi.czm.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("/article")
public class ArticleController {
    //注入
    @Resource
    private ArticleService articleService;
    @Resource
    private GuruService guruService;

    //查询
    @RequestMapping("/showAll")
    public HashMap<String,Object> showAll(Integer page, Integer rows,String guru_id){
        HashMap<String, Object> map = articleService.showAll(page, rows);
        return map;
    }
    //根据guru_id查上师
    @RequestMapping("queryId")
    public Guru queryId(String guru_id){
        Guru guru = guruService.queryId(guru_id);
        return guru;
    }

    //添加文章
    @RequestMapping("/add")
    public void add(Article article){

        articleService.add(article);
    }
    //修改文章
    @RequestMapping("/update")
    public void update(Article article){

        articleService.update(article);
    }

    //文章删除
    @RequestMapping("delete")
    public void delete(String id){
        articleService.delete(id);
    }


}
