package com.manheiminfoscanner.mudule;


import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.LikeView;
import com.facebook.share.widget.ShareButton;
import com.google.gson.Gson;
import com.inthecheesefactory.lib.fblike.widget.FBLikeView;
import com.manheiminfoscanner.util.BusManheim;
import com.manheiminfoscanner.interfaces.ClickListener;
import com.manheiminfoscanner.adapter.MyAdapter;
import com.manheiminfoscanner.R;
import com.manheiminfoscanner.model.CarDetailData;
import com.manheiminfoscanner.model.DateData;
import com.manheiminfoscanner.model.PhotoData;
import com.manheiminfoscanner.model.PicData;
import com.manheiminfoscanner.model.VehicleData;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import uk.co.senab.photoview.PhotoViewAttacher;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PhotoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhotoFragment extends Fragment implements ClickListener,View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView previewPhoto;
    private TextView descPhoto;
    private RecyclerView galleryPhoto;
    public static int position = 0;
    private ArrayList<PhotoData.GetDocumentItemsResultBean> data;
    private ArrayList<Bitmap> img = new ArrayList<Bitmap>();
    MyAdapter adapter;


    private static PhotoFragment ourInstance = new PhotoFragment();
    private PhotoViewAttacher mAttacher;
    private ProgressBar progressBar;
    private View rootPhoto;
    private ShareButton shareButton;
    private FloatingActionButton wishList;
    private Realm realm;
    private RealmResults<PicData> query;

    public static PhotoFragment getInstance() {
        return ourInstance;
    }


    public PhotoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PhotoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PhotoFragment newInstance(String param1, String param2) {
        PhotoFragment fragment = new PhotoFragment();
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
    public void onResume() {
        super.onResume();
        //callService();

        try {


            data = PhotoData.getInstance().getGetDocumentItemsResult();

                if (img.size()>0){
                    img.clear();
                }

                checkFavState();

                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getDocumentTypeID()==3){
                        byte[] decodedString = Base64.decode(data.get(i).getDocumentFile(), Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        img.add(decodedByte);
                    }
                }

                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

                galleryPhoto.setLayoutManager(layoutManager);


                adapter = new MyAdapter(img,getActivity());
                adapter.setClickListener(this);
                galleryPhoto.setAdapter(adapter);

                progressBar.setVisibility(View.GONE);

                rootPhoto.setVisibility(View.VISIBLE);

                galleryPhoto.smoothScrollToPosition(position);

                preView();



            //previewPhoto.setImageBitmap(img.get(position));

            //descPhoto.setText(data.get(position).getDocumentDescription_BU());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    private void checkFavState(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                query = realm.where(PicData.class).equalTo("DocumentFile", data.get(position).getDocumentFile()).findAll();
                if (query.size()>0){
                    wishList.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), R.color.favSelected)));
                }else{
                    wishList.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), R.color.colorPrimary)));
                }
            }
        });

    }
    @Subscribe
    public void setPhoto(PhotoData photoData){

        try {


            data = PhotoData.getInstance().getGetDocumentItemsResult();

                if (img.size()>0){
                    img.clear();
                }

                checkFavState();

                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getDocumentTypeID()==3){
                        byte[] decodedString = Base64.decode(data.get(i).getDocumentFile(), Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        img.add(decodedByte);
                    }

                }

                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

                galleryPhoto.setLayoutManager(layoutManager);


                adapter = new MyAdapter(img,getActivity());
                adapter.setClickListener(this);
                galleryPhoto.setAdapter(adapter);

                progressBar.setVisibility(View.GONE);

                rootPhoto.setVisibility(View.VISIBLE);

                galleryPhoto.smoothScrollToPosition(position);

                preView();


            //previewPhoto.setImageBitmap(img.get(position));

            //descPhoto.setText(data.get(position).getDocumentDescription_BU());
        } catch (Exception e) {
            e.printStackTrace();

            rootPhoto.setVisibility(View.GONE);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_photo, container, false);
        previewPhoto = (ImageView)v.findViewById(R.id.previewPhoto);

        descPhoto = (TextView)v.findViewById(R.id.descPhoto);

        galleryPhoto = (RecyclerView)v.findViewById(R.id.galleryPhoto);

        progressBar = (ProgressBar)v.findViewById(R.id.progressBar);

        rootPhoto = v.findViewById(R.id.rootPhoto);

        FBLikeView fbLikeView = (FBLikeView) v.findViewById(R.id.fbLikeView);
        fbLikeView.getLikeView().setObjectIdAndType(
                "",
                LikeView.ObjectType.OPEN_GRAPH);

        shareButton = (ShareButton)v.findViewById(R.id.fbShare);

        wishList = (FloatingActionButton)v.findViewById(R.id.wishList);

        wishList.setOnClickListener(this);

        return v;
    }
    public void preView(){

        if (img.size()>0){

            checkFavState();

            wishList.setVisibility(View.VISIBLE);

            previewPhoto.setImageBitmap(img.get(position));
            //descPhoto.setText( ((Lang.getInstance().getLang().equals("BU")) ? data.get(position).getDocumentDescription_BU() : data.get(position).getDocumentDescription_LO()));


            mAttacher = new PhotoViewAttacher(previewPhoto);

            mAttacher.update();

            SharePhoto photo = new SharePhoto.Builder()
                    .setBitmap(addWatermark(getResources(),img.get(position)))
                    .build();
            SharePhotoContent content = new SharePhotoContent.Builder()
                    .addPhoto(photo)
                    .build();

            shareButton.setShareContent(content);
        }else{
            rootPhoto.setVisibility(View.GONE);
        }

    }


    @Override
    public void ItemClicked(int position) {
        this.position = position;
        preView();
    }

    public static Bitmap addWatermark(Resources res, Bitmap source) {
        int w, h;
        Canvas c;
        Paint paint;
        Bitmap bmp, watermark;

        Matrix matrix;
        float scale;
        RectF r;

        w = source.getWidth();
        h = source.getHeight();

        // Create the new bitmap
        bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.FILTER_BITMAP_FLAG);

        // Copy the original bitmap into the new one
        c = new Canvas(bmp);
        c.drawBitmap(source, 0, 0, paint);

        // Load the watermark
        watermark = BitmapFactory.decodeResource(res, R.drawable.logo_watermark);
        // Scale the watermark to be approximately 10% of the source image height
        scale = (float) (((float) h * 0.10) / (float) watermark.getHeight());

        // Create the matrix
        matrix = new Matrix();
        matrix.postScale(scale, scale);
        // Determine the post-scaled size of the watermark
        r = new RectF(0, 0, watermark.getWidth(), watermark.getHeight());
        matrix.mapRect(r);
        // Move the watermark to the bottom right corner
        matrix.postTranslate(30, h - r.height()-30);

        // Draw the watermark
        c.drawBitmap(watermark, matrix, paint);
        // Free up the bitmap memory
        watermark.recycle();

        return bmp;
    }

    private void insertPhotoData(PhotoData.GetDocumentItemsResultBean data) {
        Gson gson = new Gson();

        data.setLotNumber(CarDetailData.getInstance().getGetVehicleDetailsByVehicleResult().get(0).getLotNumber());

        data.setAuctionCode(VehicleData.getInstance().getAuctionCode());


        data.setSaveDate(DateData.getInstance().getDateString());


        String jsonInString = gson.toJson(data);

        try {

            realm.beginTransaction();

            PicData picData = realm.createObjectFromJson(PicData.class, jsonInString);

            realm.copyToRealm(picData);

            realm.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.wishList){

            if (query.size()==0){
                insertPhotoData(data.get(position));

            }else{
                deletePhotoData();
            }

            checkFavState();

        }
    }

    public void deletePhotoData(){

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                query = realm.where(PicData.class).equalTo("DocumentFile", data.get(position).getDocumentFile()).findAll();
                if (query.size()>0) {
                    query.deleteAllFromRealm();
                }
            }
        });
    }
}
