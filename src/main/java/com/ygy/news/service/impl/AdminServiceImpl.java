package com.ygy.news.service.impl;

import com.ygy.news.entity.Admin;
import com.ygy.news.mapper.AdminMapper;
import com.ygy.news.service.AdminService;
import com.ygy.news.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 实现登陆
     * @param userName
     * @param password
     * @return
     */
    @Override
    public Admin login(String userName, String password) {
        String passwordMd5= MD5Util.MD5Encode(password,"UTF-8");
        return adminMapper.login(userName,passwordMd5);
    }
}
