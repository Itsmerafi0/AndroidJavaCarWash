/*
 * *
 * Created by DuoSecondPorject on 7/5/22, 8:44 PM
 * Copyright (c) $today.year.All rights reserved.
 * Last modified 7/5/22, 8:43 PM
 * /
 */

package com.sitnclean.carwash.Dashboard;

import static java.lang.Integer.parseInt;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.sitnclean.carwash.Harga.MobilBasic;

import com.sitnclean.carwash.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sitnclean.carwash.databinding.ActivityMotorBinding;

import java.util.ArrayList;
import java.util.Calendar;

public class Motor extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private Button dateButton, timeButton;

    private TextView totaltext;


    private EditText editTextnama, editTexthp, editTextaddress, editTextplat, editTextothinformation;

    ActivityMotorBinding binding;

    String email, nama, hp, date, time, ourservice,ourservice1,ourservice2,  address,  plat, othinformation, total, status;

    private static final String KEY_EMAIL = "email";

    DatabaseReference databaseMotor;

    ArrayList<String> arrayList_harga, arrayList_harga1, arrayList_harga2;
    ArrayList<String> arrayList_ourservice, arrayList_ourservice1, arrayList_ourservice2;
    ArrayList<String> arrayList_ourservice_basic, arrayList_ourservice_premium, arrayList_ourservice_hyper;
    ArrayAdapter<String> arrayAdapter_ourservice, arrayAdapter_harga, arrayAdapter_ourservice1, arrayAdapter_ourservice2, arrayAdapter_harga1, arrayAdapter_harga2, arrayAdapter_kategori, arrayAdapter_total;

    String selection_ourservice, selection_harga, selection_ourservice1, selection_ourservice2, selection_harga1, selection_harga2;

    int totalBasic = 0, totalPremium = 0, totalHyper = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMotorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        totaltext = findViewById(R.id.total);
        pesanan();

        binding.ourservice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selection_ourservice = binding.ourservice.getSelectedItem().toString().trim();
                arrayList_ourservice_basic.clear();
                arrayList_ourservice_basic.add(arrayList_harga.get(position));
                arrayAdapter_harga = new ArrayAdapter<>(Motor.this, R.layout.style_harga, arrayList_ourservice_basic);

                Log.d("test", "ourservice");
                totalBasic = Integer.parseInt(arrayList_harga.get(position));
                setHarga();
                binding.harga.setAdapter(arrayAdapter_harga);
                set_selection();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.harga.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selection_harga = binding.harga.getSelectedItem().toString().trim();
                set_selection();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.ourservice1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selection_ourservice1 = binding.ourservice1.getSelectedItem().toString().trim();
                arrayList_ourservice_premium.clear();
                arrayList_ourservice_premium.add(arrayList_harga1.get(position));
                arrayAdapter_harga1 = new ArrayAdapter<>(Motor.this, R.layout.style_harga, arrayList_ourservice_premium);

                Log.d("test", "ourservice1");
                totalPremium = Integer.parseInt(arrayList_harga1.get(position));
                setHarga();
                binding.harga1.setAdapter(arrayAdapter_harga1);
                set_selection();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.harga1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selection_harga1 = binding.harga1.getSelectedItem().toString().trim();
                set_selection();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.ourservice2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selection_ourservice2 = binding.ourservice2.getSelectedItem().toString().trim();
                arrayList_ourservice_hyper.clear();
                arrayList_ourservice_hyper.add(arrayList_harga2.get(position));
                arrayAdapter_harga2 = new ArrayAdapter<>(Motor.this, R.layout.style_harga, arrayList_ourservice_hyper);

                Log.d("test", "ourservice2");
                totalHyper = Integer.parseInt(arrayList_harga2.get(position));
                setHarga();
                binding.harga2.setAdapter(arrayAdapter_harga2);
                set_selection();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.harga2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selection_harga2 = binding.harga2.getSelectedItem().toString().trim();
                set_selection();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    void setHarga() {
        int total = 0;
        total += totalBasic;
        total += totalPremium;
        total += totalHyper;

        Log.i("totalBasic", Integer.toString(totalBasic));
        Log.i("totalPremium", Integer.toString(totalPremium));
        Log.i("totalHyper", Integer.toString(totalHyper));
        totaltext.setText(Integer.toString(total));
    }

    void pesanan() {
        arrayList_ourservice = new ArrayList<>();
        arrayList_harga = new ArrayList<>();
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        MobilBasic hargamotor = snapshot.getValue(MobilBasic.class);

                        arrayList_ourservice.add(hargamotor.getNama());
                        arrayList_harga.add(hargamotor.getHarga().toString());

                    }

                    arrayAdapter_ourservice = new ArrayAdapter<>(Motor.this, R.layout.style_ourservice, arrayList_ourservice);
                    binding.ourservice.setAdapter(arrayAdapter_ourservice);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        Query query = FirebaseDatabase.getInstance().getReference("Basicmotor");
        query.addListenerForSingleValueEvent(valueEventListener);

        arrayList_ourservice1 = new ArrayList<>();
        arrayList_harga1 = new ArrayList<>();
        ValueEventListener valueEventListener1 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        MobilBasic hargamotor = snapshot.getValue(MobilBasic.class);

                        arrayList_ourservice1.add(hargamotor.getNama());
                        arrayList_harga1.add(hargamotor.getHarga().toString());
                    }

                    arrayAdapter_ourservice1 = new ArrayAdapter<>(Motor.this, R.layout.style_ourservice, arrayList_ourservice1);
                    binding.ourservice1.setAdapter(arrayAdapter_ourservice1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        Query query1 = FirebaseDatabase.getInstance().getReference("Basicmotor");
        query1.addListenerForSingleValueEvent(valueEventListener1);

        arrayList_ourservice2 = new ArrayList<>();
        arrayList_harga2 = new ArrayList<>();
        ValueEventListener valueEventListener2 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        MobilBasic hargamotor = snapshot.getValue(MobilBasic.class);

                        arrayList_ourservice2.add(hargamotor.getNama());
                        arrayList_harga2.add(hargamotor.getHarga().toString());
                    }

                    arrayAdapter_ourservice2 = new ArrayAdapter<>(Motor.this, R.layout.style_ourservice, arrayList_ourservice2);
                    binding.ourservice2.setAdapter(arrayAdapter_ourservice2);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        Query query2 = FirebaseDatabase.getInstance().getReference("Basicmotor");
        query2.addListenerForSingleValueEvent(valueEventListener2);

        arrayList_ourservice_basic = new ArrayList<>();
        arrayList_ourservice_basic.add("");

        arrayList_ourservice_premium = new ArrayList<>();
        arrayList_ourservice_premium.add("");

        arrayList_ourservice_hyper = new ArrayList<>();
        arrayList_ourservice_hyper.add("");

    }

    void set_selection() {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        email = prefs.getString("email", "");

        binding.ordermotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editTextnama = findViewById(R.id.nama);
                editTexthp = findViewById(R.id.hp);
                dateButton = findViewById(R.id.date);
                timeButton = findViewById(R.id.time);
                editTextaddress = findViewById(R.id.address);
                editTextplat = findViewById(R.id.plat);
                editTextothinformation = findViewById(R.id.othinformation);

                nama = binding.nama.getText().toString();
                hp = binding.hp.getText().toString();
                date = binding.date.getText().toString();
                time = binding.time.getText().toString();
                ourservice = binding.ourservice.getSelectedItem().toString();
                ourservice1 = binding.ourservice1.getSelectedItem().toString();
                ourservice2 = binding.ourservice2.getSelectedItem().toString();
                total = binding.total.getText().toString();
                address = binding.address.getText().toString();
                plat = binding.plat.getText().toString();
                othinformation = binding.othinformation.getText().toString();
                status = binding.status.getText().toString();

                if (nama.isEmpty()) {
                    editTextnama.setError("name harus di isi!");
                    editTextnama.requestFocus();
                    return;
                }
                if (hp.isEmpty()) {
                    editTexthp.setError("No HP harus di isi!");
                    editTexthp.requestFocus();
                    return;
                }
                if (hp.length() < 10) {
                    editTexthp.setError("Nomor HP kurang dari 10 angka!");
                    editTexthp.requestFocus();
                    return;
                }
                if (date.isEmpty()) {
                    dateButton.setError("date harus di isi");
                    dateButton.requestFocus();
                    return;
                }
                if (time.isEmpty()) {
                    timeButton.setError("date harus di isi");
                    timeButton.requestFocus();
                    return;
                }
                if (address.isEmpty()) {
                    editTextaddress.setError("alamat harus di isi!");
                    editTextaddress.requestFocus();
                    return;
                }
                if (plat.isEmpty()) {
                    editTextplat.setError("plat harus di isi!");
                    editTextplat.requestFocus();
                    return;
                }
                if (plat.length() > 9) {
                    editTextplat.setError("Nomor Plat lebih dari 9 Karakter!");
                    editTextplat.requestFocus();
                    return;
                }
                if (othinformation.isEmpty()) {
                    editTextothinformation.setError("informasi harus di isi!");
                    editTextothinformation.requestFocus();
                    return;
                }

                {

                    databaseMotor = FirebaseDatabase.getInstance().getReference("OrderMotor");
                    String id = databaseMotor.push().getKey();

                    orderMotor ordermotor = new orderMotor(id, nama, email, hp, date, time, ourservice, ourservice1,ourservice2,  address, plat, othinformation, total, status);

                    databaseMotor.child(id).setValue(ordermotor).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            binding.nama.setText("");
                            binding.hp.setText("");
                            binding.date.setText("");
                            binding.time.setText("");
                            binding.address.setText("");
                            binding.plat.setText("");
                            binding.othinformation.setText("");
                            binding.status.setText("");

                            Toast.makeText(Motor.this, "Order Berhasil!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Motor.this, Thanks.class));

                        }
                    });
                }
            }
        });


        dateButton = findViewById(R.id.date);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerHelper mDatePickerDialogFragment;         //creates a dialog fragment with the help of datePickerHelper.java
                mDatePickerDialogFragment = new datePickerHelper(); //allocates memory for dialog box
                mDatePickerDialogFragment.show(getSupportFragmentManager(), "DATE PICK");        //shows the dialog box and calls OnDateSet


            }
        });


        timeButton = findViewById(R.id.time);
        timeButton.setOnClickListener(new View.OnClickListener() {                                //Time Picker Image Button On Click Function launches a dialog box with calling the supporting function OnTimeSet (see below)
            @Override
            public void onClick(View v) {
                timePickerHelper mTimePickerDialogFragment;         // creates a dialog fragment with the help of timePickerHelper.java
                mTimePickerDialogFragment = new timePickerHelper(); // allocates memory for dialog box
                mTimePickerDialogFragment.show(getSupportFragmentManager(), "TIME PICK");       // shows the dialog box and calls OnTimeSet
            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {                        // support method for DatePickerDialogFragment
        Calendar mCalender = Calendar.getInstance();
        mCalender.set(Calendar.YEAR, year);                                                              //gets Year, Month and Date from java.util.Calendar
        mCalender.set(Calendar.MONTH, month);
        mCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;                                // month+1 because java.util.Calender.MONTH starts from 0 so like January is 0 instead of 1
        dateButton.setText(selectedDate);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {                                // support method for TimePickerDialogFragment
        String ampm;
        Calendar mCalender = Calendar.getInstance();                                                   //gets Hour and Minute from java.util.Calendar
        mCalender.set(Calendar.HOUR_OF_DAY, hourOfDay);
        mCalender.set(Calendar.MINUTE, minute);

        String selectedTime;

        if (hourOfDay > 12) {                                                                            // to set AM & PM as calling the ampm method is lil bit complicated (not required when is24HourView is set to True)
            ampm = " PM";
            hourOfDay = hourOfDay - 12;
        } else
            ampm = " AM";

        if (minute < 9)                                                                                 // to add a 0 prefix for single digit minutes (for ex earlier it was 1:9PM instead of 1:09PM)
            selectedTime = hourOfDay + ":0" + minute + ampm;

        else
            selectedTime = hourOfDay + ":" + minute + ampm;

        timeButton.setText(selectedTime);
    }
}