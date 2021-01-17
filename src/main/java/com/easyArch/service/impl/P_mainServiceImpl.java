package com.easyArch.service.impl;

import com.alibaba.fastjson.JSON;
import com.easyArch.entity.*;
import com.easyArch.mapper.*;
import com.easyArch.service.P_mainService;
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
@SuppressWarnings("unchecked")
@Service
public class P_mainServiceImpl implements P_mainService {
    @Autowired
    private DateNumberDao dateNumberDao;
    @Autowired
    private P_UserDao p_userDao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private PictureDao pictureDao;
    @Autowired
    private ColorDao colorDao;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String selectP_mainData(P_User p_user) {
        //数据之一：用户地址
        String userAddress;
        String username=p_user.getUsername();

        /**
         * @allNumber 返回给前端整个页面需要的数据总类
         * userAddress地址，List<Construction_inAll>封装的除了地址以外其他总数据集合，
         */
        AllNumber allNumber=new AllNumber();
        /**
         * @list_inCons封装了Mac地址，和符合时间段的数据条数，Color,location
         */
        List<Data_inCons> list_inCons=new ArrayList<>();
        /**
         * @list_inAll construction,picture_url,List<Data_inCons>list_inCons
         */
        List<Construction_inAll>list_inAll=new ArrayList<>();
        /**
         * @construction_inAll
         */
        Construction_inAll construction_inAll=new Construction_inAll();

        if (redisTemplate.opsForValue().get("@"+username)!=null){
            userAddress=(String) redisTemplate.opsForValue().get("@"+username);
        }else {
            userAddress=p_userDao.selectUserAddress(username);
            redisTemplate.opsForValue().set("@"+username,userAddress,7, TimeUnit.DAYS);
        }
        String []str= ControllerUtil.slipAddress(userAddress);
        String city=str[0];
        String county=str[1];
        String street=str[2];
        String specificAddress=str[3];
        //设置日期格式
        DateTime now=DateTime.now();
        String date2=now.toString("yyyy-MM-dd HH:mm:ss");
        String [] str2=date2.split(" ");
        String date1="2020-08-11 00:00:00";//str2[0]+" 00:00:00";
//            获取日期
//            String date2="2020-08-11 23:59:00";//df.format(new Date());
//            String [] str2=ControllerUtil.slipDate2(date2);
//            String date1="2020-08-11 01:00:00";//str2[0]+" 01:00:00";
        String []str3=ControllerUtil.slipDate3(str2[1]);
        Integer time_inDate=new Integer(str3[0]);
        Integer time_inList=0;

        /**
         * @cons_List
         */
        List<String> cons_List;
        if (redisTemplate.hasKey("cons_List")){
            cons_List=redisTemplate.opsForList().range("cons_List",0,-1);
        }else {
            cons_List= addressDao
                    .select_construction(specificAddress,city,county,street);
            for (String s:cons_List){
                redisTemplate.opsForList().rightPush("cons_List",s);
            }
            redisTemplate.expire("cons_List",24,TimeUnit.HOURS);
        }
        for(int i=0;i<cons_List.size();i++){
            construction_inAll.setConstruction(cons_List.get(i));
            List<String> pics;
            if (redisTemplate.hasKey("pics")){
                pics=redisTemplate.opsForList().range("pics",0,-1);
            }else {
                pics=pictureDao.selectPic(specificAddress,city,county,street,cons_List.get(i));
                for (String s:pics){
                    redisTemplate.opsForList().rightPush("pics",s);
                }
                redisTemplate.expire("pics",24,TimeUnit.HOURS);
            }
            construction_inAll.setPicture_url(pics.get(0));
            /**
             * 通过地址查找盒子，返回符合地址条件的所有盒子
             */
            List<String>mac_list;
            if (redisTemplate.hasKey("mac_list")){
                mac_list=redisTemplate.opsForList().range("mac_list",0,-1);
            }else {
                mac_list=addressDao
                        .select_mac(specificAddress,city,county,street,cons_List.get(i));
                for (String s:mac_list){
                    redisTemplate.opsForList().rightPush("mac_list",s);
                }
                redisTemplate.expire("mac_list",24,TimeUnit.HOURS);
            }

            for (int j = 0; j <mac_list.size() ; j++) {
                /**
                 * 红，绿警报的范围值
                 */
                Color color1 = null;
                if (redisTemplate.opsForValue().get("color@"+mac_list.get(j))!=null){
                    color1=(Color)redisTemplate.opsForValue().get("color@"+mac_list.get(j));
                }else {
                    color1=colorDao
                            .selectColor(mac_list.get(j));
                    redisTemplate.opsForValue().set("color@"+mac_list.get(j),color1,7,TimeUnit.DAYS);
                }
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
                /**
                 * 盒子的对应收集到的人数
                 */
                Integer num=0;
                List<DateAndNumber>numberList=new ArrayList<>();
                numberList=dateNumberDao.selectTwoHour(mac_list.get(j),date1,date2);
                if(numberList.size()!=0){
                    time_inList=new Integer(numberList.get(numberList.size()-1).getTime());
                    if (time_inDate.equals(time_inList)||time_inDate-1==time_inList){
                        num=numberList.get(numberList.size()-1).getNum();
                    }else {
                        num=0;
                    }
                }else {
                    num=0;
                }
//
                System.out.println("----------->num:"+num +"------------>mac:"+mac_list.get(j));
                Data_inCons data_inCons =new Data_inCons();
                data_inCons.setNumber(num);
                data_inCons.setLocation(locationTier.getLocation());
                data_inCons.setColor(color1);
                data_inCons.setMac_address(mac_list.get(j));
                list_inCons.add(data_inCons);
            }
            construction_inAll.setList_inCons(list_inCons);
            list_inAll.add(construction_inAll);
        }
        allNumber.setList_inAll(list_inAll);
        allNumber.setUserAddress(specificAddress);
        return JSON.toJSONString(allNumber);
    }
}
