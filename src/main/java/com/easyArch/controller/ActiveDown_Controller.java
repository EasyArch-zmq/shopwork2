package com.easyArch.controller;

import com.alibaba.fastjson.JSON;
import com.easyArch.entity.*;
import com.easyArch.mapper.AddressDao;
import com.easyArch.mapper.ColorDao;
import com.easyArch.mapper.DateNumberDao;
import com.easyArch.mapper.P_UserDao;
import com.easyArch.util.ControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ActiveDown_Controller {
    ControllerUtil util=new ControllerUtil();
    @Autowired
    AddressDao addressDao;
    @Autowired
    ColorDao colorDao;
    @Autowired
    P_UserDao p_userDao;
    @Autowired
    DateNumberDao dateNumberDao;

    /**
     * 返回地址下拉框数据之省
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "ProvinceAddress",method = RequestMethod.POST)
    public List<String> selecePAddress() {
        return addressDao.province();
    }


    /**
     * 返回地址下拉框数据之市
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "CityAddress",method = RequestMethod.POST)
public List<String> seleceCAddress(@RequestBody Address address){
        String Province=address.getProvince();
        return addressDao.city(Province);
        }

    /**
     * 返回地址下拉框数据之县
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "CountyAddress",method = RequestMethod.POST)
public List<String> seleceCoAddress(@RequestBody Address address) {
        String Province=address.getProvince();
        String City=address.getCity();
    return addressDao.county(Province,City);

}
    /**
     * 返回地址下拉框数据之具体地址提示
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "SpecificAddress",method = RequestMethod.POST)
    public List<String> seleceSAddress(@RequestBody Address address) {
        String Province=address.getProvince();
        String City=address.getCity();
        String County=address.getCounty();
        return addressDao.specificAddress(Province,City,County);
    }



    /**
     *返回用户地址，具体地址，总人数，green,red
     */

    @ResponseBody
    @RequestMapping(value = "selectAllNumber",produces = "application/json;charset=utf-8",method = RequestMethod.POST)
    public String selectAllNumber(@RequestBody P_User p_user){
        if(p_user!=null){
            String username=p_user.getUsername();
            //获取范围值
            Color color=colorDao.selectColor();
            //返回给前端整个页面需要的数据总类
            AllNumber allNumber=new AllNumber();
            List<AddressAndNumber>list=new ArrayList<>();
            //数据之一：用户地址
            String userAddress=p_userDao.selectUserAddress(username);
            String []str=util.slipAddress(userAddress);
            String province=str[0];
            String city=str[1];
            String county=str[2];
            String specificAddress=str[3];
            //返回boxid和具体地址
            List<BoxidAndAddress> boxidsList = addressDao
                    .selectBoxids(specificAddress,province,city,county);
            for(BoxidAndAddress listAddress:boxidsList){
                //盒子的对应收集到的人数
                Integer num=dateNumberDao.selectAllNumber(listAddress.getBoxid());
                AddressAndNumber addressAndNumber=new AddressAndNumber();
                String str1=listAddress.getSpecificadress();
                String[] strings=str1.split(specificAddress);
                addressAndNumber.setAddress(strings[1]);
                addressAndNumber.setNumber(num);
                list.add(addressAndNumber);
            }
            allNumber.setUserAddress(specificAddress);
            allNumber.setColor(color);
            allNumber.setList(list);
            return JSON.toJSONString(allNumber);
        }
        return JSON.toJSONString("f");
    }

}
