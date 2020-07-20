package com.easyArch.mapper;

import com.easyArch.entity.YanGan;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface YanGanDao {

    /**
     *
     * @param
     * @return
     */
    List<YanGan>yanGanList(String specificadress,String date1,String date2);
}
