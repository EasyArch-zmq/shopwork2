package com.easyArch.interceptors;


import com.easyArch.entity.G_User;
import com.easyArch.entity.P_User;
import com.easyArch.service.LoginService;
import com.easyArch.support.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        String str[]={"g_login","p_login","p_register","g_register"};
        for (int i = 0; i < str.length; i++) {
            if (request.getRequestURI().contains(str[i])) {
                return true;
            }
        }

        if (null != token) {
            G_User login_g = JWTUtil.unsign(token, G_User.class);
            P_User login_p= JWTUtil.unsign(token, P_User.class);
//            String loginId = request.getParameter("password");
            //解密用户信息，根据用户名密码查用户。
            if (null != login_g) {
                G_User loginResult = loginService.selectBygName(login_g.getUsername());
                if (loginResult != null) {
                    return true;
                } else {
                    responseMessage(response, response.getWriter());
                    return false;
                }
            } else if (null != login_p){
                P_User loginResult = loginService.selectBypName(login_p.getUsername());
                if (loginResult != null) {
                    return true;
                } else {
                    responseMessage(response, response.getWriter());
                    return false;
                }
            }else {
                responseMessage(response, response.getWriter());
                return false;
            }
        } else {
            responseMessage(response, response.getWriter());
            return false;
        }
    }


    //请求不通过，返回错误信息给客户端
    private void responseMessage(HttpServletResponse response, PrintWriter out) {
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(403);
        out.print("验证未通过");
        out.flush();
        out.close();
    }

}
