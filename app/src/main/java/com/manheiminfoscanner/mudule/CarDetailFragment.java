package com.manheiminfoscanner.mudule;


import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.share.widget.LikeView;
import com.google.gson.Gson;
import com.inthecheesefactory.lib.fblike.widget.FBLikeView;
import com.manheiminfoscanner.util.BusManheim;
import com.manheiminfoscanner.R;
import com.manheiminfoscanner.model.CarData;
import com.manheiminfoscanner.model.CarDetailData;
import com.manheiminfoscanner.model.DateData;
import com.manheiminfoscanner.model.Lang;
import com.squareup.otto.Subscribe;

import java.text.DateFormatSymbols;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CarDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarDetailFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private boolean loading = false;
    private ImageView titleFlag;
    private TextView flagCondition;
    private FloatingActionButton wishList;
    private RealmResults<CarData> query;
    private Realm realm;

    public CarDetailFragment() {
        // Required empty public constructor
    }

    CarDetailData.GetVehicleDetailsByVehicleResultBean data;

    TextView carDetailText;
    public static CarDetailFragment newInstance(String param1, String param2) {
        CarDetailFragment fragment = new CarDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        BusManheim.getInstance().register(this);
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void onStop() {
        super.onStop();
        BusManheim.getInstance().unregister(this);
        realm.close();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //callService();


    }

    @Override
    public void onResume() {
        super.onResume();
        //callService();
        try {
            data = CarDetailData.getInstance().getGetVehicleDetailsByVehicleResult().get(CarDetailData.getInstance().getPosition());
            if (data!=null){
                wishList.setVisibility(View.VISIBLE);
                checkFavState();
                carDetailText.setText("\n "+getString(R.string.lot)+" : "+data.getLotNumber()+
                        "\n "+getString(R.string.auction_code)+" : "+data.getAuctionCode()+
                        "\n "+getString(R.string.auction_date)+" : "+converseDate(data.getAuctionDate())+
                        "\n "+getString(R.string.auction_location)+" : "+((data.getAuctionLocation_BU()==null && data.getAuctionLocation_LO()==null ) ? "-" : (Lang.getInstance().getLang().equals("BU")) ? data.getAuctionLocation_BU() : data.getAuctionLocation_LO())+
                        "\n "+getString(R.string.model)+" : "+((Lang.getInstance().getLang().equals("BU")) ? data.getDesc_BU()+" "+data.getModel_BU() : data.getDesc_LO()+" "+data.getModel_LO())+
                        "\n "+getString(R.string.reg_date)+" : "+(data.getDateFirstReg()==null || data.getDateFirstReg().equals("") || data.getDateFirstReg()=="" ? "-" : converseDate2(data.getDateFirstReg().split(" ")[0]))+
                        "\n "+getString(R.string.engine)+" : "+data.getEngineCapacity()+" "+data.getEngineCapacityUnit()+" "+
                        ((Lang.getInstance().getLang().equals("BU")) ? data.getFuelDelivery_BU() : data.getFuelDelivery_LO())+
                        ((Lang.getInstance().getLang().equals("BU")) ? data.getFuelType_BU() : data.getFuelType_LO())+
                        "\n "+getString(R.string.gear)+" : "+((Lang.getInstance().getLang().equals("BU")) ? data.getGearBox_BU() : data.getGearBox_LO())+
                        "\n "+getString(R.string.colour)+" : "+((Lang.getInstance().getLang().equals("BU")) ? data.getColour_BU() : data.getColour_LO())+
                        "\n "+getString(R.string.km)+" : "+data.getKm()+
                        "\n "+getString(R.string.reg)+" : "+data.getRegistration()+
                        "\n "+getString(R.string.reg_province)+" : "+((Lang.getInstance().getLang().equals("BU")) ? data.getRegState_BU() : data.getRegState_LO())+
                        "\n "+getString(R.string.owner)+" : "+data.getNumberofOwners()+
                        "\n "+getString(R.string.drive)+" : "+((Lang.getInstance().getLang().equals("BU")) ? data.getDriveDesc_BU() : data.getDriveDesc_LO())+
                        "\n "+getString(R.string.body)+" : "+((data.getBody_BU()==null && data.getBody_LO()==null) ? "-" : (Lang.getInstance().getLang().equals("BU")) ? data.getBody_BU() : data.getBody_LO())+
                        "\n "+getString(R.string.reg_expiry)+" : "+((data.getRegExpiry()==null || data.getRegExpiry().equals("") ) ? "-" : converseDate2(data.getRegExpiry().split(" ")[0]))+
                        "\n "+getString(R.string.detail)+" : "+((Lang.getInstance().getLang().equals("BU")) ? data.getDetails_BU() : data.getDetails_LO())+
                        "\n\n "+((Lang.getInstance().getLang().equals("BU")) ? data.getCatalogueDesc_BU() : data.getCatalogueDesc_LO()));
                setFlag(data.getTitleFlag());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCarDetailText(String detailText){

    }
    public void setFlag(String flagName){

        Resources res = getResources();

        switch (flagName){

            case  "Y":

                String[] yellow = res.getStringArray(R.array.yellow_flag);

                flagCondition.setText(getString(R.string.vehicle_that));

                for (int i = 0; i < yellow.length; i++) {
                    flagCondition.append("\n - "+yellow[i]);
                }

                titleFlag.setImageResource(R.drawable.title_flag_yellow);

                break;

            case  "G":
                String[] green = res.getStringArray(R.array.green_flag);

                flagCondition.setText(getString(R.string.vehicle_that));

                for (int i = 0; i < green.length; i++) {
                    flagCondition.append("\n - "+green[i]);
                }

                titleFlag.setImageResource(R.drawable.title_flag_green);

                break;

            case  "R":
                String[] red = res.getStringArray(R.array.red_flag);

                for (int i = 0; i < red.length; i++) {
                    flagCondition.append("\n - "+red[i]);
                }

                titleFlag.setImageResource(R.drawable.title_flag_red);

                break;

        }
    }

    private void checkFavState(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                query = realm.where(CarData.class).equalTo("Registration", data.getRegistration()).findAll();
                if (query.size()>0){
                    wishList.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), R.color.favSelected)));
                }else{
                    wishList.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), R.color.colorPrimary)));
                }
            }
        });

    }
    @Subscribe
    public void setCarDetail(CarDetailData carDetailData){
        try {
            data = CarDetailData.getInstance().getGetVehicleDetailsByVehicleResult().get(CarDetailData.getInstance().getPosition());
            if (data!=null){
                wishList.setVisibility(View.VISIBLE);
                checkFavState();
                carDetailText.setText("\n "+getString(R.string.lot)+" : "+data.getLotNumber()+
                        "\n "+getString(R.string.auction_code)+" : "+data.getAuctionCode()+
                        "\n "+getString(R.string.auction_date)+" : "+converseDate(data.getAuctionDate())+
                        "\n "+getString(R.string.auction_location)+" : "+((data.getAuctionLocation_BU()==null && data.getAuctionLocation_LO()==null ) ? "-" : (Lang.getInstance().getLang().equals("BU")) ? data.getAuctionLocation_BU() : data.getAuctionLocation_LO())+
                        "\n "+getString(R.string.model)+" : "+((Lang.getInstance().getLang().equals("BU")) ? data.getDesc_BU()+" "+data.getModel_BU() : data.getDesc_LO()+" "+data.getModel_LO())+
                        "\n "+getString(R.string.reg_date)+" : "+(data.getDateFirstReg()==null || data.getDateFirstReg().equals("") || data.getDateFirstReg()=="" ? "-" : converseDate2(data.getDateFirstReg().split(" ")[0]))+
                        "\n "+getString(R.string.engine)+" : "+data.getEngineCapacity()+" "+data.getEngineCapacityUnit()+" "+
                        ((Lang.getInstance().getLang().equals("BU")) ? data.getFuelDelivery_BU() : data.getFuelDelivery_LO())+
                        ((Lang.getInstance().getLang().equals("BU")) ? data.getFuelType_BU() : data.getFuelType_LO())+
                        "\n "+getString(R.string.gear)+" : "+((Lang.getInstance().getLang().equals("BU")) ? data.getGearBox_BU() : data.getGearBox_LO())+
                        "\n "+getString(R.string.colour)+" : "+((Lang.getInstance().getLang().equals("BU")) ? data.getColour_BU() : data.getColour_LO())+
                        "\n "+getString(R.string.km)+" : "+data.getKm()+
                        "\n "+getString(R.string.reg)+" : "+data.getRegistration()+
                        "\n "+getString(R.string.reg_province)+" : "+((Lang.getInstance().getLang().equals("BU")) ? data.getRegState_BU() : data.getRegState_LO())+
                        "\n "+getString(R.string.owner)+" : "+data.getNumberofOwners()+
                        "\n "+getString(R.string.drive)+" : "+((Lang.getInstance().getLang().equals("BU")) ? data.getDriveDesc_BU() : data.getDriveDesc_LO())+
                        "\n "+getString(R.string.body)+" : "+((data.getBody_BU()==null && data.getBody_LO()==null) ? "-" : (Lang.getInstance().getLang().equals("BU")) ? data.getBody_BU() : data.getBody_LO())+
                        "\n "+getString(R.string.reg_expiry)+" : "+((data.getRegExpiry()==null || data.getRegExpiry().equals("") ) ? "-" : converseDate2(data.getRegExpiry().split(" ")[0]))+
                        "\n "+getString(R.string.detail)+" : "+((Lang.getInstance().getLang().equals("BU")) ? data.getDetails_BU() : data.getDetails_LO())+
                        "\n\n "+((Lang.getInstance().getLang().equals("BU")) ? data.getCatalogueDesc_BU() : data.getCatalogueDesc_LO()));
                setFlag(data.getTitleFlag());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertCarData(CarDetailData.GetVehicleDetailsByVehicleResultBean data) {
        Gson gson = new Gson();

        data.setSaveDate(DateData.getInstance().getDateString());


        String jsonInString = gson.toJson(data);


        try {

            realm.beginTransaction();

            CarData carData = realm.createObjectFromJson(CarData.class, jsonInString);

            realm.copyToRealm(carData);

            realm.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_car_detail, container, false);

        carDetailText = (TextView)v.findViewById(R.id.carDetailText);
        titleFlag = (ImageView)v.findViewById(R.id.titleFlag);
        flagCondition = (TextView)v.findViewById(R.id.flagCondition);


        FBLikeView fbLikeView = (FBLikeView) v.findViewById(R.id.fbLikeView);
        fbLikeView.getLikeView().setObjectIdAndType(
                "",
                LikeView.ObjectType.OPEN_GRAPH);


        wishList = (FloatingActionButton)v.findViewById(R.id.wishList);

        wishList.setOnClickListener(this);


        return v;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.car_detail_menu, menu);

        if (Lang.getInstance().getLang().equals("BU")){
            menu.getItem(1).setTitle("EN");
            Lang.getInstance().setLang("BU");
        }else{
            menu.getItem(1).setTitle("TH");
            Lang.getInstance().setLang("LO");
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.wishList){
            if (query.size()==0){
                insertCarData(data);
            }else{
                deleteCarData();
            }

            checkFavState();

        }
    }
    public void deleteCarData(){

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                query = realm.where(CarData.class).equalTo("Registration", data.getRegistration()).findAll();
                if (query.size()>0) {
                    query.deleteAllFromRealm();
                }
            }
        });
    }

    public String converseDate(String date){
        StringBuilder dateString = new StringBuilder();

        String[] dates = date.split("/");


        dateString.append(dates[0]);

        if (Lang.getInstance().getLang().equals("BU")){


            dateString.append(" "+getMonth(Integer.valueOf(dates[1]),new Locale("en_US"))+ " ");

            dateString.append(" "+dates[2]);

        }else{

            dateString.append(" "+getMonth(Integer.valueOf(dates[1]),new Locale("th", "TH"))+ " ");

            dateString.append(getYear(dates[2]));

        }
        return dateString.toString();
    }

    public String converseDate2(String date){
        StringBuilder dateString = new StringBuilder();

        String[] dates = date.split("/");


        dateString.append(dates[1]);

        if (Lang.getInstance().getLang().equals("BU")){


            dateString.append(" "+getMonth(Integer.valueOf(dates[0]),new Locale("en_US"))+ " ");

            dateString.append(" "+dates[2]);

        }else{

            dateString.append(" "+getMonth(Integer.valueOf(dates[0]),new Locale("th", "TH"))+ " ");

            dateString.append(getYear(dates[2]));

        }
        return dateString.toString();
    }

    public String getMonth(int month , Locale locale) {

        return new DateFormatSymbols().getInstance(locale).getMonths()[month-1];
    }

    public String getYear(String yearString) {
        int year = Integer.valueOf(yearString)+543;
        return String.valueOf(year);
    }
}
