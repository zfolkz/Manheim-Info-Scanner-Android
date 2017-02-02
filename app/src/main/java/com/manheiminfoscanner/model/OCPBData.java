package com.manheiminfoscanner.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zfolkz on 12/19/2016 AD.
 */

public class OCPBData implements Parcelable {

    private static OCPBData ourInstance = new OCPBData();
    private List<GetOCPBResultBean> GetOCPBResult;

    public static OCPBData getInstance() {
        return ourInstance;
    }

    private OCPBData() {

    }

    public List<GetOCPBResultBean> getGetOCPBResult() {
        return GetOCPBResult;
    }

    public void setGetOCPBResult(List<GetOCPBResultBean> GetOCPBResult) {
        this.GetOCPBResult = GetOCPBResult;
    }


    public static class GetOCPBResultBean {
        /**
         * AccidentCar : โปรดตรวจสอบก่อนประมูล
         * AuctionCode : X1FBK549
         * BookService : มี
         * BuildYear : 2012
         * CatalogueDesc : โอนผ่านบริษัทผู้ขาย ค่าโอน 2,000
         * ChasisNumber : MR0EZ39G708047574
         * Colour : ขาว
         * DateFirstReg : 06/03/2012
         * EngineCapacity : 3000.0
         * EngineCapacityUnit : ซีซี
         * EngineManufacturer : Toyota
         * EngineNumber : 1KD6983874
         * FloodCar : ไม่สามารถระบุได้
         * FuelType : ดีเซล
         * IsMatchEngineCapacity : false
         * Km : 153263
         * Lot : 1001
         * Make : โตโยต้า
         * Manufacturer : โตโยต้ามอเตอร์ คอปเปอร์เรชั่น
         * OdoMeter : false
         * OwnerNumber : 1 (กรุณาตรวจสอบชื่อ สกุลตามสำเนาเล่ม)
         * Registration : ฆฉ-8230
         * Seller : 0000100212
         * SellerAddress : 72/8-9 ถนนสาทรเหนือ แขวงสีลม เขตบางรัก กรุงเทพฯ 10500
         * SellerName : บริษัท คาเธ่ย์ลีสแพลน จำกัด (มหาชน)
         * SellingCategory : PU
         * SystemLanguage : LO
         * Vehicle : 000000000001163270
         * VehicleType : รถยนต์นั่งส่วนบุคคลไม่เกินเจ็ดคน (รย.1)
         */

        private String AccidentCar;
        private String AuctionCode;
        private String BookService;
        private String BuildYear;
        private String CatalogueDesc;
        private String ChasisNumber;
        private String Colour;
        private String DateFirstReg;
        private double EngineCapacity;
        private String EngineCapacityUnit;
        private String EngineManufacturer;
        private String EngineNumber;
        private String FloodCar;
        private String FuelType;
        private boolean IsMatchEngineCapacity;
        private int Km;
        private int Lot;
        private String Make;
        private String Manufacturer;
        private boolean OdoMeter;
        private String OwnerNumber;
        private String Registration;
        private String Seller;
        private String SellerAddress;
        private String SellerName;
        private String SellingCategory;
        private String SystemLanguage;
        private String Vehicle;
        private String VehicleType;

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

        public String getBookService() {
            return BookService;
        }

        public void setBookService(String BookService) {
            this.BookService = BookService;
        }

        public String getBuildYear() {
            return BuildYear;
        }

        public void setBuildYear(String BuildYear) {
            this.BuildYear = BuildYear;
        }

        public String getCatalogueDesc() {
            return CatalogueDesc;
        }

        public void setCatalogueDesc(String CatalogueDesc) {
            this.CatalogueDesc = CatalogueDesc;
        }

        public String getChasisNumber() {
            return ChasisNumber;
        }

        public void setChasisNumber(String ChasisNumber) {
            this.ChasisNumber = ChasisNumber;
        }

