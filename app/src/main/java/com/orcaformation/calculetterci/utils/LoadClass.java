package com.orcaformation.calculetterci.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.orcaformation.calculetterci.app.AppController;
import com.orcaformation.calculetterci.content.DBAdapter;
import com.orcaformation.calculetterci.entity.Credit;
import com.orcaformation.calculetterci.entity.Leasing;
import com.orcaformation.calculetterci.entity.Loa;
import com.orcaformation.calculetterci.entity.Marque;
import com.orcaformation.calculetterci.entity.Pack;
import com.orcaformation.calculetterci.entity.Prestation;
import com.orcaformation.calculetterci.entity.Url;

import java.util.ArrayList;

/**
 * Created by PC_MA22 on 23/10/2017.
 */

public class LoadClass {

    public static void loadMarques(final Activity activity){
        String tag_string_req = "req_get_marque";
        final DBAdapter mDbhelper = new DBAdapter(activity).open();
        StringRequest strReq = new StringRequest(Request.Method.GET,
                "http://rci-bo-pp.orcaformation.com/json/marques.json", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Marque[] marques = ParseJson.parseMarque(response);
                if(marques.length!=0){
                    for (int k=0;k<marques.length - 1 ;k++) {
                         mDbhelper.createMarque(marques[k]);
                    }
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

        StringRequest strReq = new StringRequest(Request.Method.GET,
                "http://rci-bo.orcaformation.com/json/prestation.json", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Prestation[] prestations = ParseJson.parsePrestation(response);
                if(prestations.length!=0){

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


        StringRequest strReq = new StringRequest(Request.Method.GET,
                "http://rci-bo.orcaformation.com/json/pack.json", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<Pack> packs = ParseJson.parsePack(response);
                if(!packs.isEmpty()){

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


        StringRequest strReq = new StringRequest(Request.Method.GET,
                "http://rci-bo.orcaformation.com/json/13814/credit.json", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<Credit> credits = ParseJson.parseCredit(response);
                if(!credits.isEmpty()){

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


        StringRequest strReq = new StringRequest(Request.Method.GET,
                "http://rci-bo.orcaformation.com/json/13814/loa.json", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<Loa> Loas = ParseJson.parseLoa(response);
                if(!Loas.isEmpty()){

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

        StringRequest strReq = new StringRequest(Request.Method.GET,
                "http://rci-bo.orcaformation.com/json/13814/leasing.json", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<Leasing> Leasings = ParseJson.parseLeasing(response);
                if(!Leasings.isEmpty()){

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
