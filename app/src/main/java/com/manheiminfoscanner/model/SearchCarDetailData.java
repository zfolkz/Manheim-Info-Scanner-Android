package com.manheiminfoscanner.model;

import java.util.List;

/**
 * Created by zfolkz on 1/8/2017 AD.
 */

public class SearchCarDetailData {


    private List<GetVehicleDetailsByLotResultBean> GetVehicleDetailsByLotResult;

    public List<GetVehicleDetailsByLotResultBean> getGetVehicleDetailsByLotResult() {
        return GetVehicleDetailsByLotResult;
    }

    public void setGetVehicleDetailsByLotResult(List<GetVehicleDetailsByLotResultBean> GetVehicleDetailsByLotResult) {
        this.GetVehicleDetailsByLotResult = GetVehicleDetailsByLotResult;
    }

    public static class GetVehicleDetailsByLotResultBean {
        /**
         * AccidentCar :
         * AuctionCode : X1FBK549
         * AuctionDate : 22/04/2015
         * BuildYear : 2012
         * CatalogueDesc_BU : The seller will do the transfer of ownership, Transfer fee 2,000
         * CatalogueDesc_LO : โอนผ่านบริษัทผู้ขาย ค่าโอน 2,000
         * Colour_BU : White
         * Colour_LO : White
         * Desc_BU : Toyota
         * Desc_LO : โตโยต้า
         * DriveDesc_BU : Rear Wheel Drive
         * DriveDesc_LO : ขับเคลื่อนล้อหลัง
         * EngineCapacity : 3000.0
         * EngineCapacityUnit : CC
         * Expr1 : null
         * FloodCar : 0
         * FuelDelivery_BU : Direct Injection
         * FuelDelivery_LO :
         * FuelType_BU : Diesel
         * FuelType_LO : ดีเซล
         * GearBox_BU : Automatic
         * GearBox_LO : ออโตเมติก
         * Gears : 4
         * Grading :
         * InstructionManual :
         * IsMatchColour : false
         * IsMatchFuelType : false
         * Km : 153263
         * LotNumber : 1001
         * Model_BU : Vigo Double Cab 3.0G Prerunner
         * Model_LO : วีโก้ดับเบิลแคป3.0Gพรีรันเนอร์
         * NumberofOwners : 1
         * OdoMeter : false
         * RegState_BU : Bangkok
         * RegState_LO : กรุงเทพมหานคร
         * Registration : ฆฉ-8230
         * StorageLocation : On Nut
         * TitleFlag : Y
         * isPrintTitle :
         */

        private String AccidentCar;
        private String AuctionCode;
        private String AuctionDate;
        private String BuildYear;
        private String CatalogueDesc_BU;
        private String CatalogueDesc_LO;
        private String Colour_BU;
        private String Colour_LO;
        private String Desc_BU;
        private String Desc_LO;
        private String DriveDesc_BU;
        private String DriveDesc_LO;
        private double EngineCapacity;
        private String EngineCapacityUnit;
        private Object Expr1;
        private String FloodCar;
        private String FuelDelivery_BU;
        private String FuelDelivery_LO;
        private String FuelType_BU;
        private String FuelType_LO;
        private String GearBox_BU;
        private String GearBox_LO;
        private String Gears;
        private String Grading;
        private String InstructionManual;
        private boolean IsMatchColour;
        private boolean IsMatchFuelType;
        private int Km;
        private int LotNumber;
        private String Model_BU;
        private String Model_LO;
        private String NumberofOwners;
        private boolean OdoMeter;
        private String RegState_BU;
        private String RegState_LO;
        private String Registration;
        private String StorageLocation;
        private String TitleFlag;
        private String isPrintTitle;

        public String getAccidentCar() {
            return AccidentCar;
        }

        public void setAccidentCar(String AccidentCar) {
            this.AccidentCar = AccidentCar;
        }

        public String getAuctionCode() {
            return AuctionCode;
        }

        public void setAuctionCode(String AuctionCode) {
            this.AuctionCode = AuctionCode;
        }

        public String getAuctionDate() {
            return AuctionDate;
        }

        public void setAuctionDate(String AuctionDate) {
            this.AuctionDate = AuctionDate;
        }

        public String getBuildYear() {
            return BuildYear;
        }

        public void setBuildYear(String BuildYear) {
            this.BuildYear = BuildYear;
        }

        public String getCatalogueDesc_BU() {
            return CatalogueDesc_BU;
        }

        public void setCatalogueDesc_BU(String CatalogueDesc_BU) {
            this.CatalogueDesc_BU = CatalogueDesc_BU;
        }

        public String getCatalogueDesc_LO() {
            return CatalogueDesc_LO;
        }

        public void setCatalogueDesc_LO(String CatalogueDesc_LO) {
            this.CatalogueDesc_LO = CatalogueDesc_LO;
        }

        public String getColour_BU() {
            return Colour_BU;
        }

        public void setColour_BU(String Colour_BU) {
            this.Colour_BU = Colour_BU;
        }

        public String getColour_LO() {
            return Colour_LO;
        }

        public void setColour_LO(String Colour_LO) {
            this.Colour_LO = Colour_LO;
        }

        public String getDesc_BU() {
            return Desc_BU;
        }

        public void setDesc_BU(String Desc_BU) {
            this.Desc_BU = Desc_BU;
        }

