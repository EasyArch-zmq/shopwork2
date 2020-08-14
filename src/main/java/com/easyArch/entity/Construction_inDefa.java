package com.easyArch.entity;


import lombok.Data;

import java.util.List;

@Data
public class Construction_inDefa {
    private String construction;
    private String picture_url;
    List<Info_inCons>  info_inCons_List;


}
