package com.easyArch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TiaoController {


    @RequestMapping("loginDate")
    public String loginDate(){
        System.out.println("xxbbb");
        return "pulic/html/login";

    }
    @RequestMapping("select")
    public String selectWeb1(){
        System.out.println("xxbbb");
        return "pulic/html/main";

    }
    @RequestMapping("light")
    public String light(){
        System.out.println("xxxxx");
        return "pulic/html/light";
    }
    @RequestMapping("yangan")
    public String selectWeb2(){
        System.out.println("xxccc");
        return "pulic/html/main2";
    }
}
