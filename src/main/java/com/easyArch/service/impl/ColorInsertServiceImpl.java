package com.easyArch.service.impl;

import com.easyArch.entity.Color2;
import com.easyArch.mapper.ColorDao;
import com.easyArch.service.ColorInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ColorInsertServiceImpl implements ColorInsertService {
    @Autowired
    ColorDao colorDao;
    @Override
    public void insertColorValue(Color2 color) {
        String mac_address1=color.getMac_address();
        String REGEX_CHINESE = "[\u4e00-\u9fa5]";
        Pattern pat = Pattern.compile(REGEX_CHINESE);
        Matcher mat = pat.matcher(mac_address1);
        String mac_address=mat.replaceAll("");
        colorDao.updateColor(color.getGreen(),color.getRed(),mac_address);
    }
}
