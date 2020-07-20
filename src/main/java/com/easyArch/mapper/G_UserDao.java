package com.easyArch.mapper;

import com.easyArch.entity.G_User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface G_UserDao {

    /**
     * 管理员注册
     * @param username
     * @param password
     */
     void insert(String username,String password,String address);

    /**
     * 管理员登录
     * @param username
     * @return
     */
    G_User isUser(String username);
    /**
     * 返回普通用户地址
     * @param username
     * @return
     */
    String selectG_UserAddress(String username);
}
