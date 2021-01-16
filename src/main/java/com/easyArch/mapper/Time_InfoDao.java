package com.easyArch.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface Time_InfoDao {
    /**
     * 存入烟感数据，时间（人数）
     * @param mac_address
     * @param mytime
     * @param yangan
     */
    void insertInfo(String mac_address,String mytime,Integer yangan);
}
