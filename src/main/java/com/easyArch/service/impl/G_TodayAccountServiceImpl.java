package com.easyArch.service.impl;

import com.alibaba.fastjson.JSON;
import com.easyArch.entity.*;
import com.easyArch.mapper.AddressDao;
import com.easyArch.mapper.DateNumberDao;
import com.easyArch.mapper.G_UserDao;
import com.easyArch.mapper.PictureDao;
import com.easyArch.net.SocketHandler;
import com.easyArch.service.G_TodayAccountService;
import com.easyArch.util.ControllerUtil;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unchecked")
@Service
public class G_TodayAccountServiceImpl implements G_TodayAccountService {
    private static final Logger LOGGER= LoggerFactory.getLogger(G_TodayAccountServiceImpl.class);
    public static String user_Address;
    @Autowired
    AddressDao addressDao;
    @Autowired
    G_UserDao g_userDao;
    @Autowired
    DateNumberDao dateNumberDao;
    @Autowired
    PictureDao pictureDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public String selectDefaultNumber(G_User g_user) {
        List<DateAndNumber> list;
        String g_name=g_user.getUsername();
        String userAddress;
        if (redisTemplate.hasKey("@"+g_name)){
            userAddress=(String) redisTemplate.opsForValue().get("@"+g_name);
        }else {
            userAddress=g_userDao.selectG_UserAddress(g_name);
            redisTemplate.opsForValue().set("@"+g_name,userAddress,7, TimeUnit.DAYS);
        }
        G_TodayAccountServiceImpl.user_Address=userAddress;
        System.out.println("g_userDdress:"+userAddress);
        List<Construction_inDefa>list_data=new ArrayList<>();
        List<Info_inCons>info_list=new ArrayList<>();
        String []str= ControllerUtil.slipAddress(userAddress);
        String city=str[0];
        String county=str[1];
        String street=str[2];
        String specificAddress=str[3];
        LOGGER.info("地址是："+userAddress);
        /**
         * @cons_List
         */
        List<String> cons_List;
        if (redisTemplate.hasKey(specificAddress+city+county+street+"cons_List")){
            cons_List=redisTemplate.opsForList().range(specificAddress+city+county+street+"cons_List",0,-1);
        }else {
            cons_List= addressDao
                    .select_construction(specificAddress,city,county,street);
            for (String s:cons_List){
                redisTemplate.opsForList().rightPush(specificAddress+city+county+street+"cons_List",s);
            }
            redisTemplate.expire(specificAddress+city+county+street+"cons_List",24,TimeUnit.HOURS);
        }

        for(int i=0;i<cons_List.size();i++) {
            LOGGER.info("能到达这里！cons_List1"+cons_List.get(i));
            Construction_inDefa construction_inDefa = new Construction_inDefa();
            construction_inDefa.setConstruction(cons_List.get(i));
            List<String> pics;
            if (redisTemplate.hasKey(specificAddress+city+county+street+cons_List.get(i)+"pics")){
                pics=redisTemplate
                        .opsForList().range(specificAddress+city+county+street+cons_List.get(i)+"pics",0,-1);
            }else {
                LOGGER.info("能到达这里！进入else");
                pics=pictureDao
                        .selectPic(specificAddress,city,county,street,cons_List.get(i));
                LOGGER.info("能到达这里！调用了数据库");
                for (String s:pics){
                    redisTemplate
                            .opsForList().rightPush(specificAddress+city+county+street+cons_List.get(i)+"pics",s);
                }
                redisTemplate
                        .expire(specificAddress+city+county+street+cons_List.get(i)+"pics",24,TimeUnit.HOURS);
            }
            construction_inDefa.setPicture_url(pics.get(0));
            LOGGER.info("能到达这里！pics0"+pics.get(0));
            /**
             *
             * 通过地址查找盒子，返回符合地址条件的所有盒子
             */
            List<String>mac_list;
            if (redisTemplate.hasKey(specificAddress+city+county+street+cons_List.get(i)+"mac_list")){
                mac_list=redisTemplate
                        .opsForList().range(specificAddress+city+county+street+cons_List.get(i)+"mac_list",0,-1);
                LOGGER.info("能到达这里！mac_list从缓存获取");
            }else {
                mac_list=addressDao
                        .select_mac(specificAddress,city,county,street,cons_List.get(i));
                LOGGER.info("能到达这里！mac_list从数据库缓存获取");
                for (String s:mac_list){
                    redisTemplate
                            .opsForList().rightPush(specificAddress+city+county+street+cons_List.get(i)+"mac_list",s);
                }
                redisTemplate
                        .expire(specificAddress+city+county+street+cons_List.get(i)+"mac_list",1,TimeUnit.SECONDS);
            }

            for (int j = 0; j < mac_list.size(); j++) {
                LOGGER.info("能到达这里！mac_list"+mac_list.get(j));
                Info_inCons info_inCons=new Info_inCons();
                info_inCons.setMac_address(mac_list.get(j));
                /**
                 * 盒子所在位置和所在层级
                 */
                Location_tier locationTier;
                if (redisTemplate.hasKey("locationTier"+mac_list.get(j))){
                    locationTier=(Location_tier)redisTemplate.opsForValue().get("locationTier"+mac_list.get(j));
                }else {
                    locationTier=addressDao
                            .selectLocation_tier(mac_list.get(j));
                    redisTemplate.opsForValue().set("locationTier"+mac_list.get(j),locationTier,24,TimeUnit.HOURS);
                }

                info_inCons.setTier(locationTier.getTier());
                info_inCons.setLocation(locationTier.getLocation());
                //设置日期格式
                DateTime now=DateTime.now();
                String date2=now.toString("yyyy-MM-dd HH:mm:ss");

                String [] str2=ControllerUtil.slipDate2(date2);
                String date1=str2[0]+" 01:00:00";
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
