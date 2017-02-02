package com.manheiminfoscanner.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zfolkz on 12/1/16 AD.
 */
public class ResultBarcode implements Parcelable {
    String textResult;

    public String getTextResult() {
        return textResult;
    }

    public void setTextResult(String textResult) {
        this.textResult = textResult;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.textResult);
    }

    public ResultBarcode() {
    }

    protected ResultBarcode(Parcel in) {
        this.textResult = in.readString();
    }

    public static final Parcelable.Creator<ResultBarcode> CREATOR = new Parcelable.Creator<ResultBarcode>() {
        @Override
        public ResultBarcode createFromParcel(Parcel source) {
            return new ResultBarcode(source);
        }

        @Override
        public ResultBarcode[] newArray(int size) {
            return new ResultBarcode[size];
        }
    };
}
