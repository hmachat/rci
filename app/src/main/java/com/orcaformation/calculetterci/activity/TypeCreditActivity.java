package com.orcaformation.calculetterci.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
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
import com.orcaformation.calculetterci.utils.LoadClassIntoSharedPref;
import com.orcaformation.calculetterci.utils.ParseJson;
import com.orcaformation.calculetterci.utils.SessionManager;
import com.orcaformation.calculetterci.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TypeCreditActivity extends Activity implements OnClickListener{

    private Button btnLoa;
    private Button btnLeasing;
    private SessionManager session;
    private ProgressBar progressBar;
    private TextView progressBarText;
    Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_credit);

        activity = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setAlpha(0.25f);
        fab.setOnClickListener(this);


        // session manager
        session = new SessionManager(getApplicationContext());
        if (!session.isLoggedIn()) {
            logoutUser();
        }

        btnLoa = (Button) findViewById(R.id.btnLoa);
        btnLeasing = (Button) findViewById(R.id.btnLeasing);
        progressBar = (ProgressBar) findViewById(R.id.progressBarLoading);
        progressBarText = (TextView) findViewById(R.id.progressBarText);

        btnLoa.setOnClickListener(this);
        btnLeasing.setOnClickListener(this);

        startProgress(activity);
    }

    public void startProgress(final Activity activity) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 8; i++) {

                    final Activity act = activity;
                    final int value = i;

                        if(i == 1) {
                            LoadClassIntoSharedPref.loadUrl(act);
                            SystemClock.sleep(500);
                        }
                        if(i == 2){
                            LoadClassIntoSharedPref.loadMarques(act);
                            SystemClock.sleep(500);
                        }
                        if(i == 3){
                            LoadClassIntoSharedPref.loadPrestations(act);
                            SystemClock.sleep(500);
                        }
                        if(i == 4){
                            LoadClassIntoSharedPref.loadPacks(act);
                            SystemClock.sleep(500);
                        }
                        if(i == 5){
                            LoadClassIntoSharedPref.loadCredits(act);
                        }
                        if(i == 6){
                            LoadClassIntoSharedPref.loadLoa(act);
                            SystemClock.sleep(500);
                        }
                        if(i == 7){
                            LoadClassIntoSharedPref.loadLeasing(act);
                            SystemClock.sleep(500);
                        }
                    progressBar.post(new Runnable() {
                        @Override
                        public void run() {
                           progressBarText.setText("Téléchargement ...");
                            progressBar.setProgress(value);
                            if(value == 8){
                                progressBarText.setVisibility(View.INVISIBLE);
                                progressBar.setVisibility(View.INVISIBLE) ;
                            }
                        }
                    });
                }
            }
        };
        new Thread(runnable).start();
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
            case R.id.fab:
                logoutUser();
                break;
            default:
                break;
        }
    }



}
