package com.baizhi.czm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.baizhi.czm.dao")
@SpringBootApplication
public class CmfzCzmApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmfzCzmApplication.class, args);
    }

}
