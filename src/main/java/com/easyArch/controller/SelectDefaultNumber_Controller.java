package com.easyArch.controller;

import com.alibaba.fastjson.JSON;
import com.easyArch.entity.MacAndDataList;
import com.easyArch.entity.DateAndNumber;
import com.easyArch.entity.G_User;
import com.easyArch.entity.MacAndAddress;
import com.easyArch.mapper.AddressDao;
import com.easyArch.mapper.DateNumberDao;
import com.easyArch.mapper.G_UserDao;
import com.easyArch.util.ControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
            List<MacAndDataList>list_data=new ArrayList<>();
            String g_name=g_user.getUsername();
            System.out.println("vvvvv"+g_name);
            String g_address=g_userDao.selectG_UserAddress(g_name);
            System.out.println(g_address);
            userDdress=g_address;
            String[] str = ControllerUtil.slipAddress(g_address);
            String province=str[0];
            String city=str[1];
            String county=str[2];
            String specificAddress=str[3];
            List<MacAndAddress> macsList = addressDao
                    .selectMacs(specificAddress,province,city,county);
            for (MacAndAddress listAddress : macsList) {
                MacAndDataList macAndDataList = new MacAndDataList();
                macAndDataList.setMac_address(listAddress.getMac_address());
                //设置日期格式
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //获取日期
                String date2=df.format(new Date());
                String [] str2=ControllerUtil.slipDate2(date2);
                String date1=str2[0]+" 00:00:00";
//                list = dateNumberDao.selectTwoHour(listAddress.getMac_address(),"2020-07-28 00:00:00", "2020-07-28 23:59:00");
                list = dateNumberDao.selectTwoHour(listAddress.getMac_address(),date1,date2);
//                String[] strings;
//                strings = ControllerUtil.slipDate3(str2[1]);
                List<DateAndNumber>resList=ControllerUtil.filterTowHour(list,"23");
                macAndDataList.setList(resList);
                list_data.add(macAndDataList);
                continue;

            }
            return JSON.toJSONString(list_data);
        }
        return JSON.toJSONString("f");
    }
}
