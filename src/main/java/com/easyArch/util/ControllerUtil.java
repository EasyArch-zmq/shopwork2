package com.easyArch.util;

import com.alibaba.fastjson.JSON;
import com.easyArch.entity.*;
import com.easyArch.mapper.Date_TimeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

public class ControllerUtil {

    public static String[] slipAddress(String userAddress) {
        String[] str;
        str = userAddress.split(",");
        return str;
    }

    public static String[] slipDate(String date) {
        String[] str;
        str = date.split("-");
        return str;
    }

    public static String[] slipDate2(String date) {
        String[] str;
        str = date.split(" ");
        return str;
    }

    public static String[] slipDate3(String str3) {
        String[] str;
        str = str3.split(":");
        return str;
    }
    public static String[] slipDate4(String str3) {
        String[] str;
        str = str3.split(" :");
        return str;
    }

    /**
     * 过滤数据，给数据库返回空的数据添加0值
     * @param list
     * @param time
     * @return
     */
    public static List<DateAndNumber> filterTowHour(List<DateAndNumber> list, String time) {
        List<DateAndNumber> list1 = new ArrayList<>();
        Integer timeT = new Integer(time);
        Integer temp = 1;
        Integer i = 1;
        System.out.println(list);
        for (DateAndNumber dateAndNumber : list) {
            Integer temInt = new Integer(dateAndNumber.getTime());
//            System.out.println("当前时间是："+temInt);
            for (i = temp; i < temInt; i +=2) {
                DateAndNumber dateAndNumberTem = new DateAndNumber();
                Integer time1=i+1;
                if (time1 < 10) {
                    dateAndNumberTem.setTime("0" + time1);
                } else {
                    dateAndNumberTem.setTime(time1.toString());
                }
                dateAndNumberTem.setNum(0);
//                System.out.println("新加数据："+dateAndNumberTem);
                list1.add(dateAndNumberTem);
            }
            temp = temInt;
            DateAndNumber dateAndNumberTem = new DateAndNumber();
            dateAndNumberTem.setNum(dateAndNumber.getNum());
            Integer time2=temInt+1;
            if (time2 < 10) {
                dateAndNumberTem.setTime("0" + time2);
            } else {
                dateAndNumberTem.setTime(time2.toString());
            }

            list1.add(dateAndNumberTem);
            temp+=2;
        }
        Integer j = 1;
        for (j = temp; j <= timeT; j+=2) {
            DateAndNumber dateAndNumberTem2;
            dateAndNumberTem2 = new DateAndNumber();
            Integer time3=j+1;
            if (time3 < 10) {
                dateAndNumberTem2.setTime("0" + time3);
            } else {
                dateAndNumberTem2.setTime(time3.toString());
            }
            dateAndNumberTem2.setNum(0);
//            System.out.println("新加数据："+dateAndNumberTem2);
            list1.add(dateAndNumberTem2);
        }

        return list1;
    }
    public static List<DateAndNumber> TowHourSortUtil(List<DateAndNumber> list, String time) {
        //14:42:00
        List<DateAndNumber> list1 = new ArrayList<>();
        String[] str2 = ControllerUtil.slipDate3(time);
        Integer timeT = new Integer(str2[0]);//14
        Integer temp = 1;
        Integer i;
        System.out.println(list);
        for (DateAndNumber dateAndNumber : list) {
            Integer temInt = new Integer(dateAndNumber.getTime());//09
//            System.out.println("当前时间是："+temInt);
            for (i = temp; i < temInt; i +=2) {
                DateAndNumber dateAndNumberTem = new DateAndNumber();
                if(i==(timeT-1)){
                    dateAndNumberTem.setTime(timeT+":00:00-"+time);
                } else {
                    dateAndNumberTem.setTime((i+1)+":00:00-"+(i+2)+":00:00");
                }
                dateAndNumberTem.setNum(0);
//                System.out.println("新加数据："+dateAndNumberTem);
                list1.add(dateAndNumberTem);
            }
            DateAndNumber dateAndNumberTem = new DateAndNumber();
            if(temInt==(timeT-1)){
                dateAndNumberTem.setTime(timeT+":00:00-"+time);
            } else {
                dateAndNumberTem.setTime((temInt+1)+":00:00-"+(temInt+2)+":00:00");
            }
            dateAndNumberTem.setNum(dateAndNumber.getNum());
            temp = temInt;
            list1.add(dateAndNumberTem);
            temp+=2;
        }
        Integer j;
        for (j = temp; j <= timeT; j+=2) {
            DateAndNumber dateAndNumberTem2;
            dateAndNumberTem2 = new DateAndNumber();
            if(j==(timeT-1)){
                dateAndNumberTem2.setTime(timeT+":00:00-"+time);
            } else {
                dateAndNumberTem2.setTime((j+1)+":00:00-"+(j+2)+":00:00");
            }
            dateAndNumberTem2.setNum(0);
//            System.out.println("新加数据："+dateAndNumberTem2);
            list1.add(dateAndNumberTem2);
        }

        return list1;
    }

