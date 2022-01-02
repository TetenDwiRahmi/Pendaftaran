package com.mobile.pendaftaran;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "data_app";

    public PrefManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setAlamat(String Alamat) {
        editor.putString("Alamat", Alamat);
        editor.apply();
    }

    public String getAlamat() {
        return pref.getString("Alamat", "");
    }
}
