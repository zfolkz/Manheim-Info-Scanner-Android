package com.manheiminfoscanner.model;

/**
 * Created by zfolkz on 12/1/16 AD.
 */
public class BarcodeData {
    String registration;

    BarcodeData(String registration){
        this.registration = registration;

    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }
}
