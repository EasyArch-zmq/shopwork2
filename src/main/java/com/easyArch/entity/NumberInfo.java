package com.easyArch.entity;


import java.io.Serializable;

public class NumberInfo implements Serializable {
      private String specificadress;
      private Integer number;
      public String getSpecificadress() {
            return specificadress;
      }

      public void setSpecificadress(String specificadress) {
            this.specificadress = specificadress;
      }

      public Integer getNumber() {
            return number;
      }

      public void setNumber(Integer number) {
            this.number = number;
      }



}
