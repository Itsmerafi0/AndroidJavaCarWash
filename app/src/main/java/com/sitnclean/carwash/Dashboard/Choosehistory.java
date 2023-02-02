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

import com.sitnclean.carwash.History.SemuaMobil;
import com.sitnclean.carwash.History.SemuaMotor;
import com.sitnclean.carwash.R;


public class Choosehistory extends AppCompatActivity implements View.OnClickListener {

    Button historyMotor, historyMobil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosehistory);
        historyMotor = findViewById(R.id.historyMotor);
        historyMotor.setOnClickListener(this);

        historyMobil = findViewById(R.id.historyMobil);
        historyMobil.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.historyMobil:
                startActivity(new Intent(this, SemuaMobil.class));
                break;
            case R.id.historyMotor:
                startActivity(new Intent(this, SemuaMotor.class));
        }
    }
}