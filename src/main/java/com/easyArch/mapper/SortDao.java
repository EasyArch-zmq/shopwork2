package com.easyArch.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SortDao {
    Integer selectMacNumber(String mac_address, String date1, String date2 );
}
