package com.manheiminfoscanner.mudule;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manheiminfoscanner.util.BusManheim;
import com.manheiminfoscanner.interfaces.ClickListener;
import com.manheiminfoscanner.util.DividerItemDecoration;
import com.manheiminfoscanner.adapter.PhotoWishAdapter;
import com.manheiminfoscanner.R;
import com.manheiminfoscanner.model.PicData;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WishListPhotoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WishListPhotoFragment extends Fragment implements ClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView photoWishList;
    private Realm realm;
    private RealmResults<PicData> query;
    private PhotoWishAdapter adapter;

    private ArrayList<Bitmap> images = new ArrayList<>();


    public WishListPhotoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WishListPhotoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WishListPhotoFragment newInstance(String param1, String param2) {
        WishListPhotoFragment fragment = new WishListPhotoFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_wish_list_photo, container, false);
        photoWishList = (RecyclerView)v.findViewById(R.id.photoWishList);



        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        photoWishList.setLayoutManager(layoutManager);

        photoWishList.addItemDecoration(new DividerItemDecoration(getActivity()));


        realm = Realm.getDefaultInstance();
        query = realm.where(PicData.class).findAll();
        query.size();



        return v;

    }

    @Override
    public void onResume() {
        super.onResume();
        Bitmap decodedByte;
        for (int i = 0; i < query.size(); i++) {

            byte[] decodedString = Base64.decode(query.get(i).getDocumentFile(), Base64.DEFAULT);
            decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            images.add(decodedByte);

        }


        adapter = new PhotoWishAdapter(query,images,getActivity());
        adapter.setClickListener(this);
        photoWishList.setAdapter(adapter);
    }

    @Override
    public void ItemClicked(int position) {
        deletePhotoData(position);
    }

    public void deletePhotoData(final int position){

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                query = realm.where(PicData.class).equalTo("DocumentFile", query.get(position).getDocumentFile()).equalTo("Vehicle", query.get(position).getVehicle()).findAll();
                if (query.size()>0) {
                    query.deleteAllFromRealm();
                    reloadData();
                }
            }
        });
    }

    private void reloadData(){
        query = realm.where(PicData.class).findAll();
        adapter.notifyDataSetChanged();

    }
}
