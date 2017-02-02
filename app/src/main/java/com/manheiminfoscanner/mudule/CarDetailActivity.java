package com.manheiminfoscanner.mudule;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.widget.LikeView;
import com.inthecheesefactory.lib.fblike.widget.FBLikeView;
import com.manheiminfoscanner.util.BusManheim;
import com.manheiminfoscanner.R;
import com.manheiminfoscanner.model.CarData;
import com.manheiminfoscanner.model.Lang;
import com.squareup.otto.Subscribe;

import java.text.DateFormatSymbols;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;

public class CarDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView carDetailWishText;
    private ImageView wishTitleFlag;
    private TextView wishFlagCondition;
    Configuration config;

    CarData data;
    private FloatingActionButton wishList;
    private RealmResults<CarData> query;
    private Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        FacebookSdk.sdkInitialize(this.getApplicationContext());

        CallbackManager callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Toast.makeText(getApplicationContext(),"onSuccess",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Toast.makeText(getApplicationContext(),"onCancel",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Toast.makeText(getApplicationContext(),"onError",Toast.LENGTH_SHORT).show();
                    }
                });

        config = new Configuration();

        setContentView(R.layout.activity_car_detail);


        carDetailWishText = (TextView)findViewById(R.id.carDetailWishText);
        wishTitleFlag = (ImageView)findViewById(R.id.wishTitleFlag);
        wishFlagCondition = (TextView)findViewById(R.id.wishFlagCondition);

        setUpToolbar();


        if (getIntent().getExtras().getParcelable("carDetail")!=null){

            data = getIntent().getExtras().getParcelable("carDetail");

            carDetailWishText.setText("\n "+getString(R.string.lot)+" : "+data.getLotNumber()+
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



        FBLikeView fbLikeView = (FBLikeView)findViewById(R.id.fbLikeView);
        fbLikeView.getLikeView().setObjectIdAndType(
                "https://www.facebook.com/ManheimThailand/",
                LikeView.ObjectType.OPEN_GRAPH);

        wishList = (FloatingActionButton)findViewById(R.id.wishList);

        wishList.setOnClickListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        BusManheim.getInstance().register(this);
        realm = Realm.getDefaultInstance();
    }

    @Override
    protected void onStop() {
        super.onStop();
        BusManheim.getInstance().unregister(this);
        realm.close();
    }

    public void setFlag(String flagName){

        Resources res = getResources();

        switch (flagName){

            case  "Y":

                String[] yellow = res.getStringArray(R.array.yellow_flag);

                wishFlagCondition.setText(getString(R.string.vehicle_that));

                for (int i = 0; i < yellow.length; i++) {
                    wishFlagCondition.append("\n - "+yellow[i]);
                }

                wishTitleFlag.setImageResource(R.drawable.title_flag_yellow);

                break;

            case  "G":
                String[] green = res.getStringArray(R.array.green_flag);

                wishFlagCondition.setText(getString(R.string.vehicle_that));

                for (int i = 0; i < green.length; i++) {
                    wishFlagCondition.append("\n - "+green[i]);
                }

                wishTitleFlag.setImageResource(R.drawable.title_flag_green);

                break;

            case  "R":
                String[] red = res.getStringArray(R.array.red_flag);

                for (int i = 0; i < red.length; i++) {
                    wishFlagCondition.append("\n - "+red[i]);
                }

                wishTitleFlag.setImageResource(R.drawable.title_flag_red);

                break;

        }
    }
    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        if (toolbar != null) {
            setSupportActionBar(toolbar);

        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.car_detail_menu, menu);
        if (Lang.getInstance().getLang().equals("BU")){
            menu.getItem(0).setTitle("EN");
            Lang.getInstance().setLang("BU");
        }else{
            menu.getItem(0).setTitle("TH");
            Lang.getInstance().setLang("LO");
        }

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.changeLang:
                if (item.getTitle().equals("EN")){
                    item.setTitle("TH");
                    Lang.getInstance().setLang("LO");
                    config.locale = new Locale("th");
                    getResources().updateConfiguration(config, null);

                }else{
                    item.setTitle("EN");
                    Lang.getInstance().setLang("BU");
                    config.locale = Locale.ENGLISH;
                    getResources().updateConfiguration(config, null);

                }

                BusManheim.getInstance().post(CarData.getInstance());

                break;
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        FBLikeView.onActivityResult(requestCode, resultCode, data);

    }

    @Subscribe
    public void setCarDetail(CarData carData){
        try {

            carDetailWishText.setText("\n "+getString(R.string.lot)+" : "+data.getLotNumber()+
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.wishList){
            deleteCarData();
            checkFavState();
            finish();
        }
    }
    private void checkFavState(){
        try {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    query = realm.where(CarData.class).equalTo("Registration", data.getRegistration()).findAll();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void deleteCarData(){

        try {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    query = realm.where(CarData.class).equalTo("Registration", data.getRegistration()).findAll();
                    if (query.size()>0) {
                        query.deleteAllFromRealm();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
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
