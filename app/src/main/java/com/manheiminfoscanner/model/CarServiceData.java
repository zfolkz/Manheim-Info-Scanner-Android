package com.manheiminfoscanner.model;

import java.util.List;

/**
 * Created by zfolkz on 1/8/2017 AD.
 */

public class CarServiceData {
    private static CarServiceData ourInstance = new CarServiceData();
    private List<CarDetailData> GetVehicleDetailsByVehicleResult;
    private List<CarDetailData> GetVehicleDetailsByLotResult;
    private List<CarDetailData> GetVehicleDetailsByRegResult;


    public List<CarDetailData> getGetVehicleDetailsByVehicleResult() {
        return GetVehicleDetailsByVehicleResult;
    }

    public void setGetVehicleDetailsByVehicleResult(List<CarDetailData> getVehicleDetailsByVehicleResult) {
        GetVehicleDetailsByVehicleResult = getVehicleDetailsByVehicleResult;
    }

    public List<CarDetailData> getGetVehicleDetailsByLotResult() {
        return GetVehicleDetailsByLotResult;
    }

    public void setGetVehicleDetailsByLotResult(List<CarDetailData> getVehicleDetailsByLotResult) {
        GetVehicleDetailsByLotResult = getVehicleDetailsByLotResult;
    }

    public List<CarDetailData> getGetVehicleDetailsByRegResult() {
        return GetVehicleDetailsByRegResult;
    }

    public void setGetVehicleDetailsByRegResult(List<CarDetailData> getVehicleDetailsByRegResult) {
        GetVehicleDetailsByRegResult = getVehicleDetailsByRegResult;
    }

    public static CarServiceData getInstance() {
        return ourInstance;
    }

    private CarServiceData() {

    }
}
