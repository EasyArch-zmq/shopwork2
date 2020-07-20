package com.easyArch.mapper;

import com.easyArch.entity.DateAndNumber;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DateNumberDao {
    /**
     * 根据boxid查询人数
     * @param boxid
     * @return
     */
    Integer selectDateNumber(String boxid);

    /**
     * 每两小时查询
     * @param date1
     * @return
     */
    List<DateAndNumber>selectTwoHour(String boxid,String date1,String date2);

    List<DateAndNumber>selectTwoHour1(String province ,String date1,String date2);

    List<DateAndNumber>selectTwoHour2(String province ,String city,String date1,String date2);

    List<DateAndNumber>selectTwoHour3(String province ,String city,String county,String date1,String date2);
    /**
     * 每日查询
     * @param date1
     * @param date2
     * @return
     */
    List<DateAndNumber>selectDay1(String province ,String date1,String date2);

    List<DateAndNumber>selectDay2(String province ,String city,String date1,String date2);

    List<DateAndNumber>selectDay3(String province ,String city,String county,String date1,String date2);

    /**
     * 每月查询
     * @param date1
     * @param date2
     * @return
     */
    List<DateAndNumber>selectMonth1(String province ,String date1,String date2);

    List<DateAndNumber>selectMonth2(String province,String city ,String date1,String date2);

    List<DateAndNumber>selectMonth3(String province ,String city,String county,String date1,String date2);

    /**
     *每年查询到省
     * @param date1
     * @param date2
     * @return
     */
    List<DateAndNumber>selectYear1(String province ,String date1,String date2);

    /**
     * 到市
     * @param province
     * @param city
     * @param date1
     * @param date2
     * @return
     */
    List<DateAndNumber>selectYear2(String province ,String city,String date1,String date2);

    List<DateAndNumber>selectYear3(String province ,String city,String county,String date1,String date2);


}
