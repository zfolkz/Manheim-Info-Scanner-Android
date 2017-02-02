package com.manheiminfoscanner.mudule;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.manheiminfoscanner.util.BusManheim;
import com.manheiminfoscanner.R;
import com.manheiminfoscanner.model.SimucastData;

import uk.co.senab.photoview.PhotoViewAttacher;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SimucastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SimucastFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView simucastDetail;
    private PhotoViewAttacher mAttacher;
    SimucastData.GetDocumentByIdResultBean simucastData;
    private ProgressBar progressBar;

    public SimucastFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SimucastFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SimucastFragment newInstance(String param1, String param2) {
        SimucastFragment fragment = new SimucastFragment();
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

            simucastData = SimucastData.getInstance().getGetDocumentByIdResult().get(0);

            if (simucastData!=null){
                byte[] decodedString = Base64.decode(simucastData.getDocumentFile(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                simucastDetail.setImageBitmap(decodedByte);

                mAttacher = new PhotoViewAttacher(simucastDetail);

                mAttacher.update();


            }

        } catch (Exception e) {
            e.printStackTrace();


        }
    }

    /*@Subscribe
    public void setSimucast(SimucastData simucastData){
        this.simucastData = SimucastData.getInstance().getGetDocumentByIdResult().get(0);
        byte[] decodedString = Base64.decode(this.simucastData.getDocumentFile(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        simucastDetail.setImageBitmap(decodedByte);

        mAttacher = new PhotoViewAttacher(simucastDetail);

        mAttacher.update();

    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_simucast, container, false);
        simucastDetail = (ImageView)v.findViewById(R.id.simucastDetail);
        progressBar = (ProgressBar)v.findViewById(R.id.progressBar);
        return v;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
    }

}
