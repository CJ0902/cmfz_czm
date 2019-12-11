package com.baizhi.czm.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.util.Date;

@Data
@NoArgsConstructor    //无参构造
@AllArgsConstructor  //有参构造
public class Banner {
    private String id;//id
    private String src_img;//图片
    private String describ;//描述
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date upload_time;//上传日期
    private String state;//状态
}
