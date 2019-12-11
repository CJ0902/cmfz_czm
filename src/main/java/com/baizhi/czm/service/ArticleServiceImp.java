package com.baizhi.czm.service;

import com.baizhi.czm.dao.ArticleDao;
import com.baizhi.czm.entity.Article;
import com.baizhi.czm.util.UUIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImp implements ArticleService {
    
    //注入
    @Resource
    private ArticleDao articleDao;


    //查所有
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap<String, Object> showAll(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        //1.总条数    records
        Integer records = articleDao.totalcount();
        map.put("records",records);
        //2.总页数   total
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        map.put("total",total);
        //3.当前页   page
        map.put("page",page);
        //4.数据    rows
        List<Article> articles = articleDao.queryAll(page,rows);
        map.put("rows",articles);
        return map;
    }

    //添加
    @Override
    public void add(Article article) {
        String uuid = UUIDUtil.getUUID();
        article.setId(uuid);
        article.setUpload_time(new Date());
        //暂定
        article.setGuru_id("1");
        articleDao.inse(article);
    }

    //修改
    @Override
    public void update(Article article) {
        articleDao.update(article);
    }

    //删除
    @Override
    public void delete(String id) {
        articleDao.delete(id);
    }

}
