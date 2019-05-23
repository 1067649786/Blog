package com.ygy.news.mapper;

import com.ygy.news.entity.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {

    /**
     * 登陆接口
     * @param userName
     * @param password
     * @return
     */
    Admin login(String userName,String password);
}
