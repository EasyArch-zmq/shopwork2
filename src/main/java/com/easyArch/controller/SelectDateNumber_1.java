package com.easyArch.controller;

import com.alibaba.fastjson.JSON;
import com.easyArch.Application;
import com.easyArch.entity.DateAndAddress;
import com.easyArch.entity.DateAndNumber;
import com.easyArch.entity.DateNumberAll;
import com.easyArch.mapper.AddressDao;
import com.easyArch.mapper.DateNumberDao;
import com.easyArch.mapper.Date_TimeDao;
import com.easyArch.util.ControllerUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SelectDateNumber_1 {
    private static final Logger logger=Logger.getLogger(SelectDateNumber_1.class);
    @Autowired
    DateNumberDao dateNumberDao;
    @Autowired
    AddressDao addressDao;

    @ResponseBody
    @RequestMapping(value = "selectDateNumber_1"
            , produces = "application/json;charset=utf-8"
            , method = RequestMethod.POST)
    public String selectDateNumber(@RequestBody DateAndAddress dateAndAddress) {
        logger.info("selectDateNumber!!!!!");
        System.out.println("selectDateNumber!!!!!");
        if (dateAndAddress != null) {
            int state=0;
            List<DateAndNumber> list=new ArrayList<>();
            String year1 = dateAndAddress.getYear1();
            String month1 = dateAndAddress.getMonth1();
            String day1 = dateAndAddress.getDay1();
            String year2 = dateAndAddress.getYear2();
            String month2 = dateAndAddress.getMonth2();
            String day2 = dateAndAddress.getDay2();
            String myTime1=dateAndAddress.getTime1();
            String myTime2=dateAndAddress.getTime2();

            DateNumberAll dateNumberAll=new DateNumberAll();

            String address = dateAndAddress.getAddress();
            String[] str = ControllerUtil.slipAddress(address);

            String city=city=str[0];;
            String county=str[1];;
            String town=str[2];
            String street=str[3];
            String specific_address=str[4];
            if(county.equals("null")&&town.equals("null")&&street.equals("null")&&specific_address.equals("null")){
                state=1;
                dateNumberAll.setAddress(city);
            }else if (town.equals("null")&&street.equals("null")&&specific_address.equals("null")){
                state=2;
                dateNumberAll.setAddress(county);
            }else if(street.equals("null")&&specific_address.equals("null")){
                state=3;
                dateNumberAll.setAddress(town);
            }else if(specific_address.equals("null")){
                state=4;
                dateNumberAll.setAddress(street);
            }else {
                state=5;
                dateNumberAll.setAddress(specific_address);
            }

            System.out.println("address: "+address);

            if (!year1.equals("null") && !year2.equals("null")) {
                if (!month1.equals("null") && !month2.equals("null")) {
                    if (!day1.equals("null") && !day2.equals("null")) {
                        String str1 = year1 + "-" + month1 + "-" + day1;
                        String str2 = year2 + "-" + month2 + "-" + day2;
                        //查询一天里
                        if (str1.equals(str2)) {
                            System.out.println("str1=str2------------------");
                            if(myTime1==null&&myTime2==null){
                                System.out.println();
                                str1 = str1 + " 00:00:00";
                                str2 = str2 + " 23:59:29";
                            }else {
                                str1 = str1 + " "+myTime1;
                                str2 = str2 + " "+myTime2;
                            }

                            switch (state){
                                case 1:if (myTime1==null&&myTime2==null){
                                    list=dateNumberDao.selectTwoHour_Ci(city, str1, str2);
                                    list=ControllerUtil.filterTowHour(list,"23");

                                }else {
                                    DateAndNumber andNumber=new DateAndNumber();
                                    andNumber.setNum(dateNumberDao.selectDay_adCi(city,str1,str2));
                                    andNumber.setTime(str1+"至"+str2);
                                    list.add(andNumber);
                                }
                                    dateNumberAll.setList(list);
                                    return JSON.toJSONString(dateNumberAll);
                                case 2:if (myTime1==null&&myTime2==null){
                                    list=dateNumberDao.selectTwoHour_Co(city,county,str1, str2);
                                    list=ControllerUtil.filterTowHour(list,"23");
                                }else {
                                    DateAndNumber andNumber=new DateAndNumber();
                                    andNumber.setNum(dateNumberDao.selectDay_adCo(city,county,str1,str2));
                                    andNumber.setTime(str1+"至"+str2);
                                    list.add(andNumber);
                                }
                                    dateNumberAll.setList(list);
                                    return JSON.toJSONString(dateNumberAll);
                                case 3:if (myTime1==null&&myTime2==null){
                                    list=dateNumberDao.selectTwoHour_To(city,county,town, str1, str2);
                                    list=ControllerUtil.filterTowHour(list,"23");
                                }else {
                                    DateAndNumber andNumber = new DateAndNumber();
                                    andNumber.setNum(dateNumberDao.selectDay_adTo(city, county, town, str1, str2));
                                    andNumber.setTime(str1 + "至" + str2);
                                    list.add(andNumber);
                                }
                                    dateNumberAll.setList(list);
                                    return JSON.toJSONString(dateNumberAll);
                                case 4:if (myTime1==null&&myTime2==null){
                                    list=dateNumberDao.selectTwoHour_St(city,county,town, street,str1, str2);
                                    list=ControllerUtil.filterTowHour(list,"23");
                                }else {
                                    DateAndNumber andNumber=new DateAndNumber();
                                    andNumber.setNum(dateNumberDao.selectDay_adSt(city,county,town,street, str1, str2));
                                    andNumber.setTime(str1+"至"+str2);
                                    list.add(andNumber);
                                }
                                    dateNumberAll.setList(list);
                                    return JSON.toJSONString(dateNumberAll);
                                case  5:if (myTime1==null&&myTime2==null){
                                    list=dateNumberDao.selectTwoHour_Sp(city,county,town,street,specific_address, str1, str2);
                                    list=ControllerUtil.filterTowHour(list,"23");
                                }else {
                                    DateAndNumber andNumber=new DateAndNumber();
                                    andNumber.setNum(dateNumberDao.selectDay_adSp(city,county,town,street,specific_address, str1, str2));
                                    andNumber.setTime(str1+"至"+str2);
                                    list.add(andNumber);
                                }
                                    dateNumberAll.setList(list);
                                    return JSON.toJSONString(dateNumberAll);
                                default:return JSON.toJSONString("f");

                            }

                        }
                        //按照天为单位查询多天
                        switch (state){
                            case 1:list = dateNumberDao.selectDay_Ci(city, str1, str2);
                                dateNumberAll.setList(list);
                                return JSON.toJSONString(dateNumberAll);
                            case 2: list = dateNumberDao.selectDay_Co(city,county, str1, str2);
                                dateNumberAll.setList(list);
                                return JSON.toJSONString(dateNumberAll);
                            case 3:list = dateNumberDao.selectDay_To(city,county,town, str1, str2);
                                dateNumberAll.setList(list);
                                return JSON.toJSONString(dateNumberAll);
                            case 4:list = dateNumberDao.selectDay_St(city,county,town,street, str1, str2);
                                dateNumberAll.setList(list);
                                return JSON.toJSONString(dateNumberAll);
                            case 5:list = dateNumberDao.selectDay_Sp(city,county,town,street,specific_address, str1, str2);
                                dateNumberAll.setList(list);
                                return JSON.toJSONString(dateNumberAll);
                            default:return JSON.toJSONString("f");
                        }
                    }
                    String str1 = year1 + "-" + month1 + "-01";
                    String str2 = year2 + "-" + month2 + "-01";
                    //查询一个月里的31天
                    if (str1.equals(str2)) {
                        str2 = year2 + "-" + month2 + "-31";
                        switch (state){
                            case 1:list = dateNumberDao.selectDay_Ci(city, str1, str2);
                                dateNumberAll.setList(list);
                                return JSON.toJSONString(dateNumberAll);
                            case 2: list = dateNumberDao.selectDay_Co(city,county, str1, str2);
                                dateNumberAll.setList(list);
                                return JSON.toJSONString(dateNumberAll);
                            case 3:list = dateNumberDao.selectDay_To(city,county,town, str1, str2);
                                dateNumberAll.setList(list);
                                return JSON.toJSONString(dateNumberAll);
                            case 4:list = dateNumberDao.selectDay_St(city,county,town,street, str1, str2);
                                dateNumberAll.setList(list);
                                return JSON.toJSONString(dateNumberAll);
                            case 5:list = dateNumberDao.selectDay_Sp(city,county,town,street,specific_address, str1, str2);
                                dateNumberAll.setList(list);
                                return JSON.toJSONString(dateNumberAll);
                            default:return JSON.toJSONString("f");
                        }
                    }
                    //查询以月为单位的多个月
                    str2 = year2 + "-" + month2 + "-31";
                    switch (state){
                        case 1:list = dateNumberDao.selectMonth_Ci(city, str1, str2);
                            dateNumberAll.setList(list);
                            return JSON.toJSONString(dateNumberAll);
                        case 2:list = dateNumberDao.selectMonth_Co(city,county, str1, str2);
                            dateNumberAll.setList(list);
                            return JSON.toJSONString(dateNumberAll);
                        case 3: list = dateNumberDao.selectMonth_To(city,county,town, str1, str2);
                            dateNumberAll.setList(list);
                            return JSON.toJSONString(dateNumberAll);
                        case 4:dateNumberDao.selectMonth_St(city,county,town,street, str1, str2);
                            dateNumberAll.setList(list);
                            return JSON.toJSONString(dateNumberAll);
                        case 5: list = dateNumberDao.selectMonth_Sp(city,county,town,street,specific_address, str1, str2);
                            dateNumberAll.setList(list);
                            return JSON.toJSONString(dateNumberAll);
                        default:return JSON.toJSONString("f");
                    }

                }
                String str1 = year1;
                String str2 = year2;
                //查询一年内
                if (str1.equals(str2)) {
                    str1 = year1 + "-01" + "-01";
                    str2 = year2 + "-12" + "-31";
                    switch (state){
                        case 1: list = dateNumberDao.selectMonth_Ci(city, str1, str2);
                            dateNumberAll.setList(list);
                            return JSON.toJSONString(dateNumberAll);
                        case 2:list = dateNumberDao.selectMonth_Co(city,county, str1, str2);
                            dateNumberAll.setList(list);
                            return JSON.toJSONString(dateNumberAll);
                        case 3:list = dateNumberDao.selectMonth_To(city,county,town, str1, str2);
                            dateNumberAll.setList(list);
                            return JSON.toJSONString(dateNumberAll);
                        case 4: list = dateNumberDao.selectMonth_St(city,county,town,street, str1, str2);
                            dateNumberAll.setList(list);
                            return JSON.toJSONString(dateNumberAll);
                        case 5:list = dateNumberDao.selectMonth_Sp(city,county,town,street,specific_address, str1, str2);
                            dateNumberAll.setList(list);
                            return JSON.toJSONString(dateNumberAll);
                        default:return JSON.toJSONString("f");
                    }

                }
                //查询多年
                str1 = year1 + "-01" + "-01";
                str2 = year2 + "-12" + "-31";
                switch (state){
                    case 1:list = dateNumberDao.selectYear_Ci(city, str1, str2);
                        dateNumberAll.setList(list);
                        return JSON.toJSONString(dateNumberAll);
                    case 2:list = dateNumberDao.selectYear_Co(city,county, str1, str2);
                        dateNumberAll.setList(list);
                        return JSON.toJSONString(dateNumberAll);
                    case 3: list = dateNumberDao.selectYear_To(city,county,town, str1, str2);
                        dateNumberAll.setList(list);
                        return JSON.toJSONString(dateNumberAll);
                    case 4:list = dateNumberDao.selectYear_St(city,county,town,street, str1, str2);
                        dateNumberAll.setList(list);
                        return JSON.toJSONString(dateNumberAll);
                    case 5:list = dateNumberDao.selectYear_Sp(city,county,town,street,specific_address, str1, str2);
                        dateNumberAll.setList(list);
                        return JSON.toJSONString(dateNumberAll);
                    default:JSON.toJSONString("f");
                }
            }
        }

        return JSON.toJSONString("f");
    }
}

