/*
 * *
 * Created by DuoSecondPorject on 7/5/22, 8:44 PM
 * Copyright (c) $today.year.All rights reserved.
 * Last modified 7/5/22, 8:43 PM
 * /
 */

package com.sitnclean.carwash.Login;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    //Create constructor
    public SessionManager(Context context){
        sharedPreferences = context.getSharedPreferences("AppKey", 0 );
        editor = sharedPreferences.edit();
        editor.apply();
    }

    //Create set login method
    public  void setLogin(boolean login){
        editor.putBoolean("KEY_LOGIN",login);
        editor.commit();
    }

    //Create get login method
    public boolean getLogin(){
         return sharedPreferences.getBoolean("KEY_LOGIN", false);
    }

    //Create set username method
    public void setUsername(String email){
        editor.putString("KEY_EMAIL",email);
        editor.commit();
    }

    //Create get Username method
    public String getUsername(){
        return sharedPreferences.getString("KEY_EMAIL", "");
    }

}

