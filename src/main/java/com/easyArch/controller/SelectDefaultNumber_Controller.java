package com.easyArch.controller;

import com.alibaba.fastjson.JSON;
import com.easyArch.entity.BoxidAndAddress;
import com.easyArch.entity.BoxidAndDataList;
import com.easyArch.entity.DateAndNumber;
import com.easyArch.entity.G_User;
import com.easyArch.mapper.AddressDao;
import com.easyArch.mapper.DateNumberDao;
import com.easyArch.mapper.G_UserDao;
import com.easyArch.util.ControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
public class SelectDefaultNumber_Controller {
    public static String userDdress;
    @Autowired
    AddressDao addressDao;
    @Autowired
    G_UserDao g_userDao;
    @Autowired
    DateNumberDao dateNumberDao;
    @ResponseBody
    @RequestMapping(value = "selectDefaultNumber"
            , produces = "application/json;charset=utf-8"
            , method = RequestMethod.POST)
    public String selectDefaultNumber (@RequestBody G_User g_user) {
        if(g_user!=null){
            ControllerUtil util = new ControllerUtil();
            List<DateAndNumber> list;
            List<BoxidAndDataList>list_data=new ArrayList<>();
            String g_name=g_user.getUsername();
            System.out.println("vvvvv"+g_name);
            String g_address=g_userDao.selectG_UserAddress(g_name);
            System.out.println(g_address);
            userDdress=g_address;
            String[] str = util.slipAddress(g_address);
            List<BoxidAndAddress> boxidsList = addressDao
                    .selectBoxids(str[3]);
            for (BoxidAndAddress listAddress : boxidsList) {
                BoxidAndDataList boxidAndDataList = new BoxidAndDataList();
                boxidAndDataList.setBoxid(listAddress.getBoxid());
                //设置日期格式
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //获取日期
                String date2=df.format(new Date());
                String [] str2=util.slipDate2(date2);
                String date1=str2[0]+" 00:00:00";
                list = dateNumberDao.selectTwoHour(listAddress.getBoxid(),"2020-07-19 00:00:00", "2020-07-19 23:30:00");
//                list = dateNumberDao.selectTwoHour(listAddress.getBoxid(),date1,date2);
                String[] strings;
                strings = util.slipDate3(str2[1]);
                List<DateAndNumber>resList=util.filter(list,strings[0]);
                boxidAndDataList.setList(resList);
                list_data.add(boxidAndDataList);
                continue;

            }
            return JSON.toJSONString(list_data);
        }
        return JSON.toJSONString("f");
    }
}
