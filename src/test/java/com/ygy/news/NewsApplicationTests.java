package com.ygy.news;

import com.ygy.news.entity.Admin;
import com.ygy.news.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsApplicationTests {

    @Autowired
    private AdminService adminService;

    @Test
    public void contextLoads() {

        Admin admin=adminService.login("shiyanlou","123456");
        System.out.println(admin);
    }

}
