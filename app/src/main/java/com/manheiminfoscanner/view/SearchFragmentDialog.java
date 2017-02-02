package com.manheiminfoscanner.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.manheiminfoscanner.R;
import com.manheiminfoscanner.model.AuctionData;
import com.manheiminfoscanner.model.CarDetailData;
import com.manheiminfoscanner.model.DateData;
import com.manheiminfoscanner.model.Lang;
import com.manheiminfoscanner.model.LocationData;
import com.manheiminfoscanner.service.ManheimManager;
import com.manheiminfoscanner.model.SearchData;
import com.manheiminfoscanner.model.SearchNodata;
import com.manheiminfoscanner.model.VehicleData;
import com.manheiminfoscanner.util.BusManheim;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zfolkz on 9/22/16 AD.
 */
public class SearchFragmentDialog extends DialogFragment {
    public static boolean result = false;
    private TextView current_date,lane;
    private EditText searchFilter;
    private RadioGroup group;
    RadioGroupOnCheckedChangeListener radioGroupOnCheckedChangeListener = new RadioGroupOnCheckedChangeListener();

    DropDownSelect dropDownSelect = new DropDownSelect();
    String registrationEx = "^[0-9ก-ฮ][ก-ฮ][ก-ฮ]?-[0-9]{1,4}$";
    String lotEx = "^[0-9]{4,4}$";



    private ArrayList<String> arrayList = new ArrayList<String>();


    String matcher = lotEx;
    private Button search_button;
    private Button cancel_button;
    private ProgressDialog progressBar;

    Spinner dropDown;
    private View rootAuctionCode;

    ArrayList<String> lotNumber = new ArrayList<>();
    ArrayList<String> auctionCode = new ArrayList<>();
    ArrayList<String> vehicleNo = new ArrayList<>();

    @Override
    public void onStart() {
        super.onStart();
        BusManheim.getInstance().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        BusManheim.getInstance().unregister(this);
    }

    static SearchFragmentDialog newInstance(int num) {
        SearchFragmentDialog f = new SearchFragmentDialog();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);

