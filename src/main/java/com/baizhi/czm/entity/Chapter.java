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
public class Chapter {
    private String id;
    private String title;
    private String src;
    private String duration;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date upload_time;
    private String size;
    private String album_id;
    private Album album;
}
