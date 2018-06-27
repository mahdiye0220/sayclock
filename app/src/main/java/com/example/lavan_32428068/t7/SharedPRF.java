package com.example.lavan_32428068.t7;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;



public  class SharedPRF {
  static SharedPreferences preferences;

public static void write(String key,String value ) {
    preferences = PreferenceManager.getDefaultSharedPreferences(G.context);
    SharedPreferences.Editor editor = preferences.edit();
    editor.putString(key, value);
    editor.commit();

}

 public static String read(String key) {
        preferences = PreferenceManager.getDefaultSharedPreferences(G.context);
        String value = preferences.getString( key, "");
        return value;
    }

}
