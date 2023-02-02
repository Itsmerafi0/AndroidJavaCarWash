/*
 * *
 * Created by DuoSecondPorject on 7/5/22, 8:44 PM
 * Copyright (c) $today.year.All rights reserved.
 * Last modified 7/5/22, 8:43 PM
 * /
 */

package com.sitnclean.carwash.Dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.sitnclean.carwash.R;

public class Pemesanan extends AppCompatActivity implements View.OnClickListener {

    private Button syaratdanketentuan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);

        syaratdanketentuan = (Button) findViewById(R.id.syaratdanketentuan);
        syaratdanketentuan.setOnClickListener(this);
    }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.syaratdanketentuan:
                    startActivity(new Intent(this, Syaratketentuan.class));
            }
        }
    }
