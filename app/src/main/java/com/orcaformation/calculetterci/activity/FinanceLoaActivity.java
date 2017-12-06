package com.orcaformation.calculetterci.activity;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.orcaformation.calculetterci.R;
import com.orcaformation.calculetterci.adapter.TarificationAdapter;
import com.orcaformation.calculetterci.adapter.TauxAdapter;
import com.orcaformation.calculetterci.content.DBAdapter;
import com.orcaformation.calculetterci.entity.TblXmlBaremes;
import com.orcaformation.calculetterci.entity.TblXmlConditions;
import com.orcaformation.calculetterci.entity.TblXmlProduit;
import com.orcaformation.calculetterci.entity.XmlTarification;
import com.orcaformation.calculetterci.utils.Utils;

import java.util.ArrayList;

public class FinanceLoaActivity extends AppCompatActivity {

    EditText prixVeh;
    TextView assuranceOneText;
    Switch assuranceOneSwitch;
    TextView assuranceTwoText;
    Switch assuranceTwoSwitch;
    TextView assuranceThreeText;
    Switch assuranceThreeSwitch;
    Spinner spinnerLoyer;
    Spinner spinnerBareme;
    Spinner spinnerTaux;
    Spinner spinnerDuree;
    Utils utils;
    ArrayList<TblXmlConditions> tblXmlConditionsList = new ArrayList<>();
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
        setContentView(R.layout.activity_finance_loa);
        Intent intent = getIntent();

        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        prixVeh = (EditText)findViewById(R.id.prixVeh);

        assuranceOneText = (TextView) findViewById(R.id.assuranceOneText);
        assuranceOneText.setVisibility(View.INVISIBLE);
        assuranceTwoText = (TextView) findViewById(R.id.assuranceTwoText);
        assuranceTwoText.setVisibility(View.INVISIBLE);
        assuranceThreeText = (TextView) findViewById(R.id.assuranceThreeText);
        assuranceThreeText.setVisibility(View.INVISIBLE);

        assuranceOneSwitch = (Switch) findViewById(R.id.assuranceOneSwitch);
        assuranceOneSwitch.setVisibility(View.INVISIBLE);
        assuranceTwoSwitch = (Switch) findViewById(R.id.assuranceTwoSwitch);
        assuranceTwoSwitch.setVisibility(View.INVISIBLE);
        assuranceThreeSwitch = (Switch) findViewById(R.id.assuranceThreeSwitch);
        assuranceThreeSwitch.setVisibility(View.INVISIBLE);

        spinnerBareme = (Spinner) findViewById(R.id.spinnerBareme);
        spinnerLoyer = (Spinner) findViewById(R.id.spinnerLoyer);
        spinnerTaux = (Spinner) findViewById(R.id.spinnerTaux);
        spinnerDuree = (Spinner) findViewById(R.id.spinnerDuree);
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

