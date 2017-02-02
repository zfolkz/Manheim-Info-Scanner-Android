package com.manheiminfoscanner.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zfolkz on 11/30/16 AD.
 */
public class CarDetailData implements Parcelable {

    private int position = 0;
    private static CarDetailData ourInstance = new CarDetailData();
    private List<GetVehicleDetailsByVehicleResultBean> GetVehicleDetailsByVehicleResult;
    private List<GetVehicleDetailsByVehicleResultBean> GetVehicleDetailsByLotResult;
    private List<GetVehicleDetailsByVehicleResultBean> GetVehicleDetailsByRegResult;

    public static CarDetailData getInstance() {
        return ourInstance;
    }

    private CarDetailData() {

    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<GetVehicleDetailsByVehicleResultBean> getGetVehicleDetailsByVehicleResult() {
        return GetVehicleDetailsByVehicleResult;
    }

    public void setGetVehicleDetailsByVehicleResult(List<GetVehicleDetailsByVehicleResultBean> GetVehicleDetailsByVehicleResult) {
        this.GetVehicleDetailsByVehicleResult = GetVehicleDetailsByVehicleResult;
    }

    public List<GetVehicleDetailsByVehicleResultBean> getGetVehicleDetailsByLotResult() {
        return GetVehicleDetailsByLotResult;
    }

    public void setGetVehicleDetailsByLotResult(List<GetVehicleDetailsByVehicleResultBean> GetVehicleDetailsByLotResult) {
        this.GetVehicleDetailsByLotResult = GetVehicleDetailsByLotResult;
    }

    public List<GetVehicleDetailsByVehicleResultBean> getGetVehicleDetailsByRegResult() {
        return GetVehicleDetailsByRegResult;
    }

    public void setGetVehicleDetailsByRegResult(List<GetVehicleDetailsByVehicleResultBean> GetVehicleDetailsByRegResult) {
        this.GetVehicleDetailsByRegResult = GetVehicleDetailsByRegResult;
    }


    public static class GetVehicleDetailsByVehicleResultBean implements Parcelable {
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
        private String AuctionLocation_BU;
        private String AuctionLocation_LO;
        private String Body_BU;
        private String Body_LO;
        private String BuildYear;
        private String CatalogueDesc_BU;
        private String CatalogueDesc_LO;
        private String Colour_BU;
        private String Colour_LO;
        private String DateFirstReg;
        private String Desc_BU;
        private String Desc_LO;
        private String Details_BU;
        private String Details_LO;
        private String DriveDesc_BU;
        private String DriveDesc_LO;
        private double EngineCapacity;
        private String EngineCapacityUnit;
        private String RegExpiry;
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

        private String saveDate;


        public String getDateFirstReg() {
            return DateFirstReg;
        }

        public void setDateFirstReg(String dateFirstReg) {
            DateFirstReg = dateFirstReg;
        }

        public String getAuctionLocation_BU() {
            return AuctionLocation_BU;
        }

        public void setAuctionLocation_BU(String auctionLocation_BU) {
            AuctionLocation_BU = auctionLocation_BU;
        }

        public String getAuctionLocation_LO() {
            return AuctionLocation_LO;
        }

        public void setAuctionLocation_LO(String auctionLocation_LO) {
            AuctionLocation_LO = auctionLocation_LO;
        }

        public String getDetails_BU() {
            return Details_BU;
        }

        public void setDetails_BU(String details_BU) {
            Details_BU = details_BU;
        }

        public String getDetails_LO() {
            return Details_LO;
        }

        public void setDetails_LO(String details_LO) {
            Details_LO = details_LO;
        }

        public String getSaveDate() {
            return saveDate;
        }

        public void setSaveDate(String saveDate) {
            this.saveDate = saveDate;
        }

        public String getBody_BU() {
            return Body_BU;
        }

        public void setBody_BU(String body_BU) {
            Body_BU = body_BU;
        }

        public String getBody_LO() {
            return Body_LO;
        }

        public void setBody_LO(String body_LO) {
            Body_LO = body_LO;
        }

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

        public String getRegExpiry() {
            return RegExpiry;
        }

        public void setRegExpiry(String regExpiry) {
            RegExpiry = regExpiry;
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

        public GetVehicleDetailsByVehicleResultBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.AccidentCar);
            dest.writeString(this.AuctionCode);
            dest.writeString(this.AuctionDate);
            dest.writeString(this.AuctionLocation_BU);
            dest.writeString(this.AuctionLocation_LO);
            dest.writeString(this.Body_BU);
            dest.writeString(this.Body_LO);
            dest.writeString(this.BuildYear);
            dest.writeString(this.CatalogueDesc_BU);
            dest.writeString(this.CatalogueDesc_LO);
            dest.writeString(this.Colour_BU);
            dest.writeString(this.Colour_LO);
            dest.writeString(this.DateFirstReg);
            dest.writeString(this.Desc_BU);
            dest.writeString(this.Desc_LO);
            dest.writeString(this.Details_BU);
            dest.writeString(this.Details_LO);
            dest.writeString(this.DriveDesc_BU);
            dest.writeString(this.DriveDesc_LO);
            dest.writeDouble(this.EngineCapacity);
            dest.writeString(this.EngineCapacityUnit);
            dest.writeString(this.RegExpiry);
            dest.writeString(this.FloodCar);
            dest.writeString(this.FuelDelivery_BU);
            dest.writeString(this.FuelDelivery_LO);
            dest.writeString(this.FuelType_BU);
            dest.writeString(this.FuelType_LO);
            dest.writeString(this.GearBox_BU);
            dest.writeString(this.GearBox_LO);
            dest.writeString(this.Gears);
            dest.writeString(this.Grading);
            dest.writeString(this.InstructionManual);
            dest.writeByte(this.IsMatchColour ? (byte) 1 : (byte) 0);
            dest.writeByte(this.IsMatchFuelType ? (byte) 1 : (byte) 0);
            dest.writeInt(this.Km);
            dest.writeInt(this.LotNumber);
            dest.writeString(this.Model_BU);
            dest.writeString(this.Model_LO);
            dest.writeString(this.NumberofOwners);
            dest.writeByte(this.OdoMeter ? (byte) 1 : (byte) 0);
            dest.writeString(this.RegState_BU);
            dest.writeString(this.RegState_LO);
            dest.writeString(this.Registration);
            dest.writeString(this.StorageLocation);
            dest.writeString(this.TitleFlag);
            dest.writeString(this.isPrintTitle);
            dest.writeString(this.saveDate);
        }

        protected GetVehicleDetailsByVehicleResultBean(Parcel in) {
            this.AccidentCar = in.readString();
            this.AuctionCode = in.readString();
            this.AuctionDate = in.readString();
            this.AuctionLocation_BU = in.readString();
            this.AuctionLocation_LO = in.readString();
            this.Body_BU = in.readString();
            this.Body_LO = in.readString();
            this.BuildYear = in.readString();
            this.CatalogueDesc_BU = in.readString();
            this.CatalogueDesc_LO = in.readString();
            this.Colour_BU = in.readString();
            this.Colour_LO = in.readString();
            this.DateFirstReg = in.readString();
            this.Desc_BU = in.readString();
            this.Desc_LO = in.readString();
            this.Details_BU = in.readString();
            this.Details_LO = in.readString();
            this.DriveDesc_BU = in.readString();
            this.DriveDesc_LO = in.readString();
            this.EngineCapacity = in.readDouble();
            this.EngineCapacityUnit = in.readString();
            this.RegExpiry = in.readString();
            this.FloodCar = in.readString();
            this.FuelDelivery_BU = in.readString();
            this.FuelDelivery_LO = in.readString();
            this.FuelType_BU = in.readString();
            this.FuelType_LO = in.readString();
            this.GearBox_BU = in.readString();
            this.GearBox_LO = in.readString();
            this.Gears = in.readString();
            this.Grading = in.readString();
            this.InstructionManual = in.readString();
            this.IsMatchColour = in.readByte() != 0;
            this.IsMatchFuelType = in.readByte() != 0;
            this.Km = in.readInt();
            this.LotNumber = in.readInt();
            this.Model_BU = in.readString();
            this.Model_LO = in.readString();
            this.NumberofOwners = in.readString();
            this.OdoMeter = in.readByte() != 0;
            this.RegState_BU = in.readString();
            this.RegState_LO = in.readString();
            this.Registration = in.readString();
            this.StorageLocation = in.readString();
            this.TitleFlag = in.readString();
            this.isPrintTitle = in.readString();
            this.saveDate = in.readString();
        }

        public static final Creator<GetVehicleDetailsByVehicleResultBean> CREATOR = new Creator<GetVehicleDetailsByVehicleResultBean>() {
            @Override
            public GetVehicleDetailsByVehicleResultBean createFromParcel(Parcel source) {
                return new GetVehicleDetailsByVehicleResultBean(source);
            }

            @Override
            public GetVehicleDetailsByVehicleResultBean[] newArray(int size) {
                return new GetVehicleDetailsByVehicleResultBean[size];
            }
        };
    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.GetVehicleDetailsByVehicleResult);
        dest.writeList(this.GetVehicleDetailsByLotResult);
        dest.writeList(this.GetVehicleDetailsByRegResult);
    }

    protected CarDetailData(Parcel in) {
        this.GetVehicleDetailsByVehicleResult = in.createTypedArrayList(GetVehicleDetailsByVehicleResultBean.CREATOR);
        this.GetVehicleDetailsByLotResult = new ArrayList<GetVehicleDetailsByVehicleResultBean>();
        in.readList(this.GetVehicleDetailsByLotResult, GetVehicleDetailsByVehicleResultBean.class.getClassLoader());
        this.GetVehicleDetailsByRegResult = new ArrayList<GetVehicleDetailsByVehicleResultBean>();
        in.readList(this.GetVehicleDetailsByRegResult, GetVehicleDetailsByVehicleResultBean.class.getClassLoader());
    }

    public static final Creator<CarDetailData> CREATOR = new Creator<CarDetailData>() {
        @Override
        public CarDetailData createFromParcel(Parcel source) {
            return new CarDetailData(source);
        }

        @Override
        public CarDetailData[] newArray(int size) {
            return new CarDetailData[size];
        }
    };
}
