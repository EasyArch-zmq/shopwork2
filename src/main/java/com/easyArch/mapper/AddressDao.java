package com.easyArch.mapper;


import com.easyArch.entity.Address;
import com.easyArch.entity.Location_tier;

import com.easyArch.entity.Mac_Loc;
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

    List<String>street(String city,String county);

    /**
     *
     * @param city
     * @param county
     * @param street
     * @return
     */
    List<String>specificAddress(String city,String county,String street);


    List<String> select_construction(@Param("specific_address") String specific_address, String city, String county,String street);

    Location_tier selectLocation_tier(String mac_address);

    /**
     *
     * @param specific_address
     * @param city
     * @param county
     * @param street
     * @param construction
     * @return
     */
    List<String>select_mac(@Param("specific_address") String specific_address, String city, String county,String street,String construction);

    List<String>select_mac2(@Param("specific_address") String specific_address, String city, String county,String street);

    List<Mac_Loc>select_ma_lo(String city, String county,String street, @Param("specific_address") String specific_address);

    void insertMac_info(String specific_address,String pic_address,String location,String mac_address);

    void insertAddress(String city,String county,String street,String mac_address);
    List<Address>selectAddress();


}
