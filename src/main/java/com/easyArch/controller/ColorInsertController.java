package com.easyArch.controller;

import com.easyArch.entity.Color2;
import com.easyArch.mapper.ColorDao;
import com.easyArch.net.SocketHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class ColorInsertController {
    private static final Logger LOGGER= LoggerFactory.getLogger(ColorInsertController.class);

    @Autowired
    ColorDao colorDao;

    @ResponseBody
    @RequestMapping( value = "insertColorValue",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST)
    public void colorInsert(@RequestBody Color2 color){
        if (color!=null){
            String mac_address1=color.getMac_address();
            String REGEX_CHINESE = "[\u4e00-\u9fa5]";
            Pattern pat = Pattern.compile(REGEX_CHINESE);
            Matcher mat = pat.matcher(mac_address1);
            String mac_address=mat.replaceAll("");
            colorDao.updateColor(color.getGreen(),color.getRed(),mac_address);
        }

    }
}