    public static List<DateAndNumber> TowHourSameMacUtil(List<DateAndNumber> list, String time1,String time2) {
        //14:42:00
        List<DateAndNumber> list1 = new ArrayList<>();
        String[] str2 = ControllerUtil.slipDate3(time2);
        String[] str1 = ControllerUtil.slipDate3(time1);
        Integer timeT2 = new Integer(str2[0]);//截止时间
        Integer timeT1 =new Integer(str1[0]);//开始时间
        Integer temp = timeT1;//奇数或者偶数
        int n=0;
        Integer j=0;
        for ( j= 0; j < list.size(); j++) {
            Integer temInt = new Integer(list.get(j).getTime());
            if(j==0){
                if(timeT1<temInt-1){
                    if (timeT1 % 2 == 0){
                        DateAndNumber dateAndNumberTem = new DateAndNumber();
                        dateAndNumberTem.setTime(time1 +"-"+ (timeT1+2)+":00:00");
                        dateAndNumberTem.setNum(0);
                        list1.add(dateAndNumberTem);
                        timeT1++;
                        n++;
                    }
                    if (timeT1 % 2!=0){
                        if (n==0){
                            DateAndNumber dateAndNumberTem = new DateAndNumber();
                            dateAndNumberTem.setTime(time1 +"-"+ (timeT1+1)+":00:00");
                            dateAndNumberTem.setNum(0);
                            list1.add(dateAndNumberTem);
                            timeT1+=2;
                            for (int i = timeT1; i <temInt; i+=2) {
                                DateAndNumber dateAndNumberTem1 = new DateAndNumber();
                                dateAndNumberTem1.setTime((i+1)+":00:00-" + (i+3)+":00:00");
                                dateAndNumberTem1.setNum(0);
                                list1.add(dateAndNumberTem1);
                            }
                            n++;
                        }else {
                            for (int i = timeT1; i <temInt; i+=2) {
                                DateAndNumber dateAndNumberTem1 = new DateAndNumber();
                                dateAndNumberTem1.setTime((i+1)+":00:00-" + (i+3)+":00:00");
                                dateAndNumberTem1.setNum(0);
                                list1.add(dateAndNumberTem1);
                            }
                        }
                    }
                }
                else if(timeT1==temInt-1){
                    DateAndNumber dateAndNumberTem = new DateAndNumber();
                    dateAndNumberTem.setTime(time1 +"-"+ (timeT1+2)+":00:00");
                    dateAndNumberTem.setNum(0);
                    list1.add(dateAndNumberTem);
                    n++;
                }
                if(n==0){
                    //取得数据的时间
                    DateAndNumber dateAndNumberTem = new DateAndNumber();
                    Integer t1 = temInt + 1;
                    dateAndNumberTem.setTime(time1 + "-" + t1 + ":00:00");
                    dateAndNumberTem.setNum(0);
                    list1.add(dateAndNumberTem);
                    DateAndNumber dateAndNumberTem1 = new DateAndNumber();
                    dateAndNumberTem1.setTime(t1 + ":00:00-" + (t1 + 2) + ":00:00");
                    dateAndNumberTem1.setNum(list.get(j).getNum());
                    list1.add(dateAndNumberTem1);
                    n++;
                    temp = temInt;
                    temp+=2;
                }else {
                    //取得数据的时间
                    DateAndNumber dateAndNumberTem = new DateAndNumber();
                    Integer t1 = temInt + 1;
                    dateAndNumberTem.setTime(t1 + ":00:00-" + (t1 + 2) + ":00:00");
                    dateAndNumberTem.setNum(list.get(j).getNum());
                    list1.add(dateAndNumberTem);
                    temp = temInt;
                    temp+=2;
                }
            }else {
                if(j==list.size()-1&&temInt+3>timeT2){
                    //取得数据的时间
                    DateAndNumber dateAndNumberTem = new DateAndNumber();
                    Integer t1 = temInt + 1;
                    dateAndNumberTem.setTime(t1 + ":00:00-" + time2);
                    dateAndNumberTem.setNum(list.get(j).getNum());
                    list1.add(dateAndNumberTem);
                    temp=temInt;
                    temp+=2;
                }else {
                    for (int i = temp; i <temInt; i+=2) {
                        DateAndNumber dateAndNumberTem = new DateAndNumber();
                        dateAndNumberTem.setTime((i+1)+":00:00-" + (i+3)+":00:00");
                        dateAndNumberTem.setNum(0);
                        list1.add(dateAndNumberTem);
                    }
                    //取得数据的时间
                    DateAndNumber dateAndNumberTem = new DateAndNumber();
                    Integer t1 = temInt + 1;
                    dateAndNumberTem.setTime(t1 + ":00:00-" + (t1 + 2) + ":00:00");
                    dateAndNumberTem.setNum(list.get(j).getNum());
                    list1.add(dateAndNumberTem);
                    temp = temInt;
                    temp+=2;
                }
            }
        }
        //处理完毕list，或者list为空
        Integer k;
        if (n==0&&timeT1<=timeT2-2) {
            if (timeT1 % 2 == 0){
                DateAndNumber dateAndNumberTem = new DateAndNumber();
                dateAndNumberTem.setTime(time1 +"-"+ (timeT1+2)+":00:00");
                dateAndNumberTem.setNum(0);
                list1.add(dateAndNumberTem);
                timeT1++;
                temp=timeT1;
                n++;
            }
            else {
                DateAndNumber dateAndNumberTem = new DateAndNumber();
                dateAndNumberTem.setTime(time1 +"-"+ (timeT1+1)+":00:00");
                dateAndNumberTem.setNum(0);
                list1.add(dateAndNumberTem);
                n++;
                temp=timeT1;
            }

        }
        if(n!=0){
            System.out.println("temp: "+temp);
            for (k = temp; k <timeT2; k+=2) {
                DateAndNumber dateAndNumberTem2;
                dateAndNumberTem2 = new DateAndNumber();
                if(k==(timeT2-1)){
                    dateAndNumberTem2.setTime(timeT2+":00:00-"+time2);
                } else {
                    dateAndNumberTem2.setTime((k+1)+":00:00-"+(k+3)+":00:00");
                }
                dateAndNumberTem2.setNum(0);
//            System.out.println("新加数据："+dateAndNumberTem2);
                list1.add(dateAndNumberTem2);
            }
        }
        System.out.println("n的值为： "+n);
        return list1;
    }


