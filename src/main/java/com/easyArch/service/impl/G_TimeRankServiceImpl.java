package com.easyArch.service.impl;

import com.alibaba.fastjson.JSON;
import com.easyArch.entity.DateAndAddress;
import com.easyArch.entity.DateAndNumber;
import com.easyArch.entity.Location_tier;
import com.easyArch.entity.Mac_ListTimeSort;
import com.easyArch.mapper.AddressDao;
import com.easyArch.mapper.DateNumberDao;
import com.easyArch.service.G_TimeRankService;
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
public class G_TimeRankServiceImpl implements G_TimeRankService {
    @Autowired
    AddressDao addressDao;
    @Autowired
    DateNumberDao dateNumberDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public String time_Rank(DateAndAddress address) {
        String addressStr = address.getAddress();
        String[] str = ControllerUtil.slipAddress(addressStr);
        String city = str[0];
        String county = str[1];
        String street = str[2];
        String specificAddress = str[3];

        //设置日期格式
        DateTime now=DateTime.now();
        String date2=now.toString("yyyy-MM-dd HH:mm:ss");

        String[] str2 = ControllerUtil.slipDate2(date2);
        String date1 = str2[0] + " 01:00:00";
        String index=str2[1];
        List<DateAndNumber> list;
        List<Mac_ListTimeSort>listTimeSortList=new ArrayList<Mac_ListTimeSort>();

        List<String> listMac;
        /**
         * 通过地址查找盒子，返回符合地址条件的所有盒子
         */
        if (redisTemplate.hasKey("listMac")){
            listMac=redisTemplate.opsForList().range("listMac",0,-1);
        }else {
            listMac=addressDao.select_mac2(specificAddress, city, county, street);
            for (String s:listMac){
                redisTemplate.opsForList().rightPush("listMac",s);
            }
            redisTemplate.expire("listMac",24, TimeUnit.HOURS);
        }
        for (int j = 0; j < listMac.size(); j++) {
            /**
             * 盒子所在位置和所在层级
             */
            Location_tier locationTier;
            if (redisTemplate.hasKey("locationTier"+listMac.get(j))){
                locationTier=(Location_tier)redisTemplate.opsForValue().get("locationTier"+listMac.get(j));
            }else {
                locationTier=addressDao
                        .selectLocation_tier(listMac.get(j));
                redisTemplate.opsForValue().set("locationTier"+listMac.get(j),locationTier,24,TimeUnit.HOURS);
            }
            list = dateNumberDao.selectTwoHour(listMac.get(j),date1,date2);
            list=ControllerUtil.TowHourSortUtil(list,index);
            List<DateAndNumber>resList=ControllerUtil.listTimeSort(list);
            Mac_ListTimeSort mac_listTimeSort=new Mac_ListTimeSort();
            mac_listTimeSort.setList(resList);
            mac_listTimeSort.setMac_address(locationTier.getLocation());
            listTimeSortList.add(mac_listTimeSort);
        }
        return JSON.toJSONString(listTimeSortList);
    }
}
