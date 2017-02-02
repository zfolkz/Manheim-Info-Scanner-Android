package com.manheiminfoscanner.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by zfolkz on 1/19/2017 AD.
 */

public class LocationData implements Parcelable {

    private static LocationData ourInstance = new LocationData();
    public static LocationData getInstance() {
        return ourInstance;
    }

    private ArrayList<GetAuctionLocationByDateResultBean> GetAuctionLocationByDateResult;

    public ArrayList<GetAuctionLocationByDateResultBean> getGetAuctionLocationByDateResult() {
        return GetAuctionLocationByDateResult;
    }

    public void setGetAuctionLocationByDateResult(ArrayList<GetAuctionLocationByDateResultBean> GetAuctionLocationByDateResult) {
        this.GetAuctionLocationByDateResult = GetAuctionLocationByDateResult;
    }

    public static class GetAuctionLocationByDateResultBean {
        /**
         * AuctionCode : X3FAS001
         * AuctionLocation_BU : Vehicles Financiers on Lane 3 Simulcast at 10.00 AM.
         * AuctionLocation_LO : รถยนต์ทั่วไป ลานประมูล 3 ซีมูลแคสท์ เริ่ม เวลา 10.00 น.
         */

        private String AuctionCode;
        private String AuctionLocation_BU;
        private String AuctionLocation_LO;

        public String getAuctionCode() {
            return AuctionCode;
        }

        public void setAuctionCode(String AuctionCode) {
            this.AuctionCode = AuctionCode;
        }

        public String getAuctionLocation_BU() {
            return AuctionLocation_BU;
        }

        public void setAuctionLocation_BU(String AuctionLocation_BU) {
            this.AuctionLocation_BU = AuctionLocation_BU;
        }

        public String getAuctionLocation_LO() {
            return AuctionLocation_LO;
        }

        public void setAuctionLocation_LO(String AuctionLocation_LO) {
            this.AuctionLocation_LO = AuctionLocation_LO;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.GetAuctionLocationByDateResult);
    }

    public LocationData() {
    }

    protected LocationData(Parcel in) {
        this.GetAuctionLocationByDateResult = new ArrayList<GetAuctionLocationByDateResultBean>();
        in.readList(this.GetAuctionLocationByDateResult, GetAuctionLocationByDateResultBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<LocationData> CREATOR = new Parcelable.Creator<LocationData>() {
        @Override
        public LocationData createFromParcel(Parcel source) {
            return new LocationData(source);
        }

        @Override
        public LocationData[] newArray(int size) {
            return new LocationData[size];
        }
    };
}
