package com.manheiminfoscanner.mudule;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manheiminfoscanner.util.BusManheim;
import com.manheiminfoscanner.R;
import com.manheiminfoscanner.model.Lang;
import com.manheiminfoscanner.service.ManheimManager;
import com.manheiminfoscanner.model.OCPBData;
import com.manheiminfoscanner.model.VehicleData;
import com.squareup.otto.Subscribe;

import java.text.DateFormatSymbols;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OCPBFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OCPBFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView ocpbDetail;
    private OCPBData.GetOCPBResultBean data;
    private TextView notice;


    public OCPBFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OCPBFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OCPBFragment newInstance(String param1, String param2) {
        OCPBFragment fragment = new OCPBFragment();
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
    }

    @Override
    public void onStop() {
        super.onStop();
        BusManheim.getInstance().unregister(this);
    }
    public void callService(){
        Call<OCPBData> call =  ManheimManager.getInstance().getService().getOCPB(Lang.getInstance().getLang(), VehicleData.getInstance().getVehicleNo());
        call.enqueue(new Callback<OCPBData>() {

            @Override
            public void onResponse(Call<OCPBData> call, Response<OCPBData> response) {

                if (response.body()!=null){

                    OCPBData.getInstance().setGetOCPBResult(response.body().getGetOCPBResult());
                    BusManheim.getInstance().post(OCPBData.getInstance());

                }


            }

            @Override
            public void onFailure(Call<OCPBData> call, Throwable t) {

            }

        });
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        //callService();

        try {
            data = OCPBData.getInstance().getGetOCPBResult().get(0);

            if (data!=null){
                ocpbDetail.setText("\n "+getString(R.string.auction_code)+" : "+data.getAuctionCode()+
                        "\n IMAP : "+data.getVehicle()+
                        "\n "+getString(R.string.lot)+" : "+data.getLot()+
                        "\n "+getString(R.string.type)+" : "+data.getVehicleType()+
                        "\n "+getString(R.string.seller)+" : "+data.getSellerName()+
                        "\n "+getString(R.string.location)+" : "+data.getSellerAddress()+
                        "\n\n "+getString(R.string.capacity)+" : "+data.getEngineCapacity()+" "+data.getEngineCapacityUnit()+
                        "\n "+getString(R.string.book_service)+" : "+data.getBookService()+
                        "\n "+getString(R.string.build_year)+" : "+data.getBuildYear()+
                        "\n "+getString(R.string.price)+" : "+"ผู้ประมูลเป็นผู้เสนอราคา"+
                        "\n "+getString(R.string.date_first_reg)+" : "+converseDate(data.getDateFirstReg())+
                        "\n "+getString(R.string.reg)+" : "+data.getRegistration()+
                        "\n "+getString(R.string.chasis_number)+" : "+data.getChasisNumber()+
                        "\n "+getString(R.string.engine_number)+" : "+data.getEngineNumber()+
                        "\n "+getString(R.string.make)+" : "+data.getMake()+
                        "\n "+getString(R.string.engine_manufacturer)+" : "+data.getEngineManufacturer()+
                        "\n "+getString(R.string.colour)+" : "+data.getColour()+
                        "\n "+getString(R.string.fuel_type)+" : "+data.getFuelType()+
                        "\n "+getString(R.string.owner)+" : "+data.getOwnerNumber()+
                        "\n "+getString(R.string.obligations)+" : "+data.getCatalogueDesc()+
                        "\n "+getString(R.string.accident_car)+" : "+data.getAccidentCar()+
                        "\n "+getString(R.string.flood_car)+" : "+data.getFloodCar()+
                        "\n "+getString(R.string.distance)+" : "+data.getKm()+"ไม่การันตีเลขไมล์"+
                        "\n "+getString(R.string.category)+" : รถยนต์ใช้แล้ว"+
                        "\n" +
                        "\n วิธีใช้ : ระบุในเครื่องมือหรือขอคำแนะนำจากผู้ผลิต"+
                        "\n ข้อแนะนำการใช้ : ตรวจสอบความพร้อมของรถก่อนขับ"+
                        "\n คำเตือน : รถยนต์ขายตามสภาพกรุณาตรวจสอบสภาพทุกครั้งก่อนซื้อ"
                );
                notice.setText(getString(R.string.notice));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void setOcpb(OCPBData ocpbData){
        try {
            data = OCPBData.getInstance().getGetOCPBResult().get(0);

            if (data!=null){
                ocpbDetail.setText("\n "+getString(R.string.auction_code)+" : "+data.getAuctionCode()+
                        "\n IMAP : "+data.getVehicle()+
                        "\n "+getString(R.string.lot)+" : "+data.getLot()+
                        "\n "+getString(R.string.type)+" : "+data.getVehicleType()+
                        "\n "+getString(R.string.seller)+" : "+data.getSellerName()+
                        "\n "+getString(R.string.location)+" : "+data.getSellerAddress()+
                        "\n\n "+getString(R.string.capacity)+" : "+data.getEngineCapacity()+" "+data.getEngineCapacityUnit()+
                        "\n "+getString(R.string.book_service)+" : "+data.getBookService()+
                        "\n "+getString(R.string.build_year)+" : "+((Lang.getInstance().getLang().equals("BU")) ? data.getBuildYear() : getYear(data.getBuildYear()))+
                        "\n "+getString(R.string.price)+" : "+"ผู้ประมูลเป็นผู้เสนอราคา"+
                        "\n "+getString(R.string.date_first_reg)+" : "+converseDate(data.getDateFirstReg())+
                        "\n "+getString(R.string.reg)+" : "+data.getRegistration()+
                        "\n "+getString(R.string.chasis_number)+" : "+data.getChasisNumber()+
                        "\n "+getString(R.string.engine_number)+" : "+data.getEngineNumber()+
                        "\n "+getString(R.string.make)+" : "+data.getMake()+
                        "\n "+getString(R.string.engine_manufacturer)+" : "+data.getEngineManufacturer()+
                        "\n "+getString(R.string.colour)+" : "+data.getColour()+
                        "\n "+getString(R.string.fuel_type)+" : "+data.getFuelType()+
                        "\n "+getString(R.string.owner)+" : "+data.getOwnerNumber()+
                        "\n "+getString(R.string.obligations)+" : "+data.getCatalogueDesc()+
                        "\n "+getString(R.string.accident_car)+" : "+data.getAccidentCar()+
                        "\n "+getString(R.string.flood_car)+" : "+data.getFloodCar()+
                        "\n "+getString(R.string.distance)+" : "+data.getKm()+"ไม่การันตีเลขไมล์"+
                        "\n "+getString(R.string.category)+" : รถยนต์ใช้แล้ว"+
                        "\n" +
                        "\n วิธีใช้ : ระบุในเครื่องมือหรือขอคำแนะนำจากผู้ผลิต"+
                        "\n ข้อแนะนำการใช้ : ตรวจสอบความพร้อมของรถก่อนขับ"+
                        "\n คำเตือน : รถยนต์ขายตามสภาพกรุณาตรวจสอบสภาพทุกครั้งก่อนซื้อ"
                );
                notice.setText(getString(R.string.notice));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_ocpb, container, false);
        ocpbDetail = (TextView)v.findViewById(R.id.ocpbDetail);
        notice  = (TextView)v.findViewById(R.id.notice);
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
    public String converseDate(String date){
        if (date.equals("")){
            return "-";
        }
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
    public String getMonth(int month , Locale locale) {

        return new DateFormatSymbols().getInstance(locale).getMonths()[month-1];
    }

    public String getYear(String yearString) {
        int year = Integer.valueOf(yearString)+543;
        return String.valueOf(year);
    }
}
