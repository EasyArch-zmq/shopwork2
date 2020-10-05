package com.easyArch.service.impl;

import com.alibaba.fastjson.JSON;
import com.easyArch.entity.G_User;
import com.easyArch.entity.P_User;
import com.easyArch.mapper.G_UserDao;
import com.easyArch.mapper.P_UserDao;
import com.easyArch.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private P_UserDao p_userDao;
    @Autowired
    private G_UserDao g_userDao;

    @Override
    public String p_register(P_User p_user) {
        String username_1 = p_user.getUsername();
        String password_1 = p_user.getPassword();
        System.out.println(password_1);
        p_userDao.insert(username_1,password_1,p_user.getAddress());
        return JSON.toJSONString("T");
    }

    @Override
    public String g_register(G_User g_user) {
        String username_1 = g_user.getUsername();
        String password_1 = g_user.getPassword();
        System.out.println(password_1);
        System.out.println(username_1);
        g_userDao.insert(username_1,password_1,g_user.getAddress());
        return JSON.toJSONString("T");
    }

    @Override
    public String g_login(G_User g_user) {
        String username_1 = g_user.getUsername();
        String password_1 = g_user.getPassword();
        System.out.println(password_1);
        System.out.println(username_1);
        G_User user1 = g_userDao.isUser(username_1);
        if (user1 != null && user1.getPassword().equals(password_1)) {
            return JSON.toJSONString("T");
        }else {
            return JSON.toJSONString("F");
        }
    }

    @Override
    public String p_login(P_User p_user) {
        String username_1 = p_user.getUsername();
        String password_1 = p_user.getPassword();
        System.out.println(password_1);
        System.out.println(username_1);
        P_User user1 = p_userDao.isUser(username_1);
        if (user1 != null && user1.getPassword().equals(password_1)) {
            return JSON.toJSONString("T");
        }else {
            return JSON.toJSONString("F");
        }
    }
}