        public String getDesc_LO() {
            return Desc_LO;
        }

        public void setDesc_LO(String Desc_LO) {
            this.Desc_LO = Desc_LO;
        }

        public String getDriveDesc_BU() {
            return DriveDesc_BU;
        }

        public void setDriveDesc_BU(String DriveDesc_BU) {
            this.DriveDesc_BU = DriveDesc_BU;
        }

        public String getDriveDesc_LO() {
            return DriveDesc_LO;
        }

        public void setDriveDesc_LO(String DriveDesc_LO) {
            this.DriveDesc_LO = DriveDesc_LO;
        }

        public double getEngineCapacity() {
            return EngineCapacity;
        }

        public void setEngineCapacity(double EngineCapacity) {
            this.EngineCapacity = EngineCapacity;
        }

        public String getEngineCapacityUnit() {
            return EngineCapacityUnit;
        }

        public void setEngineCapacityUnit(String EngineCapacityUnit) {
            this.EngineCapacityUnit = EngineCapacityUnit;
        }

        public Object getExpr1() {
            return Expr1;
        }

        public void setExpr1(Object Expr1) {
            this.Expr1 = Expr1;
        }

        public String getFloodCar() {
            return FloodCar;
        }

        public void setFloodCar(String FloodCar) {
            this.FloodCar = FloodCar;
        }

        public String getFuelDelivery_BU() {
            return FuelDelivery_BU;
        }

        public void setFuelDelivery_BU(String FuelDelivery_BU) {
            this.FuelDelivery_BU = FuelDelivery_BU;
        }

        public String getFuelDelivery_LO() {
            return FuelDelivery_LO;
        }

        public void setFuelDelivery_LO(String FuelDelivery_LO) {
            this.FuelDelivery_LO = FuelDelivery_LO;
        }

        public String getFuelType_BU() {
            return FuelType_BU;
        }

        public void setFuelType_BU(String FuelType_BU) {
            this.FuelType_BU = FuelType_BU;
        }

        public String getFuelType_LO() {
            return FuelType_LO;
        }

        public void setFuelType_LO(String FuelType_LO) {
            this.FuelType_LO = FuelType_LO;
        }

        public String getGearBox_BU() {
            return GearBox_BU;
        }

        public void setGearBox_BU(String GearBox_BU) {
            this.GearBox_BU = GearBox_BU;
        }

        public String getGearBox_LO() {
            return GearBox_LO;
        }

        public void setGearBox_LO(String GearBox_LO) {
            this.GearBox_LO = GearBox_LO;
        }

        public String getGears() {
            return Gears;
        }

        public void setGears(String Gears) {
            this.Gears = Gears;
        }

        public String getGrading() {
            return Grading;
        }

        public void setGrading(String Grading) {
            this.Grading = Grading;
        }

        public String getInstructionManual() {
            return InstructionManual;
        }

        public void setInstructionManual(String InstructionManual) {
            this.InstructionManual = InstructionManual;
        }

        public boolean isIsMatchColour() {
            return IsMatchColour;
        }

        public void setIsMatchColour(boolean IsMatchColour) {
            this.IsMatchColour = IsMatchColour;
        }

        public boolean isIsMatchFuelType() {
            return IsMatchFuelType;
        }

        public void setIsMatchFuelType(boolean IsMatchFuelType) {
            this.IsMatchFuelType = IsMatchFuelType;
        }

        public int getKm() {
            return Km;
        }

        public void setKm(int Km) {
            this.Km = Km;
        }

        public int getLotNumber() {
            return LotNumber;
        }

        public void setLotNumber(int LotNumber) {
            this.LotNumber = LotNumber;
        }

        public String getModel_BU() {
            return Model_BU;
        }

        public void setModel_BU(String Model_BU) {
            this.Model_BU = Model_BU;
        }

        public String getModel_LO() {
            return Model_LO;
        }

        public void setModel_LO(String Model_LO) {
            this.Model_LO = Model_LO;
        }

        public String getNumberofOwners() {
            return NumberofOwners;
        }

        public void setNumberofOwners(String NumberofOwners) {
            this.NumberofOwners = NumberofOwners;
        }

        public boolean isOdoMeter() {
            return OdoMeter;
        }

        public void setOdoMeter(boolean OdoMeter) {
            this.OdoMeter = OdoMeter;
        }

        public String getRegState_BU() {
            return RegState_BU;
        }

        public void setRegState_BU(String RegState_BU) {
            this.RegState_BU = RegState_BU;
        }

        public String getRegState_LO() {
            return RegState_LO;
        }

        public void setRegState_LO(String RegState_LO) {
            this.RegState_LO = RegState_LO;
        }

        public String getRegistration() {
            return Registration;
        }

        public void setRegistration(String Registration) {
            this.Registration = Registration;
        }

        public String getStorageLocation() {
            return StorageLocation;
        }

        public void setStorageLocation(String StorageLocation) {
            this.StorageLocation = StorageLocation;
        }

        public String getTitleFlag() {
            return TitleFlag;
        }

        public void setTitleFlag(String TitleFlag) {
            this.TitleFlag = TitleFlag;
        }

        public String getIsPrintTitle() {
            return isPrintTitle;
        }

        public void setIsPrintTitle(String isPrintTitle) {
            this.isPrintTitle = isPrintTitle;
        }
    }
}
