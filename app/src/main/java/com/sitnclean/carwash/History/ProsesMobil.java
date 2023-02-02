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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sitnclean.carwash.R;

import java.util.ArrayList;

public class ProsesMobil extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter myAdapter;
    ArrayList<Kendaraan> list;

    String email;

    Button menunggu, selesai, semua;

    private long backPressedTime;   // for back button timing less than 2 sec
    private Toast backToast;     // to hold message of exit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proses);

        menunggu = findViewById(R.id.menunggu);
        menunggu.setOnClickListener(this);

        selesai = findViewById(R.id.selesai);
        selesai.setOnClickListener(this);

        semua = findViewById(R.id.semua);
        semua.setOnClickListener(this);

        recyclerView = findViewById(R.id.userList);
        database = FirebaseDatabase.getInstance().getReference("OrderMobil");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        email = prefs.getString("email", "");

        list = new ArrayList<>();
        myAdapter = new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);

        Query query = FirebaseDatabase.getInstance().getReference("OrderMobil")
                .orderByChild("status")
                .equalTo("Proses");
        query.addListenerForSingleValueEvent(valueEventListener);

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
            case R.id.menunggu:
                startActivity(new Intent(this, MenungguMobil.class));
                break;
            case R.id.selesai:
                startActivity(new Intent(this, SelesaiMobil.class));
                break;
            case R.id.semua:
                startActivity(new Intent(this, SemuaMobil.class));
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
