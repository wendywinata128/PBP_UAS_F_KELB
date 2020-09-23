package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {
    private final SharedPreferences pref;

    public SharedPreference(Context context){
        pref = context.getSharedPreferences("mySharedPreference",Context.MODE_PRIVATE);
    }
}
