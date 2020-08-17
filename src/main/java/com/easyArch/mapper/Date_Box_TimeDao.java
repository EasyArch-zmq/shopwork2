package com.easyArch.mapper;

import com.easyArch.entity.DateAndNumber;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Date_Box_TimeDao {

    List<DateAndNumber>selectDay(String mac_address,String date1,String date2,String time1,String time2);

    List<DateAndNumber>selectMonth(String mac_address ,String date1,String date2,String time1,String time2);

    List<DateAndNumber>selectYear(String mac_address,String date1,String date2,String time1,String time2);
}
