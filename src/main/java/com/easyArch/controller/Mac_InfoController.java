//package com.easyArch.controller;
//
//import com.easyArch.entity.Mac_info;
//import com.easyArch.mapper.AddressDao;
//import com.easyArch.mapper.ColorDao;
//import com.easyArch.util.ControllerUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//public class Mac_InfoController {
//    @Autowired
//    AddressDao addressDao;
//    @Autowired
//    ColorDao colorDao;
//
//    @ResponseBody
//    @RequestMapping(value = "Mac_info"
//            , produces = "application/json;charset=utf-8"
//            , method = RequestMethod.POST)
//    public String insertMac_info(@RequestBody Mac_info mac_info){
//        if(mac_info!=null){
//            String address=mac_info.getAddress();
//            String specific_address=mac_info.getSpecific_address();
//            String pic_address=mac_info.getPic_address();
//            String location=mac_info.getLocation();
//            String mac_address=mac_info.getMac_address();
//            String red=mac_info.getRed();
//            String green=mac_info.getGreen();
//
//
//            addressDao.insertMac_info(specific_address,pic_address,location,mac_address);
//
//            colorDao.insertColor(red,green,mac_address);
//
//            List<Map<String, String>>strlist= ControllerUtil.resolution(address);
//            String city=null;
//            String county=null;
//            String street=null;
//            for (int i = 0; i <strlist.size() ; i++) {
//                if (strlist.get(i).get("city")!=null&&strlist.get(i).get("county")!=null&&strlist.get(i).get("town")!=null){
//                    city=strlist.get(i).get("city");
//                    county=strlist.get(i).get("county");
//                    street=strlist.get(i).get("town");
//                    break;
//                }
//            }
//
//            if (city!=null&&county!=null&&street!=null){
//                addressDao.insertAddress(city,county,street,mac_address);
//            }
//
//        }
//        return "F";
//    }
//}
