/*
 * *
 * Created by DuoSecondPorject on 7/5/22, 8:44 PM
 * Copyright (c) $today.year.All rights reserved.
 * Last modified 7/5/22, 8:43 PM
 * /
 */

package com.sitnclean.carwash.History;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sitnclean.carwash.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<Kendaraan> list;


    public MyAdapter(Context context, ArrayList<Kendaraan> list) {
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

        Kendaraan user = list.get(position);
        holder.id.setText(user.getId());
        holder.email.setText(user.getEmail());
        holder.nama.setText(user.getNama());
        holder.hp.setText(user.getHp());
        holder.date.setText(user.getDate());
        holder.time.setText(user.getTime());
        holder.ourservice.setText(user.getOurservice());
        holder.ourservice1.setText(user.getOurservice1());
        holder.ourservice2.setText(user.getOurservice2());
        holder.address.setText(user.getAddress());
        holder.plat.setText(user.getPlat());
        holder.othinformation.setText(user.getOthinformation());
        holder.total.setText(user.getTotal());
        holder.status.setText(user.getStatus());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id, email, nama, hp, address, date, time, ourservice,ourservice1,ourservice2,plat, othinformation, total, status;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            email = itemView.findViewById(R.id.email);
            nama = itemView.findViewById(R.id.nama);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            ourservice = itemView.findViewById(R.id.ourservice);
            ourservice1 = itemView.findViewById(R.id.ourservice1);
            ourservice2 = itemView.findViewById(R.id.ourservice2);
            hp = itemView.findViewById(R.id.hp);
            address = itemView.findViewById(R.id.address);
            plat = itemView.findViewById(R.id.plat);
            othinformation = itemView.findViewById(R.id.othinformation);
            total = itemView.findViewById(R.id.total);
            status = itemView.findViewById(R.id.status);

        }
    }

}