        public String getColour() {
            return Colour;
        }

        public void setColour(String Colour) {
            this.Colour = Colour;
        }

        public String getDateFirstReg() {
            return DateFirstReg;
        }

        public void setDateFirstReg(String DateFirstReg) {
            this.DateFirstReg = DateFirstReg;
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

        public String getEngineManufacturer() {
            return EngineManufacturer;
        }

        public void setEngineManufacturer(String EngineManufacturer) {
            this.EngineManufacturer = EngineManufacturer;
        }

        public String getEngineNumber() {
            return EngineNumber;
        }

        public void setEngineNumber(String EngineNumber) {
            this.EngineNumber = EngineNumber;
        }

        public String getFloodCar() {
            return FloodCar;
        }

        public void setFloodCar(String FloodCar) {
            this.FloodCar = FloodCar;
        }

        public String getFuelType() {
            return FuelType;
        }

        public void setFuelType(String FuelType) {
            this.FuelType = FuelType;
        }

        public boolean isIsMatchEngineCapacity() {
            return IsMatchEngineCapacity;
        }

        public void setIsMatchEngineCapacity(boolean IsMatchEngineCapacity) {
            this.IsMatchEngineCapacity = IsMatchEngineCapacity;
        }

        public int getKm() {
            return Km;
        }

        public void setKm(int Km) {
            this.Km = Km;
        }

        public int getLot() {
            return Lot;
        }

        public void setLot(int Lot) {
            this.Lot = Lot;
        }

        public String getMake() {
            return Make;
        }

        public void setMake(String Make) {
            this.Make = Make;
        }

        public String getManufacturer() {
            return Manufacturer;
        }

        public void setManufacturer(String Manufacturer) {
            this.Manufacturer = Manufacturer;
        }

        public boolean isOdoMeter() {
            return OdoMeter;
        }

        public void setOdoMeter(boolean OdoMeter) {
            this.OdoMeter = OdoMeter;
        }

        public String getOwnerNumber() {
            return OwnerNumber;
        }

        public void setOwnerNumber(String OwnerNumber) {
            this.OwnerNumber = OwnerNumber;
        }

        public String getRegistration() {
            return Registration;
        }

        public void setRegistration(String Registration) {
            this.Registration = Registration;
        }

        public String getSeller() {
            return Seller;
        }

        public void setSeller(String Seller) {
            this.Seller = Seller;
        }

        public String getSellerAddress() {
            return SellerAddress;
        }

        public void setSellerAddress(String SellerAddress) {
            this.SellerAddress = SellerAddress;
        }

        public String getSellerName() {
            return SellerName;
        }

        public void setSellerName(String SellerName) {
            this.SellerName = SellerName;
        }

        public String getSellingCategory() {
            return SellingCategory;
        }

        public void setSellingCategory(String SellingCategory) {
            this.SellingCategory = SellingCategory;
        }

        public String getSystemLanguage() {
            return SystemLanguage;
        }

        public void setSystemLanguage(String SystemLanguage) {
            this.SystemLanguage = SystemLanguage;
        }

        public String getVehicle() {
            return Vehicle;
        }

        public void setVehicle(String Vehicle) {
            this.Vehicle = Vehicle;
        }

        public String getVehicleType() {
            return VehicleType;
        }

        public void setVehicleType(String VehicleType) {
            this.VehicleType = VehicleType;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.GetOCPBResult);
    }

    protected OCPBData(Parcel in) {
        this.GetOCPBResult = new ArrayList<GetOCPBResultBean>();
        in.readList(this.GetOCPBResult, GetOCPBResultBean.class.getClassLoader());
    }

    public static final Creator<OCPBData> CREATOR = new Creator<OCPBData>() {
        @Override
        public OCPBData createFromParcel(Parcel source) {
            return new OCPBData(source);
        }

        @Override
        public OCPBData[] newArray(int size) {
            return new OCPBData[size];
        }
    };
}
