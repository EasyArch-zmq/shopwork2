package com.easyArch.service;

import com.easyArch.entity.G_User;
import com.easyArch.entity.P_User;

public interface LoginService {

    //普通用户注册
    public String p_register(P_User p_user);
    //管理员用户注册
    public String g_register(G_User g_user);

    //普通用户登录
    public String p_login(P_User p_user);
    //管理员用户登录
    public String g_login(G_User g_user);
}
