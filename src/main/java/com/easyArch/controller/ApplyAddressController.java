package com.easyArch.controller;

import com.easyArch.entity.*;
import com.easyArch.service.ApplyAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApplyAddressController {
    private static final Logger LOGGER= LoggerFactory.getLogger(ApplyAddressController.class);

    @Autowired
    private ApplyAddressService addressService;


    /**
     * 返回地址下拉框数据之市
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "CityAddress",method = RequestMethod.POST)
    public List<String> selectCAddress(){
        return addressService.selectCity();
        }

    /**
     * 返回地址下拉框数据之县
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "CountyAddress",method = RequestMethod.POST)
    public List<String> selectCoAddress(@RequestBody Address address) {
    return addressService.selectCounty(address);
    }

    /**
     * 返回地址下拉框数据之街
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "StreetAddress",method = RequestMethod.POST)
    public List<String> selectStAddress(@RequestBody Address address) {
       return addressService.selectStreet(address);
    }
    /**
     * 返回地址下拉框数据之具体地址提示
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "SpecificAddress",method = RequestMethod.POST)
    public List<String> seleceSAddress(@RequestBody Address address) {
       return addressService.selectSpecific(address);
    }
    @ResponseBody
    @RequestMapping(value = "Mac_Address"
            , produces = "application/json;charset=utf-8"
            ,method = RequestMethod.POST)
    public String mac_Address(@RequestBody Address address) {
       return addressService.selectMac_Address(address);
    }



}
