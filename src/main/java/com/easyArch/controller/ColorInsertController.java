package com.easyArch.controller;

import com.easyArch.entity.Color;
import com.easyArch.mapper.ColorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ColorInsertController {

    @Autowired
    ColorDao colorDao;

    @ResponseBody
    @RequestMapping( value = "insertColorValue",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST)
    public void colorInsert(@RequestBody Color color){
        if (color!=null){
            colorDao.updateColor(color.getGreen(),color.getRed());
        }

    }
}
