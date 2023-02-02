/*
 * *
 * Created by DuoSecondPorject on 7/5/22, 8:44 PM
 * Copyright (c) $today.year.All rights reserved.
 * Last modified 7/5/22, 8:43 PM
 * /
 */

package com.sitnclean.carwash.Harga;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sitnclean.carwash.R;

import java.util.ArrayList;

public class AdapterPremium extends RecyclerView.Adapter<AdapterPremium.MyViewHolder> {

    Context context;

    ArrayList<MobilPremium> list;


    public AdapterPremium(Context context, ArrayList<MobilPremium> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        MobilPremium user = list.get(position);
        holder.ourservice1.setText(user.getNama1());
        holder.harga1.setText(user.getHarga1().toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView ourservice1, harga1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ourservice1 = itemView.findViewById(R.id.ourservice);
            harga1 = itemView.findViewById(R.id.harga);

        }
    }

}
