package com.easyArch.mapper;

import com.easyArch.entity.Color;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ColorDao {
    /**
     * 查找范围值
     * @return
     */
    Color selectColor();

    /**
     * 设置绿色和红色警报
     * @param green
     * @param red
     */
    void updateColor(String green,String red);
}
