package com.easyArch.service.impl;


import com.alibaba.fastjson.JSON;
import com.easyArch.entity.Address;
import com.easyArch.entity.Mac_Loc;
import com.easyArch.mapper.AddressDao;
import com.easyArch.service.ApplyAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@SuppressWarnings("unchecked")
@Service
public class ApplyAddressServiceImpl implements ApplyAddressService {
    @Autowired
    private AddressDao addressDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<String> selectCity() {
        List cityList=null;
        if (redisTemplate.hasKey("cityList")) {
            cityList = redisTemplate.opsForList().range("cityList", 0, -1);
        }else {
            cityList=addressDao.city();
            if (0 !=cityList.size()){
                for (Object str:cityList){
                    redisTemplate.opsForList().rightPush("cityList",str);
                }
            }
        }
        return cityList;
    }

    @Override
    public List<String> selectCounty(Address address) {
        String City=address.getCity();
        List<String> countyList=null;
        if (redisTemplate.hasKey("countyList"))
            countyList = redisTemplate.opsForList().range("countyList", 0, -1);
        else {
            countyList=addressDao.county(City);
            if (0 !=countyList.size()){
                for (String str:countyList){
                    redisTemplate.opsForList().rightPush("countyList",str);
                }
            }
        }
        return countyList;
    }

    @Override
    public List<String> selectStreet(Address address) {
        String City=address.getCity();
        String County=address.getCounty();
        List<String> streetList=null;
        if (redisTemplate.hasKey("streetList"))
            streetList = redisTemplate.opsForList().range("streetList", 0, -1);
        else {
            streetList=addressDao.street(City,County);
            if (0 !=streetList.size()){
                for (String str:streetList){
                    redisTemplate.opsForList().rightPush("streetList",str);
                }
            }
        }
        return streetList;
    }

    @Override
    public List<String> selectSpecific(Address address) {
        String City=address.getCity();
        String County=address.getCounty();
        String Street=address.getStreet();
        List<String> SpecificList=null;
        if (redisTemplate.hasKey("SpecificList"))
            SpecificList = redisTemplate.opsForList().range("SpecificList", 0, -1);
        else {
            SpecificList=addressDao.specificAddress(City,County,Street);
            if (0 !=SpecificList.size()){
                for (String str:SpecificList){
                    redisTemplate.opsForList().rightPush("SpecificList",str);
                }
            }
        }
        return SpecificList;
    }

    @Override
    public String selectMac_Address(Address address) {
        String City=address.getCity();
        String County=address.getCounty();
        String Street=address.getStreet();
        String Specific_address=address.getSpecial_address();
        List<Mac_Loc>locList=null;
        if (redisTemplate.hasKey("locList"))
            locList = redisTemplate.opsForList().range("locList", 0, -1);
        else {
            locList=addressDao.select_ma_lo(City,County,Street,Specific_address);
            if (0 !=locList.size()){
                for (Mac_Loc loc:locList){
                    redisTemplate.opsForList().rightPush("locList",loc);
                }
            }
        }
        return JSON.toJSONString(locList);
    }
}
