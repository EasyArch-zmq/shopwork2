package com.easyArch.util;

import com.easyArch.entity.DateAndNumber;
import com.easyArch.entity.YanGan;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

public class ControllerUtil {
    public String[] slipAddress(String userAddress) {
        String[] str;
        str = userAddress.split(",");
        return str;
    }

    public String[] slipDate(String date) {
        String[] str;
        str = date.split("-");
        return str;
    }

    public String[] slipDate2(String date) {
        String[] str;
        str = date.split(" ");
        return str;
    }

    public String[] slipDate3(String str3) {
        String[] str;
        str = str3.split(":");
        return str;
    }

    /**
     * 过滤数据，给数据库返回空的数据添加0值
     * @param list
     * @param time
     * @return
     */
    public List<DateAndNumber> filterTowHour(List<DateAndNumber> list, String time) {
        List<DateAndNumber> list1 = new ArrayList<>();
        Integer timeT = new Integer(time);
        Integer temp = 0;
        Integer i = 0;
        System.out.println(list);
        for (DateAndNumber dateAndNumber : list) {
            Integer temInt = new Integer(dateAndNumber.getTime());
//            System.out.println("当前时间是："+temInt);
            for (i = temp; i < temInt; i += 2) {
                DateAndNumber dateAndNumberTem = new DateAndNumber();
                if (i < 10) {
                    dateAndNumberTem.setTime("0" + i);
                } else {
                    dateAndNumberTem.setTime(i.toString());
                }
                dateAndNumberTem.setNum(0);
//                System.out.println("新加数据："+dateAndNumberTem);
                list1.add(dateAndNumberTem);
            }
            temp = temInt;
            list1.add(dateAndNumber);
            temp += 2;
        }
        Integer j = 0;
        for (j = temp; j <= timeT; j += 2) {
            DateAndNumber dateAndNumberTem2;
            dateAndNumberTem2 = new DateAndNumber();
            if (j < 10) {
                dateAndNumberTem2.setTime("0" + j);
            } else {
                dateAndNumberTem2.setTime(j.toString());
            }
            dateAndNumberTem2.setNum(0);
//            System.out.println("新加数据："+dateAndNumberTem2);
            list1.add(dateAndNumberTem2);
        }

        return list1;
    }

    public List<YanGan>filterOneHour(List<YanGan> list,String time){
        List<YanGan> list1 = new ArrayList<>();
        Integer timeT = new Integer(time);
        Integer temp = 0;
        Integer i;
        System.out.println(list);
        for (YanGan yanGan : list) {
            Integer temInt = new Integer(yanGan.getTime());
            for (i = temp; i < temInt; i ++) {
                YanGan yanGan1 = new YanGan();
                if (i < 10) {
                    yanGan1.setTime("0" + i);
                } else {
                    yanGan1.setTime(i.toString());
                }
                yanGan1.setYanGan(0);
                list1.add(yanGan1);
            }
            temp = temInt;
            list1.add(yanGan);
            temp ++;
        }
        Integer j;
        for (j = temp; j <=timeT; j ++) {
            YanGan yanGan2;
            yanGan2 = new YanGan();
            if (j < 10) {
                yanGan2.setTime("0" + j);
            } else {
                yanGan2.setTime(j.toString());
            }
            yanGan2.setYanGan(0);
            list1.add(yanGan2);
        }

        return list1;
    }

    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }



    /**
     * 解析客户端传来的数据，拿到boxid和yangan的值
     * @param info
     * @return
     */
    public String[] getInfo(String info){
        String[]strings = new String[2];
        Matcher matcher = compile("\\d{2,}").matcher(info);
        int i = 0;
        while (matcher.find()){
            strings[i]=matcher.group();
            System.out.println(matcher.group());
            i++;
        }
        return strings;
    }


    /**
     * 分割APP传过来的地址
     *
     * @param address
     * @return
     */
    public static List<Map<String, String>> resolution(String address) {
        String regex = "((?<province>[^省]+省|.+自治区)|上海|北京|天津|重庆)(?<city>[^市]+市|.+自治区)(?<county>[^县]+县|.+区|.+镇|.+局)?(?<town>[^区]+区|.+镇)?(?<data>.*)";
        Matcher m = compile(regex).matcher(address);
        String province, city, county, town, data;
        List<Map<String, String>> table = new ArrayList<>();
        Map<String, String> row;
        while (m.find()) {
            row = new HashMap<>();
            province = m.group("province");
            row.put("province", province == null ? "" : province.trim());
            city = m.group("city");
            row.put("city", city == null ? "" : city.trim());
            county = m.group("county");
            row.put("county", county == null ? "" : county.trim());
            town = m.group("town");
            row.put("town", town == null ? "" : town.trim());
            data = m.group("data");
            row.put("data", data == null ? "" : data.trim());
            table.add(row);
        }
        return table;
    }

    public static void main(String[] args) {
        String[] placeList = new String[]{"广西省梧州市藤县蒙江镇18街68号二楼", "上海上海市金山区xxxxx", "浙江省台州市玉环县xxxx", "湖北省潜江市潜江经济开发区xxx", "湖北省潜江市江汉石油管理局", "湖北省天门市马湾镇xxx"};
        for (String place : placeList) {
            System.out.println(resolution(place));
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式

        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间


        String u = "#A#001111#176";
        Matcher matcher = compile("\\d{2,}").matcher(u);
        int i = 0;
        while (matcher.find()){
            System.out.println(matcher.group());
            i++;
        }


    }
}
