package com.baizhi.czm.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

@Data
@NoArgsConstructor    //无参构造
@AllArgsConstructor  //有参构造
public class Guru {
    @Excel (name="上师id")
    private String id;
    private String name;
    private String pic_img;//头像
    private String username;
    private String password;
    private String salt;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String reg_time;//注册时间

    @Excel(name = "用户")
    private List<User> users;


}
