package com.easyArch.controller;

import com.alibaba.fastjson.JSON;
import com.easyArch.entity.YanGan;
import com.easyArch.mapper.YanGanDao;
import com.easyArch.util.ControllerUtil;
import org.apache.log4j.Logger;
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
    private static final Logger logger= Logger.getLogger(YanGanController.class);
    @Autowired
    YanGanDao yanGanDao;
    @ResponseBody
    @RequestMapping( value = "yanGan",
                     produces = "application/json;charset=utf-8",
                     method = RequestMethod.POST)
    public String yanGan(){
        String userDdress=SelectDefaultNumber_Controller.user_Address;
        String[]str=ControllerUtil.slipAddress(userDdress);
        String city=str[0];
        String county=str[1];
        String town=str[2];
        String street=str[3];
        String specificAddress=str[4];
        List<YanGan> list=null;
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        //获取日期
        String date2=df.format(new Date());
        String [] str2=ControllerUtil.slipDate2(date2);
        String date1=str2[0]+" 00:00:00:000";
        list= yanGanDao.yanGanList(city,county,town,street,specificAddress, date1, date2);
//        list= yanGanDao.yanGanList(city,county,town,street,specificAddress, "2020-08-16 00:00:00", "2020-08-18 23:59:59");
        String[] strings;
        strings = ControllerUtil.slipDate3(str2[1]);
        List<YanGan> listRes=ControllerUtil.filterOneHour(list,"23");
//        List<YanGan> listRes=util.filterOneHour(list,"23");
        return JSON.toJSONString(listRes);
    }

}
