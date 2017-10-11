package com.orcaformation.calculetterci.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.orcaformation.calculetterci.R;
import com.orcaformation.calculetterci.app.AppConfig;
import com.orcaformation.calculetterci.app.AppController;
import com.orcaformation.calculetterci.entity.Credit;
import com.orcaformation.calculetterci.entity.DateFinValidite;
import com.orcaformation.calculetterci.entity.Leasing;
import com.orcaformation.calculetterci.entity.Loa;
import com.orcaformation.calculetterci.entity.Marque;
import com.orcaformation.calculetterci.entity.Pack;
import com.orcaformation.calculetterci.entity.Prestation;
import com.orcaformation.calculetterci.entity.Url;
import com.orcaformation.calculetterci.utils.DialogManager;
import com.orcaformation.calculetterci.utils.ParseJson;
import com.orcaformation.calculetterci.utils.SessionManager;
import com.orcaformation.calculetterci.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TypeCreditActivity extends Activity implements OnClickListener{

    private Button btnLogout;
    private Button btnLoa;
    private Button btnLeasing;
    private SessionManager session;
    private ProgressDialog pDialog;
    private static final String URL = "url";
    private static final String MARQUES = "marques";
    private static final String PRESTATIONS = "prestations";
    private static final String PACKS = "packs";
    private static final String CREDITS = "credits";
    private static final String LOAS = "loas";
    private static final String LEASINGS = "leasings";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_credit);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


        // session manager
        session = new SessionManager(getApplicationContext());
        if (!session.isLoggedIn()) {
            logoutUser();
        }

        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLoa = (Button) findViewById(R.id.btnLoa);
        btnLeasing = (Button) findViewById(R.id.btnLeasing);

        btnLogout.setOnClickListener(this);
        btnLoa.setOnClickListener(this);
        btnLeasing.setOnClickListener(this);

        loadUrl(session.getLogin(),session.getPassword());
        loadMarques();
        loadPrestations();
        loadPacks();
        loadCredits();
        loadLoa();
        loadLeasing();
    }

    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     * */
    private void logoutUser() {
        session.setLogin(false,null,null);
        Intent intent = new Intent(TypeCreditActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLoa:
                Intent intentCredit = new Intent(TypeCreditActivity.this, MarqueActivity.class);
                intentCredit.putExtra("TYPE_CREDIT","Crédit / LOA");
                startActivity(intentCredit);
                break;
            case R.id.btnLeasing:
                Intent intentLeasing = new Intent(TypeCreditActivity.this, MarqueActivity.class);
                intentLeasing.putExtra("TYPE_CREDIT","Leasing BOX PRO");
                startActivity(intentLeasing);
                break;
            case R.id.btnLogout:
                logoutUser();
                break;
            default:
                break;
        }
    }

    public void loadUrl(String login, String password){
        String tag_string_req = "req_get_url";
        String URL_SERVICES = String.format(AppConfig.URL_SERVICES+"?login=%1$s&password=%2$s", login, password);
        StringRequest strReq = new StringRequest(Request.Method.GET,
                URL_SERVICES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                        Url url = ParseJson.parseUrl(response);
                        if(url!=null){
                            Utils.saveInSharedPrefs(getApplicationContext(), URL, Utils.ObjToJson(url));
                            Toast.makeText(getApplicationContext(),
                                    "Url loaded... ", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(),
                                    "error parsing url... ", Toast.LENGTH_LONG).show();
                        }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public void loadMarques(){
        String tag_string_req = "req_get_marque";

        //url from shared preferences
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(URL, Context.MODE_PRIVATE);
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
                    Utils.saveInSharedPrefs(getApplicationContext(), PRESTATIONS, marquesToJson);
                    Toast.makeText(getApplicationContext(),
                            "Marques loaded... ", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),
                            "error parsing marques... ", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public void loadPrestations(){
        String tag_string_req = "req_get_prestation";

        //url from shared preferences
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(URL, Context.MODE_PRIVATE);
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
                    Utils.saveInSharedPrefs(getApplicationContext(), MARQUES, prestationsToJson);
                    Toast.makeText(getApplicationContext(),
                            "Prestations loaded... ", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),
                            "error parsing prestations... ", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public void loadPacks(){
        String tag_string_req = "req_get_pack";

        //url from shared preferences
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(URL, Context.MODE_PRIVATE);
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
                    Utils.saveInSharedPrefs(getApplicationContext(), PACKS, packsToJson);
                    Toast.makeText(getApplicationContext(),
                            "Packs loaded... ", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),
                            "error parsing packs... ", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public void loadCredits(){
        String tag_string_req = "req_get_credit";

        //url from shared preferences
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(URL, Context.MODE_PRIVATE);
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
                    Utils.saveInSharedPrefs(getApplicationContext(), CREDITS, creditsToJson);
                    Toast.makeText(getApplicationContext(),
                            "Credits loaded... ", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),
                            "error parsing credits... ", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public void loadLoa(){
        String tag_string_req = "req_get_loa";

        //url from shared preferences
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(URL, Context.MODE_PRIVATE);
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
                    Utils.saveInSharedPrefs(getApplicationContext(), LOAS, LoasToJson);
                    Toast.makeText(getApplicationContext(),
                            "Loas loaded... ", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),
                            "error parsing loas... ", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public void loadLeasing(){
        String tag_string_req = "req_get_leasing";

        //url from shared preferences
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(URL, Context.MODE_PRIVATE);
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
                    Utils.saveInSharedPrefs(getApplicationContext(), LEASINGS, LeasingsToJson);
                    Toast.makeText(getApplicationContext(),
                            "Leasings loaded... ", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),
                            "error parsing leasings... ", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

}
