package com.baizhi.czm;

import com.baizhi.czm.dao.ArticleDao;
import com.baizhi.czm.dao.UserDao;
import com.baizhi.czm.entity.Article;
import com.baizhi.czm.entity.Guru;
import com.baizhi.czm.entity.User;
import com.baizhi.czm.service.GuruService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= CmfzCzmApplication.class)
public class Tests {
    @Resource
    private ArticleDao  articleDao;
    @Resource
    private GuruService guruService;
    @Resource
    private UserDao userDao;
   @Test
    public void  test(){
        List<Article> articles = articleDao.queryAll(1,3);
        System.out.println(articles);
    }
   @Test
    public void test1(){
       Guru guru = guruService.queryId("1");
       System.out.println(guru);
   }
   @Test
    public void test2(){
       List<User> users =userDao.queryAlls();
       int b=0;
       int g=0;
       for (User users1:users){
           if (users1.getSex().equals("1")){
                    b++;
               System.out.println("sum="+b);

           }else {
               g++;
               System.out.println("g="+g);
           }
           System.out.println(b);
           System.out.println(g);
       }
   }
}
