/*
 * *
 * Created by DuoSecondPorject on 7/5/22, 8:44 PM
 * Copyright (c) $today.year.All rights reserved.
 * Last modified 7/5/22, 8:43 PM
 * /
 */

package com.sitnclean.carwash.Dashboard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.sitnclean.carwash.R;

public class SlideViewOurservice extends PagerAdapter {

    Context ctx;

    public SlideViewOurservice(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_ourservice, container, false);

        ImageView logo = view.findViewById(R.id.logo);
        ImageView ind1 = view.findViewById(R.id.ind1);
        ImageView ind2 = view.findViewById(R.id.ind2);
        ImageView ind3 = view.findViewById(R.id.ind3);
        ImageView ind4 = view.findViewById(R.id.ind4);
        ImageView ind5 = view.findViewById(R.id.ind5);
        ImageView ind6 = view.findViewById(R.id.ind6);

        TextView desc = view.findViewById(R.id.title);

        ImageView next = view.findViewById(R.id.next);
        ImageView back = view.findViewById(R.id.back);
        ImageView Mobil = view.findViewById(R.id.mobil);
        Mobil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, Mobil.class);
                ctx.startActivity(intent);
            }
        });
        ImageView Motor = view.findViewById(R.id.motor);
        Motor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, Motor.class);
                ctx.startActivity(intent);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SlideOurservice.viewPager.setCurrentItem(position + 1);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SlideOurservice.viewPager.setCurrentItem(position - 1);
            }
        });
        switch (position) {
            case 0:
                logo.setImageResource(R.drawable.basicmobil);
                ind1.setImageResource(R.drawable.seleted);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.unselected);
                ind4.setImageResource(R.drawable.unselected);
                ind5.setImageResource(R.drawable.unselected);
                ind6.setImageResource(R.drawable.unselected);

                desc.setText("Rp.70.000");
                back.setVisibility(View.GONE);
                next.setVisibility(View.VISIBLE);
                break;
            case 1:
                logo.setImageResource(R.drawable.premiummobil);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.seleted);
                ind3.setImageResource(R.drawable.unselected);
                ind4.setImageResource(R.drawable.unselected);
                ind5.setImageResource(R.drawable.unselected);
                ind6.setImageResource(R.drawable.unselected);

                desc.setText("Rp.90.000");
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                break;

            case 2:
                logo.setImageResource(R.drawable.hypermobil);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.seleted);
                ind4.setImageResource(R.drawable.unselected);
                ind5.setImageResource(R.drawable.unselected);
                ind6.setImageResource(R.drawable.unselected);

                desc.setText("Rp.125.000");
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                break;

            case 3:
                logo.setImageResource(R.drawable.basicmotor);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.unselected);
                ind4.setImageResource(R.drawable.seleted);
                ind5.setImageResource(R.drawable.unselected);
                ind6.setImageResource(R.drawable.unselected);

                desc.setText("Rp.50.000");
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                break;

            case 4:
                logo.setImageResource(R.drawable.premiummotor);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.unselected);
                ind4.setImageResource(R.drawable.unselected);
                ind5.setImageResource(R.drawable.seleted);
                ind6.setImageResource(R.drawable.unselected);

                desc.setText("Rp.80.000");
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                break;

            case 5:
                logo.setImageResource(R.drawable.hypermotor);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.unselected);
                ind4.setImageResource(R.drawable.unselected);
                ind5.setImageResource(R.drawable.unselected);
                ind6.setImageResource(R.drawable.seleted);

                desc.setText("Rp.100.000");
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.GONE);
                break;

        }

        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
