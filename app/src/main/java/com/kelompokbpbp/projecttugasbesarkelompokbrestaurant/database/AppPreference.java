package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreference {
    private static final String PREFERENCE_RUN = "PREFERENCE_FIRST_RUN";
    private static final String PREFERENCE_NAME = "PREFERENCE_APP";
    private Context context;
    private SharedPreferences pref;

    public AppPreference(Context context){
        this.context = context;
        this.pref = this.context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
    }

    public Boolean getFirstRun(){
        boolean firstRun = pref.getBoolean(PREFERENCE_RUN,true);

        if(firstRun){
            pref.edit().putBoolean(PREFERENCE_RUN,false);
            return true;
        }

        return false;
    }
}
