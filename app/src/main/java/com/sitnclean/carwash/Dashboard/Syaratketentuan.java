/*
 * *
 * Created by DuoSecondPorject on 7/5/22, 8:44 PM
 * Copyright (c) $today.year.All rights reserved.
 * Last modified 7/5/22, 8:43 PM
 * /
 */

package com.sitnclean.carwash.Dashboard;

import androidx.appcompat.app.AppCompatActivity;

import com.sitnclean.carwash.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Syaratketentuan extends AppCompatActivity implements View.OnClickListener{

    private Button order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syaratketentuan);

        order = (Button) findViewById(R.id.order);
        order.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order:
                startActivity(new Intent(this, SlideOurservice.class));
        }
    }


}
