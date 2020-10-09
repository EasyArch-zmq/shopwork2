package com.easyArch.controller;

import com.alibaba.fastjson.JSON;
import com.easyArch.entity.G_User;
import com.easyArch.entity.P_User;
import com.easyArch.service.LoginService;
import com.easyArch.support.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class LoginController {
    private static final Logger LOGGER= LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    /**
     * 普通用户登录
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "p_login",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String p_login(@RequestBody P_User p_user){
        if (p_user != null) {
            if (loginService.p_login(p_user).equals("T")) {
                String sign = JWTUtil.sign(p_user, 60L * 1000L * 120L);
                return JSON.toJSONString("token:" + sign);
            }
        }
        return JSON.toJSONString("F");
    }


    /**
     * 管理员登录
     * @param g_user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "g_login",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String g_login(@RequestBody G_User g_user){
        System.out.println("xxxxx");
        if (g_user != null) {
            if (loginService.g_login(g_user).equals("T")) {
                String sign = JWTUtil.sign(g_user, 60L * 1000L * 120L);
                return JSON.toJSONString("token:" + sign);
            }
        }

        return JSON.toJSONString("F");
    }

    /**
     * 普通用户注册
     */
    @RequestMapping(value = "p_register",method = RequestMethod.POST)
    public String p_register(@RequestBody P_User user){
        System.out.println("xxxxx");
        if (user != null) {
           return loginService.p_register(user);
        }
        return JSON.toJSONString("F");
    }

    /**
     * 管理员注册
     * @param user
     * @return
     */
    @RequestMapping(value = "g_register",method = RequestMethod.POST)
    public String g_register(@RequestBody G_User user) {
        System.out.println("bbbbb");
        if (user != null) {
            return loginService.g_register(user);
        }
        return JSON.toJSONString("T");
    }


}
