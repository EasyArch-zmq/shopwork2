package com.easyArch.service.impl;

import com.alibaba.fastjson.JSON;
import com.easyArch.entity.DateAndAddress;
import com.easyArch.entity.Mac_Num;
import com.easyArch.mapper.AddressDao;
import com.easyArch.mapper.DateNumberDao;
import com.easyArch.service.G_SameTimeAccountService;
import com.easyArch.util.ControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
@SuppressWarnings("unchecked")
@Service
public class G_SameTimeAccountServiceImpl implements G_SameTimeAccountService {
    @Autowired
    AddressDao addressDao;
    @Autowired
    DateNumberDao dateNumberDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public String sameTimeStatistic(DateAndAddress address) {
        String addressStr = address.getAddress();
        String[] str = ControllerUtil.slipAddress(addressStr);
        String city = str[0];
        String county = str[1];
        String street = str[2];
        String specificAddress = str[3];
        String time1 = address.getTime1();
        String time2 = address.getTime2();
        String date1;
        String date2;
        if (time1 != null) {
            date1 = address.getYear1() + "-" + address.getMonth1() + "-" + address.getDay1() + " " + time1;
            date2 = address.getYear2() + "-" + address.getMonth2() + "-" + address.getDay2() + " " + time2;
        } else {
            date1 = address.getYear1() + "-" + address.getMonth1() + "-" + address.getDay1();
            date2 = address.getYear2() + "-" + address.getMonth2() + "-" + address.getDay2();
        }
        List<Mac_Num> list = new ArrayList<>();

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
        for (int j = 0; j < listMac.size(); j++) {
            Integer num = dateNumberDao.selectDayAndTime(listMac.get(j), date1, date2);
            Mac_Num mac_num = new Mac_Num();
            mac_num.setNum(num);
            mac_num.setMac_address(listMac.get(j));
            list.add(mac_num);
        }
        return JSON.toJSONString(list);
    }
}
