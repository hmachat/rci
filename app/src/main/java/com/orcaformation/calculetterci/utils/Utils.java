package com.orcaformation.calculetterci.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.orcaformation.calculetterci.entity.Marque;
import com.orcaformation.calculetterci.entity.Url;


/**
 * Created by PC_MA22 on 04/10/2017.
 */

public class Utils {

    public static void saveInSharedPrefs(Context context, String prefName, String prefValue){
        SharedPreferences prefs= context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(prefName, prefValue);
        editor.apply();
    }

    public static String ObjToJson(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }





}