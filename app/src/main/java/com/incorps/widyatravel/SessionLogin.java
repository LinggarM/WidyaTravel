package com.incorps.widyatravel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionLogin {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String NOHP = "NOHP";
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";
    public static final String ALAMAT = "ALAMAT";
    public static final String FOTOPROFIL = "FOTOPROFIL";

    public SessionLogin(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String idCustomer, String namaCustomer, String noHp, String email, String password, String alamat, String fotoProfil) {
        editor.putBoolean(LOGIN, true);
        editor.putString(ID, idCustomer);
        editor.putString(NAME, namaCustomer);
        editor.putString(NOHP, noHp);
        editor.putString(EMAIL, email);
        editor.putString(PASSWORD, password);
        editor.putString(ALAMAT, alamat);
        editor.putString(FOTOPROFIL, fotoProfil);
        editor.apply();
    }

    public boolean isLogin(){
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin(){
        if (!this.isLogin()){
            Intent i = new Intent(context, LoginActivity.class);
            context.startActivity(i);
            ((DashboardActivity) context).finish();
        }
    }

    public HashMap<String, String> getUserDetail(){

        HashMap<String, String> user = new HashMap<>();
        user.put(ID, sharedPreferences.getString(ID, null));
        user.put(NAME, sharedPreferences.getString(NAME, null));
        user.put(NOHP, sharedPreferences.getString(NOHP, null));
        user.put(EMAIL, sharedPreferences.getString(EMAIL, null));
        user.put(PASSWORD, sharedPreferences.getString(PASSWORD, null));
        user.put(ALAMAT, sharedPreferences.getString(ALAMAT, null));
        user.put(FOTOPROFIL, sharedPreferences.getString(FOTOPROFIL, null));

        return user;
    }

    public void logout() {
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, LoginActivity.class);
        context.startActivity(i);
        ((DashboardActivity) context).finish();
    }
}
