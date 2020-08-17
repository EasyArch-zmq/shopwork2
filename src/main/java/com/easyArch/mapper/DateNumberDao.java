package com.easyArch.mapper;

import com.easyArch.entity.DateAndNumber;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DateNumberDao {
    /**
     * 根据boxid查询总人数
     * @param mac_address
     * @return
     */
    Integer selectAllNumber(String mac_address);

    /**
     * 每两小时查询
     * @param date1
     * @return
     */
    List<DateAndNumber>selectTwoHour(String mac_address,String date1,String date2);

    List<DateAndNumber>selectTwoHour_Ci(String city,String date1,String date2);

    List<DateAndNumber>selectTwoHour_Co(String city,String county,String date1,String date2);

    List<DateAndNumber>selectTwoHour_To(String city,String county,String town,String date1,String date2);

    List<DateAndNumber>selectTwoHour_St(String city,String county,String town,String street,String date1,String date2);

    List<DateAndNumber>selectTwoHour_Sp(String city,String county,String town,String street,String specific_address,String date1,String date2);
    /**
     * 每日查询
     * @param date1
     * @param date2
     * @return
     */


    List<DateAndNumber>selectDay(String mac_address,String date1,String date2);
    
    List<DateAndNumber>selectDay_Ci(String city,String date1,String date2);

    List<DateAndNumber>selectDay_Co(String city,String county,String date1,String date2);

    List<DateAndNumber>selectDay_To(String city,String county,String town,String date1,String date2);

    List<DateAndNumber>selectDay_St(String city,String county,String town,String street,String date1,String date2);

    List<DateAndNumber>selectDay_Sp(String city,String county,String town,String street,String specific_address,String date1,String date2);

    /**
     * 每月查询
     * @param date1
     * @param date2
     * @return
     */

    List<DateAndNumber>selectMonth(String mac_address ,String date1,String date2);
    
    List<DateAndNumber>selectMonth_Ci(String city ,String date1,String date2);

    List<DateAndNumber>selectMonth_Co(String city,String county,String date1,String date2);

    List<DateAndNumber>selectMonth_To(String city,String county,String town,String date1,String date2);

    List<DateAndNumber>selectMonth_St(String city,String county,String town,String street,String date1,String date2);

    List<DateAndNumber>selectMonth_Sp(String city,String county,String town,String street,String specific_address,String date1,String date2);

    /**
     *每年查询到省
     * @param date1
     * @param date2
     * @return
     */
    List<DateAndNumber>selectYear(String mac_address,String date1,String date2);

    List<DateAndNumber>selectYear_Ci(String city,String date1,String date2);

    /**
     * 到市
     * @param
     * @param city
     * @param date1
     * @param date2
     * @return
     */
    List<DateAndNumber>selectYear_Co(String city,String county,String date1,String date2);

    List<DateAndNumber>selectYear_To(String city,String county,String town,String date1,String date2);

    List<DateAndNumber>selectYear_St(String city,String county,String town,String street,String date1,String date2);

    List<DateAndNumber>selectYear_Sp(String city,String county,String town,String street,String specific_address,String date1,String date2);


}
