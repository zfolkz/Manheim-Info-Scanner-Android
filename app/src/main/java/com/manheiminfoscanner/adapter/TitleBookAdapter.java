package com.manheiminfoscanner.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.manheiminfoscanner.interfaces.ClickListener;
import com.manheiminfoscanner.R;
import com.manheiminfoscanner.model.TitleBookData;

import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;


/**
 * Created by zfolkz on 12/23/2016 AD.
 */

public class TitleBookAdapter extends RecyclerView.Adapter<TitleBookAdapter.MyViewHolder>{

    private Context mContext;

   ClickListener clickListener;


    PhotoViewAttacher mAttacher;

    List<TitleBookData.GetDocumentByIdResultBean> data;

    public TitleBookAdapter(List<TitleBookData.GetDocumentByIdResultBean> carData, Context context) {
        data = carData;
        mContext = context;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.titlebook, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        byte[] decodedString = Base64.decode(data.get(position).getDocumentFile(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        holder.previewPic.setImageBitmap(decodedByte);


        mAttacher = new PhotoViewAttacher(holder.previewPic);

        mAttacher.update();

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView previewPic;
        private MyViewHolder(View v) {
            super(v);
            previewPic = (ImageView)v.findViewById(R.id.previewPic);
        }
    }
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

}
