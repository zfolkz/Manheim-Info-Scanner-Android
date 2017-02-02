package com.manheiminfoscanner.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by zfolkz on 1/8/2017 AD.
 */

public class CarData extends RealmObject implements Parcelable{
    public String AccidentCar;
    public String AuctionCode;
    public String AuctionDate;
    public String AuctionLocation_BU;
    public String AuctionLocation_LO;
    public String Body_BU;
    public String Body_LO;
    public String BuildYear;
    public String CatalogueDesc_BU;
    public String CatalogueDesc_LO;
    public String Colour_BU;
    public String Colour_LO;
    public String DateFirstReg;
    public String Desc_BU;
    public String Desc_LO;
    public String Details_BU;
    public String Details_LO;
    public String DriveDesc_BU;
    public String DriveDesc_LO;
    public double EngineCapacity;
    public String EngineCapacityUnit;
    public String RegExpiry;
    public String FloodCar;
    public String FuelDelivery_BU;
    public String FuelDelivery_LO;
    public String FuelType_BU;
    public String FuelType_LO;
    public String GearBox_BU;
    public String GearBox_LO;
    public String Gears;
    public String Grading;
    public String InstructionManual;
    public boolean IsMatchColour;
    public boolean IsMatchFuelType;
    public int Km;
    public int LotNumber;
    public String Model_BU;
    public String Model_LO;
    public String NumberofOwners;
    public boolean OdoMeter;
    public String RegState_BU;
    public String RegState_LO;
    @PrimaryKey
    public String Registration;
    public String StorageLocation;
    public String TitleFlag;
    public String isPrintTitle;


    private String saveDate;

    public String getSaveDate() {
        return saveDate;
    }


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

    public void setSaveDate(String saveDate) {
        this.saveDate = saveDate;
    }
    private static CarData ourInstance = new CarData();
    public static CarData getInstance() {
        return ourInstance;
    }

