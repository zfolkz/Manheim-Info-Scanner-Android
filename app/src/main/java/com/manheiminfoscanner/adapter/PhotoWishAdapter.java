package com.manheiminfoscanner.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.manheiminfoscanner.interfaces.ClickListener;
import com.manheiminfoscanner.R;
import com.manheiminfoscanner.model.PicData;

import java.util.ArrayList;

import io.realm.RealmResults;
import uk.co.senab.photoview.PhotoViewAttacher;


/**
 * Created by zfolkz on 12/23/2016 AD.
 */

public class PhotoWishAdapter extends RecyclerView.Adapter<PhotoWishAdapter.MyViewHolder>{

    private Context mContext;

   ClickListener clickListener;

    RealmResults<PicData> data;

    ArrayList<Bitmap> images = new ArrayList<>();

    private PhotoViewAttacher mAttacher;

    public PhotoWishAdapter(RealmResults<PicData> carData, ArrayList<Bitmap> image , Context context) {
        data = carData;
        images = image;
        mContext = context;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_photo, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.photoWish.setText((mContext.getString(R.string.auction_code)+" : "+data.get(position).getAuctionCode()+"     "+mContext.getString(R.string.lot_number)+" : "+data.get(position).getLotNumber()));
        holder.wishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.ItemClicked(holder.getAdapterPosition());
                }
            }
        });

      /*  byte[] decodedString = Base64.decode(data.get(position).getDocumentFile(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);*/
        holder.previewPic.setImageBitmap(images.get(position));
       /* mAttacher = new PhotoViewAttacher(holder.previewPic);

        mAttacher.update();*/
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView photoWish;
        public ImageView previewPic;
        private FloatingActionButton wishList;
        private MyViewHolder(View v) {
            super(v);
            photoWish = (TextView)v.findViewById(R.id.photoWish);
            previewPic = (ImageView)v.findViewById(R.id.previewPic);
            wishList = (FloatingActionButton)v.findViewById(R.id.wishList);

        }
    }
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

}
