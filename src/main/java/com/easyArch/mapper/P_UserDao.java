package com.easyArch.mapper;

import com.easyArch.entity.P_User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface P_UserDao {
    /**
     * 普通用户注册
     * @param username
     * @param password
     */
    void insert(String username,String password,String address);

    /**
     * 普通用户登录验证
     * @param username
     * @return
     */
    P_User isUser(String username);

    /**
     * 返回普通用户地址
     * @param username
     * @return
     */
    String selectUserAddress(String username);
}
