package com.easyArch.mapper;

import com.easyArch.entity.BoxidAndAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AddressDao {

    /**
     *  省
     * @return
     */
    List<String>province();

    /**
     *  市
     * @return
     */
    List<String>city(String province );

    /**
     *  县
     * @return
     */
    List<String>county(String province,String city);

    /**
     * 具体
     * @return
     */
    List<String>specificAddress(String province,String city,String county);

    List<BoxidAndAddress> selectBoxids(@Param("specificadress") String specificadress,String province,String city,String county);

}
