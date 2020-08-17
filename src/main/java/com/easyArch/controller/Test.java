package com.easyArch.controller;


import com.alibaba.fastjson.JSON;
import com.easyArch.mapper.Time_InfoDao;
import com.easyArch.util.ControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
public class Test {
    @Autowired
    Time_InfoDao time_infoDao;


    @ResponseBody
    @RequestMapping(value = "Test",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String test(){
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss :SSS");
        //获取日期

        System.out.println("xxxxxxxxxxxxxxxxxxxxxx");
        System.out.println(df.format(new Date()));
        String [] time_split= ControllerUtil.slipDate4(df.format(new Date()));
        String mytime=time_split[0];
        String hao_miao=time_split[1];
        System.out.println("时间："+mytime+";毫秒："+hao_miao);
//        String boxid=strings[0];
        String mac_address="9CA525A72C90";
        Integer yangan=new Integer("345");
        time_infoDao.insertInfo(mac_address,mytime,hao_miao,yangan);
        return JSON.toJSONString("T");
}
}
