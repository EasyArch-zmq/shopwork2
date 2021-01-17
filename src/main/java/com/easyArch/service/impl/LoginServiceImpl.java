package com.easyArch.service.impl;

import com.alibaba.fastjson.JSON;
import com.easyArch.entity.G_User;
import com.easyArch.entity.P_User;
import com.easyArch.mapper.G_UserDao;
import com.easyArch.mapper.P_UserDao;
import com.easyArch.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.concurrent.TimeUnit;
@SuppressWarnings("unchecked")
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private P_UserDao p_userDao;
    @Autowired
    private G_UserDao g_userDao;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String p_register(P_User p_user) {
        String username_1 = p_user.getUsername();
        //MD5加密
        String password_1 = p_user.getPassword();//DigestUtils.md5DigestAsHex(p_user.getPassword().getBytes());
        System.out.println(password_1);
        p_userDao.insert(username_1,password_1,p_user.getAddress());
        redisTemplate.opsForValue().set(username_1,p_user,3600, TimeUnit.SECONDS);
        return JSON.toJSONString("T");
    }

    @Override
    public String g_register(G_User g_user) {
        String username_1 = g_user.getUsername();
        //MD5加密
        String password_1 = g_user.getPassword();//DigestUtils.md5DigestAsHex(g_user.getPassword().getBytes());
        System.out.println(password_1);
        System.out.println(username_1);
        g_userDao.insert(username_1,password_1,g_user.getAddress());
        redisTemplate.opsForValue().set(username_1,g_user,3600, TimeUnit.SECONDS);
        return JSON.toJSONString("T");
    }

    @Override
    public String g_login(G_User g_user) {
        String username_1 = g_user.getUsername();
        G_User user1;
        //MD5加密
        String password_1 =g_user.getPassword();//DigestUtils.md5DigestAsHex(g_user.getPassword().getBytes());
        if (redisTemplate.opsForValue().get(username_1)!=null){
            user1= (G_User) redisTemplate.opsForValue().get(username_1);
        }else {
            user1=g_userDao.isUser(username_1);
            redisTemplate.opsForValue().set(username_1,user1,3600, TimeUnit.SECONDS);
        }
        if (user1 != null && user1.getPassword().equals(password_1)) {
            return "T";
        }else {
            return "F";
        }
    }

    @Override
    public String p_login(P_User p_user) {
        String username_1 = p_user.getUsername();
        //MD5加密
        String password_1 =p_user.getPassword();//DigestUtils.md5DigestAsHex(p_user.getPassword().getBytes());
        System.out.println(password_1);
        System.out.println(username_1);
        P_User user1;
        if (redisTemplate.opsForValue().get(username_1)!=null){
            user1= (P_User) redisTemplate.opsForValue().get(username_1);
        }else {
            user1=p_userDao.isUser(username_1);
            redisTemplate.opsForValue().set(username_1,user1,3600, TimeUnit.SECONDS);
        }
        if (user1 != null && user1.getPassword().equals(password_1)) {
            return "T";
        }else {
            return "F";
        }
    }


    @Cacheable(cacheNames = "p_user", key = "#username")
    @Override
    public P_User selectBypName(String name) {
        return p_userDao.isUser(name);
    }

    @Cacheable(cacheNames = "g_user", key = "#username")
    @Override
    public G_User selectBygName(String name) {
        return g_userDao.isUser(name);
    }
}
