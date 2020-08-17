package com.easyArch.mapper;

import com.easyArch.entity.Color1;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ColorDao {

    /**
     *
     * @param mac_address
     * @return
     */
    Color1 selectColor(String mac_address);

    /**
     * 设置绿色和红色警报
     * @param green
     * @param red
     */
    void updateColor(String green,String red,String mac_address);
}
