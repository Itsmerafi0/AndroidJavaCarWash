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
import android.os.Handler;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.sitnclean.carwash.R;

public class Thanks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_thanks);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), DashBoard.class));
                finish();
            }
        }, 5000L);
    }
}