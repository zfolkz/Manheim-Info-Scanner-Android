package com.manheiminfoscanner.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.manheiminfoscanner.interfaces.ClickListener;
import com.manheiminfoscanner.R;

import java.util.ArrayList;

/**
 * Created by zfolkz on 12/23/2016 AD.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private ArrayList<Bitmap> img = new ArrayList<Bitmap>();


    private Context mContext;

    ClickListener clickListener;


    public MyAdapter(ArrayList<Bitmap> img,Context context) {
        this.img = img;
        mContext = context;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_thumb, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,int position) {
        holder.imageView.setImageBitmap(img.get(position));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.ItemClicked(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return img.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        private MyViewHolder(View v) {
            super(v);
            imageView = (ImageView)v.findViewById(R.id.galleryPhotoThumb);
        }
    }
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

}
