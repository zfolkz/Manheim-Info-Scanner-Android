package com.manheiminfoscanner.model;

/**
 * Created by zfolkz on 12/21/2016 AD.
 */
public class Lang {
    private static Lang ourInstance = new Lang();

    public static Lang getInstance() {
        return ourInstance;
    }

    private Lang() {
    }

    String lang = "BU";

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
