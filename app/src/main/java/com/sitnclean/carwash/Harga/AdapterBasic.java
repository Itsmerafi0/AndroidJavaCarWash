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

import com.sitnclean.carwash.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterBasic extends RecyclerView.Adapter<AdapterBasic.MyViewHolder> {

    Context context;

    ArrayList<MobilBasic> list;


    public AdapterBasic(Context context, ArrayList<MobilBasic> list) {
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

        MobilBasic user = list.get(position);
        holder.ourservice.setText(user.getNama());
        holder.harga.setText(user.getHarga().toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView ourservice, harga;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ourservice = itemView.findViewById(R.id.ourservice);
            harga = itemView.findViewById(R.id.harga);

        }
    }

}
