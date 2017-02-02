package com.manheiminfoscanner.mudule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manheiminfoscanner.adapter.CarDetailWishAdapter;
import com.manheiminfoscanner.interfaces.ClickListener;
import com.manheiminfoscanner.util.DividerItemDecoration;
import com.manheiminfoscanner.R;
import com.manheiminfoscanner.model.CarData;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WishListCarDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WishListCarDetailFragment extends Fragment implements ClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView carDetailWishList;
    private RealmResults<CarData> query;


    public WishListCarDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WishListCarDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WishListCarDetailFragment newInstance(String param1, String param2) {
        WishListCarDetailFragment fragment = new WishListCarDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View v = inflater.inflate(R.layout.fragment_wish_list_car_detail, container, false);
        carDetailWishList = (RecyclerView)v.findViewById(R.id.carDetailWishList);



        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        carDetailWishList.setLayoutManager(layoutManager);

        carDetailWishList.addItemDecoration(new DividerItemDecoration(getActivity()));


        Realm realm = Realm.getDefaultInstance();
        query = realm.where(CarData.class).findAll();
        query.size();

        CarDetailWishAdapter adapter = new CarDetailWishAdapter(query,getActivity());
        adapter.setClickListener(this);
        carDetailWishList.setAdapter(adapter);

        return v;

    }


    @Override
    public void onResume() {
        super.onResume();
        CarDetailWishAdapter adapter = new CarDetailWishAdapter(query,getActivity());
        adapter.setClickListener(this);
        carDetailWishList.setAdapter(adapter);
    }

    @Override
    public void ItemClicked(int position) {
        Intent i = new Intent(getActivity(),CarDetailActivity.class);
        i.putExtra("carDetail",query.get(position));
        startActivity(i);
    }
}
