package com.easyArch.service.impl;

import com.alibaba.fastjson.JSON;
import com.easyArch.entity.DateAndAddress;
import com.easyArch.entity.DateAndNumber;
import com.easyArch.entity.MacAndDataList;
import com.easyArch.mapper.AddressDao;
import com.easyArch.mapper.DateNumberDao;
import com.easyArch.util.ControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
@SuppressWarnings("unchecked")
@Service
public class G_SameMacRankServiceImpl implements com.easyArch.service.G_SameMacRankService {

    @Autowired
    AddressDao addressDao;
    @Autowired
    DateNumberDao dateNumberDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public String getSameMac_timeRank(DateAndAddress address) {
        String addressStr = address.getAddress();
        String[] str = ControllerUtil.slipAddress(addressStr);
        String city = str[0];
        String county = str[1];
        String street = str[2];
        String specificAddress = str[3];
        String time1 = address.getTime1();
        String time2 = address.getTime2();
        String date1;
        String date2 ;
        date1 =  address.getYear() + "-" + address.getMonth() + "-" + address.getDay() + " " + time1;
        date2 =  address.getYear() + "-" + address.getMonth() + "-" + address.getDay() + " " + time2;
        /**
         * 通过地址查找盒子，返回符合地址条件的所有盒子
         */
        List<String> listMac;
        if (redisTemplate.hasKey(specificAddress+city+county+street+"listMac")){
            listMac=redisTemplate.opsForList().range(specificAddress+city+county+street+"listMac",0,-1);
        }else {
            listMac=addressDao.select_mac2(specificAddress, city, county, street);
            for (String s:listMac){
                redisTemplate.opsForList().rightPush(specificAddress+city+county+street+"listMac",listMac);
            }
            redisTemplate.expire(specificAddress+city+county+street+"listMac",24, TimeUnit.HOURS);
        }
        System.out.println(listMac.size());
        List<DateAndNumber>list;
        List<MacAndDataList>lists=new ArrayList<>();
        for (int j = 0; j < listMac.size(); j++) {
            list=dateNumberDao.selectTwoHour(listMac.get(j),date1,date2);
            list=ControllerUtil.TowHourSameMacUtil(list,time1,time2);
            list=ControllerUtil.listTimeSort(list);
            MacAndDataList macAndDataList=new MacAndDataList();
            macAndDataList.setMac_address(listMac.get(j));
            macAndDataList.setList(list);
            lists.add(macAndDataList);
        }
        return JSON.toJSONString(lists);
    }
}
