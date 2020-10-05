package com.easyArch.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PictureDao {

    List<String> selectPic(@Param("specific_address") String specific_address, String city, String county, String street, String construction);
}
