/*
 * *
 * Created by DuoSecondPorject on 7/5/22, 8:44 PM
 * Copyright (c) $today.year.All rights reserved.
 * Last modified 7/5/22, 8:43 PM
 * /
 */

package com.sitnclean.carwash.Dashboard;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.sitnclean.carwash.R;

public class SlideOurservice extends AppCompatActivity {

    public static ViewPager viewPager;
    SlideViewOurservice adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_ourservice);

        viewPager=findViewById(R.id.viewpager);
        adapter=new SlideViewOurservice(this);
        viewPager.setAdapter(adapter);
    }
}