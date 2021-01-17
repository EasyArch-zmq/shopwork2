package com.easyArch.service.impl;

import com.easyArch.entity.Color;
import com.easyArch.mapper.ColorDao;
import com.easyArch.service.ColorInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@SuppressWarnings("unchecked")
@Service
public class ColorInsertServiceImpl implements ColorInsertService {
    @Autowired
    ColorDao colorDao;
    @Autowired
    RedisTemplate redisTemplate;
    @Override
    public void insertColorValue(Color color) {
        String mac_address1=color.getMac_address();
        String REGEX_CHINESE = "[\u4e00-\u9fa5]";
        Pattern pat = Pattern.compile(REGEX_CHINESE);
        Matcher mat = pat.matcher(mac_address1);
        String mac_address=mat.replaceAll("");
        color.setMac_address(mac_address);
        colorDao.updateColor(color.getGreen(),color.getRed(),mac_address);
        redisTemplate.opsForValue().set("color@"+mac_address,color,7, TimeUnit.DAYS);

    }
}
