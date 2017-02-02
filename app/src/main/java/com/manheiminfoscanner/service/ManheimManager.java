package com.manheiminfoscanner.service;

import com.manheiminfoscanner.BuildConfig;
import com.manheiminfoscanner.model.CarDetailData;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zfolkz on 12/1/16 AD.
 */
public class ManheimManager {
    CarDetailData carDetailData;
    Retrofit retrofit;
    ManheimService service;
    private static final String BaseUrl = BuildConfig.URL_API;

    private static ManheimManager ourInstance = new ManheimManager();

    public static ManheimManager getInstance() {
        return ourInstance;
    }

    private ManheimManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ManheimService.class);

    }

    public ManheimService getService() {
        return service;
    }

    public void setService(ManheimService service) {
        this.service = service;
    }

}
