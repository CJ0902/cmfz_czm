package com.baizhi.czm.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor    //无参构造
@AllArgsConstructor  //有参构造
public class User {
    @Excel(name = "Id")
    private String id;
    @Excel(name = "电话")
    private String phone;
    @Excel(name = "密码")
    private String password;
    private String salt;
    @Excel(name = "状态")
    private String status;
    @Excel(name = "头像")
    private String pic_img;
    @Excel(name = "上师名")
    private String name;
    @Excel(name = "法名")
    private String f_name;
    @Excel(name = "性别")
    private String sex;
    @Excel(name = "住址")
    private String city;
    @Excel(name = "签名")
    private String sign;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "注册时间",format = "yyyy年mm月dd日",width = 20)
    private Date reg_time;//注册时间
    @Excel(name = "所属上师id")
    private String guru_id;
}
