package com.easyArch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JumpPageController {

    @RequestMapping("Login")
    public String login(){
        System.out.println("Login");
        return "html/login/Login";

    }

    @RequestMapping("resgister")
    public String g_register(){
        System.out.println("register了");
        return "html/login/resgister";

    }



    @RequestMapping("RiskControll")
    public String riskControll(){
        System.out.println("RiskControll了");
        return "html/Background/RiskControll";

    }


    @RequestMapping("p_main")
    public String p_main(){
        System.out.println("main3了");
        return "html/General/p_main";

    }
    @RequestMapping("smoke")
    public String smoke(){
        System.out.println("smoke了");
        return "html/Background/smoke";
    }
    @RequestMapping("week_Rank1")
    public String week_Rank(){
        System.out.println("week_Rank了");
        return "html/Background/Account/week_Rank";
    }
    @RequestMapping("time_Rank1")
    public String time_Rank(){
        System.out.println("time_Rank了");
        return "html/Background/Account/time_Rank";
    }
    @RequestMapping("sameMac_timeRank1")
    public String sameMac_timeRank(){
        System.out.println("sameMac_timeRank了");
        return "html/Background/Account/sameMac_timeRank";
    }
    @RequestMapping("statistic_Color_Data1")
    public String statistic_Color_Data(){
        System.out.println("statistic_Color_Data了");
        return "html/Background/Account/statistic_Color_Data";
    }
    @RequestMapping("date")
    public String date(){
        System.out.println("date");
        return "html/Background/Account/date";

    }
    @RequestMapping("sameTime_Statistic1")
    public String sameTime_Statistic(){
        System.out.println("sameTime_Statistic");
        return "html/Background/Account/sameTime_Statistic";

    }

    @RequestMapping("nav")
    public String nav(){
        System.out.println("nav");
        return "html/Background/nav";

    }
    @RequestMapping("live")
    public String live(){
        System.out.println("live");
        return "html/Background/live";

    }

    @RequestMapping("P_TodayAccount")
    public String P_TodayAccount(){
        System.out.println("P_TodayAccount");
        return "html/General/P_TodayAccount";

    }

    @RequestMapping("P_Todayliveness")
    public String P_Todayliveness(){
        System.out.println("P_Todayliveness");
        return "html/General/P_Todayliveness";

    }
    @RequestMapping("area")
    public String area(){
        System.out.println("area");
        return "html/Background/area";

    }

}
