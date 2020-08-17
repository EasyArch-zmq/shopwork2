package com.easyArch.mapper;


import com.easyArch.entity.Location_tier;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AddressDao {


    /**
     *  市
     * @return
     */
    List<String>city( );

    /**
     *  县
     * @return
     */
    List<String>county(String city);

    List<String>town(String city,String county);

    List<String>street(String city,String county,String town);

    /**
     *
     * @param city
     * @param county
     * @param town
     * @param street
     * @return
     */
    List<String>specificAddress(String city,String county,String town,String street);


    List<String> select_construction(@Param("specific_address") String specific_address, String city, String county, String town, String street);

    Location_tier selectLocation_tier(String mac_address);

    /**
     *
     * @param specific_address
     * @param city
     * @param county
     * @param town
     * @param street
     * @param construction
     * @return
     */
    List<String>select_mac(@Param("specific_address") String specific_address, String city, String county, String town, String street,String construction);


}
