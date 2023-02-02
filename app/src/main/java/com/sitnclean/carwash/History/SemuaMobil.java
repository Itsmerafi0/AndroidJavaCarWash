/*
 * *
 * Created by DuoSecondPorject on 7/5/22, 8:44 PM
 * Copyright (c) $today.year.All rights reserved.
 * Last modified 7/5/22, 8:43 PM
 * /
 */

package com.sitnclean.carwash.History;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sitnclean.carwash.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SemuaMobil extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter myAdapter;
    ArrayList<Kendaraan> list;

    String email;

    Button menunggu, proses, selesai;

    private long backPressedTime;   // for back button timing less than 2 sec
    private Toast backToast;     // to hold message of exit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

        menunggu = findViewById(R.id.menunggu);
        menunggu.setOnClickListener(this);

        proses = findViewById(R.id.proses);
        proses.setOnClickListener(this);

        selesai = findViewById(R.id.selesai);
        selesai.setOnClickListener(this);

        recyclerView = findViewById(R.id.userList);
        database = FirebaseDatabase.getInstance().getReference("OrderMobil");
        database.addListenerForSingleValueEvent(valueEventListener);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        email = prefs.getString("email", "");

        list = new ArrayList<>();
        myAdapter = new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);

    }

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Kendaraan user = dataSnapshot.getValue(Kendaraan.class);
                    if(user.getEmail().equalsIgnoreCase(email)) {
                        list.add(user);
                    }



                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.proses:
                startActivity(new Intent(this, ProsesMobil.class));
                break;
            case R.id.selesai:
                startActivity(new Intent(this, SelesaiMobil.class));
                break;
            case R.id.menunggu:
                startActivity(new Intent(this, MenungguMobil.class));
                break;

        }
    }

    public void onBackPressed() {


        if (backPressedTime + 2000 > System.currentTimeMillis()) {

            backToast.cancel();    // abruptly cancles the toast when pressed BACK Button *back2back*
            super.onBackPressed();
            finishAffinity();
            System.exit(0);
            return;

        } else {

            backToast = Toast.makeText(getBaseContext(), "Tekan Sekali lagi untuk keluar.",
                    Toast.LENGTH_SHORT);
            backToast.show();

        }
        backPressedTime = System.currentTimeMillis();

    }

}

