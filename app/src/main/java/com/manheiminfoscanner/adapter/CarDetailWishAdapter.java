package com.manheiminfoscanner.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.manheiminfoscanner.interfaces.ClickListener;
import com.manheiminfoscanner.R;
import com.manheiminfoscanner.model.CarData;

import io.realm.RealmResults;


/**
 * Created by zfolkz on 12/23/2016 AD.
 */

public class CarDetailWishAdapter extends RecyclerView.Adapter<CarDetailWishAdapter.MyViewHolder>{

    private Context mContext;

   ClickListener clickListener;

    RealmResults<CarData> data;

    public CarDetailWishAdapter(RealmResults<CarData> carData, Context context) {
        data = carData;
        mContext = context;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_cardetail, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.carDetailWish.setText(mContext.getString(R.string.auction_code)+" : "+data.get(position).getAuctionCode()+"     "+mContext.getString(R.string.lot_number)+" : "+data.get(position).getLotNumber());
        holder.carDetailWish.setOnClickListener(new View.OnClickListener() {
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
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView carDetailWish;

        private MyViewHolder(View v) {
            super(v);
            carDetailWish = (TextView)v.findViewById(R.id.carDetailWish);
        }
    }
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

}
