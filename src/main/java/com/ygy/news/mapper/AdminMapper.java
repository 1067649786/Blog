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

    /**
     * 修改用户的信息
     * @param admin
     * @return
     */
    int updateByPrimaryKeySelective(Admin admin);


    /**
     * 通过id查找用户
     * @param adminId
     * @return
     */
    Admin selectByPrimaryKey(Long adminId);


}
