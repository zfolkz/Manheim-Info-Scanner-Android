package com.manheiminfoscanner.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

/**
 * Created by zfolkz on 1/8/2017 AD.
 */

public class PicData extends RealmObject implements Parcelable {
    private String DocumentDescription_BU;
    private String DocumentDescription_LO;
    private String DocumentFile;
    private int DocumentLenght;
    private int DocumentTypeID;
    private String Vehicle;
    private int LotNumber;
    private String AuctionCode;
    private String saveDate;

    public String getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(String saveDate) {
        this.saveDate = saveDate;
    }
    public String getAuctionCode() {
        return AuctionCode;
    }

    public void setAuctionCode(String auctionCode) {
        AuctionCode = auctionCode;
    }

    public int getLotNumber() {
        return LotNumber;
    }

    public void setLotNumber(int lotNumber) {
        LotNumber = lotNumber;
    }

    public String getDocumentDescription_BU() {
        return DocumentDescription_BU;
    }

    public void setDocumentDescription_BU(String DocumentDescription_BU) {
        this.DocumentDescription_BU = DocumentDescription_BU;
    }

    public String getDocumentDescription_LO() {
        return DocumentDescription_LO;
    }

    public void setDocumentDescription_LO(String DocumentDescription_LO) {
        this.DocumentDescription_LO = DocumentDescription_LO;
    }

    public String getDocumentFile() {
        return DocumentFile;
    }

    public void setDocumentFile(String DocumentFile) {
        this.DocumentFile = DocumentFile;
    }

    public int getDocumentLenght() {
        return DocumentLenght;
    }

    public void setDocumentLenght(int DocumentLenght) {
        this.DocumentLenght = DocumentLenght;
    }

    public int getDocumentTypeID() {
        return DocumentTypeID;
    }

    public void setDocumentTypeID(int DocumentTypeID) {
        this.DocumentTypeID = DocumentTypeID;
    }

    public String getVehicle() {
        return Vehicle;
    }

    public void setVehicle(String Vehicle) {
        this.Vehicle = Vehicle;
    }


    public PicData() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.DocumentDescription_BU);
        dest.writeString(this.DocumentDescription_LO);
        dest.writeString(this.DocumentFile);
        dest.writeInt(this.DocumentLenght);
        dest.writeInt(this.DocumentTypeID);
        dest.writeString(this.Vehicle);
        dest.writeInt(this.LotNumber);
        dest.writeString(this.AuctionCode);
    }

    protected PicData(Parcel in) {
        this.DocumentDescription_BU = in.readString();
        this.DocumentDescription_LO = in.readString();
        this.DocumentFile = in.readString();
        this.DocumentLenght = in.readInt();
        this.DocumentTypeID = in.readInt();
        this.Vehicle = in.readString();
        this.LotNumber = in.readInt();
        this.AuctionCode = in.readString();
    }

    public static final Creator<PicData> CREATOR = new Creator<PicData>() {
        @Override
        public PicData createFromParcel(Parcel source) {
            return new PicData(source);
        }

        @Override
        public PicData[] newArray(int size) {
            return new PicData[size];
        }
    };
}
