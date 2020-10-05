package com.easyArch.controller;

import com.alibaba.fastjson.JSON;
import com.easyArch.entity.Address_Month;
import com.easyArch.entity.Color2;
import com.easyArch.entity.DateAndAddress;
import com.easyArch.entity.G_User;
import com.easyArch.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class G_DataController {
    @Autowired
    private ColorInsertService service;
    @Autowired
    private G_SameMacRankService gSameMacRankService;
    @Autowired
    G_SameTimeAccountService gSameTimeAccountService;
    @Autowired
    G_AppointTimeService gAppointTimeService;
    @Autowired
    G_SuccessiveTimeService gSuccessiveTimeService;
    @Autowired
    G_TodayAccountService g_todayAccountService;
    @Autowired
    G_TimeRankService g_timeRankService;
    @Autowired
    G_AccountAboutColorService gAccountAboutColorService;
    @Autowired
    G_WeekRankService gWeekRankService;
    @Autowired
    G_YanGanService gYanGanService;

    /**
     * 管理员用户首页默认数据
     * @param g_user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectDefaultNumber"
            , produces = "application/json;charset=utf-8"
            , method = RequestMethod.POST)
    public String selectDefaultNumber (@RequestBody G_User g_user) {
        if(g_user!=null){
            return g_todayAccountService.selectDefaultNumber(g_user);
        }
        return JSON.toJSONString("f");
    }

    /**
     * 管理员用户修改范围值页面保存红绿值
     * @param color
     */
    @ResponseBody
    @RequestMapping( value = "insertColorValue",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST)
    public void colorInsert(@RequestBody Color2 color){
        if (color!=null){
            service.insertColorValue(color);
        }

    }

    /**
     * 管理员页面 同一个盒子在一定时间的两小时排行
     * @param address
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "sameMac_timeRank"
            , produces = "application/json;charset=utf-8"
            , method = RequestMethod.POST)
    public String  sameMac_timeRank(@RequestBody DateAndAddress address) {
        if (address != null) {
            gSameMacRankService.getSameMac_timeRank(address);
        }
        return JSON.toJSONString("f");
    }

    /**
     * 管理员页面 同时间段各个盒子数据统计（柱子横坐标是mac_address，不排行）
     * @param address
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "sameTime_Statistic"
            , produces = "application/json;charset=utf-8"
            , method = RequestMethod.POST)
    public String sameTimeStatistic(@RequestBody DateAndAddress address) {
        if (address != null) {
            return gSameTimeAccountService.sameTimeStatistic(address);
        }
        return JSON.toJSONString("f");
    }

    /**
     * 管理员数据统计页面，按连续时间查询数据
     * 比如 2020-09-01 12:00  -  2020-09-02 14:00
     * @param dateAndAddress
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectDateNumber_1"
            , produces = "application/json;charset=utf-8"
            , method = RequestMethod.POST)
    public String selectDateNumber1(@RequestBody DateAndAddress dateAndAddress) {
        System.out.println("selectDateNumber_1连续时间!!!!!");
        if (dateAndAddress != null) {
            return gSuccessiveTimeService.selectDateNumber1(dateAndAddress);
        }
        return JSON.toJSONString("f");
    }


    /**
     * 管理员数据统计页面，按指定时间查询数据
     * 比如 2020-09-01   -  2020-09-02       12:00-14:00
     * @param dateAndAddress
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectDateNumber_2"
            , produces = "application/json;charset=utf-8"
            , method = RequestMethod.POST)
    public String selectDateNumber2(@RequestBody DateAndAddress dateAndAddress) {
        System.out.println("指定时间");
        if (dateAndAddress != null) {
            return gAppointTimeService.selectDateNumber2(dateAndAddress);
        }
        return JSON.toJSONString("f");
    }

    /**
     * 管理员页面 每个盒子每天各个时间段排行（每天统计）
     * @param address
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "time_Rank"
            , produces = "application/json;charset=utf-8"
            , method = RequestMethod.POST)
    public String time_Rank(@RequestBody DateAndAddress address) {
        if (address != null) {
            return g_timeRankService.time_Rank(address);
        }
        return JSON.toJSONString("f");
    }


    /**
     * 管理员页面   每天统计红色，黄色，绿色
     * 两小时为分割时间
     * @param address
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "statistic_Color_Data"
            , produces = "application/json;charset=utf-8"
            , method = RequestMethod.POST)
    public String statistic_color(@RequestBody DateAndAddress address){
        if (address!=null){
            return gAccountAboutColorService.getNum_color(address);
        }
        return JSON.toJSONString("f");
    }

    /**
     * 管理员页面 以月为单位，按照周一，二排序。。。。。前端需要用五个颜色区分是第几个周一
     * @param address_month
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "week_Rank"
            , produces = "application/json;charset=utf-8"
            , method = RequestMethod.POST)
    public String week_Rank(@RequestBody Address_Month address_month) {
        if (address_month != null) {
            return gWeekRankService.week_Rank(address_month);
        }
        return JSON.toJSONString("f");
    }

    /**
     * 管理员页面 查看烟感数据
     * @return
     */
    @ResponseBody
    @RequestMapping( value = "yanGan",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST)
    public String yanGan(){
        return gYanGanService.getYanGan();
    }

}
