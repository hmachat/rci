package com.orcaformation.calculetterci.utils;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.orcaformation.calculetterci.app.AppConfig;
import com.orcaformation.calculetterci.app.AppController;
import com.orcaformation.calculetterci.entity.Pack;
import com.orcaformation.calculetterci.entity.Prestation;
import com.orcaformation.calculetterci.entity.XmlTarification;

import java.util.ArrayList;

/**
 * Created by PC_MA22 on 23/10/2017.
 */

public class LoadClass {

    public static void loadMarques(final Activity activity){
        String tag_string_req = "req_get_marque";
        StringRequest strReq = new StringRequest(Request.Method.GET,
                AppConfig.URL_MARQUE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Boolean marqueLoaded = ParseJson.parseMarqueIntoDB(response,activity);
                if(marqueLoaded){
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
                AppConfig.URL_PRESTATION, new Response.Listener<String>() {
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
                AppConfig.URL_PACK, new Response.Listener<String>() {
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

    public static void loadTarification(final Activity activity){
        /* ****************************************************************************************
                                                     CREDIT
        ******************************************************************************************/
        String tag_string_req_credit = "req_get_credit";
        StringRequest strReqCredit = new StringRequest(Request.Method.GET,
                AppConfig.URL_CREADIT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<XmlTarification> credits = ParseJson.parseTarificationIntoDB(response, XmlTarification.CREDIT, activity);
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
        AppController.getInstance().addToRequestQueue(strReqCredit, tag_string_req_credit);

        /* ****************************************************************************************
                                                     LOA
        ******************************************************************************************/
        String tag_string_req_loa = "req_get_loa";
        StringRequest strReqLoa = new StringRequest(Request.Method.GET,
                AppConfig.URL_LOA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<XmlTarification> Loas = ParseJson.parseTarificationIntoDB(response, XmlTarification.LOA, activity);
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
        AppController.getInstance().addToRequestQueue(strReqLoa, tag_string_req_loa);

        /* ****************************************************************************************
                                                     LOA
        ******************************************************************************************/
        String tag_string_req_leasing = "req_get_leasing";
        StringRequest strReqLeasing = new StringRequest(Request.Method.GET,
                AppConfig.URL_LEASING, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<XmlTarification> Leasings = ParseJson.parseTarificationIntoDB(response, XmlTarification.LEASING, activity);
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
        AppController.getInstance().addToRequestQueue(strReqLeasing, tag_string_req_leasing);

    }

}
