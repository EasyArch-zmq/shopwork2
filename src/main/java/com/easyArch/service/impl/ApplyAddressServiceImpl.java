package com.easyArch.service.impl;

import com.alibaba.fastjson.JSON;
import com.easyArch.entity.Address;
import com.easyArch.entity.Mac_Loc;
import com.easyArch.mapper.AddressDao;
import com.easyArch.service.ApplyAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ApplyAddressServiceImpl implements ApplyAddressService {
    @Autowired
    private AddressDao addressDao;

    @Override
    public List<String> selectCity() {
        return addressDao.city();
    }

    @Override
    public List<String> selectCounty(Address address) {
        String City=address.getCity();
        return addressDao.county(City);
    }

    @Override
    public List<String> selectStreet(Address address) {
        String City=address.getCity();
        String County=address.getCounty();
        return addressDao.street(City,County);
    }

    @Override
    public List<String> selectSpecific(Address address) {
        String City=address.getCity();
        String County=address.getCounty();
        String Street=address.getStreet();
        return addressDao.specificAddress(City,County,Street);
    }

    @Override
    public String selectMac_Address(Address address) {
        String City=address.getCity();
        String County=address.getCounty();
        String Street=address.getStreet();
        String Specific_address=address.getSpecial_address();
        List<Mac_Loc>locList=addressDao.select_ma_lo(City,County,Street,Specific_address);
        return JSON.toJSONString(locList);
    }
}
