package com.orcaformation.calculetterci.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.orcaformation.calculetterci.R;
import com.orcaformation.calculetterci.adapter.TypeClientAdapter;
import com.orcaformation.calculetterci.content.DBAdapter;
import com.orcaformation.calculetterci.entity.RefTypeClient;
import com.orcaformation.calculetterci.utils.Utils;

import java.util.ArrayList;

public class ClientActivity extends AppCompatActivity {
    //TextView info;

    Spinner spinnerTypeClient;
    EditText nomClient;
    EditText prenomClient;
    EditText raisonClient;
    EditText TelClient;
    EditText emailClient;
    EditText adresseClient;
    Button btnValiderClient;
    ArrayList<RefTypeClient> RefClientList = new ArrayList<>();
    String typeClientId;
    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        spinnerTypeClient = (Spinner) findViewById(R.id.spinnerTypeClient);
        nomClient = (EditText) findViewById(R.id.nomClient);
        prenomClient = (EditText) findViewById(R.id.prenomClient);
        raisonClient = (EditText) findViewById(R.id.raisonClient);
        TelClient = (EditText) findViewById(R.id.TelClient);
        emailClient = (EditText) findViewById(R.id.emailClient);
        adresseClient = (EditText) findViewById(R.id.adresseClient);


        RefClientList.clear();
        DBAdapter mDbhelper = new DBAdapter(this).open();
        final Resources res = getResources();
        Cursor cr = mDbhelper.fetchAllRefTypeClient();
        if(cr.moveToFirst()) {
            for (int i = 0; i < cr.getCount(); i++) {
                RefTypeClient refTypeClientObj = new RefTypeClient(cr.getString(cr.getColumnIndex("ref_type_client_id")),cr.getString(cr.getColumnIndex("ref_type_client_libelle")),cr.getString(cr.getColumnIndex("ref_type_client_libelle_long")),cr.getString(cr.getColumnIndex("ref_type_client_code")));
                RefClientList.add(refTypeClientObj);
                cr.moveToNext();
            }
        }else{
            Log.d("cursor : ",String.valueOf(cr.getCount()));
        }

        final TypeClientAdapter myAdapter = new TypeClientAdapter(this, R.layout.client_list_view_row,RefClientList,res);

        spinnerTypeClient.setAdapter(myAdapter);
        spinnerTypeClient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeClientId = view.getTag().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnValiderClient = (Button) findViewById(R.id.btnValiderClient);
        btnValiderClient.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                utils.saveInSharedPrefs(getApplicationContext(), "CLIENT", "TYPE_CLIENT_ID", typeClientId);
                utils.saveInSharedPrefs(getApplicationContext(), "CLIENT", "NOM_CLIENT", nomClient.getText().toString());
                utils.saveInSharedPrefs(getApplicationContext(), "CLIENT", "PRENOM_CLIENT", prenomClient.getText().toString());
                utils.saveInSharedPrefs(getApplicationContext(), "CLIENT", "RAISON_CLIENT", raisonClient.getText().toString());
                utils.saveInSharedPrefs(getApplicationContext(), "CLIENT", "TEL_CLIENT",TelClient.getText().toString());
                utils.saveInSharedPrefs(getApplicationContext(), "CLIENT", "EMAIL_CLIENT", emailClient.getText().toString());
                utils.saveInSharedPrefs(getApplicationContext(), "CLIENT", "ADRESSE_CLIENT", adresseClient.getText().toString());

                if(utils.getFromSharedPrefs(getApplicationContext(), "TARIF", "CHOICE").equals("3")){
                    Intent intent = new Intent(ClientActivity.this, FinanceLeasingActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(ClientActivity.this, TypeCreditSecondChoice.class);
                    startActivity(intent);
                }

            }
        });
    }

}