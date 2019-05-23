package com.ygy.news.service;

import com.ygy.news.entity.Admin;

public interface AdminService {

    /**
     * 登陆
     * @param userName
     * @param password
     * @return
     */
    Admin login(String userName,String password);
}
