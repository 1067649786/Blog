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

    /**
     * 通过id获取用户信息
     * @param loginUserId
     * @return
     */
    Admin getUserDetailById(Long loginUserId);

    /**
     * 修改当前用户的密码
     * @param loginUserId
     * @param originalPassword
     * @param newPassword
     * @return
     */
    Boolean updatePassword(Long loginUserId,String originalPassword,String newPassword);

    /**
     * 修改当前用户的名称信息
     * @param loginUserId
     * @param loginUserName
     * @param nickName
     * @return
     */
    Boolean updateName(Long loginUserId,String loginUserName,String nickName);
}
