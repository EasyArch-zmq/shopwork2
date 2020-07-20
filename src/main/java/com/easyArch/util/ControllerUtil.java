package com.easyArch.util;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerUtil {
    public String[] slipAddress(String userAddress){
        String []str;
        str=userAddress.split("，");
        return str;
    }
    public String[] slipDate(String date){
        String []str;
        str=date.split("-");
        return str;
    }
    public String[] slipDate2(String date){
        String []str;
        str=date.split(" ");
        return str;
    }


    /**
     *
     * 分割APP传过来的地址
     * @param address
     * @return
     */
    public static List<Map<String,String>> resolution(String address){
        String regex="((?<province>[^省]+省|.+自治区)|上海|北京|天津|重庆)(?<city>[^市]+市|.+自治区)(?<county>[^县]+县|.+区|.+镇|.+局)?(?<town>[^区]+区|.+镇)?(?<data>.*)";
        Matcher m= Pattern.compile(regex).matcher(address);
        String province,city,county,town,data;
        List<Map<String,String>> table= new ArrayList<>();
        Map<String,String> row;
        while(m.find()){
            row= new HashMap<>();
            province=m.group("province");
            row.put("province", province==null?"":province.trim());
            city=m.group("city");
            row.put("city", city==null?"":city.trim());
            county=m.group("county");
            row.put("county", county==null?"":county.trim());
            town=m.group("town");
            row.put("town", town==null?"":town.trim());
            data=m.group("data");
            row.put("data", data==null?"":data.trim());
            table.add(row);
        }
        return table;
    }

    public static void main(String[] args) {
        String[] placeList = new String[]{"广西省梧州市藤县蒙江镇18街68号二楼","上海上海市金山区xxxxx","浙江省台州市玉环县xxxx","湖北省潜江市潜江经济开发区xxx","湖北省潜江市江汉石油管理局","湖北省天门市马湾镇xxx"};
        for (String place : placeList) {
            System.out.println(resolution(place));
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
    }
}
