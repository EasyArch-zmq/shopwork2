package com.easyArch.controller;

import com.easyArch.entity.Temp;
import com.easyArch.mapper.Time_InfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
public class Test {
    @Autowired
    Time_InfoDao time_infoDao;


    @ResponseBody
    @RequestMapping(value = "test",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String test(@RequestBody Temp temp){
        if (temp!=null){
            //设置日期格式
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //获取日期
            String mytime=df.format(new Date());
            String boxid=temp.getMac_address();
            Integer yangan=new Integer(temp.getYangan());
            time_infoDao.insertInfo(boxid,mytime,yangan);
            return "T";
        }

        return "f";
    }
}
