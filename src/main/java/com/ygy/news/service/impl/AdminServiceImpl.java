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
        String passwordMd5 = MD5Util.MD5Encode(password, "UTF-8");
        return adminMapper.login(userName, passwordMd5);
    }

    @Override
    public Admin getUserDetailById(Long loginUserId) {
        return adminMapper.selectByPrimaryKey(loginUserId);
    }

    /**
     * 修改密码
     * @param loginUserId
     * @param originalPassword
     * @param newPassword
     * @return
     */
    @Override
    public Boolean updatePassword(Long loginUserId, String originalPassword, String newPassword) {

        Admin admin=adminMapper.selectByPrimaryKey(loginUserId);

        //当用户非空才能进行更改
        if(admin!=null){
            String originalPasswordMD5=MD5Util.MD5Encode(originalPassword,"UTF-8");
            String newPasswordMD5=MD5Util.MD5Encode(newPassword,"UTF-8");
            //比较原密码是否正确
            if(originalPasswordMD5.equals(admin.getLoginPassword())){
                //设置新密码并修改
                admin.setLoginPassword(newPasswordMD5);
                if(adminMapper.updateByPrimaryKeySelective(admin)>0){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 修改用户名
     * @param loginUserId
     * @param loginUserName
     * @param nickName
     * @return
     */
    @Override
    public Boolean updateName(Long loginUserId, String loginUserName, String nickName) {

        Admin admin=adminMapper.selectByPrimaryKey(loginUserId);
        //当前用户非空才可以进行更改
        if(admin!=null){
            admin.setLoginName(loginUserName);
            admin.setAdminNickName(nickName);
            if(adminMapper.updateByPrimaryKeySelective(admin)>0){
                return true;
            }
        }
        return false;
    }
}