    public CarData() {

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

    public void setAccidentCar(String accidentCar) {
        AccidentCar = accidentCar;
    }

    public String getAuctionCode() {
        return AuctionCode;
    }

    public void setAuctionCode(String auctionCode) {
        AuctionCode = auctionCode;
    }

    public String getAuctionDate() {
        return AuctionDate;
    }

    public void setAuctionDate(String auctionDate) {
        AuctionDate = auctionDate;
    }

    public String getBuildYear() {
        return BuildYear;
    }

    public void setBuildYear(String buildYear) {
        BuildYear = buildYear;
    }

    public String getCatalogueDesc_BU() {
        return CatalogueDesc_BU;
    }

    public void setCatalogueDesc_BU(String catalogueDesc_BU) {
        CatalogueDesc_BU = catalogueDesc_BU;
    }

    public String getCatalogueDesc_LO() {
        return CatalogueDesc_LO;
    }

    public void setCatalogueDesc_LO(String catalogueDesc_LO) {
        CatalogueDesc_LO = catalogueDesc_LO;
    }

    public String getColour_BU() {
        return Colour_BU;
    }

    public void setColour_BU(String colour_BU) {
        Colour_BU = colour_BU;
    }

    public String getColour_LO() {
        return Colour_LO;
    }

    public void setColour_LO(String colour_LO) {
        Colour_LO = colour_LO;
    }

    public String getDesc_BU() {
        return Desc_BU;
    }

    public void setDesc_BU(String desc_BU) {
        Desc_BU = desc_BU;
    }

    public String getDesc_LO() {
        return Desc_LO;
    }

    public void setDesc_LO(String desc_LO) {
        Desc_LO = desc_LO;
    }

    public String getDriveDesc_BU() {
        return DriveDesc_BU;
    }

    public void setDriveDesc_BU(String driveDesc_BU) {
        DriveDesc_BU = driveDesc_BU;
    }

    public String getDriveDesc_LO() {
        return DriveDesc_LO;
    }

    public void setDriveDesc_LO(String driveDesc_LO) {
        DriveDesc_LO = driveDesc_LO;
    }

    public double getEngineCapacity() {
        return EngineCapacity;
    }

    public void setEngineCapacity(double engineCapacity) {
        EngineCapacity = engineCapacity;
    }

    public String getEngineCapacityUnit() {
        return EngineCapacityUnit;
    }

    public void setEngineCapacityUnit(String engineCapacityUnit) {
        EngineCapacityUnit = engineCapacityUnit;
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

    public void setFloodCar(String floodCar) {
        FloodCar = floodCar;
    }

    public String getFuelDelivery_BU() {
        return FuelDelivery_BU;
    }

    public void setFuelDelivery_BU(String fuelDelivery_BU) {
        FuelDelivery_BU = fuelDelivery_BU;
    }

    public String getFuelDelivery_LO() {
        return FuelDelivery_LO;
    }

    public void setFuelDelivery_LO(String fuelDelivery_LO) {
        FuelDelivery_LO = fuelDelivery_LO;
    }

    public String getFuelType_BU() {
        return FuelType_BU;
    }

    public void setFuelType_BU(String fuelType_BU) {
        FuelType_BU = fuelType_BU;
    }

    public String getFuelType_LO() {
        return FuelType_LO;
    }

    public void setFuelType_LO(String fuelType_LO) {
        FuelType_LO = fuelType_LO;
    }

    public String getGearBox_BU() {
        return GearBox_BU;
    }

    public void setGearBox_BU(String gearBox_BU) {
        GearBox_BU = gearBox_BU;
    }

    public String getGearBox_LO() {
        return GearBox_LO;
    }

    public void setGearBox_LO(String gearBox_LO) {
        GearBox_LO = gearBox_LO;
    }

    public String getGears() {
        return Gears;
    }

    public void setGears(String gears) {
        Gears = gears;
    }

    public String getGrading() {
        return Grading;
    }

    public void setGrading(String grading) {
        Grading = grading;
    }

    public String getInstructionManual() {
        return InstructionManual;
    }

    public void setInstructionManual(String instructionManual) {
        InstructionManual = instructionManual;
    }

    public boolean isMatchColour() {
        return IsMatchColour;
    }

    public void setMatchColour(boolean matchColour) {
        IsMatchColour = matchColour;
    }

    public boolean isMatchFuelType() {
        return IsMatchFuelType;
    }

    public void setMatchFuelType(boolean matchFuelType) {
        IsMatchFuelType = matchFuelType;
    }

    public int getKm() {
        return Km;
    }

    public void setKm(int km) {
        Km = km;
    }

    public int getLotNumber() {
        return LotNumber;
    }

    public void setLotNumber(int lotNumber) {
        LotNumber = lotNumber;
    }

    public String getModel_BU() {
        return Model_BU;
    }

    public void setModel_BU(String model_BU) {
        Model_BU = model_BU;
    }

    public String getModel_LO() {
        return Model_LO;
    }

    public void setModel_LO(String model_LO) {
        Model_LO = model_LO;
    }

    public String getNumberofOwners() {
        return NumberofOwners;
    }

    public void setNumberofOwners(String numberofOwners) {
        NumberofOwners = numberofOwners;
    }

    public boolean isOdoMeter() {
        return OdoMeter;
    }

    public void setOdoMeter(boolean odoMeter) {
        OdoMeter = odoMeter;
    }

    public String getRegState_BU() {
        return RegState_BU;
    }

    public void setRegState_BU(String regState_BU) {
        RegState_BU = regState_BU;
    }

    public String getRegState_LO() {
        return RegState_LO;
    }

    public void setRegState_LO(String regState_LO) {
        RegState_LO = regState_LO;
    }

    public String getRegistration() {
        return Registration;
    }

    public void setRegistration(String registration) {
        Registration = registration;
    }

    public String getStorageLocation() {
        return StorageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        StorageLocation = storageLocation;
    }

    public String getTitleFlag() {
        return TitleFlag;
    }

    public void setTitleFlag(String titleFlag) {
        TitleFlag = titleFlag;
    }

    public String getIsPrintTitle() {
        return isPrintTitle;
    }

    public void setIsPrintTitle(String isPrintTitle) {
        this.isPrintTitle = isPrintTitle;
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

    protected CarData(Parcel in) {
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

    public static final Creator<CarData> CREATOR = new Creator<CarData>() {
        @Override
        public CarData createFromParcel(Parcel source) {
            return new CarData(source);
        }

        @Override
        public CarData[] newArray(int size) {
            return new CarData[size];
        }
    };
}
