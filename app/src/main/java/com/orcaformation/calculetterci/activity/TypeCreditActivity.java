package com.orcaformation.calculetterci.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

import com.orcaformation.calculetterci.R;
import com.orcaformation.calculetterci.utils.LoadClass;
import com.orcaformation.calculetterci.utils.SessionManager;
import com.orcaformation.calculetterci.utils.Utils;

public class TypeCreditActivity extends Activity implements View.OnClickListener {

    private Button btnLoa;
    private Button btnLeasing;
    private Button fab;
    private SessionManager session;

    Activity activity;
    Utils utils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_credit);

        activity = this;

        // session manager
        session = new SessionManager(getApplicationContext());
        if (!session.isLoggedIn()) {
            logoutUser();
        }

        btnLoa = (Button) findViewById(R.id.btnCredit);
        btnLeasing = (Button) findViewById(R.id.btnLeasing);
        fab = (Button) findViewById(R.id.fab);


        btnLoa.setOnClickListener(this);
        btnLeasing.setOnClickListener(this);
        fab.setOnClickListener(this);


        LoadClass.loadMarques(activity);
        LoadClass.loadTarification(activity);
        //startProgressData(activity);

    }


    public void startProgressData(final Activity activity) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 8; i++) {
                    final Activity act = activity;
                    if(i == 2){

                        SystemClock.sleep(500);
                    }
                    if(i == 3){
                        LoadClass.loadPrestations(act);
                        SystemClock.sleep(500);
                    }
                    if(i == 4){
                        LoadClass.loadPacks(act);
                        SystemClock.sleep(500);
                    }
                    if(i == 5){
                        LoadClass.loadTarification(act);
                    }
                    if(i == 6){
                        //LoadClass.loadLoa(act);
                        SystemClock.sleep(500);
                    }
                    if(i == 7){
                        //LoadClass.loadLeasing(act);
                        SystemClock.sleep(500);
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
            case R.id.btnCredit:
                Intent intentCredit = new Intent(TypeCreditActivity.this, MarqueActivity.class);
                utils.saveInSharedPrefs(getApplicationContext(), "TARIF", "CHOICE", "12");
                //intentCredit.putExtra("TYPE_CREDIT","Crédit / LOA");
                startActivity(intentCredit);
                break;
            case R.id.btnLeasing:
                Intent intentLeasing = new Intent(TypeCreditActivity.this, MarqueActivity.class);
                utils.saveInSharedPrefs(getApplicationContext(), "TARIF", "CHOICE", "3");
                //intentLeasing.putExtra("TYPE_CREDIT","Leasing BOX PRO");
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
