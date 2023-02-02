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

public class AdapterHyper extends RecyclerView.Adapter<AdapterHyper.MyViewHolder> {

    Context context;

    ArrayList<MobilHyper> list;


    public AdapterHyper(Context context, ArrayList<MobilHyper> list) {
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

        MobilHyper user = list.get(position);
        holder.ourservice2.setText(user.getNama2());
        holder.harga2.setText(user.getHarga2().toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView ourservice2, harga2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ourservice2 = itemView.findViewById(R.id.ourservice);
            harga2 = itemView.findViewById(R.id.harga);

        }
    }

}
