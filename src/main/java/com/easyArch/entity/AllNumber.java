package com.easyArch.entity;



import java.io.Serializable;
import java.util.List;

/**
 * 此数据作为返回前端的总数据
 */

public class AllNumber implements Serializable {
    private String userAddress;

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public List<Construction_inAll> getList_inAll() {
        return list_inAll;
    }

    public void setList_inAll(List<Construction_inAll> list_inAll) {
        this.list_inAll = list_inAll;
    }

    private List<Construction_inAll> list_inAll;
}
