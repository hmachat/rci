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

public class CalculCreditActivity extends AppCompatActivity {

    Button btnValiderCalcul;
    TextView rowBaremeLib;
    TextView rowBaremeVal;
    TextView rowPrixVehiculeLib;
    TextView rowPrixVehiculeVal;
    TextView rowApportLib;
    TextView rowApportVal;
    TextView rowMontantLib;
    TextView rowMontantVal;
    TextView rowNombreMensualiteLib;
    TextView rowNombreMensualiteVal;
    TextView rowMonsualiteHorsAssurLib;
    TextView rowMonsualiteHorsAssurVal;
    TextView rowAssOneLib;
    TextView rowAssOneVal;
    TextView rowAssTwoLib;
    TextView rowAssTwoVal;
    TextView rowAssThreeLib;
    TextView rowAssThreeVal;
    TextView rowMensualiteAvecAssurLib;
    TextView rowMensualiteAvecAssurVal;
    TextView rowDerniereEcheanceLib;
    TextView rowDerniereEcheanceVal;
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
        setContentView(R.layout.activity_calcul_credit);

        /*Log.d("tarification id ", utils.getFromSharedPrefs(getApplicationContext(), "FINANCE", "TARIFICATION_ID"));
        Log.d("bareme id ", utils.getFromSharedPrefs(getApplicationContext(), "FINANCE", "BAREME_ID"));
        Log.d("bareme id ", utils.getFromSharedPrefs(getApplicationContext(), "FINANCE", "LOYER"));*/

        btnValiderCalcul = (Button) findViewById(R.id.btnValiderCalcul);

        rowBaremeLib = (TextView) findViewById(R.id.rowBaremeLib);
        rowBaremeVal = (TextView) findViewById(R.id.rowBaremeVal);
        rowPrixVehiculeLib = (TextView) findViewById(R.id.rowPrixVehiculeLib);
        rowPrixVehiculeVal = (TextView) findViewById(R.id.rowPrixVehiculeVal);
        rowApportLib = (TextView) findViewById(R.id.rowApportLib);
        rowApportVal = (TextView) findViewById(R.id.rowApportVal);
        rowMontantLib = (TextView) findViewById(R.id.rowMontantLib);
        rowMontantVal = (TextView) findViewById(R.id.rowMontantVal);
        rowNombreMensualiteLib = (TextView) findViewById(R.id.rowNombreMensualiteLib);
        rowNombreMensualiteVal = (TextView) findViewById(R.id.rowNombreMensualiteVal);
        rowMonsualiteHorsAssurLib = (TextView) findViewById(R.id.rowMonsualiteHorsAssurLib);
        rowMonsualiteHorsAssurVal = (TextView) findViewById(R.id.rowMonsualiteHorsAssurVal);
        rowAssOneLib = (TextView) findViewById(R.id.rowAssOneLib);
        rowAssOneVal = (TextView) findViewById(R.id.rowAssOneVal);
        rowAssTwoLib = (TextView) findViewById(R.id.rowAssTwoLib);
        rowAssTwoVal = (TextView) findViewById(R.id.rowAssTwoVal);
        rowAssThreeLib = (TextView) findViewById(R.id.rowAssThreeLib);
        rowAssThreeVal = (TextView) findViewById(R.id.rowAssThreeVal);
        rowMensualiteAvecAssurLib = (TextView) findViewById(R.id.rowMensualiteAvecAssurLib);
        rowMensualiteAvecAssurVal = (TextView) findViewById(R.id.rowMensualiteAvecAssurVal);
        rowDerniereEcheanceLib = (TextView) findViewById(R.id.rowDerniereEcheanceLib);
        rowDerniereEcheanceVal = (TextView) findViewById(R.id.rowDerniereEcheanceVal);

        rowBaremeLib.setText("Barème : ");
        rowPrixVehiculeLib.setText("Prix du véhicule : ");
        rowApportLib.setText("Apport : ");
        rowMontantLib.setText("Montant financé : ");
        rowNombreMensualiteLib.setText("Nombre de mensualités : ");
        rowMonsualiteHorsAssurLib.setText("Mensualité hors assur : ");

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


        rowMensualiteAvecAssurLib.setText("Mensualité avec assur. : ");
        rowDerniereEcheanceLib.setText("Dernière échéance : ");

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
        apport = 0;
        if(!utils.getFromSharedPrefs(getApplicationContext(), "FINANCE", "APPORT").equals("")){
            apport = Double.parseDouble(utils.getFromSharedPrefs(getApplicationContext(), "FINANCE", "APPORT"));
        }
        Cursor crApport = mDbhelper.fetchApportTxByXmlBaremeId(utils.getFromSharedPrefs(getApplicationContext(), "FINANCE", "BAREME_ID"));
        if(crApport.moveToFirst()) {
            double min = Double.parseDouble(crApport.getString(crApport.getColumnIndex("XmlConditionApportMinTx")));
            double max = Double.parseDouble(crApport.getString(crApport.getColumnIndex("XmlConditionApportMaxTx")));
            apportMinTx = Double.parseDouble(utils.getFromSharedPrefs(getApplicationContext(), "INFO_VEH", "MONTANT")) * (min / 100);
            apportMaxTx = Double.parseDouble(utils.getFromSharedPrefs(getApplicationContext(), "INFO_VEH", "MONTANT")) * (max / 100);
            if(apport < apportMinTx) apport = apportMinTx;
            else if(apport > apportMaxTx) apport = apportMaxTx;
            utils.saveInSharedPrefs(getApplicationContext(), "FINANCE", "APPORT", String.valueOf(apport));
        }

