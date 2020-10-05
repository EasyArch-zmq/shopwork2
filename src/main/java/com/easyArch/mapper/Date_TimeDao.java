package com.easyArch.mapper;

import com.easyArch.entity.DateAndNumber;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Date_TimeDao {
    /**
     * 每日查询
     * @param date1
     * @param date2
     * @return
     */


    List<DateAndNumber>selectDay_Ci(String city,String date1,String date2,String time1,String time2);

    List<DateAndNumber>selectDay_Co(String city,String county,String date1,String date2,String time1,String time2);

    List<DateAndNumber>selectDay_St(String city,String county,String street,String date1,String date2,String time1,String time2);

    List<DateAndNumber>selectDay_Sp(String city,String county,String street,String specific_address,String date1,String date2,String time1,String time2);

    /**
     * 每月查询
     * @param date1
     * @param date2
     * @return
     */

    List<DateAndNumber>selectMonth_Ci(String city ,String date1,String date2,String time1,String time2);

    List<DateAndNumber>selectMonth_Co(String city,String county,String date1,String date2,String time1,String time2);

    List<DateAndNumber>selectMonth_St(String city,String county,String street,String date1,String date2,String time1,String time2);

    List<DateAndNumber>selectMonth_Sp(String city,String county,String street,String specific_address,String date1,String date2,String time1,String time2);

    /**
     *每年查询到省
     * @param date1
     * @param date2
     * @return
     */
    List<DateAndNumber> selectYear_Ci(String city, String date1, String date2,String time1,String time2);

    List<DateAndNumber>selectYear_Co(String city,String county,String date1,String date2,String time1,String time2);

    List<DateAndNumber>selectYear_St(String city,String county,String street,String date1,String date2,String time1,String time2);

    List<DateAndNumber>selectYear_Sp(String city,String county,String street,String specific_address,String date1,String date2,String time1,String time2);

}
