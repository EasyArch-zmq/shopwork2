package com.easyArch.controller;

import com.alibaba.fastjson.JSON;
import com.easyArch.Application;
import com.easyArch.entity.DateAndAddress;
import com.easyArch.entity.DateAndNumber;
import com.easyArch.entity.DateNumberAll;
import com.easyArch.mapper.AddressDao;
import com.easyArch.mapper.DateNumberDao;
import com.easyArch.util.ControllerUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class SelectDate_Mac_1 {
    private static final Logger logger=Logger.getLogger(SelectDate_Mac_1.class);
    @Autowired
    DateNumberDao dateNumberDao;
    @Autowired
    AddressDao addressDao;


    @ResponseBody
    @RequestMapping(value = "/selectDataNumberBox_id_1"
            , produces = "application/json;charset=utf-8"
            , method = RequestMethod.POST)
    public String selectDateNumber(@RequestBody DateAndAddress dateAndAddress) {
        logger.info("selectDateNumber!!!!!");
        System.out.println("selectDateNumber!!!!!");
        if (dateAndAddress != null) {
            System.out.println("------------------------------------");
            List<DateAndNumber> list=new ArrayList<>();
            String year1 = dateAndAddress.getYear1();
            String month1 = dateAndAddress.getMonth1();
            String day1 = dateAndAddress.getDay1();
            String year2 = dateAndAddress.getYear2();
            String month2 = dateAndAddress.getMonth2();
            String day2 = dateAndAddress.getDay2();
            String myTime1=dateAndAddress.getTime1();
            String myTime2=dateAndAddress.getTime2();
            String mac_address1=dateAndAddress.getMac_address();
            String REGEX_CHINESE = "[\u4e00-\u9fa5]";
            Pattern pat = Pattern.compile(REGEX_CHINESE);
            Matcher mat = pat.matcher(mac_address1);
            String mac_address=mat.replaceAll("");
            DateNumberAll dateNumberAll=new DateNumberAll();
            if(mac_address1!=null){
                dateNumberAll.setMac_address(mac_address1);
            }else {
                return JSON.toJSONString("f");
            }
            if (year1!=null&& year2!=null) {
                if (month1!=null&& month2!=null) {
                    if (day1!=null&& day2!=null) {
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
                            if(myTime1==null&&myTime2==null){
                                list=dateNumberDao.selectTwoHour(mac_address,str1,str2);
                                list= ControllerUtil.filterTowHour(list,"23");
                            }else {
                                DateAndNumber andNumber=new DateAndNumber();
                                andNumber.setNum(dateNumberDao.selectDayAndTime(mac_address,str1,str2));
                                andNumber.setTime(str1+"至"+str2);
                                list.add(andNumber);
                            }
                            dateNumberAll.setList(list);
                            return JSON.toJSONString(dateNumberAll);
                        }
                        //按照天为单位查询多天
                        if (myTime1==null){
                            list=dateNumberDao.selectDay(mac_address, str1, str2);
                        }else {
                            str1 = str1 + " "+myTime1;
                            str2 = str2 + " "+myTime2;
                            DateAndNumber andNumber=new DateAndNumber();
                            andNumber.setNum(dateNumberDao.selectDayAndTime(mac_address,str1,str2));
                            andNumber.setTime(str1+"至"+str2);
                            list.add(andNumber);
                        }
                        dateNumberAll.setList(list);
                        return JSON.toJSONString(dateNumberAll);
                    }
                    String str1 = year1 + "-" + month1 + "-01";
                    String str2 = year2 + "-" + month2 + "-01";
                    //查询一个月里的31天
                    if (str1.equals(str2)) {
                        str2 = year2 + "-" + month2 + "-31";
                        list= dateNumberDao.selectDay(mac_address, str1, str2);
                        dateNumberAll.setList(list);
                        return JSON.toJSONString(dateNumberAll);

                    }
                    //查询以月为单位的多个月
                    str2 = year2 + "-" + month2 + "-31";
                    list = dateNumberDao.selectMonth(mac_address, str1, str2);
                    dateNumberAll.setList(list);
                    return JSON.toJSONString(dateNumberAll);
                }
                String str1 = year1;
                String str2 = year2;
                //查询一年内
                if (str1.equals(str2)) {
                    str1 = year1 + "-01" + "-01";
                    str2 = year2 + "-12" + "-31";
                    list = dateNumberDao.selectMonth(mac_address, str1, str2);
                    dateNumberAll.setList(list);
                    return JSON.toJSONString(dateNumberAll);
                }
                //查询多年
                str1 = year1 + "-01" + "-01";
                str2 = year2 + "-12" + "-31";
                list = dateNumberDao.selectYear(mac_address, str1, str2);
                dateNumberAll.setList(list);
                return JSON.toJSONString(dateNumberAll);
            }
        }
        return JSON.toJSONString("f");
    }
}
