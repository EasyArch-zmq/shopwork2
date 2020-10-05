package com.easyArch.mapper;

import com.easyArch.entity.YanGan;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface YanGanDao {

    /**
     * 查看当天零点到目前为止的烟感数据情况
     * @param specific_address：用户具体地址用来模糊查找boxid
     * @param date1：当天的零点
     * @param date2：截取的系统时间时间
     * @return
     */
    /**
     *
     * @param city
     * @param county
     * @param street
     * @param specific_address
     * @param date1
     * @param date2
     * @return
     */
    List<YanGan>yanGanList(String city,String county,String street,String specific_address,String date1,String date2);
}
