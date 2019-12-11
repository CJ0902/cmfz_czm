package com.baizhi.czm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor    //无参构造
@AllArgsConstructor  //有参构造
public class Album {
    private String id;
    private String title;//标题
    private String cover_img;//封面
    private String score;//评分
    private String author;//作者
    private String broadcast;//播音
    private String counts;//集数
    private String content;//内容
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date pub_date;//发布日期

}
