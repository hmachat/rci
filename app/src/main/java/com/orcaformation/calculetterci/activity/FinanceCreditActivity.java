package com.orcaformation.calculetterci.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import com.orcaformation.calculetterci.R;
import com.orcaformation.calculetterci.adapter.TarificationAdapter;
import com.orcaformation.calculetterci.adapter.TauxAdapter;
import com.orcaformation.calculetterci.content.DBAdapter;
import com.orcaformation.calculetterci.entity.TblXmlBaremes;
import com.orcaformation.calculetterci.entity.TblXmlProduit;
import com.orcaformation.calculetterci.entity.XmlTarification;
import com.orcaformation.calculetterci.utils.Utils;

import java.util.ArrayList;

public class FinanceCreditActivity extends AppCompatActivity {

    EditText prixVeh;
    EditText apport;
    CheckBox assuranceOne;
    CheckBox assuranceTwo;
    CheckBox assuranceThree;
    ListView listBaremeView;
    ListView listTauxView;
    ListView listDureeView;
    Utils utils;
    ArrayList<XmlTarification> xmlTarificationList = new ArrayList<>();
    ArrayList<TblXmlBaremes> tblXmlBaremesList = new ArrayList<>();
    String xmlTarificationId;
    String baremId;
    String dureeMin;
    String dureeMax;
    String pasDureeId;
    String pas;
    Button btnCalculer;
    String tna;
    ArrayList<TblXmlProduit> tblXmlProduitList = new ArrayList<TblXmlProduit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance_credit);
        Intent intent = getIntent();



        prixVeh = (EditText)findViewById(R.id.prixVeh);
        apport = (EditText)findViewById(R.id.apport);
        assuranceOne = (CheckBox) findViewById(R.id.assuranceOne);
        assuranceOne.setChecked(false);
        assuranceOne.setVisibility(View.INVISIBLE);
        assuranceTwo = (CheckBox) findViewById(R.id.assuranceTwo);
        assuranceTwo.setChecked(false);
        assuranceTwo.setVisibility(View.INVISIBLE);
        assuranceThree = (CheckBox) findViewById(R.id.assuranceThree);
        assuranceThree.setChecked(false);
        assuranceThree.setVisibility(View.INVISIBLE);
        listBaremeView = (ListView) findViewById(R.id.listBaremeView);
        listTauxView = (ListView) findViewById(R.id.listTauxView);
        listDureeView = (ListView) findViewById(R.id.listDureeView);
        btnCalculer = (Button) findViewById(R.id.btnCalculer);

        final DBAdapter mDbhelper = new DBAdapter(this).open();


        if(utils.getFromSharedPrefs(getApplicationContext(), "INFO_VEH", "MONTANT").equals("")){
            Cursor crMontant = mDbhelper.fetchPrixTTCByModeleId(utils.getFromSharedPrefs(getApplicationContext(), "INFO_VEH", "MODELE_ID"));
            if(crMontant.moveToFirst()) {
                for (int i = 0; i < crMontant.getCount(); i++) {
                    prixVeh.setText(crMontant.getString(crMontant.getColumnIndex("prix_ttc")));
                    crMontant.moveToNext();
                }
            }else{
                prixVeh.setText(utils.getFromSharedPrefs(getApplicationContext(), "INFO_VEH", "MONTANT"));
            }
        }else{
            prixVeh.setText(utils.getFromSharedPrefs(getApplicationContext(), "INFO_VEH", "MONTANT"));
        }



        //fetchBaremeLibelleByTypeTarificationAndTypeClientId
        Cursor cr = mDbhelper.fetchBaremeLibelleByTypeTarificationAndTypeClientId(utils.getFromSharedPrefs(getApplicationContext(), "TARIF", "CHOICE"), utils.getFromSharedPrefs(getApplicationContext(), "CLIENT", "TYPE_CLIENT_ID"));
        Log.d("cursor size : ",String.valueOf(cr.getCount()));
        //String[] tarificationLibelleList = new String[cr.getCount()];
        if(cr.moveToFirst()) {
            Log.d("cursor empty : ","no" );
            for (int i = 0; i < cr.getCount(); i++) {
                XmlTarification xmlTarificationObj = new XmlTarification(cr.getString(cr.getColumnIndex("_tarification_id")), cr.getString(cr.getColumnIndex("tarification_libelle")));
                xmlTarificationList.add(xmlTarificationObj);
                //tarificationLibelleList[i] = cr.getString(cr.getColumnIndex("tarification_libelle"));
                /*Log.d("cursor brm id : ", cr.getString(cr.getColumnIndex("_tarification_id")));
                Log.d("cursor brm libelle : ", cr.getString(cr.getColumnIndex("tarification_libelle")) );*/
                cr.moveToNext();
            }
        }else{
            Log.d("cursor empty : ","yes " );
            Log.d("cursor : ",String.valueOf(cr.getCount()));
        }

        final TarificationAdapter tarificationAdapter = new TarificationAdapter(this, R.layout.bareme_list_view_row,xmlTarificationList);
        //ArrayAdapter<String> adapterBareme = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tarificationLibelleList);
        listBaremeView.setAdapter(tarificationAdapter);
        listBaremeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("bareme :  ", view.getTag().toString());
                utils.saveInSharedPrefs(getApplicationContext(), "FINANCE", "TARIFICATION_ID", view.getTag().toString());
                tblXmlBaremesList.clear();

                //set taux VR list view
                //fetchTauxVrByXmlTarificationId
                xmlTarificationId = view.getTag().toString();
                Cursor crtaux= mDbhelper.fetchTauxVrByXmlTarificationId(view.getTag().toString());
                Log.d("cursor size : ",String.valueOf(crtaux.getCount()));
                if(crtaux.moveToFirst()) {
                    Log.d("cursor empty : ","no" );
                    for (int i = 0; i < crtaux.getCount(); i++) {
                        TblXmlBaremes tblXmlBaremeObj = new TblXmlBaremes(crtaux.getString(crtaux.getColumnIndex("XmlBaremeId")), crtaux.getString(crtaux.getColumnIndex("PasDureeId")), crtaux.getString(crtaux.getColumnIndex("XmlBaremeTxVr")));
                        tblXmlBaremesList.add(tblXmlBaremeObj);
                        baremId = crtaux.getString(crtaux.getColumnIndex("XmlBaremeId"));
                        utils.saveInSharedPrefs(getApplicationContext(), "FINANCE", "BAREME_ID", baremId);
                        Log.d("cursor taux value : ", crtaux.getString(crtaux.getColumnIndex("XmlBaremeTxVr")) );
                        Log.d("cursor baremeId : ", crtaux.getString(crtaux.getColumnIndex("XmlBaremeId")) );
                        Log.d("cursor duree id : ", crtaux.getString(crtaux.getColumnIndex("PasDureeId")) );
                        tna = crtaux.getString(crtaux.getColumnIndex("XmlBaremeTnaDefault"));
                        utils.saveInSharedPrefs(getApplicationContext(), "FINANCE", "TNA",tna);
                        Log.d("cursor tna id : ", crtaux.getString(crtaux.getColumnIndex("XmlBaremeTnaDefault")) );
                        pasDureeId = crtaux.getString(crtaux.getColumnIndex("PasDureeId"));
                        crtaux.moveToNext();
                    }
                }else{
                    Log.d("cursor empty : ","yes " );
                    Log.d("cursor : ",String.valueOf(crtaux.getCount()));
                }
                TauxAdapter tauxAdapter = new TauxAdapter(getApplicationContext(), R.layout.taux_list_view_row, tblXmlBaremesList);
                listTauxView.setAdapter(tauxAdapter);
                listTauxView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String taux = parent.getItemAtPosition(position).toString();
                        utils.saveInSharedPrefs(getApplicationContext(), "FINANCE", "TAUX", view.getTag().toString());
                        /*Gson gson = new Gson();
                        TblXmlBaremes  tblXmlBaremes = gson.fromJson(taux, TblXmlBaremes.class);
                        utils.saveInSharedPrefs(getApplicationContext(), "FINANCE", "TAUX", tblXmlBaremes.getXmlBaremeTxVr());*/
                    }
                });

                //set Duree list view
                //fetchDureeByXmlBaremeId
                Cursor crtMois= mDbhelper.fetchDureeByXmlBaremeId(baremId);
                Log.d("cursor size : ",String.valueOf(crtMois.getCount()));
                if(crtMois.moveToFirst()) {
                    Log.d("cursor duree empty : ","no" );
                    for (int i = 0; i < crtMois.getCount(); i++) {
                        dureeMin = crtMois.getString(crtMois.getColumnIndex("XmlConditionMoisMin"));
                        dureeMax = crtMois.getString(crtMois.getColumnIndex("XmlConditionMoisMax"));
                        Log.d("cursor mois min : ", dureeMin);
                        Log.d("cursor mois max : ", dureeMax);
                        crtMois.moveToNext();
                    }
                }else{
                    Log.d("cursor duree empty : ","yes " );
                    Log.d("cursor : ",String.valueOf(crtMois.getCount()));
                }
                //fetchDureePasById
                Cursor crtPas= mDbhelper.fetchDureePasById(pasDureeId);
                Log.d("cursor size : ",String.valueOf(crtPas.getCount()));
                if(crtPas.moveToFirst()) {
                    Log.d("cursor empty : ","no" );
                    for (int i = 0; i < crtPas.getCount(); i++) {
                        Log.d("cursor Pas : ", crtPas.getString(crtPas.getColumnIndex("PasDureeValeur")) );
                        pas = crtPas.getString(crtPas.getColumnIndex("PasDureeValeur"));
                        crtPas.moveToNext();
                    }
                }else{
                    Log.d("cursor empty : ","yes " );
                    Log.d("cursor : ",String.valueOf(crtPas.getCount()));
                }

                int incrm = Integer.parseInt(dureeMin);
                String[] dureeList = new String[(Integer.parseInt(dureeMax)- Integer.parseInt(dureeMin))/Integer.parseInt(pas) + 1];
                dureeList[0]= Integer.toString(incrm);
                int j = 1;
                while(incrm < Integer.parseInt(dureeMax)){
                    incrm += Integer.parseInt(pas);
                    dureeList[j]= Integer.toString(incrm);
                    j++;
                }

                ArrayAdapter<String> dureeAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.duree_list_view_row, R.id.listDureeText, dureeList);
                listDureeView.setAdapter(dureeAdapter);
                listDureeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String duree = (String) parent.getItemAtPosition(position);
                        utils.saveInSharedPrefs(getApplicationContext(), "FINANCE", "DUREE", duree);
                    }
                });


                //set choices
                //fetchChecksByXmlProduitId
                Cursor crtCheck= mDbhelper.fetchChecksByXmlProduitId(xmlTarificationId);
                tblXmlProduitList.clear();
                Log.d("cursor size : ",String.valueOf(crtCheck.getCount()));
                if(crtCheck.moveToFirst()) {
                    Log.d("cursor empty : ","no" );
                    for (int i = 0; i < crtCheck.getCount(); i++) {
                        if(crtCheck.getCount() < 2){
                            assuranceTwo.setVisibility(View.INVISIBLE);
                            assuranceThree.setVisibility(View.INVISIBLE);
                        }
                        if(i==0){
                            if(crtCheck.getString(crtCheck.getColumnIndex("XmlProduitLibelle"))!=null){
                                assuranceOne.setVisibility(View.VISIBLE);
                                assuranceOne.setText(crtCheck.getString(crtCheck.getColumnIndex("XmlProduitLibelle")));
                                if(crtCheck.getString(crtCheck.getColumnIndex("PrestationChecked")).equals("true") || crtCheck.getString(crtCheck.getColumnIndex("PrestationObligatoire")).equals("true")){
                                    assuranceOne.setChecked(true);
                                }
                                TblXmlProduit tblXmlProduitObj = new TblXmlProduit(crtCheck.getString(crtCheck.getColumnIndex("XmlProduitId")),crtCheck.getString(crtCheck.getColumnIndex("XmlProduitLibelle")),crtCheck.getString(crtCheck.getColumnIndex("XmlProduitPrime")),crtCheck.getString(crtCheck.getColumnIndex("XmlProduitTaux")),crtCheck.getString(crtCheck.getColumnIndex("PrestationBaseCalculId")),crtCheck.getString(crtCheck.getColumnIndex("PrestationObligatoire")), crtCheck.getString(crtCheck.getColumnIndex("PrestationChecked")),crtCheck.getString(crtCheck.getColumnIndex("PrestationDisabled")),crtCheck.getString(crtCheck.getColumnIndex("PrestationVisible")),crtCheck.getString(crtCheck.getColumnIndex("PrestationIsFd")));
                                tblXmlProduitList.add(tblXmlProduitObj);
                            }
                        }
                        if(i==1){
                            if(crtCheck.getString(crtCheck.getColumnIndex("XmlProduitLibelle"))!=null) {
                                assuranceTwo.setVisibility(View.VISIBLE);
                                assuranceTwo.setText(crtCheck.getString(crtCheck.getColumnIndex("XmlProduitLibelle")));
                                if(crtCheck.getString(crtCheck.getColumnIndex("PrestationChecked")).equals("true") || crtCheck.getString(crtCheck.getColumnIndex("PrestationObligatoire")).equals("true") ){
                                    assuranceTwo.setChecked(true);
                                }
                                TblXmlProduit tblXmlProduitObj = new TblXmlProduit(crtCheck.getString(crtCheck.getColumnIndex("XmlProduitId")),crtCheck.getString(crtCheck.getColumnIndex("XmlProduitLibelle")),crtCheck.getString(crtCheck.getColumnIndex("XmlProduitPrime")),crtCheck.getString(crtCheck.getColumnIndex("XmlProduitTaux")),crtCheck.getString(crtCheck.getColumnIndex("PrestationBaseCalculId")),crtCheck.getString(crtCheck.getColumnIndex("PrestationObligatoire")), crtCheck.getString(crtCheck.getColumnIndex("PrestationChecked")),crtCheck.getString(crtCheck.getColumnIndex("PrestationDisabled")),crtCheck.getString(crtCheck.getColumnIndex("PrestationVisible")),crtCheck.getString(crtCheck.getColumnIndex("PrestationIsFd")));
                                tblXmlProduitList.add(tblXmlProduitObj);
                            }
                        }
                        if(i==2){
                            if(crtCheck.getString(crtCheck.getColumnIndex("XmlProduitLibelle"))!=null) {
                                assuranceThree.setVisibility(View.VISIBLE);
                                assuranceThree.setText(crtCheck.getString(crtCheck.getColumnIndex("XmlProduitLibelle")));
                                if(crtCheck.getString(crtCheck.getColumnIndex("PrestationChecked")).equals("true") || crtCheck.getString(crtCheck.getColumnIndex("PrestationObligatoire")).equals("true")){
                                    assuranceThree.setChecked(true);
                                }
                                TblXmlProduit tblXmlProduitObj = new TblXmlProduit(crtCheck.getString(crtCheck.getColumnIndex("XmlProduitId")),crtCheck.getString(crtCheck.getColumnIndex("XmlProduitLibelle")),crtCheck.getString(crtCheck.getColumnIndex("XmlProduitPrime")),crtCheck.getString(crtCheck.getColumnIndex("XmlProduitTaux")),crtCheck.getString(crtCheck.getColumnIndex("PrestationBaseCalculId")),crtCheck.getString(crtCheck.getColumnIndex("PrestationObligatoire")), crtCheck.getString(crtCheck.getColumnIndex("PrestationChecked")),crtCheck.getString(crtCheck.getColumnIndex("PrestationDisabled")),crtCheck.getString(crtCheck.getColumnIndex("PrestationVisible")),crtCheck.getString(crtCheck.getColumnIndex("PrestationIsFd")));
                                tblXmlProduitList.add(tblXmlProduitObj);
                            }

                        }
                        /*Log.d("cursor produit id : ", crtCheck.getString(crtCheck.getColumnIndex("XmlProduitId")) );
                        Log.d("cursor taux : ", crtCheck.getString(crtCheck.getColumnIndex("XmlProduitTaux")) );
                        Log.d("cursor prime : ", crtCheck.getString(crtCheck.getColumnIndex("XmlProduitPrime")) );
                        Log.d("cursor is fd : ", crtCheck.getString(crtCheck.getColumnIndex("PrestationIsFd")) );
                        Log.d("cursor libelle : ", crtCheck.getString(crtCheck.getColumnIndex("XmlProduitLibelle")) );
                        Log.d("cursor libelle : ", crtCheck.getString(crtCheck.getColumnIndex("PrestationBaseCalculId")) );
                        Log.d("cursor baseCalculId : ", crtCheck.getString(crtCheck.getColumnIndex("PrestationObligatoire")) );
                        Log.d("cursor checked : ", crtCheck.getString(crtCheck.getColumnIndex("PrestationChecked")) );
                        Log.d("cursor disabled : ", crtCheck.getString(crtCheck.getColumnIndex("PrestationDisabled")) );
                        Log.d("cursor visible : ", crtCheck.getString(crtCheck.getColumnIndex("PrestationVisible")) );*/
                        crtCheck.moveToNext();
                    }
                }else{
                    Log.d("cursor empty : ","yes " );
                    assuranceOne.setVisibility(View.INVISIBLE);
                    assuranceOne.setChecked(false);
                    assuranceTwo.setVisibility(View.INVISIBLE);
                    assuranceTwo.setChecked(false);
                    assuranceThree.setVisibility(View.INVISIBLE);
                    assuranceThree.setChecked(false);
                    Log.d("cursor : ",String.valueOf(crtCheck.getCount()));
                }


            }
        });


        btnCalculer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                utils.saveInSharedPrefs(getApplicationContext(), "INFO_VEH", "MONTANT",prixVeh.getText().toString());
                utils.saveInSharedPrefs(getApplicationContext(), "FINANCE", "APPORT",apport.getText().toString());

                if(assuranceOne.isChecked()) utils.saveInSharedPrefs(getApplicationContext(), "FINANCE", "ASS_ONE","1");
                else utils.saveInSharedPrefs(getApplicationContext(), "FINANCE", "ASS_ONE","0");

                if(assuranceTwo.isChecked()) utils.saveInSharedPrefs(getApplicationContext(), "FINANCE", "ASS_TWO","1");
                else utils.saveInSharedPrefs(getApplicationContext(), "FINANCE", "ASS_TWO","0");

                if(assuranceThree.isChecked()) utils.saveInSharedPrefs(getApplicationContext(), "FINANCE", "ASS_THREE","1");
                else utils.saveInSharedPrefs(getApplicationContext(), "FINANCE", "ASS_THREE","0");

                Intent intent = new Intent(FinanceCreditActivity.this, CalculCreditActivity.class);
                intent.putExtra("tblXmlProduitList", tblXmlProduitList);
                startActivity(intent);
            }
        });



    }
}
