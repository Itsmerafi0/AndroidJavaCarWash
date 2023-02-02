/*
 * *
 * Created by DuoSecondPorject on 7/5/22, 8:44 PM
 * Copyright (c) $today.year.All rights reserved.
 * Last modified 7/5/22, 8:43 PM
 * /
 */

package com.sitnclean.carwash.Dashboard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.sitnclean.carwash.Login.Login;
import com.sitnclean.carwash.R;
import com.sitnclean.carwash.Login.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DashBoard extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout order, profile, history, about;
    private TextView logout;
    FirebaseAuth mAuth;

    private DatabaseReference reference;
    private String userID;
    private FirebaseUser user;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";

    private long backPressedTime;   // for back button timing less than 2 sec
    private Toast backToast;     // to hold message of exit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        order = (LinearLayout) findViewById(R.id.order);
        order.setOnClickListener(this);

        profile = (LinearLayout) findViewById(R.id.profile);
        profile.setOnClickListener(this);

        history = (LinearLayout) findViewById(R.id.history);
        history.setOnClickListener(this);

        about = (LinearLayout) findViewById(R.id.about);
        about.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();

                editor.clear();
                editor.commit();
                Intent intent = new Intent(DashBoard.this, Login.class);
                startActivity(intent);
                Toast.makeText(DashBoard .this,"Berhasil Logout", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    user = FirebaseAuth.getInstance().getCurrentUser();
    reference = FirebaseDatabase.getInstance().getReference("Users");
    userID = user.getUid();

    final TextView fullnameTextView = (TextView) findViewById(R.id.fullName);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            User userProfile = snapshot.getValue(User.class);

            if (userProfile != null) {
                String fullName = userProfile.fullname;

                fullnameTextView.setText(fullName);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(DashBoard.this, "Something wrong happened!", Toast.LENGTH_LONG).show();
        }
    });
}

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout:
                startActivity(new Intent(this, Login.class));
            case R.id.order:
                startActivity(new Intent(this, Pemesanan.class));
                break;
            case R.id.profile:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
            case R.id.history:
                startActivity(new Intent(this, Choosehistory.class));
                break;
            case R.id.about:
                startActivity(new Intent(this, About.class));
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

            backToast = Toast.makeText(getBaseContext(), "Tekan Sekali lagi untuk keluar Sit N Clean.",
                    Toast.LENGTH_SHORT);
            backToast.show();

        }
        backPressedTime = System.currentTimeMillis();

    }

}
