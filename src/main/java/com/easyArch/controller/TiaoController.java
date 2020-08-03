package com.easyArch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TiaoController {

    @RequestMapping("login")
    public String login(){
        System.out.println("login");
        return "peoplenumber/pulic/html/Login";

    }

    @RequestMapping("register")
    public String g_register(){
        System.out.println("register了");
        return "peoplenumber/pulic/html/resgister";

    }

//    @RequestMapping("g_loginA")
//    public String g_login(){
//        System.out.println("g_login了");
//        return "peoplenumber/pulic/html/reserve/administrator/g_Login";
//
//    }
//
//    @RequestMapping("p_registerA")
//    public String p_register(){
//        System.out.println("p_register了");
//        return "peoplenumber/pulic/html/reserve/CommonUser/p_register";
//
//    }
//    @RequestMapping("p_loginA")
//    public String p_login(){
//        System.out.println("p_login了");
//        return "peoplenumber/pulic/html/reserve/CommonUser/p_Login";
//
//    }

//    @RequestMapping("lightA")
//    public String light(){
//        System.out.println("light了");
//        return "html/light";
//
//    }

    @RequestMapping("RiskControll")
    public String riskControll(){
        System.out.println("RiskControll了");
        return "peoplenumber/pulic/html/RiskControll";

    }
//    @RequestMapping("main3A")
//    public String main3(){
//        System.out.println("main3了");
//
//        return "peoplenumber/pulic/html/reserve/main3";
//
//    }

    @RequestMapping("p_main")
    public String p_main(){
        System.out.println("main3了");
        return "peoplenumber/pulic/html/p_main";

    }
    @RequestMapping("smoke")
    public String smoke(){
        System.out.println("smoke了");
        return "peoplenumber/pulic/html/smoke";

    }
    @RequestMapping("date")
    public String date(){
        System.out.println("date");
        return "peoplenumber/pulic/html/date";

    }

    @RequestMapping("live")
    public String live(){
        System.out.println("live");
        return "peoplenumber/pulic/html/live";

    }


}