        return f;
    }



   @Override
   public Dialog onCreateDialog(Bundle savedInstanceState) {

       //getting proper access to LayoutInflater is the trick. getLayoutInflater is a                   //Function
       LayoutInflater inflater = getActivity().getLayoutInflater();

       View view = inflater.inflate(R.layout.searc_filter_dialog, null);

       lane = (TextView)view.findViewById(R.id.lane);

       //viewPager = (ViewPager) view.findViewById(R.id.view_pager);

       current_date = (TextView)view.findViewById(R.id.current_date);


       current_date.append(" "+DateData.getInstance().getDateString());

       group = (RadioGroup) view.findViewById(R.id.group);


       dropDown = (Spinner) view.findViewById(R.id.dropDown);

       rootAuctionCode = view.findViewById(R.id.rootAuctionCode);

       search_button = (Button)view.findViewById(R.id.search_button);
       search_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               hideKeyboardFrom(getContext(),view);

               dismiss();

               if (searchFilter.getText().toString().matches(matcher)) {

                   if (matcher.equals(lotEx)){
                       callService(true);
                   }else{
                       callService(false);
                   }


               }else{
                   searchFilter.setError(getString(R.string.search_error));
               }
               // BusManheim.getInstance().post(new SearchData("ฆฉ-8230"));
               //dismiss();
           }
       });

       cancel_button = (Button)view.findViewById(R.id.cancel_button);
       cancel_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               dismiss();
           }
       });


       searchFilter = (EditText)view.findViewById(R.id.searchFilter);


       searchFilter.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int j, int j1, int j2) {

               try {
                   lane.setText("");
                   dropDown.setVisibility(View.GONE);

                   if (searchFilter.getText().toString().matches(matcher)) {



                       if (matcher.equals(lotEx)){




                           if (AuctionData.getInstance().getGetAuctionByDateResult()!=null && AuctionData.getInstance().getGetAuctionByDateResult().size()>0){

                               progressBar = ProgressDialog.show(getContext(), null, "Loading...");


                               lotNumber = new ArrayList<>();
                               auctionCode = new ArrayList<>();
                               vehicleNo = new ArrayList<>();

                               for (int i = 0; i < AuctionData.getInstance().getGetAuctionByDateResult().size(); i++) {
                                   if (AuctionData.getInstance().getGetAuctionByDateResult().get(i).getLotNumber().equals(searchFilter.getText().toString())) {
                                       //Toast.makeText(getContext(), AuctionData.getInstance().getGetAuctionByDateResult().get(i).getVehicle(), Toast.LENGTH_LONG).show();

                                       VehicleData.getInstance().setVehicleNo(AuctionData.getInstance().getGetAuctionByDateResult().get(i).getVehicle());

                                       //VehicleData.getInstance().setAuctionCode(AuctionData.getInstance().getGetAuctionByDateResult().get(i).getAuctionCode());
                                       auctionCode.add(AuctionData.getInstance().getGetAuctionByDateResult().get(i).getAuctionCode());
                                       lotNumber.add(AuctionData.getInstance().getGetAuctionByDateResult().get(i).getLotNumber());
                                       vehicleNo.add(AuctionData.getInstance().getGetAuctionByDateResult().get(i).getVehicle());
                                   }
                               }
                               if (lotNumber.size()==0) {
                                   lane.setText(R.string.no_found_data);


                                   if (progressBar.isShowing()) {
                                       progressBar.dismiss();
                                   }
                               }else if (lotNumber.size()==1){

                                   lane.setText("Lane : "+lotNumber.get(0).charAt(0));

                                   search_button.setEnabled(true);

                                   if (progressBar.isShowing()) {
                                       progressBar.dismiss();
                                   }

                                   search_button.setTextColor(Color.parseColor("#0E2A56"));
                               }else{

                                   callServiceLocation();

                                   if (progressBar.isShowing()) {
                                       progressBar.dismiss();
                                   }
                               }

                           }else{
                               callServiceData();
                           }



                           //callService(true);
                       }else{



                           if (AuctionData.getInstance().getGetAuctionByDateResult()!=null && AuctionData.getInstance().getGetAuctionByDateResult().size()>0) {

                               progressBar = ProgressDialog.show(getContext(), null, "Loading...");

                               lotNumber = new ArrayList<>();
                               auctionCode = new ArrayList<>();
                               vehicleNo = new ArrayList<>();

                               for (int i = 0; i < AuctionData.getInstance().getGetAuctionByDateResult().size(); i++) {
                                   if (AuctionData.getInstance().getGetAuctionByDateResult().get(i).getRegistration().equals(searchFilter.getText().toString())) {
                                       //Toast.makeText(getContext(), AuctionData.getInstance().getGetAuctionByDateResult().get(i).getVehicle(), Toast.LENGTH_LONG).show();
                                       VehicleData.getInstance().setVehicleNo(AuctionData.getInstance().getGetAuctionByDateResult().get(i).getVehicle());

                                       lotNumber.add(AuctionData.getInstance().getGetAuctionByDateResult().get(i).getLotNumber());
                                       auctionCode.add(AuctionData.getInstance().getGetAuctionByDateResult().get(i).getAuctionCode());
                                       vehicleNo.add(AuctionData.getInstance().getGetAuctionByDateResult().get(i).getVehicle());
                                   }
                               }

                               if (lotNumber.size()==0) {

                                   lane.setText(R.string.no_found_data);

                                   if (progressBar.isShowing()) {
                                       progressBar.dismiss();
                                   }

                               }else if (lotNumber.size()==1){

                                   lane.setText("Lane : "+lotNumber.get(0).charAt(0));

                                   search_button.setEnabled(true);

                                   if (progressBar.isShowing()) {
                                       progressBar.dismiss();
                                   }

                                   search_button.setTextColor(Color.parseColor("#0E2A56"));


                               }else{

                                   callServiceLocation();

                                   if (progressBar.isShowing()) {
                                       progressBar.dismiss();
                                   }

                               }
                           }else{
                               callServiceData();

                           }

                           //callService(false);
                       }


                   }else{
                       if (searchFilter.getText().toString().length()>3){
                           lane.setText(R.string.search_error);
                       }

                       search_button.setEnabled(false);
                       search_button.setTextColor(Color.parseColor("#D11F26"));
                   }
               } catch (Exception e) {
                   e.printStackTrace();
               }


           }

           @Override
           public void afterTextChanged(Editable editable) {

           }
       });

       group.post(new Runnable() {
           @Override public void run() {
               radioGroupOnCheckedChangeListener.onCheckedChanged(group,R.id.lot);
           }
       });
       group.setOnCheckedChangeListener(radioGroupOnCheckedChangeListener);
       /*group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup radioGroup, int id) {



           }
       });*/

       //setUpViewpager();


       //final Pattern mPattern = Pattern.compile("^[0-9ก-ฮ][ก-ฮ][ก-ฮ]?-[0-9]{1,4}$");

       //callServiceLocation();


       AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
       builder.setView(view);

       return builder.create();
   }

    public class RadioGroupOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {


        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int id) {
            searchFilter.setText("");
            searchFilter.setError(null);
            if (id == R.id.lot) {
                ((RadioButton)radioGroup.findViewById(id)).setChecked(true);
                matcher = lotEx;
                searchFilter.setHint(R.string.fill_lot);
                searchFilter.setInputType(InputType.TYPE_CLASS_PHONE);
            } else if (id == R.id.registration) {
                ((RadioButton)radioGroup.findViewById(id)).setChecked(true);
                matcher = registrationEx;
                searchFilter.setHint(R.string.fill_regis);
                searchFilter.setInputType(InputType.TYPE_CLASS_TEXT);
            }
        }
    }


    public class DropDownSelect implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
            VehicleData.getInstance().setVehicleNo(vehicleNo.get(pos));
            VehicleData.getInstance().setAuctionCode(auctionCode.get(pos));
            search_button.setEnabled(true);
            search_button.setTextColor(Color.parseColor("#0E2A56"));
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void callService(boolean isLot){

        //progressBar = ProgressDialog.show(getContext(), null, "Loading...");

        if (isLot){

            Call<CarDetailData> call =  ManheimManager.getInstance().getService().getCarDetailByLot(DateData.getInstance().getDateString(),searchFilter.getText().toString());
            call.enqueue(new Callback<CarDetailData>() {

                @Override
                public void onResponse(Call<CarDetailData> call, Response<CarDetailData> response) {
                    /*if (progressBar.isShowing()) {
                        progressBar.dismiss();
                    }*/
                    if (response.body()!=null){


                        try {

                            if (response.body().getGetVehicleDetailsByLotResult().size()>1){
                                for (int i = 0; i < response.body().getGetVehicleDetailsByLotResult().size(); i++) {
                                    if (VehicleData.getInstance().getAuctionCode().equals(response.body().getGetVehicleDetailsByLotResult().get(i).getAuctionCode())){
                                        CarDetailData.getInstance().setGetVehicleDetailsByVehicleResult(response.body().getGetVehicleDetailsByLotResult());
                                        CarDetailData.getInstance().setPosition(i);
                                        BusManheim.getInstance().post(new SearchData(CarDetailData.getInstance().getGetVehicleDetailsByVehicleResult().get(i).getRegistration()));
                                    }
                                }
                            }else{
                                CarDetailData.getInstance().setGetVehicleDetailsByVehicleResult(response.body().getGetVehicleDetailsByLotResult());
                                CarDetailData.getInstance().setPosition(0);
                                BusManheim.getInstance().post(new SearchData(CarDetailData.getInstance().getGetVehicleDetailsByVehicleResult().get(0).getRegistration()));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            BusManheim.getInstance().post(new SearchNodata());

                        }

                        dismiss();
                    }


                }

                @Override
                public void onFailure(Call<CarDetailData> call, Throwable t) {
                    if (progressBar.isShowing()) {
                        progressBar.dismiss();
                    }
                }

            });

        }else{

            Call<CarDetailData> call =  ManheimManager.getInstance().getService().getCarDetailByReg(DateData.getInstance().getDateString(),searchFilter.getText().toString());
            call.enqueue(new Callback<CarDetailData>() {

                @Override
                public void onResponse(Call<CarDetailData> call, Response<CarDetailData> response) {
                    /*if (progressBar.isShowing()) {
                        progressBar.dismiss();
                    }*/
                    if (response.body()!=null){

                        try {
                            if (response.body().getGetVehicleDetailsByRegResult().size()>1){
                                for (int i = 0; i < response.body().getGetVehicleDetailsByRegResult().size(); i++) {
                                    if (VehicleData.getInstance().getAuctionCode().equals(response.body().getGetVehicleDetailsByRegResult().get(i).getAuctionCode())){
                                        CarDetailData.getInstance().setGetVehicleDetailsByVehicleResult(response.body().getGetVehicleDetailsByRegResult());
                                        CarDetailData.getInstance().setPosition(i);
                                        BusManheim.getInstance().post(new SearchData(CarDetailData.getInstance().getGetVehicleDetailsByVehicleResult().get(i).getRegistration()));;
                                    }
                                }
                            }else{
                                CarDetailData.getInstance().setGetVehicleDetailsByVehicleResult(response.body().getGetVehicleDetailsByRegResult());
                                CarDetailData.getInstance().setPosition(0);
                                BusManheim.getInstance().post(new SearchData(CarDetailData.getInstance().getGetVehicleDetailsByVehicleResult().get(0).getRegistration()));;
                            }



                        } catch (Exception e) {
                            e.printStackTrace();
                            BusManheim.getInstance().post(new SearchNodata());

                        }

                    }
                    if (progressBar.isShowing()) {
                        progressBar.dismiss();
                    }

                }

                @Override
                public void onFailure(Call<CarDetailData> call, Throwable t) {

                }

            });

        }

    }

    public void callServiceData(){

        progressBar = ProgressDialog.show(getContext(), null, "Loading...");

        Call<AuctionData> call =  ManheimManager.getInstance().getService().getAuctionByDate(DateData.getInstance().getDateString());
        call.enqueue(new Callback<AuctionData>() {

            @Override
            public void onResponse(Call<AuctionData> call, Response<AuctionData> response) {
                if (response.body()!=null){
                    try {
                        AuctionData.getInstance().setGetAuctionByDateResult(response.body().getGetAuctionByDateResult());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (response.body().getGetAuctionByDateResult().size()==0){
                    Toast.makeText(getContext(), R.string.no_auction,Toast.LENGTH_SHORT).show();
                }

                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }

            }

            @Override
            public void onFailure(Call<AuctionData> call, Throwable t) {
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
            }

        });
    }
    public void callServiceLocation(){

        Call<LocationData> call =  ManheimManager.getInstance().getService().getAuctionLocationByDate(DateData.getInstance().getDateString());
        call.enqueue(new Callback<LocationData>() {

            @Override
            public void onResponse(Call<LocationData> call, Response<LocationData> response) {
                if (response.body()!=null){
                    try {
                        LocationData.getInstance().setGetAuctionLocationByDateResult(response.body().getGetAuctionLocationByDateResult());
                        arrayList.clear();
                        for (int j = 0; j < auctionCode.size(); j++) {
                            for (int i = 0; i < LocationData.getInstance().getGetAuctionLocationByDateResult().size(); i++) {
                                if (auctionCode.get(j).equals(LocationData.getInstance().getGetAuctionLocationByDateResult().get(i).getAuctionCode())){
                                    arrayList.add(((Lang.getInstance().getLang().equals("BU")) ? response.body().getGetAuctionLocationByDateResult().get(i).getAuctionLocation_BU():response.body().getGetAuctionLocationByDateResult().get(i).getAuctionLocation_LO()));
                                }
                            }
                        }




                        if (arrayList.size()>0){

                            dropDown.setVisibility(View.VISIBLE);

                            CustomAdapter customAdapter = new CustomAdapter(getContext(),arrayList);
                            dropDown.setAdapter(customAdapter);
                            //ArrayAdapter<String> adapterThai = new ArrayAdapter<String>(getContext(),
                            //        android.R.layout.simple_spinner_dropdown_item, arrayList);
                            //dropDown.setAdapter(adapterThai);
                            dropDown.post(new Runnable() {
                                public void run() {
                                   // dropDownSelect.onItemSelected(dropDown,dropDown,0,0);
                                    dropDown.setSelection(0);
                                    /*dropDown.setOnItemSelectedListener(DropDownSelect) {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                        }
                                    });*/
                                }
                            });
                            dropDown.setOnItemSelectedListener(dropDownSelect);

                        }else{
                            rootAuctionCode.setVisibility(View.GONE);
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<LocationData> call, Throwable t) {

            }

        });
    }

    public class CustomAdapter extends BaseAdapter{

        Context context;

        LayoutInflater inflter;

        ArrayList<String> data = new ArrayList<>();

        public CustomAdapter(Context applicationContext, ArrayList<String> data) {
            this.context = applicationContext;
            this.data = data;
            inflter = (LayoutInflater.from(applicationContext));
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = inflter.inflate(R.layout.custom_spinner, null);
            TextView txtSpinner = (TextView) view.findViewById(R.id.txtSpinner);
            txtSpinner.setText(arrayList.get(i));
            return view;
        }
    }
}
