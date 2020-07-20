package com.easyArch.controller;

import com.alibaba.fastjson.JSON;
import com.easyArch.entity.YanGan;
import com.easyArch.mapper.YanGanDao;
import com.easyArch.util.ControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class YanGanController {

    @Autowired
    YanGanDao yanGanDao;
    @ResponseBody
    @RequestMapping( value = "yanGan",
                     produces = "application/json;charset=utf-8",
                     method = RequestMethod.POST)
    public String yanGan(){
        ControllerUtil util=new ControllerUtil();
        String userDdress=SelectDefaultNumber_Controller.userDdress;
        String[]str=util.slipAddress(userDdress);
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取日期
        String date2=df.format(new Date());
        System.out.println(date2);
        String [] str2=util.slipDate(date2);
        String[]str3=util.slipDate2(str2[2]);
         Integer day22 = new Integer(str3[0]);
          day22=day22-1;

         String date1=str2[0]+"-"+str2[1]+"-"+day22+" 00:00:00";
        List<YanGan> list=yanGanDao.yanGanList(str[3],date1,date2);
        return JSON.toJSONString(list);
    }

}