        final Resources res = getResources();
        final TarificationAdapter tarificationAdapter = new TarificationAdapter(this, R.layout.bareme_list_view_row, xmlTarificationList, res);
        //final TarificationAdapter tarificationAdapter = new TarificationAdapter(this, R.layout.bareme_list_view_row,xmlTarificationList);
        //ArrayAdapter<String> adapterBareme = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tarificationLibelleList);
        spinnerBareme.setAdapter(tarificationAdapter);
        spinnerBareme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("bareme :  ", view.getTag().toString());
                utils.saveInSharedPrefs(getApplicationContext(), "FINANCE", "TARIFICATION_ID", view.getTag().toString());
                tblXmlConditionsList.clear();
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
                TauxAdapter tauxAdapter = new TauxAdapter(getApplicationContext(), R.layout.taux_list_view_row, tblXmlBaremesList, res);
                spinnerTaux.setAdapter(tauxAdapter);
                spinnerTaux.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                        String taux = parent.getItemAtPosition(position).toString();
                        utils.saveInSharedPrefs(getApplicationContext(), "FINANCE", "TAUX", view.getTag().toString());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // TODO Auto-generated method stub
                    }
                });

                //set Loyer List view
                Cursor crtloyer= mDbhelper.fetchLoyerTxByXmlBaremeId(baremId);
                Log.d("crtloyer size : ",String.valueOf(crtloyer.getCount()));
                ArrayList<String> loyerlist = new ArrayList<String>();
                if(crtloyer.moveToFirst()) {
                    Log.d("cursor empty : ","no" );
                    for (int i = 0; i < crtloyer.getCount(); i++) {
                        /*Log.d("MontantMin : ",crtloyer.getString(crtloyer.getColumnIndex("XmlConditionMontantMin")));
                        Log.d("MontantMax : ",crtloyer.getString(crtloyer.getColumnIndex("XmlConditionMontantMax")));
                        Log.d("LoyerMin",crtloyer.getString(crtloyer.getColumnIndex("XmlConditionTxPremierLoyerMin")));
                        Log.d("LoyerMax",crtloyer.getString(crtloyer.getColumnIndex("XmlConditionTxPremierLoyerMax")));*/
                        if( (Double.parseDouble(prixVeh.getText().toString()) > Double.parseDouble(crtloyer.getString(crtloyer.getColumnIndex("XmlConditionMontantMin")))) && (Double.parseDouble(prixVeh.getText().toString()) < Double.parseDouble(crtloyer.getString(crtloyer.getColumnIndex("XmlConditionMontantMax"))))){
                            for(int j = Integer.parseInt(crtloyer.getString(crtloyer.getColumnIndex("XmlConditionTxPremierLoyerMin")));j<=Integer.parseInt(crtloyer.getString(crtloyer.getColumnIndex("XmlConditionTxPremierLoyerMax")));j++){
                                loyerlist.add(String.valueOf(j));
                                //Log.d("j : ", String.valueOf(j));
                            }
                        }
                        /*TblXmlConditions tblXmlConditionsObj = new TblXmlConditions(crtloyer.getString(crtloyer.getColumnIndex("XmlConditionMontantMin")), crtloyer.getString(crtloyer.getColumnIndex("XmlConditionMontantMax")), crtloyer.getString(crtloyer.getColumnIndex("XmlConditionTxPremierLoyerMin")), crtloyer.getString(crtloyer.getColumnIndex("XmlConditionTxPremierLoyerMax")));
                        tblXmlConditionsList.add(tblXmlConditionsObj);
                        Log.d("loyer :", tblXmlConditionsObj.toString());*/
                        crtloyer.moveToNext();
                    }
                }else{
                    Log.d("cursor empty : ","yes " );
                    Log.d("cursor : ",String.valueOf(crtloyer.getCount()));
                }
                String[] loyerlistStrings = new String[loyerlist.size()];
                for(int k = 0;k < loyerlist.size();k++){
                    loyerlistStrings[k] = loyerlist.get(k);
                }
                ArrayAdapter<String> loyerAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.loyer_list_view_row, R.id.listLoyerText, loyerlistStrings);
                spinnerLoyer.setAdapter(loyerAdapter);
                spinnerLoyer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                        String  loyerValue    = (String) spinnerLoyer.getItemAtPosition(position);
                        utils.saveInSharedPrefs(getApplicationContext(), "FINANCE", "LOYER", loyerValue);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // TODO Auto-generated method stub
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
                spinnerDuree.setAdapter(dureeAdapter);
                spinnerDuree.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                        String duree = (String) parent.getItemAtPosition(position);
                        utils.saveInSharedPrefs(getApplicationContext(), "FINANCE", "DUREE", duree);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // TODO Auto-generated method stub
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
                            assuranceTwoSwitch.setVisibility(View.INVISIBLE);
                            assuranceTwoText.setVisibility(View.INVISIBLE);
                            assuranceThreeSwitch.setVisibility(View.INVISIBLE);
                            assuranceThreeText.setVisibility(View.INVISIBLE);
                        }
                        if(i==0){
                            if(crtCheck.getString(crtCheck.getColumnIndex("XmlProduitLibelle"))!=null){
                                assuranceOneSwitch.setVisibility(View.VISIBLE);
                                assuranceOneText.setVisibility(View.VISIBLE);
                                assuranceOneText.setText(crtCheck.getString(crtCheck.getColumnIndex("XmlProduitLibelle")));
                                if(crtCheck.getString(crtCheck.getColumnIndex("PrestationChecked")).equals("true") || crtCheck.getString(crtCheck.getColumnIndex("PrestationObligatoire")).equals("true")){
                                    assuranceOneSwitch.setChecked(true);
                                }
                                TblXmlProduit tblXmlProduitObj = new TblXmlProduit(crtCheck.getString(crtCheck.getColumnIndex("XmlProduitId")),crtCheck.getString(crtCheck.getColumnIndex("XmlProduitLibelle")),crtCheck.getString(crtCheck.getColumnIndex("XmlProduitPrime")),crtCheck.getString(crtCheck.getColumnIndex("XmlProduitTaux")),crtCheck.getString(crtCheck.getColumnIndex("PrestationBaseCalculId")),crtCheck.getString(crtCheck.getColumnIndex("PrestationObligatoire")), crtCheck.getString(crtCheck.getColumnIndex("PrestationChecked")),crtCheck.getString(crtCheck.getColumnIndex("PrestationDisabled")),crtCheck.getString(crtCheck.getColumnIndex("PrestationVisible")),crtCheck.getString(crtCheck.getColumnIndex("PrestationIsFd")));
                                tblXmlProduitList.add(tblXmlProduitObj);
                            }
                        }
                        if(i==1){
                            if(crtCheck.getString(crtCheck.getColumnIndex("XmlProduitLibelle"))!=null) {
                                assuranceTwoSwitch.setVisibility(View.VISIBLE);
                                assuranceTwoText.setVisibility(View.VISIBLE);
                                assuranceTwoText.setText(crtCheck.getString(crtCheck.getColumnIndex("XmlProduitLibelle")));
                                if(crtCheck.getString(crtCheck.getColumnIndex("PrestationChecked")).equals("true") || crtCheck.getString(crtCheck.getColumnIndex("PrestationObligatoire")).equals("true") ){
                                    assuranceTwoSwitch.setChecked(true);
                                }
                                TblXmlProduit tblXmlProduitObj = new TblXmlProduit(crtCheck.getString(crtCheck.getColumnIndex("XmlProduitId")),crtCheck.getString(crtCheck.getColumnIndex("XmlProduitLibelle")),crtCheck.getString(crtCheck.getColumnIndex("XmlProduitPrime")),crtCheck.getString(crtCheck.getColumnIndex("XmlProduitTaux")),crtCheck.getString(crtCheck.getColumnIndex("PrestationBaseCalculId")),crtCheck.getString(crtCheck.getColumnIndex("PrestationObligatoire")), crtCheck.getString(crtCheck.getColumnIndex("PrestationChecked")),crtCheck.getString(crtCheck.getColumnIndex("PrestationDisabled")),crtCheck.getString(crtCheck.getColumnIndex("PrestationVisible")),crtCheck.getString(crtCheck.getColumnIndex("PrestationIsFd")));
                                tblXmlProduitList.add(tblXmlProduitObj);
                            }
                        }
                        if(i==2){
                            if(crtCheck.getString(crtCheck.getColumnIndex("XmlProduitLibelle"))!=null) {
                                assuranceThreeSwitch.setVisibility(View.VISIBLE);
                                assuranceThreeText.setVisibility(View.VISIBLE);
                                assuranceThreeText.setText(crtCheck.getString(crtCheck.getColumnIndex("XmlProduitLibelle")));
                                if(crtCheck.getString(crtCheck.getColumnIndex("PrestationChecked")).equals("true") || crtCheck.getString(crtCheck.getColumnIndex("PrestationObligatoire")).equals("true")){
                                    assuranceThreeSwitch.setChecked(true);
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
                    assuranceOneSwitch.setVisibility(View.INVISIBLE);
                    assuranceOneText.setVisibility(View.INVISIBLE);
                    assuranceOneSwitch.setChecked(false);
                    assuranceTwoSwitch.setVisibility(View.INVISIBLE);
                    assuranceTwoText.setVisibility(View.INVISIBLE);
                    assuranceTwoSwitch.setChecked(false);
                    assuranceThreeSwitch.setVisibility(View.INVISIBLE);
                    assuranceThreeText.setVisibility(View.INVISIBLE);
                    assuranceThreeSwitch.setChecked(false);
                    Log.d("cursor : ",String.valueOf(crtCheck.getCount()));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        btnCalculer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                utils.saveInSharedPrefs(getApplicationContext(), "INFO_VEH", "MONTANT",prixVeh.getText().toString());

                if(assuranceOneSwitch.isChecked()) utils.saveInSharedPrefs(getApplicationContext(), "FINANCE", "ASS_ONE","1");
                else utils.saveInSharedPrefs(getApplicationContext(), "FINANCE", "ASS_ONE","0");

                if(assuranceTwoSwitch.isChecked()) utils.saveInSharedPrefs(getApplicationContext(), "FINANCE", "ASS_TWO","1");
                else utils.saveInSharedPrefs(getApplicationContext(), "FINANCE", "ASS_TWO","0");

                if(assuranceThreeSwitch.isChecked()) utils.saveInSharedPrefs(getApplicationContext(), "FINANCE", "ASS_THREE","1");
                else utils.saveInSharedPrefs(getApplicationContext(), "FINANCE", "ASS_THREE","0");

                Intent intent = new Intent(FinanceLoaActivity.this, CalculLoaActivity.class);
                intent.putExtra("tblXmlProduitList", tblXmlProduitList);
                startActivity(intent);
            }
        });

    }
}
