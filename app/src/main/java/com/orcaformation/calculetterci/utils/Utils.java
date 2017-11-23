package com.orcaformation.calculetterci.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import static com.orcaformation.calculetterci.utils.DialogManager.showDialog;


/**
 * Created by PC_MA22 on 04/10/2017.
 */

public class Utils {

    public static void saveInSharedPrefs(Context context, String prefName, String prefRow, String prefValue){
        SharedPreferences prefs= context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(prefRow, prefValue);
        editor.apply();
        editor.apply();
    }

    public static String getFromSharedPrefs(Context context, String prefName, String prefRow){
        SharedPreferences prefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return prefs.getString(prefRow, "");
    }

    public static String ObjToJson(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static ProgressDialog createProgressBar(Activity activity, String text){
        ProgressDialog pDialog = new ProgressDialog(activity);
        pDialog.setCancelable(false);
        pDialog.setMessage(text);
        showDialog(pDialog);

        return pDialog;
    }





}
