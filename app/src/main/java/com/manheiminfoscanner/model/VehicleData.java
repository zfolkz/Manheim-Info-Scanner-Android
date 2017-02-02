package com.manheiminfoscanner.model;

/**
 * Created by zfolkz on 12/19/2016 AD.
 */
public class VehicleData {

    private static VehicleData ourInstance = new VehicleData();

    public static VehicleData getInstance() {
        return ourInstance;
    }

    private VehicleData() {
    }

    String vehicleNo;

    public String getAuctionCode() {
        return AuctionCode;
    }

    public void setAuctionCode(String auctionCode) {
        AuctionCode = auctionCode;
    }

    String AuctionCode;
    String registration;



    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }
}
