package com.manheiminfoscanner.service;

import com.manheiminfoscanner.model.AuctionData;
import com.manheiminfoscanner.model.CarDetailData;
import com.manheiminfoscanner.model.LocationData;
import com.manheiminfoscanner.model.OCPBData;
import com.manheiminfoscanner.model.PhotoData;
import com.manheiminfoscanner.model.SimucastData;
import com.manheiminfoscanner.model.TitleBookData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by zfolkz on 11/30/16 AD.
 */
public interface ManheimService {

    @GET("ServiceAuctionByDate.svc/GetAuctionByDate/{auctionDate}")
    Call<AuctionData> getAuctionByDate(@Path("auctionDate") String auctionDate);


    @GET("ServiceAuctionLocations.svc/GetAuctionLocationByDate/{auctionDate}")
    Call<LocationData> getAuctionLocationByDate(@Path("auctionDate") String auctionDate);

    @GET("ServiceVehicleDetailsByLot.svc/GetVehicleDetailsByLot/{auctionDate}/{lotNumber}")
    Call<CarDetailData> getCarDetailByLot(@Path("auctionDate") String auctionDate , @Path("lotNumber") String lotNumber);

    @GET("ServiceVehicleDetailsByReg.svc/GetVehicleDetailsByReg/{auctionDate}/{registration}")
    Call<CarDetailData> getCarDetailByReg(@Path("auctionDate") String auctionDate , @Path("registration") String registration);

    @GET("ServiceVehicleDetailsByVehicle.svc/GetVehicleDetailsByVehicle/{vehicle}")
    Call<CarDetailData> getCarDetailByVehicle(@Path("vehicle") String vehicle);


    @GET("ServiceDocuments.svc/GetDocumentById/{vehicle}/{documentTypeID}")
    //documentTypeID 4 = simu , 6 title
    Call<TitleBookData> getDocumentTitlebook(@Path("vehicle") String vehicle , @Path("documentTypeID") String documentTypeID);

    @GET("ServiceDocuments.svc/GetDocumentById/{vehicle}/{documentTypeID}")
    Call<SimucastData> getDocumentSimucast(@Path("vehicle") String vehicle , @Path("documentTypeID") String documentTypeID);


    @GET("ServiceOCPB.svc/GetOCPB/{systemLanguage}/{vehicle}")
    Call<OCPBData> getOCPB(@Path("systemLanguage") String systemLanguage , @Path("vehicle") String vehicle);

    @GET("ServiceDocumentItems.svc/GetDocumentItems/{vehicle}")
    Call<PhotoData> getPhoto(@Path("vehicle") String vehicle);


}