    public static List<YanGan>filterOneHour(List<YanGan> list,String time){
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

//    public static List<WebWeekList> getSortWeek(List<Week_withList>list) {
//        List<List<IntWeekNum>>listList;
//        for (Week_withList weekWithList:list){
//            for (int i = 0; i < 7; i++) {
//                IntWeekNum intWeekNum=new IntWeekNum();
//                intWeekNum.setNum(weekWithList.getList().get(i).getNum());
//                intWeekNum.setWeek(weekWithList.getWeek());
//
//            }
//        }
//
//    }

    /**
     * 解析客户端传来的数据，拿到boxid和yangan的值
     * @param info
     * @return
     */
    public static String[] getInfo(String info){
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
        String regex = "(?<city>[^市]+市|.+自治区)(?<county>[^县]+县|.+区|.+镇|.+局)?(?<town>[^区]+区|.+镇|.+路|.+街|.+道)?(?<data>.*)";
        Matcher m = compile(regex).matcher(address);
        String city, county, town, data;
        List<Map<String, String>> table = new ArrayList<>();
        Map<String, String> row;
        while (m.find()) {
            row = new HashMap<>();
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
    /* 根据日期 找到对应日期的 星期
     */
    public static String getDayOfWeekByDate(String date) {
        String dayOfweek = "-1";
        try {
            SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = myFormatter.parse(date);
            SimpleDateFormat formatter = new SimpleDateFormat("E");
            String str = formatter.format(myDate);
            dayOfweek = str;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return dayOfweek;
    }

    public static List<Mac_Num>listCustomSort(List<Mac_Num>list1){
        List<Mac_Num>list;
        list=list1;
        Collections.sort(list, new Comparator<Mac_Num>() {
            // 按年龄从大到小排序
            @Override
            public int compare(Mac_Num p1, Mac_Num p2) {
                return p1.getNum() == p2.getNum() ? 0 : (p1.getNum() < p2.getNum() ? 1 : -1);
            }
        });
        return list;
    }
    public static List<DateAndNumber>listTimeSort(List<DateAndNumber>list1){
        List<DateAndNumber>list;
        list=list1;
        Collections.sort(list, new Comparator<DateAndNumber>() {
            // 按年龄从大到小排序
            @Override
            public int compare(DateAndNumber p1, DateAndNumber p2) {
                return p1.getNum() == p2.getNum() ? 0 : (p1.getNum() < p2.getNum() ? 1 : -1);
            }
        });
        return list;
    }
    public static List<IntWeekNum>listWeekSort(List<IntWeekNum>list1){
        List<IntWeekNum>list;
        list=list1;
        Collections.sort(list, new Comparator<IntWeekNum>() {
            // 按年龄从大到小排序
            @Override
            public int compare(IntWeekNum p1, IntWeekNum p2) {
                return p1.getNum() == p2.getNum() ? 0 : (p1.getNum() < p2.getNum() ? 1 : -1);
            }
        });
        return list;
    }

    public static void main(String[] args) {
//        String[] placeList = new String[]{"天津市津南区渌水道天津职业师范大学", "广西壮族自治区梧州市藤县蒙江镇189号", "浙江省台州市玉环县xxxx", "湖北省潜江市潜江经济开发区xxx", "湖北省潜江市江汉石油管理局", "湖北省天门市马湾镇xxx"};
//        for (String place : placeList) {
//            System.out.println(resolution(place));
//        }
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");//设置日期格式
//
//        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
//
//
//        String u = "#A#001111#176";
//        Matcher matcher = compile("\\d{2,}").matcher(u);
//        int i = 0;
//        while (matcher.find()){
//            System.out.println(matcher.group());
//            i++;
//        }



    }
}
