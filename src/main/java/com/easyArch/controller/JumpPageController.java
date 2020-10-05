package com.easyArch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JumpPageController {

    @RequestMapping("Login")
    public String login(){
        System.out.println("Login");
        return "peoplenumber/pulic/html/Login";

    }

    @RequestMapping("resgister")
    public String g_register(){
        System.out.println("register了");
        return "peoplenumber/pulic/html/resgister";

    }



    @RequestMapping("RiskControll")
    public String riskControll(){
        System.out.println("RiskControll了");
        return "peoplenumber/pulic/html/RiskControll";

    }


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

    @RequestMapping("P_TodayAccount")
    public String P_TodayAccount(){
        System.out.println("P_TodayAccount");
        return "peoplenumber/pulic/html/P_TodayAccount";

    }

    @RequestMapping("P_Todayliveness")
    public String P_Todayliveness(){
        System.out.println("P_Todayliveness");
        return "peoplenumber/pulic/html/P_Todayliveness";

    }
}
