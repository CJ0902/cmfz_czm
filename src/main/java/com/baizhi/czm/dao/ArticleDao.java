package com.baizhi.czm.dao;

import com.baizhi.czm.entity.Article;
import com.baizhi.czm.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ArticleDao {
    //查所有@Param("guru_id") String guru_id,
    public List<Article> queryAll(@Param("page") Integer page, @Param("rows") Integer rows);
    //求总条数
    public  Integer  totalcount();

    //文章添加
    public void inse(Article article);
    //文章修改
    public void update(Article article);
    //文章删除
    public void delete(String id);
}
