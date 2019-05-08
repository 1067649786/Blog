package com.ygy.newsmanagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.ygy.newsmanagement.dao")
@SpringBootApplication
@EnableTransactionManagement
public class NewsmanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsmanagementApplication.class, args);
    }

}
