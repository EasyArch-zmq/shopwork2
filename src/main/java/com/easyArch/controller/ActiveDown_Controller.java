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
    private static int cont;
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
     * 返回地址下拉框数据
     * @return
     */
    @RequestMapping(value = "address",method = RequestMethod.POST)
    public List<String> seleceAddress(){
        cont+=1;
        if(cont==1){
            System.out.println(cont);
            return addressDao.province();
        }
        if (cont==2){
            System.out.println(cont);
            return addressDao.city();
        }
        if (cont==3){
            System.out.println(cont);
            return addressDao.county();
        }
        else {
            System.out.println(cont);
            return addressDao.specificAddress();
        }
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
            //返回boxid和具体地址
            List<BoxidAndAddress>boxidsList=addressDao
                    .selectBoxids(str[3]);
            for(BoxidAndAddress listAddress:boxidsList){

                //盒子的对应收集到的人数
                Integer num=dateNumberDao.selectDateNumber(listAddress.getBoxid());
                AddressAndNumber addressAndNumber=new AddressAndNumber();
                addressAndNumber.setAddress(listAddress.getSpecificadress());
                addressAndNumber.setNumber(num);
                list.add(addressAndNumber);
            }
            allNumber.setUserAddress(str[3]);
            allNumber.setColor(color);
            allNumber.setList(list);
            return JSON.toJSONString(allNumber);
        }
        return JSON.toJSONString("f");
    }

}
