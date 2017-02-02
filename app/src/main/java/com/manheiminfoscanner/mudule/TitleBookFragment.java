package com.manheiminfoscanner.mudule;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.manheiminfoscanner.util.BusManheim;
import com.manheiminfoscanner.util.DividerItemDecoration;
import com.manheiminfoscanner.R;
import com.manheiminfoscanner.adapter.TitleBookAdapter;
import com.manheiminfoscanner.model.TitleBookData;

import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TitleBookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TitleBookFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView titleBookDetail;

    List<TitleBookData.GetDocumentByIdResultBean> titleBook;

    private PhotoViewAttacher mAttacher;
    private ProgressBar progressBar;

    public TitleBookFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TitleBookFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TitleBookFragment newInstance(String param1, String param2) {
        TitleBookFragment fragment = new TitleBookFragment();
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

        titleBook = TitleBookData.getInstance().getGetDocumentByIdResult();
        try {
            if (titleBook.size()>0){
                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

                titleBookDetail.setLayoutManager(layoutManager);

                titleBookDetail.addItemDecoration(new DividerItemDecoration(getActivity()));

                TitleBookAdapter adapter = new TitleBookAdapter(TitleBookData.getInstance().getGetDocumentByIdResult(),getActivity());
                //adapter.setClickListener(this);
                titleBookDetail.setAdapter(adapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


       /* try {


            titleBook = TitleBookData.getInstance().getGetDocumentByIdResult().get(0);
            if (titleBook!=null){
                byte[] decodedString = Base64.decode(titleBook.getDocumentFile(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                titleBookDetail.setImageBitmap(decodedByte);

                mAttacher = new PhotoViewAttacher(titleBookDetail);

                mAttacher.update();


            }else{

            }

        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    /*@Subscribe
    public void setTitleBook(TitleBookData titleBook){
        this.titleBook = TitleBookData.getInstance().getGetDocumentByIdResult().get(0);
        byte[] decodedString = Base64.decode(this.titleBook.getDocumentFile(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        titleBookDetail.setImageBitmap(decodedByte);

        mAttacher = new PhotoViewAttacher(titleBookDetail);

        mAttacher.update();

    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_title_book, container, false);
        titleBookDetail = (RecyclerView)v.findViewById(R.id.titleBookDetail);

        return v;
    }

}
