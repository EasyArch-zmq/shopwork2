package com.easyArch.service.impl;

import com.alibaba.fastjson.JSON;
import com.easyArch.entity.Address;
import com.easyArch.entity.Mac_Loc;
import com.easyArch.mapper.AddressDao;
import com.easyArch.service.G_XiGuaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class G_XiGuaServiceImpl implements G_XiGuaService {
    @Autowired
    private AddressDao addressDao;
    @Override
    public String getAddressList() {
        List<Mac_Loc>address_macList=new ArrayList<>();
        List<Address>addressList=addressDao.selectAddress();
        Integer i=0;
        for (Address as:addressList){
            i++;
            Mac_Loc address_mac=new Mac_Loc();
            address_mac.setAddress(as.getCity()+","+as.getCounty()+","+as.getStreet()+","+as.getSpecific_address());
            address_mac.setMac_address(as.getLocation()+"("+as.getMac_address()+")");
            address_mac.setId(i);
            address_macList.add(address_mac);
        }
        return JSON.toJSONString(address_macList);
    }
}