        double tva_achat = 20;

        report_bar = 0;
        Cursor crReportValeur = mDbhelper.fetchReportValeurByXmlTarificationId(utils.getFromSharedPrefs(getApplicationContext(), "FINANCE", "TARIFICATION_ID"));
        if(crReportValeur.moveToFirst()) {
            report_bar = Integer.parseInt(crReportValeur.getString(crReportValeur.getColumnIndex("ReportValeur"))) / 30;
        }


        HashMap<String, String> resultCredit = new HashMap<>();
        //if(utils.getFromSharedPrefs(getApplicationContext(), "TARIF", "CHOICE").equals("1")){//CREADIT
            /*Log.d("arg 1 :"," ");
            Log.d("arg 2 :",utils.getFromSharedPrefs(getApplicationContext(), "INFO_VEH", "MONTANT"));
            Log.d("arg 3 :",String.valueOf(0));
            Log.d("arg 4 :",String.valueOf(apport));
            Log.d("arg 5 :",String.valueOf(tva_achat));
            Log.d("arg 6 :",String.valueOf(tva));
            Log.d("arg 7 :",utils.getFromSharedPrefs(getApplicationContext(), "FINANCE", "TNA"));
            Log.d("arg 8 :",utils.getFromSharedPrefs(getApplicationContext(), "FINANCE", "DUREE"));
            Log.d("arg 9 :",utils.getFromSharedPrefs(getApplicationContext(), "FINANCE", "TAUX"));
            Log.d("arg 10 :",String.valueOf(report_bar));*/
            resultCredit = utilCalcul.calculCredit(" ", Double.parseDouble(utils.getFromSharedPrefs(getApplicationContext(), "INFO_VEH", "MONTANT")), 0, apport, tva_achat, tva,  Double.parseDouble(utils.getFromSharedPrefs(getApplicationContext(), "FINANCE", "TNA")), Integer.parseInt(utils.getFromSharedPrefs(getApplicationContext(), "FINANCE", "DUREE")), Double.parseDouble(utils.getFromSharedPrefs(getApplicationContext(), "FINANCE", "TAUX")), report_bar);
            calculNewAssurance(resultCredit, utils.getFromSharedPrefs(getApplicationContext(), "TARIF", "CHOICE"));




        rowBaremeVal.setText(crBareme.getString(crBareme.getColumnIndex("tarification_libelle")));
        rowPrixVehiculeVal.setText(utils.getFromSharedPrefs(getApplicationContext(), "INFO_VEH", "MONTANT"));
        rowApportVal.setText(String.valueOf(apport));
        rowMontantVal.setText(String.valueOf(resultCredit.get("montantCredit")));
        rowNombreMensualiteVal.setText(String.valueOf(resultCredit.get("duree"))+ " mois");
        rowMonsualiteHorsAssurVal.setText(String.valueOf(resultCredit.get("mensualite")));
        rowDerniereEcheanceVal.setText(String.valueOf(resultCredit.get("mtVr")));

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

        for(int i = 0; i < tblXmlProduitList.size(); i++){
            Log.d("tblXmlProduitList : ", tblXmlProduitList.get(i).toString());
            Log.d("base calcul : ", String.valueOf(tblXmlProduitList.get(i).getPrestationBaseCalculId()));

            //CREADIT
                if(tblXmlProduitList.get(i).getPrestationBaseCalculId() != null){
                    if(tblXmlProduitList.get(i).getPrestationBaseCalculId().equals("1") || tblXmlProduitList.get(i).getPrestationBaseCalculId().equals("2")){ //BL or //VP
                        assurance = calculMensAssurance(Double.parseDouble(tblXmlProduitList.get(i).getXmlProduitTaux()), 0,Double.parseDouble(resultCredit.get("montantCredit"))) + Double.parseDouble(tblXmlProduitList.get(i).getXmlProduitPrime());
                    }else if(tblXmlProduitList.get(i).getPrestationBaseCalculId().equals("3") || tblXmlProduitList.get(i).getPrestationBaseCalculId().equals("4")){ //PTTC - 1L or //PTTC
                        assurance = calculMensAssurance(Double.parseDouble(tblXmlProduitList.get(i).getXmlProduitTaux()), 0, Double.parseDouble(resultCredit.get("prixTotal"))) + Double.parseDouble(tblXmlProduitList.get(i).getXmlProduitPrime());
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
        }

         rowMensualiteAvecAssurVal.setText(String.valueOf(utilCalcul.arrondi2chiffresPoint(Double.parseDouble(resultCredit.get("mensualite")) + somme_assurance,2)));

    }


    double calculMensAssurance(double pfTauxAssurance, double pfTauxTVA, double pfMontantAssure){
        return ((pfTauxAssurance / 100) * (1 + pfTauxTVA / 100)) * pfMontantAssure;
    }
}
