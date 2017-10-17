package com.orcaformation.calculetterci.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.orcaformation.calculetterci.app.AppConfig;
import com.orcaformation.calculetterci.app.AppController;
import com.orcaformation.calculetterci.entity.Credit;
import com.orcaformation.calculetterci.entity.Leasing;
import com.orcaformation.calculetterci.entity.Loa;
import com.orcaformation.calculetterci.entity.Marque;
import com.orcaformation.calculetterci.entity.Pack;
import com.orcaformation.calculetterci.entity.Prestation;
import com.orcaformation.calculetterci.entity.Url;

import java.util.ArrayList;

import static com.orcaformation.calculetterci.utils.DialogManager.hideDialog;
import static com.orcaformation.calculetterci.utils.Utils.createProgressBar;

/**
 * Created by Aicha on 13/10/2017.
 */


public class LoadClassIntoSharedPref {

    private static final String URL = "url";
    private static final String MARQUES = "marques";
    private static final String PRESTATIONS = "prestations";
    private static final String PACKS = "packs";
    private static final String CREDITS = "credits";
    private static final String LOAS = "loas";
    private static final String LEASINGS = "leasings";

    public static void loadUrl(final Activity activity){
        SessionManager session = new SessionManager(activity.getApplicationContext());
        String login = session.getLogin();
        String password = session.getPassword();
        String tag_string_req = "req_get_url";
        String URL_SERVICES = String.format(AppConfig.URL_SERVICES+"?login=%1$s&password=%2$s", login, password);
        StringRequest strReq = new StringRequest(Request.Method.GET,
                URL_SERVICES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Url url = ParseJson.parseUrl(response);
                if(url!=null){
                    Utils.saveInSharedPrefs(activity.getApplicationContext(), URL, Utils.ObjToJson(url));
                    Log.d("Status : ", "Url loaded... ");
                }else{
                    Toast.makeText(activity.getApplicationContext(),
                            "error parsing url... ", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity.getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public static void loadMarques(final Activity activity){
        String tag_string_req = "req_get_marque";

        //url from shared preferences
        SharedPreferences sharedPref = activity.getApplicationContext().getSharedPreferences(URL, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonResult = sharedPref.getString(URL,"");
        StringRequest strReq = new StringRequest(Request.Method.GET,
                gson.fromJson(jsonResult,Url.class).getMarques(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Marque[] maques = ParseJson.parseMarque(response);
                if(maques.length!=0){
                    Gson gson = new Gson();
                    String marquesToJson=  gson.toJson(maques);
                    Utils.saveInSharedPrefs(activity.getApplicationContext(), PRESTATIONS, marquesToJson);
                    Log.d("Status : ", "Marques loaded... ");
                }else{
                    Toast.makeText(activity.getApplicationContext(),
                            "error parsing marques... ", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity.getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public static void loadPrestations(final Activity activity){
        String tag_string_req = "req_get_prestation";

        //url from shared preferences
        SharedPreferences sharedPref = activity.getApplicationContext().getSharedPreferences(URL, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonResult = sharedPref.getString(URL,"");
        StringRequest strReq = new StringRequest(Request.Method.GET,
                gson.fromJson(jsonResult,Url.class).getPrestations(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Prestation[] prestations = ParseJson.parsePrestation(response);
                if(prestations.length!=0){
                    Gson gson = new Gson();
                    String prestationsToJson=  gson.toJson(prestations);
                    Utils.saveInSharedPrefs(activity.getApplicationContext(), MARQUES, prestationsToJson);
                    Log.d("Status : ", "Prestations loaded... ");
                }else{
                    Toast.makeText(activity.getApplicationContext(),
                            "error parsing prestations... ", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity.getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public static void loadPacks(final Activity activity){
        String tag_string_req = "req_get_pack";

        //url from shared preferences
        SharedPreferences sharedPref = activity.getApplicationContext().getSharedPreferences(URL, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonResult = sharedPref.getString(URL,"");
        StringRequest strReq = new StringRequest(Request.Method.GET,
                gson.fromJson(jsonResult,Url.class).getPacks(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<Pack> packs = ParseJson.parsePack(response);
                if(!packs.isEmpty()){
                    Gson gson = new Gson();
                    String packsToJson =  gson.toJson(packs);
                    Utils.saveInSharedPrefs(activity.getApplicationContext(), PACKS, packsToJson);
                    Log.d("Status : ", "Packs loaded... ");
                }else{
                    Toast.makeText(activity.getApplicationContext(),
                            "error parsing packs... ", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity.getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public static void loadCredits(final Activity activity){
        String tag_string_req = "req_get_credit";

        //url from shared preferences
        SharedPreferences sharedPref = activity.getApplicationContext().getSharedPreferences(URL, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonResult = sharedPref.getString(URL,"");
        StringRequest strReq = new StringRequest(Request.Method.GET,
                gson.fromJson(jsonResult,Url.class).getCredit(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<Credit> credits = ParseJson.parseCredit(response);
                if(!credits.isEmpty()){
                    Gson gson = new Gson();
                    String creditsToJson =  gson.toJson(credits);
                    Utils.saveInSharedPrefs(activity.getApplicationContext(), CREDITS, creditsToJson);
                    Log.d("Status : ", "Credits loaded... ");
                }else{
                    Toast.makeText(activity.getApplicationContext(),
                            "error parsing credits... ", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity.getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public static void loadLoa(final Activity activity){
        String tag_string_req = "req_get_loa";

        //url from shared preferences
        SharedPreferences sharedPref = activity.getApplicationContext().getSharedPreferences(URL, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonResult = sharedPref.getString(URL,"");
        StringRequest strReq = new StringRequest(Request.Method.GET,
                gson.fromJson(jsonResult,Url.class).getLoa(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<Loa> Loas = ParseJson.parseLoa(response);
                if(!Loas.isEmpty()){
                    Gson gson = new Gson();
                    String LoasToJson =  gson.toJson(Loas);
                    Utils.saveInSharedPrefs(activity.getApplicationContext(), LOAS, LoasToJson);
                    Log.d("Status : ", "Loas loaded... ");
                }else{
                    Toast.makeText(activity.getApplicationContext(),
                            "error parsing loas... ", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity.getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public static void loadLeasing(final Activity activity){
        String tag_string_req = "req_get_leasing";

        //url from shared preferences
        SharedPreferences sharedPref = activity.getApplicationContext().getSharedPreferences(URL, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonResult = sharedPref.getString(URL,"");
        StringRequest strReq = new StringRequest(Request.Method.GET,
                gson.fromJson(jsonResult,Url.class).getLeasing(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<Leasing> Leasings = ParseJson.parseLeasing(response);
                if(!Leasings.isEmpty()){
                    Gson gson = new Gson();
                    String LeasingsToJson =  gson.toJson(Leasings);
                    Utils.saveInSharedPrefs(activity.getApplicationContext(), LEASINGS, LeasingsToJson);
                    Log.d("Status : ", "Leasings loaded... ");
                }else{
                    Toast.makeText(activity.getApplicationContext(),
                            "error parsing leasings... ", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity.getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
}
