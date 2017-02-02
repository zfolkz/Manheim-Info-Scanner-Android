package com.manheiminfoscanner.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zfolkz on 1/10/2017 AD.
 */

public class AuctionData implements Parcelable {


    private static AuctionData ourInstance = new AuctionData();
    public static AuctionData getInstance() {
        return ourInstance;
    }


    private List<GetAuctionByDateResultBean> GetAuctionByDateResult;

    public List<GetAuctionByDateResultBean> getGetAuctionByDateResult() {
        return GetAuctionByDateResult;
    }

    public void setGetAuctionByDateResult(List<GetAuctionByDateResultBean> GetAuctionByDateResult) {
        this.GetAuctionByDateResult = GetAuctionByDateResult;
    }

    public static class GetAuctionByDateResultBean {
        /**
         * AuctionCode : X1FBK549
         * LotNumber : 1001
         * Registration : ฆฉ-8230
         * Vehicle : 000000000001163270
         */

        private String AuctionCode;
        private String LotNumber;
        private String Registration;
        private String Vehicle;

        public String getAuctionCode() {
            return AuctionCode;
        }

        public void setAuctionCode(String AuctionCode) {
            this.AuctionCode = AuctionCode;
        }

        public String getLotNumber() {
            return LotNumber;
        }

        public void setLotNumber(String LotNumber) {
            this.LotNumber = LotNumber;
        }

        public String getRegistration() {
            return Registration;
        }

        public void setRegistration(String Registration) {
            this.Registration = Registration;
        }

        public String getVehicle() {
            return Vehicle;
        }

        public void setVehicle(String Vehicle) {
            this.Vehicle = Vehicle;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.GetAuctionByDateResult);
    }

    public AuctionData() {
    }

    protected AuctionData(Parcel in) {
        this.GetAuctionByDateResult = new ArrayList<GetAuctionByDateResultBean>();
        in.readList(this.GetAuctionByDateResult, GetAuctionByDateResultBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<AuctionData> CREATOR = new Parcelable.Creator<AuctionData>() {
        @Override
        public AuctionData createFromParcel(Parcel source) {
            return new AuctionData(source);
        }

        @Override
        public AuctionData[] newArray(int size) {
            return new AuctionData[size];
        }
    };
}
