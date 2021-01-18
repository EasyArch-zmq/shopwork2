package com.easyArch.service.impl;

import com.alibaba.fastjson.JSON;
import com.easyArch.entity.*;
import com.easyArch.mapper.AddressDao;
import com.easyArch.mapper.DateNumberDao;
import com.easyArch.mapper.P_UserDao;
import com.easyArch.mapper.PictureDao;
import com.easyArch.service.P_TodayAccountService;
import com.easyArch.util.ControllerUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@SuppressWarnings("unchecked")
public class P_TodayAccountServiceImpl implements P_TodayAccountService {
    @Autowired
    private DateNumberDao dateNumberDao;
    @Autowired
    private P_UserDao p_userDao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private PictureDao pictureDao;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String selectTodayNumber(P_User p_user) {
        List<DateAndNumber> list;
        String p_name=p_user.getUsername();
        String userAddress;
        if (redisTemplate.opsForValue().get("@"+p_name)!=null){
            userAddress=(String) redisTemplate.opsForValue().get("@"+p_name);
        }else {
            userAddress= p_userDao.selectUserAddress(p_name);
            redisTemplate.opsForValue().set("@" + p_name, userAddress, 7, TimeUnit.DAYS);
        }
//        System.out.println("g_userDdress:"+userAddress);
        List<Construction_inDefa>list_data=new ArrayList<>();
        List<Info_inCons>info_list=new ArrayList<>();
        String []str= ControllerUtil.slipAddress(userAddress);
        String city=str[0];
        String county=str[1];
        String street=str[2];
        String specificAddress=str[3];

        /**
         * @cons_List
         */
        List<String> cons_List;
        if (redisTemplate.hasKey(specificAddress+city+county+street+"cons_List")){
            cons_List=redisTemplate
                    .opsForList().range(specificAddress+city+county+street+"cons_List",0,-1);
        }else {
            cons_List= addressDao
                    .select_construction(specificAddress,city,county,street);
            for (String s:cons_List){
                redisTemplate
                        .opsForList().rightPush(specificAddress+city+county+street+"cons_List",s);
            }
            redisTemplate
                    .expire(specificAddress+city+county+street+"cons_List",24,TimeUnit.HOURS);
        }
        for(int i=0;i<cons_List.size();i++) {
            Construction_inDefa construction_inDefa = new Construction_inDefa();
            construction_inDefa.setConstruction(cons_List.get(i));
            List<String> pics;
            if (redisTemplate.hasKey(specificAddress+city+county+street+cons_List.get(i)+"pics")){
                pics=redisTemplate
                        .opsForList().range(specificAddress+city+county+street+cons_List.get(i)+"pics",0,-1);
            }else {
                pics=pictureDao
                        .selectPic(specificAddress,city,county,street,cons_List.get(i));
                for (String s:pics){
                    redisTemplate
                            .opsForList().rightPush(specificAddress+city+county+street+cons_List.get(i)+"pics",s);
                }
                redisTemplate
                        .expire(specificAddress+city+county+street+cons_List.get(i)+"pics",24,TimeUnit.HOURS);
            }
            construction_inDefa.setPicture_url(pics.get(0));
            List<String>mac_list;
            if (redisTemplate.hasKey(specificAddress+city+county+street+cons_List.get(i)+"mac_list")){
                mac_list=redisTemplate.opsForList().range(specificAddress+city+county+street+cons_List.get(i)+"mac_list",0,-1);
            }else {
                mac_list=addressDao
                        .select_mac(specificAddress,city,county,street,cons_List.get(i));
                for (String s:mac_list){
                    redisTemplate
                            .opsForList().rightPush(specificAddress+city+county+street+cons_List.get(i)+"mac_list",s);
                }
                redisTemplate
                        .expire(specificAddress+city+county+street+cons_List.get(i)+"mac_list",24,TimeUnit.HOURS);
            }
            for (int j = 0; j < mac_list.size(); j++) {
                Info_inCons info_inCons=new Info_inCons();
                info_inCons.setMac_address(mac_list.get(j));
                /**
                 * 盒子所在位置和所在层级
                 */
                Location_tier locationTier;
                if (redisTemplate.opsForValue().get("locationTier"+mac_list.get(j))!=null){
                    locationTier=(Location_tier)redisTemplate.opsForValue().get("locationTier"+mac_list.get(j));
                }else {
                    locationTier=addressDao
                            .selectLocation_tier(mac_list.get(j));
                    redisTemplate.opsForValue().set("locationTier"+mac_list.get(j),locationTier,24,TimeUnit.HOURS);
                }

                info_inCons.setTier(locationTier.getTier());
                info_inCons.setLocation(locationTier.getLocation());
                //设置日期格式    正式运行要放开这里
                //获取日期
                DateTime now=DateTime.now();
                String date2=now.toString("yyyy-MM-dd HH:mm:ss");
               // String date2="2020-08-11 23:59:59";
                String [] str2=ControllerUtil.slipDate2(date2);
                String date1=//"2020-08-11 01:00:00";
                              str2[0]+" 01:00:00";
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
}
