package com.manheiminfoscanner.model;

/**
 * Created by zfolkz on 1/11/2017 AD.
 */
public class DateData {
    private static DateData ourInstance = new DateData();

    public static DateData getInstance() {
        return ourInstance;
    }

    private DateData() {
    }

    private String dateString;

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }
}
