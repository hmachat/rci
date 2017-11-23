package com.orcaformation.calculetterci.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.orcaformation.calculetterci.R;
import com.orcaformation.calculetterci.content.DBAdapter;
import com.orcaformation.calculetterci.entity.TblXmlProduit;
import com.orcaformation.calculetterci.utils.Calcul;
import com.orcaformation.calculetterci.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;

public class CalculLoaActivity extends AppCompatActivity {

    Button btnValiderCalcul;
    TextView rowBaremeLib;
    TextView rowBaremeVal;
    TextView rowPrixVehiculeLib;
    TextView rowPrixVehiculeVal;
    TextView rowApportLib;
    TextView rowApportVal;
    TextView rowNbrLoyerLib;
    TextView rowNbrLoyerVal;
    TextView rowLoyerHALib;
    TextView rowLoyerHAVal;
    TextView rowAssOneLib;
    TextView rowAssOneVal;
    TextView rowAssTwoLib;
    TextView rowAssTwoVal;
    TextView rowAssThreeLib;
    TextView rowAssThreeVal;
    TextView rowLoyerAALib;
    TextView rowLoyerAAVal;
    TextView rowVRLib;
    TextView rowVRVal;
    TextView rowAvanceLib;
    TextView rowAvanceVal;
    Utils utils;
    double apportMinTx;
    double apportMaxTx;
    double apport;
    double montantFinanceTTC;
    int duree;
    double mensualite;
    String typeFinancementId;
    double tva;
    double prix_HT;
    double tauxNominalAnnuel;
    double reportValeur;
    int report_bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul_loa);

        btnValiderCalcul = (Button) findViewById(R.id.btnValiderCalcul);

        rowBaremeLib = (TextView) findViewById(R.id.rowBaremeLib);
        rowBaremeVal = (TextView) findViewById(R.id.rowBaremeVal);
        rowPrixVehiculeLib = (TextView) findViewById(R.id.rowPrixVehiculeLib);
        rowPrixVehiculeVal = (TextView) findViewById(R.id.rowPrixVehiculeVal);
        rowApportLib = (TextView) findViewById(R.id.rowApportLib);
        rowApportVal = (TextView) findViewById(R.id.rowApportVal);
        rowNbrLoyerLib = (TextView) findViewById(R.id.rowNbrLoyerLib);
        rowNbrLoyerVal = (TextView) findViewById(R.id.rowNbrLoyerVal);
        rowLoyerHALib = (TextView) findViewById(R.id.rowLoyerHALib);
        rowLoyerHAVal = (TextView) findViewById(R.id.rowLoyerHAVal);
        rowAssOneLib = (TextView) findViewById(R.id.rowAssOneLib);
        rowAssOneVal = (TextView) findViewById(R.id.rowAssOneVal);
        rowAssTwoLib = (TextView) findViewById(R.id.rowAssTwoLib);
        rowAssTwoVal = (TextView) findViewById(R.id.rowAssTwoVal);
        rowAssThreeLib = (TextView) findViewById(R.id.rowAssThreeLib);
        rowAssThreeVal = (TextView) findViewById(R.id.rowAssThreeVal);
        rowLoyerAALib = (TextView) findViewById(R.id.rowLoyerAALib);
        rowLoyerAAVal = (TextView) findViewById(R.id.rowLoyerAAVal);
        rowVRLib = (TextView) findViewById(R.id.rowVRLib);
        rowVRVal = (TextView) findViewById(R.id.rowVRVal);
        rowAvanceLib = (TextView) findViewById(R.id.rowAvanceLib);
        rowAvanceVal = (TextView) findViewById(R.id.rowAvanceVal);

        rowBaremeLib.setText("Barème : ");
        rowPrixVehiculeLib.setText("Prix du véhicule : ");
        rowApportLib.setText("Apport : ");
        rowNbrLoyerLib.setText("Nombre de loyers : ");
        rowLoyerHALib.setText("Loyer (hors assur.) : ");

        rowAssOneLib.setVisibility(View.INVISIBLE);
        rowAssOneVal.setVisibility(View.INVISIBLE);
        rowAssOneLib.setText("");
        rowAssOneVal.setText("");
        rowAssTwoLib.setVisibility(View.INVISIBLE);
        rowAssTwoVal.setVisibility(View.INVISIBLE);
        rowAssTwoLib.setText("");
        rowAssTwoVal.setText("");
        rowAssThreeLib.setVisibility(View.INVISIBLE);
        rowAssThreeVal.setVisibility(View.INVISIBLE);
        rowAssThreeLib.setText("");
        rowAssThreeVal.setText("");

        rowLoyerAALib.setText("Loyer (avec assur.) : ");
        rowVRLib.setText("VR : ");
        rowAvanceLib.setText("Avance (Apport + frais):");

        final DBAdapter mDbhelper = new DBAdapter(this).open();
        Calcul utilCalcul = new Calcul();


        //Bareme
        tva = 0;
        Cursor crBareme = mDbhelper.fetchLibelleAndTypeFinancementIdByXmlTarificationId(utils.getFromSharedPrefs(getApplicationContext(), "FINANCE", "TARIFICATION_ID"));
        if(crBareme.moveToFirst()) {
            typeFinancementId = crBareme.getString(crBareme.getColumnIndex("TypeFinancementId"));
            Cursor crTVA = mDbhelper.fetchValeurTvaByTypeFinancementId(typeFinancementId);
            if(crTVA.moveToFirst()){
                tva = Double.parseDouble(crTVA.getString(crTVA.getColumnIndex("ValeurTva")));
            }
        }

        //Apport
        apport = Double.parseDouble(utils.getFromSharedPrefs(getApplicationContext(), "INFO_VEH", "MONTANT")) * (Double.parseDouble(utils.getFromSharedPrefs(getApplicationContext(), "FINANCE", "LOYER")) / 100);

        double tva_achat = 20;

        report_bar = 0;
        Cursor crReportValeur = mDbhelper.fetchReportValeurByXmlTarificationId(utils.getFromSharedPrefs(getApplicationContext(), "FINANCE", "TARIFICATION_ID"));
        if(crReportValeur.moveToFirst()) {
            report_bar = Integer.parseInt(crReportValeur.getString(crReportValeur.getColumnIndex("ReportValeur"))) / 30;
        }

        HashMap<String, String> resultCredit = new HashMap<>();
        resultCredit = utilCalcul.calculLOA("TTC", Double.parseDouble(utils.getFromSharedPrefs(getApplicationContext(), "INFO_VEH", "MONTANT")), 0, Double.parseDouble(utils.getFromSharedPrefs(getApplicationContext(), "FINANCE", "LOYER")), Double.parseDouble(utils.getFromSharedPrefs(getApplicationContext(), "FINANCE", "LOYER")), 0, Double.parseDouble(utils.getFromSharedPrefs(getApplicationContext(), "FINANCE", "TAUX")), tva_achat, Double.parseDouble(utils.getFromSharedPrefs(getApplicationContext(), "FINANCE", "TNA")), Integer.parseInt(utils.getFromSharedPrefs(getApplicationContext(), "FINANCE", "DUREE")), tva);
        calculNewAssurance(resultCredit, utils.getFromSharedPrefs(getApplicationContext(), "TARIF", "CHOICE"));


        rowBaremeVal.setText(crBareme.getString(crBareme.getColumnIndex("tarification_libelle")));
        rowPrixVehiculeVal.setText(utils.getFromSharedPrefs(getApplicationContext(), "INFO_VEH", "MONTANT"));
        rowApportVal.setText(String.valueOf(apport));

        rowNbrLoyerVal.setText(String.valueOf(resultCredit.get("duree")) + " mois");
        rowLoyerHAVal.setText(String.valueOf(resultCredit.get("mensualite")));
        rowVRVal.setText(String.valueOf(resultCredit.get("montantVR")));


        btnValiderCalcul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });


    }


    void calculNewAssurance(HashMap<String, String> resultCredit, String typecredit) {
        Calcul utilCalcul = new Calcul();
        double assurance = 0;
        double somme_assurance = 0;

        //get Assurance
        ArrayList<TblXmlProduit> tblXmlProduitList = (ArrayList<TblXmlProduit>) getIntent().getSerializableExtra("tblXmlProduitList");
        double fd_bar = 0;
        for(int i = 0; i < tblXmlProduitList.size(); i++){
            Log.d("tblXmlProduitList : ", tblXmlProduitList.get(i).toString());
            Log.d("base calcul : ", String.valueOf(tblXmlProduitList.get(i).getPrestationBaseCalculId()));

            //LOA LEASING
                if(tblXmlProduitList.get(i).getPrestationBaseCalculId() != null){
                    if(tblXmlProduitList.get(i).getPrestationBaseCalculId().equals("1") || tblXmlProduitList.get(i).getPrestationBaseCalculId().equals("2")){ //BL or //VP
                        assurance = calculMensAssurance(Double.parseDouble(tblXmlProduitList.get(i).getXmlProduitTaux()), 0,(Double.parseDouble(resultCredit.get("prixTotal")) - Double.parseDouble(resultCredit.get("montantLoyer1"))) / (1 + (20 / 100))) + Double.parseDouble(tblXmlProduitList.get(i).getXmlProduitPrime());
                    }else if(tblXmlProduitList.get(i).getPrestationBaseCalculId().equals("3") || tblXmlProduitList.get(i).getPrestationBaseCalculId().equals("4")){ // PTTC - 1L or //PTTC
                        assurance = calculMensAssurance(Double.parseDouble(tblXmlProduitList.get(i).getXmlProduitTaux()), 0, (Double.parseDouble(resultCredit.get("prixTotal")) - Double.parseDouble(resultCredit.get("montantLoyer1")))) + Double.parseDouble(tblXmlProduitList.get(i).getXmlProduitPrime());
                    }else {
                        assurance = Double.parseDouble(tblXmlProduitList.get(i).getXmlProduitPrime());
                    }
                }else {
                    assurance = Double.parseDouble(tblXmlProduitList.get(i).getXmlProduitPrime());
                }

            //first check box
            if(i == 0){
                //check if it's checked
                if(utils.getFromSharedPrefs(getApplicationContext(), "FINANCE", "ASS_ONE").equals("1")){
                    rowAssOneLib.setVisibility(View.VISIBLE);
                    rowAssOneVal.setVisibility(View.VISIBLE);
                    rowAssOneLib.setText(tblXmlProduitList.get(i).getXmlProduitLibelle());
                    rowAssOneVal.setText(String.valueOf(utilCalcul.arrondi2chiffresPoint(assurance,2)));
                }else{
                    rowAssOneLib.setVisibility(View.INVISIBLE);
                    rowAssOneVal.setVisibility(View.INVISIBLE);
                }
            }
            //second check box
            if(i == 1){
                //check if it's checked
                if(utils.getFromSharedPrefs(getApplicationContext(), "FINANCE", "ASS_TWO").equals("1")){
                    rowAssTwoLib.setVisibility(View.VISIBLE);
                    rowAssTwoVal.setVisibility(View.VISIBLE);
                    rowAssTwoLib.setText(tblXmlProduitList.get(i).getXmlProduitLibelle());
                    rowAssTwoVal.setText(String.valueOf(utilCalcul.arrondi2chiffresPoint(assurance,2)));
                }else{
                    rowAssTwoLib.setVisibility(View.INVISIBLE);
                    rowAssTwoVal.setVisibility(View.INVISIBLE);
                }
            }
            //third check box
            if(i == 2){
                //check if it's checked
                if(utils.getFromSharedPrefs(getApplicationContext(), "FINANCE", "ASS_THREE").equals("1")){
                    rowAssThreeLib.setVisibility(View.VISIBLE);
                    rowAssThreeVal.setVisibility(View.VISIBLE);
                    rowAssThreeLib.setText(tblXmlProduitList.get(i).getXmlProduitLibelle());
                    rowAssThreeVal.setText(String.valueOf(utilCalcul.arrondi2chiffresPoint(assurance,2)));
                }else{
                    rowAssThreeLib.setVisibility(View.INVISIBLE);
                    rowAssThreeVal.setVisibility(View.INVISIBLE);
                }
            }

            if(tblXmlProduitList.get(i).getPrestationIsFd().equals("false")){
                somme_assurance += assurance;
            }



            //recuperation de la somme des frais de dossier depuis la tarification qui contient le bareme
            if(tblXmlProduitList.get(i).getPrestationIsFd().equals("true")){
                fd_bar += Double.parseDouble(tblXmlProduitList.get(i).getXmlProduitPrime()) + (Double.parseDouble(utils.getFromSharedPrefs(getApplicationContext(), "INFO_VEH", "MONTANT")) * (Double.parseDouble(tblXmlProduitList.get(i).getXmlProduitTaux()) / 100));
            }

        }

        rowLoyerAAVal.setText(String.valueOf(utilCalcul.arrondi2chiffresPoint(Double.parseDouble(resultCredit.get("mensualite")) + somme_assurance,2)));
        rowAvanceVal.setText(String.valueOf(Double.parseDouble(utils.getFromSharedPrefs(getApplicationContext(), "FINANCE", "APPORT")) + fd_bar));
    }


    double calculMensAssurance(double pfTauxAssurance, double pfTauxTVA, double pfMontantAssure){
        return ((pfTauxAssurance / 100) * (1 + pfTauxTVA / 100)) * pfMontantAssure;
    }
}
