package com.baizhi.czm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor    //无参构造
@AllArgsConstructor  //有参构造
public class Admin {
    private String id;
    private String username;
    private String password;
}
