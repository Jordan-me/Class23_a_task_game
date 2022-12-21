package com.example.class23_a_task_game;

import android.content.Context;
import android.content.SharedPreferences;

public class MSPV {
    private final String SP_FILE = "SP_FILE";

    private static MSPV mySPV;
    private SharedPreferences sharedPreferences;

    public static MSPV getMySPV() {
        return mySPV;
    }

    private MSPV(Context context) {
        sharedPreferences = context.getApplicationContext().getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
    }

    public static MSPV init(Context context) {
        if (mySPV == null) {
            mySPV = new MSPV(context);
        }
        return mySPV;
    }

    public String getString(String KEY, String defValue) {
        if(sharedPreferences==null){
            return null;
        }else
            return sharedPreferences.getString(KEY, defValue);
    }

    public void putString(String KEY, String value) {
        sharedPreferences.edit().putString(KEY, value).apply();
    }

    public int getInt(String KEY, int defValue) {
        return sharedPreferences.getInt(KEY, defValue);
    }

    public void putInt(String KEY, int value) {
        sharedPreferences.edit().putInt(KEY, value).apply();
    }


    public void putDouble(String KEY, double defValue) {
        putString(KEY, String.valueOf(defValue));
    }

    public double getDouble(String KEY, double defValue) {
        return Double.parseDouble(getString(KEY, String.valueOf(defValue)));
    }


}
