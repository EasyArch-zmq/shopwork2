package com.easyArch.service;

import com.easyArch.entity.Address;

import java.util.List;

public interface ApplyAddressService {

    public List<String> selectCity();

    public List<String> selectCounty(Address address);

    public List<String> selectStreet(Address address);

    public List<String> selectSpecific(Address address);

    public String selectMac_Address(Address address);
}
