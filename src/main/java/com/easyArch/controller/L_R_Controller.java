package com.easyArch.controller;

import com.alibaba.fastjson.JSON;
import com.easyArch.entity.G_User;
import com.easyArch.mapper.G_UserDao;
import com.easyArch.mapper.P_UserDao;
import com.easyArch.entity.P_User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class L_R_Controller {

    @Autowired
    P_UserDao p_userDao;
    @Autowired
    G_UserDao g_userDao;

    /**
     * 普通用户登录
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "p_login",method = RequestMethod.POST)
    public String p_login(@RequestBody P_User user){
        System.out.println("xxxxx");
        if (user != null) {
            String username_1 = user.getUsername();
            String password_1 = user.getPassword();
            System.out.println(password_1);
            System.out.println(username_1);
            P_User user1 = p_userDao.isUser(username_1);
            if (user1 != null && user1.getPassword().equals(password_1)) {
                return JSON.toJSONString("T");
            }
        }
        return JSON.toJSONString("F");
    }


    /**
     * 管理员登录
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "g_login",method = RequestMethod.POST)
    public String g_login(@RequestBody G_User user){
        System.out.println("xxxxx");
        if (user != null) {
            String username_1 = user.getUsername();
            String password_1 = user.getPassword();
            System.out.println(password_1);
            System.out.println(username_1);
            G_User user1 = g_userDao.isUser(username_1);
            if (user1 != null && user1.getPassword().equals(password_1)) {
                return JSON.toJSONString("T");
            }
        }
        return JSON.toJSONString("F");
    }



    @RequestMapping(value = "p_register",method = RequestMethod.POST)
    public String p_insert(@RequestBody P_User user){
        System.out.println("xxxxx");
        if (user != null) {
            String username_1 = user.getUsername();
            String password_1 = user.getPassword();
            System.out.println(password_1);
            try{
                if(p_userDao.isUser(username_1).getUsername()!=null) {
                    return JSON.toJSONString("f");
                }
            }catch (NullPointerException e){
                p_userDao.insert(username_1,password_1,user.getAddress());
            }

        }
        return JSON.toJSONString("T");
    }

    @RequestMapping(value = "g_register",method = RequestMethod.POST)
    public String  p_insert(@RequestBody G_User user){
        System.out.println("bbbbb");
        if (user != null) {
            String username_1 = user.getUsername();
            String password_1 = user.getPassword();
            System.out.println(password_1);
            System.out.println(username_1);
            try{
                if(g_userDao.isUser(username_1).getUsername()!=null) {
                    return JSON.toJSONString("f");
                }
            }catch (NullPointerException e){
                g_userDao.insert(username_1,password_1,user.getAddress());
            }

        }
        return JSON.toJSONString("T");

    }


}
