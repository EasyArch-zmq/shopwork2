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
    List<String>city();

    /**
     *  县
     * @return
     */
    List<String>county();

    /**
     * 具体
     * @return
     */
    List<String>specificAddress();

    List<BoxidAndAddress> selectBoxids(@Param("specificadress") String specificadress);

    List<String>provinceId(String province);

    List<String>cityId(String province,String city);

    List<String>countyId(String province,String city,String county);
}
