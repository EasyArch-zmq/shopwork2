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
    @Autowired
    AddressDao addressDao;
    @Autowired
    ColorDao colorDao;
    @Autowired
    P_UserDao p_userDao;
    @Autowired
    DateNumberDao dateNumberDao;


    /**
     * 返回地址下拉框数据之市
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "CityAddress",method = RequestMethod.POST)
public List<String> selectCAddress(){
        return addressDao.city();
        }

    /**
     * 返回地址下拉框数据之县
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "CountyAddress",method = RequestMethod.POST)
public List<String> selectCoAddress(@RequestBody Address address) {
        String City=address.getCity();
    return addressDao.county(City);

}

    /**
     * 返回地址下拉框数据之镇
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "TownAddress",method = RequestMethod.POST)
    public List<String> selectToAddress(@RequestBody Address address) {
        String City=address.getCity();
        String County=address.getCounty();
        return addressDao.town(City,County);

    }

    /**
     * 返回地址下拉框数据之镇
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "StreetAddress",method = RequestMethod.POST)
    public List<String> selectStAddress(@RequestBody Address address) {
        String City=address.getCity();
        String County=address.getCounty();
        String Town=address.getTown();
        return addressDao.street(City,County,Town);

    }
    /**
     * 返回地址下拉框数据之具体地址提示
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "SpecificAddress",method = RequestMethod.POST)
    public List<String> seleceSAddress(@RequestBody Address address) {
        String City=address.getCity();
        String County=address.getCounty();
        String Town=address.getTown();
        String Street=address.getStreet();
        return addressDao.specificAddress(City,County,Town,Street);
    }



    /**
     *返回用户地址，具体地址，总人数，green,red
     */

    @ResponseBody
    @RequestMapping(value = "selectAllNumber",produces = "application/json;charset=utf-8",method = RequestMethod.POST)
    public String selectAllNumber(@RequestBody P_User p_user){
        if(p_user!=null){
            String username=p_user.getUsername();
            //返回给前端整个页面需要的数据总类
            AllNumber allNumber=new AllNumber();
            List<Data_inCons>list_inCons=new ArrayList<>();

            List<Construction_inAll>list_inAll=new ArrayList<>();
            //数据之一：用户地址
            String userAddress=p_userDao
                    .selectUserAddress(username);
            String []str=ControllerUtil.slipAddress(userAddress);
            String city=str[0];
            String county=str[1];
            String town=str[2];
            String street=str[3];
            String specificAddress=str[4];
            if(town.equals("*")){
                town=null;
            }
            //返回Mac和具体地址
            List<String> cons_List = addressDao
                    .select_construction(specificAddress,city,county,town,street);
            for(int i=0;i<cons_List.size();i++){

                Construction_inAll construction_inAll=new Construction_inAll();
                construction_inAll.setConstruction(cons_List.get(i));

                List<String>mac_list=addressDao
                        .select_mac(specificAddress,city,county,town,street,cons_List.get(i));

                for (int j = 0; j <mac_list.size() ; j++) {
                    //红绿警报范围值
                    Color1 color1=colorDao
                            .selectColor(mac_list.get(j));

                    Location_tier locationTier=addressDao
                            .selectLocation_tier(mac_list.get(j));

                    //盒子的对应收集到的人数
                    Integer num=dateNumberDao
                            .selectAllNumber(mac_list.get(j));
                    System.out.println("----------->num:"+num +"------------>mac:"+mac_list.get(j));
                    Data_inCons data_inCons =new Data_inCons();

                    data_inCons.setNumber(num);
                    data_inCons.setLocation(locationTier.getLocation());
                    data_inCons.setTier(locationTier.getTier());
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
        return JSON.toJSONString("f");
    }

}
