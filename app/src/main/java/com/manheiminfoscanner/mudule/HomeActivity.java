package com.manheiminfoscanner.mudule;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.inthecheesefactory.lib.fblike.widget.FBLikeView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.manheiminfoscanner.util.BusManheim;
import com.manheiminfoscanner.R;
import com.manheiminfoscanner.view.SearchFragmentDialog;
import com.manheiminfoscanner.adapter.ViewPagerAdapter;
import com.manheiminfoscanner.model.AuctionData;
import com.manheiminfoscanner.model.CarData;
import com.manheiminfoscanner.model.CarDetailData;
import com.manheiminfoscanner.model.DateData;
import com.manheiminfoscanner.model.Lang;
import com.manheiminfoscanner.service.ManheimManager;
import com.manheiminfoscanner.model.OCPBData;
import com.manheiminfoscanner.model.OCPBFailedData;
import com.manheiminfoscanner.model.PhotoData;
import com.manheiminfoscanner.model.PhotoFailedData;
import com.manheiminfoscanner.model.PicData;
import com.manheiminfoscanner.model.SearchData;
import com.manheiminfoscanner.model.SearchNodata;
import com.manheiminfoscanner.model.SimucastData;
import com.manheiminfoscanner.model.SimucastFailedData;
import com.manheiminfoscanner.model.TitleBookData;
import com.manheiminfoscanner.model.TitleBookFailedData;
import com.manheiminfoscanner.model.VehicleData;
import com.squareup.otto.Subscribe;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener , NavigationView.OnNavigationItemSelectedListener{
    static final int PICK_BARCODE_REQUEST = 1;


    CategoryOnPageChangeListener categoryOnPageChangeListener = new CategoryOnPageChangeListener();

    TabLayout tabLayout;
    Toolbar toolbar;
    DrawerLayout mDrawerLayout;
    NavigationView navigationView;
    ViewPager viewPager;
    int active = 0;
    SearchFragmentDialog dialogFragment;
    String datePattern = "yyyy-MM-dd";
    SimpleDateFormat format;
    View nodata;
    private String[] toolbarTitle = {
            "Car Details","Photo","Title Book","OCPB","Simulcast"
    };

    private boolean today_auction = false;

    private Fragment[] fragmentList = {
            CarDetailFragment.newInstance("",""),PhotoFragment.newInstance("",""),TitleBookFragment.newInstance("",""),OCPBFragment.newInstance("",""),SimucastFragment.newInstance("","")
    };


    private int[] tabs_icon = {
            R.drawable.cardetail,
            R.drawable.photo,
            R.drawable.titlebook,
            R.drawable.ocpb,
            R.drawable.simucast,
    };

    private int[] tabs_icon_active = {
            R.drawable.cardetail_ac,
            R.drawable.photo_ac,
            R.drawable.titlebook_ac,
            R.drawable.ocpb_ac,
            R.drawable.simucast_ac,
    };

    Button search;
    private ViewPagerAdapter adapter;
    Configuration config;
    private ProgressDialog progressBar;
    private int countService = 0;

    private Realm realm;
    private RealmResults<PicData> queryPic;
    private RealmResults<CarData> queryCar;
    private TextView noDataTxt;
    private String barcodeString = "";

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

    private boolean capture = true;

    String fixdate = "2017-01-31";
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!capture){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        }
        format = new SimpleDateFormat(datePattern);
        DateData.getInstance().setDateString(format.format(new Date()));
        //DateData.getInstance().setDateString(fixdate);
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

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.manheiminfoscanner",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }




        //insertCarData();

        config = new Configuration();

        setUpLang();

        setContentView(R.layout.activity_home);


        search = (Button)findViewById(R.id.search);
        nodata = findViewById(R.id.nodata);

        noDataTxt = (TextView) findViewById(R.id.noDataTxt);

        search.setOnClickListener(this);

        setUpToolbar();

        //setUpViewpager();
        //setUpTap();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_id);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);

        Menu menu =navigationView.getMenu();
        String version = "";
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        menu.add("Maheim Info Scanner "+version);

        navigationView.setNavigationItemSelectedListener(this);

        callServiceData();

    }


    private void setUpLang(){
        Lang.getInstance().setLang("BU");
        config.locale = Locale.ENGLISH;
        getResources().updateConfiguration(config, null);
    }

    private void setupTabIcons(int active) {




        for (int i = 0; i< tabs_icon.length; i++){
            if (active==i){
                tabLayout.getTabAt(i).setIcon(tabs_icon_active[i]).setText(toolbarTitle[i]);
            }else{
                tabLayout.getTabAt(i).setIcon(tabs_icon[i]).setText(toolbarTitle[i]);
            }

        }
    }
    @Subscribe
    public void searchCallBack(SearchData searchData){

        active = 0;
        search.setText(searchData.getRegistration());
        toolbar.setTitle(toolbarTitle[0]);
        nodata.setVisibility(View.GONE);
        setUpViewpager();
        setUpTap();
        callService();
    }

    @Subscribe
    public void searchNoDataCallBack(SearchNodata searchNodata){

        Toast.makeText(getApplicationContext(),"Data not found",Toast.LENGTH_SHORT).show();

        search.setText("Search by a lot or registration");

        search.setPaintFlags(0);

        toolbar.setTitle(R.string.app_name);
        nodata.setVisibility(View.VISIBLE);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(null);
    }

    private void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setLogo(R.drawable.logo);
        //toolbar.setTitle(toolbarTitle[0]);
        if (toolbar != null) {
            setSupportActionBar(toolbar);

        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_menu);


    }

    private void setUpViewpager() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        for (int i=0;i<fragmentList.length;i++){
            adapter.addFragment(fragmentList[i], toolbarTitle[i]);
        }

        viewPager.setAdapter(adapter);

        viewPager.post(new Runnable() {
            @Override public void run() {
                categoryOnPageChangeListener.onPageSelected(viewPager.getCurrentItem());
            }
        });
        viewPager.addOnPageChangeListener(categoryOnPageChangeListener);
        /*viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                active = position;

                setupTabIcons(position);

                getSupportActionBar().setTitle(toolbarTitle[position]);



            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                viewPager.setCurrentItem(viewPager.getCurrentItem());
            }
        });*/
    }

    private void setUpTap() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons(viewPager.getCurrentItem());

        Paint p = new Paint();
        p.setColor(Color.RED);

        search.setPaintFlags(p.getColor() | Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.search:
                if (today_auction){
                    FragmentManager fm = getSupportFragmentManager();
                    dialogFragment = new SearchFragmentDialog();
                    dialogFragment.show(fm, "Sample Fragment");
                }else{
                    Toast.makeText(getApplicationContext(),getString(R.string.no_auction),Toast.LENGTH_LONG).show();
                }
                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        /*if (active == 0 || active == 3){

            getMenuInflater().inflate(R.menu.car_detail_menu, menu);

            if (menu.getItem(1).getTitle().equals("EN")){
                menu.getItem(1).setTitle("TH");
                Lang.getInstance().setLang("LO");
            }

        }else{*/
            getMenuInflater().inflate(R.menu.home_menu, menu);
        //}

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.barcodeItem:


                if (Build.VERSION.SDK_INT >= 23) {
                    // Marshmallow+

                    callPermissionCamera();

                } else {
                    // Pre-Marshmallow

                    Intent i = new Intent(this,BarcodeActivity.class);
                    startActivityForResult(i,PICK_BARCODE_REQUEST);
                }


                break;
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


                try {
                    if (active==0){
                        BusManheim.getInstance().post(CarDetailData.getInstance());
                        ((OCPBFragment) adapter.getItem(3)).callService();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    if (active==3){
                        ((OCPBFragment) adapter.getItem(3)).callService();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


                break;
            default:
                break;
        }
        return true;
    }

    public void callPermissionCamera(){
        //int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.CAMERA);
        Dexter.checkPermission(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {
                Intent i = new Intent(HomeActivity.this,BarcodeActivity.class);
                startActivityForResult(i,1);
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {/* ... */}

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                // request permission when call method again
                token.continuePermissionRequest();

                // ask permission once time
                token.cancelPermissionRequest();
            }
        }, Manifest.permission.CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_BARCODE_REQUEST){
            if (resultCode == Activity.RESULT_OK) {
                 barcodeString = data.getExtras().getString("barcode");
                callService();
            }
        }
        FBLikeView.onActivityResult(requestCode, resultCode, data);

    }

    /*public void validateVehicle(String barcodeData){
        //Toast.makeText(HomeActivity.this, ""+vehicleId, Toast.LENGTH_SHORT).show();
        callService();



    }*/



    public void callService(){

        progressBar = ProgressDialog.show(this, null, "Loading...");
        //progressBar.setCancelable(true);
        countService = 0;

        String vehicleNo = VehicleData.getInstance().getVehicleNo();

        if (PhotoData.getInstance().getGetDocumentItemsResult()!=null){
            PhotoData.getInstance().getGetDocumentItemsResult().clear();
        }
        if (TitleBookData.getInstance().getGetDocumentByIdResult()!=null){
            TitleBookData.getInstance().getGetDocumentByIdResult().clear();
        }
        /*if (CarDetailData.getInstance().getGetVehicleDetailsByVehicleResult()!=null){
            CarDetailData.getInstance().getGetVehicleDetailsByVehicleResult().clear();
        }*/
        if (SimucastData.getInstance().getGetDocumentByIdResult()!=null){
            SimucastData.getInstance().getGetDocumentByIdResult().clear();
        }
        if (OCPBData.getInstance().getGetOCPBResult()!=null){
            OCPBData.getInstance().getGetOCPBResult().clear();
        }

        Call<PhotoData> call =  ManheimManager.getInstance().getService().getPhoto(vehicleNo);
        call.enqueue(new Callback<PhotoData>() {

            @Override
            public void onResponse(Call<PhotoData> call, Response<PhotoData> response) {

                countService++;

                setUPSuccess();

                if (response.body()!=null){
                    PhotoFragment.position = 0;

                    ArrayList<PhotoData.GetDocumentItemsResultBean> photoData = new ArrayList<>();
                    for (int i = 0; i < response.body().getGetDocumentItemsResult().size(); i++) {
                        if (response.body().getGetDocumentItemsResult().get(i).getDocumentTypeID()==3){
                            photoData.add(response.body().getGetDocumentItemsResult().get(i));
                        }
                    }



                    PhotoData.getInstance().setGetDocumentItemsResult(photoData);
                    BusManheim.getInstance().post(PhotoData.getInstance());

                }


            }

            @Override
            public void onFailure(Call<PhotoData> call, Throwable t) {

                countService++;

                setUPSuccess();

                PhotoData.getInstance().setGetDocumentItemsResult(null);
                BusManheim.getInstance().post(new PhotoFailedData());
            }

        });

        Call<TitleBookData> call2 =  ManheimManager.getInstance().getService().getDocumentTitlebook(vehicleNo,"6");
        call2.enqueue(new Callback<TitleBookData>() {

            @Override
            public void onResponse(Call<TitleBookData> call, Response<TitleBookData> response) {

                countService++;

                setUPSuccess();

                if (response.body()!=null){

                    TitleBookData.getInstance().setGetDocumentByIdResult(response.body().getGetDocumentByIdResult());
                    BusManheim.getInstance().post(TitleBookData.getInstance());

                }


            }

            @Override
            public void onFailure(Call<TitleBookData> call, Throwable t) {

                countService++;

                setUPSuccess();

                TitleBookData.getInstance().setGetDocumentByIdResult(null);
                BusManheim.getInstance().post(new TitleBookFailedData());
            }

        });

        if (!barcodeString.equals("")){
            Call<CarDetailData> call3 =  ManheimManager.getInstance().getService().getCarDetailByVehicle(vehicleNo);
            call3.enqueue(new Callback<CarDetailData>() {

                @Override
                public void onResponse(Call<CarDetailData> call, Response<CarDetailData> response) {

                    countService++;

                    setUPSuccess();

                    if (response.body()!=null){

                        CarDetailData.getInstance().setGetVehicleDetailsByVehicleResult(response.body().getGetVehicleDetailsByVehicleResult());
                        CarDetailData.getInstance().setPosition(0);
                        BusManheim.getInstance().post(CarDetailData.getInstance());
                    }


                }

                @Override
                public void onFailure(Call<CarDetailData> call, Throwable t) {

                    countService++;

                    setUPSuccess();

                    //CarDetailData.getInstance().setGetVehicleDetailsByVehicleResult(null);
                    //BusManheim.getInstance().post(CarDetailData.getInstance());
                }

            });
        }



        Call<SimucastData> call4 =  ManheimManager.getInstance().getService().getDocumentSimucast(vehicleNo,"4");
        call4.enqueue(new Callback<SimucastData>() {

            @Override
            public void onResponse(Call<SimucastData> call, Response<SimucastData> response) {

                countService++;

                setUPSuccess();

                if (response.body()!=null){

                    SimucastData.getInstance().setGetDocumentByIdResult(response.body().getGetDocumentByIdResult());
                    BusManheim.getInstance().post(SimucastData.getInstance());

                }


            }

            @Override
            public void onFailure(Call<SimucastData> call, Throwable t) {

                countService++;

                setUPSuccess();

                SimucastData.getInstance().setGetDocumentByIdResult(null);
                BusManheim.getInstance().post(new SimucastFailedData());
            }

        });
        Call<OCPBData> call5 =  ManheimManager.getInstance().getService().getOCPB(Lang.getInstance().getLang(), VehicleData.getInstance().getVehicleNo());
        call5.enqueue(new Callback<OCPBData>() {

            @Override
            public void onResponse(Call<OCPBData> call, Response<OCPBData> response) {

                countService++;

                setUPSuccess();

                if (response.body()!=null){

                    OCPBData.getInstance().setGetOCPBResult(response.body().getGetOCPBResult());
                    BusManheim.getInstance().post(OCPBData.getInstance());

                }


            }

            @Override
            public void onFailure(Call<OCPBData> call, Throwable t) {

                countService++;

                setUPSuccess();

                OCPBData.getInstance().setGetOCPBResult(null);
                BusManheim.getInstance().post(new OCPBFailedData());
            }

        });
    }

    public void setUPSuccess(){
        if (countService >= 4) {
            if (progressBar.isShowing()) {
                progressBar.dismiss();
            }
        }
    }

    @Subscribe
    public void setRegistration(CarDetailData carDetailData){
        try {
            search.setText(CarDetailData.getInstance().getGetVehicleDetailsByVehicleResult().get(0).getRegistration());
            toolbar.setTitle(toolbarTitle[0]);
            nodata.setVisibility(View.GONE);

            setUpViewpager();

            setUpTap();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),getString(R.string.no_data_in_system),Toast.LENGTH_SHORT).show();

            search.setText("Search by a lot or registration");

            search.setPaintFlags(0);

            toolbar.setTitle(R.string.app_name);
            nodata.setVisibility(View.VISIBLE);

            viewPager = (ViewPager) findViewById(R.id.view_pager);
            viewPager.setAdapter(null);

        }

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.website) {
            startActivity(new Intent(this,WebActivity.class).putExtra("url","https://www.manheimthailand.com").putExtra("pageTitle","Website"));
        } else if (id == R.id.catalogue) {
            startActivity(new Intent(this,WebActivity.class).putExtra("url","https://www.manheimthailand.com/en/site/calendar").putExtra("pageTitle","Catalogue"));
        } else if (id == R.id.wishlist) {
            startActivity(new Intent(this,WishListActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_id);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private boolean backAgain = true;
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_id);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (backAgain){
                Toast.makeText(this,"Please click BACK Again to exit", Toast.LENGTH_LONG).show();

                backAgain = false;

            }else{
                super.onBackPressed();
            }
        }
    }

    public class CategoryOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {


            active = position;

            setupTabIcons(position);

            getSupportActionBar().setTitle(toolbarTitle[position]);



        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    public void callServiceData(){
        progressBar = ProgressDialog.show(this, null, "Loading...");

        Call<AuctionData> call =  ManheimManager.getInstance().getService().getAuctionByDate(DateData.getInstance().getDateString());
        call.enqueue(new Callback<AuctionData>() {

            @Override
            public void onResponse(Call<AuctionData> call, Response<AuctionData> response) {

                progressBar.dismiss();

                if (response.body()!=null){
                    try {
                        AuctionData.getInstance().setGetAuctionByDateResult(response.body().getGetAuctionByDateResult());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (response.body().getGetAuctionByDateResult().size()==0){
                    today_auction = false;

                    noDataTxt.setText(R.string.no_auction);
                    noDataTxt.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.favSelected));
                    noDataTxt.setTextSize(25);
                    noDataTxt.setTypeface(null, Typeface.BOLD);
                }else{
                    today_auction = true;
                }

            }

            @Override
            public void onFailure(Call<AuctionData> call, Throwable t) {
                progressBar.dismiss();
            }

        });

        realm = Realm.getDefaultInstance();


        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                queryCar = realm.where(CarData.class).notEqualTo("saveDate", DateData.getInstance().getDateString()).findAll();
                if (queryCar.size() > 0) {
                    queryCar.deleteAllFromRealm();
                }
                queryPic = realm.where(PicData.class).notEqualTo("saveDate", DateData.getInstance().getDateString()).findAll();
                if (queryPic.size() > 0) {
                    queryPic.deleteAllFromRealm();
                }
            }
        });
    }


}
