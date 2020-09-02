package com.easyArch.controller;

import com.alibaba.fastjson.JSON;
import com.easyArch.Application;
import com.easyArch.entity.*;
import com.easyArch.mapper.AddressDao;
import com.easyArch.mapper.DateNumberDao;
import com.easyArch.mapper.G_UserDao;
import com.easyArch.util.ControllerUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class SelectDefaultNumber_Controller {
    private static final Logger logger=Logger.getLogger(SelectDefaultNumber_Controller.class);
    public static String user_Address;
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
            List<DateAndNumber> list;
            String g_name=g_user.getUsername();
            String userAddress=g_userDao
                    .selectG_UserAddress(g_name);
            SelectDefaultNumber_Controller.user_Address=userAddress;
            System.out.println("g_userDdress:"+userAddress);
            List<Construction_inDefa>list_data=new ArrayList<>();
            List<Info_inCons>info_list=new ArrayList<>();
            String []str=ControllerUtil.slipAddress(userAddress);
            String city=str[0];
            String county=str[1];
            String town=str[2];
            String street=str[3];
            String specificAddress=str[4];
            if(town.equals("*")){
                town=null;
            }

            //返回Mac和具体地址
            List<String> cons_List = addressDao
                    .select_construction(specificAddress,city,county,town,street);
            for(int i=0;i<cons_List.size();i++) {
                Construction_inDefa construction_inDefa = new Construction_inDefa();
                construction_inDefa.setConstruction(cons_List.get(i));
                List<String> mac_list = addressDao
                        .select_mac(specificAddress, city, county, town, street, cons_List.get(i));
                for (int j = 0; j < mac_list.size(); j++) {
                    Info_inCons info_inCons=new Info_inCons();
                    info_inCons.setMac_address(mac_list.get(j));
                    String tier=addressDao.selectLocation_tier(mac_list.get(j)).getTier();
                    String location=addressDao.selectLocation_tier(mac_list.get(j)).getLocation();
                    info_inCons.setTier(tier);
                    info_inCons.setLocation(location);
                    //设置日期格式
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    //获取日期
                    String date2=df.format(new Date());
                    String [] str2=ControllerUtil.slipDate2(date2);
                    String date1=str2[0]+" 01:00:00";
//                list = dateNumberDao.selectTwoHour(mac_list.get(j),"2020-07-28 00:00:00", "2020-07-28 23:59:00");
                    list = dateNumberDao.selectTwoHour(mac_list.get(j),date1,date2);
//                String[] strings;
//                strings = ControllerUtil.slipDate3(str2[1]);
                    List<DateAndNumber>resList=ControllerUtil.filterTowHour(list,"23");
                    info_inCons.setList_inCons(resList);
                    info_list.add(info_inCons);
                }
                construction_inDefa.setInfo_inCons_List(info_list);
                list_data.add(construction_inDefa);


            }
            return JSON.toJSONString(list_data);
        }
        return JSON.toJSONString("f");
    }
}
