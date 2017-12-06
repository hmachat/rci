package com.orcaformation.calculetterci.utils;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonArray;
import com.orcaformation.calculetterci.app.AppConfig;
import com.orcaformation.calculetterci.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by PC_MA22 on 05/12/2017.
 */

public class LoadPdfFromFIPE {

    public static void loadFipePDF(final Activity activity, int flag){
        final String tag_string_req = "req_get_fipe";
        final Utils utils = new Utils();

        String items = "";
        if(!utils.getFromSharedPrefs(activity.getApplicationContext(), "FINANCE", "ASS_ONE_NOM").equals("")){
            items += "{\"xml_prestation_nom\":\"" + utils.getFromSharedPrefs(activity.getApplicationContext(), "FINANCE", "ASS_ONE_NOM") + "\"" +
                    ",\"simulation_ass_montant\":" + utils.getFromSharedPrefs(activity.getApplicationContext(), "FINANCE", "ASS_ONE_MONTANT") +
                    ",\"xml_produit\":" + utils.getFromSharedPrefs(activity.getApplicationContext(), "FINANCE", "ASS_ONE_PRODUIT") + "}";
        }
        if(!utils.getFromSharedPrefs(activity.getApplicationContext(), "FINANCE", "ASS_TWO_NOM").equals("")){
            items += ",{\"xml_prestation_nom\":\"" + utils.getFromSharedPrefs(activity.getApplicationContext(), "FINANCE", "ASS_TWO_NOM") + "\"" +
                    ",\"simulation_ass_montant\":" + utils.getFromSharedPrefs(activity.getApplicationContext(), "FINANCE", "ASS_TWO_MONTANT") +
                    ",\"xml_produit\":" + utils.getFromSharedPrefs(activity.getApplicationContext(), "FINANCE", "ASS_TWO_PRODUIT") + "}";
        }
        if(!utils.getFromSharedPrefs(activity.getApplicationContext(), "FINANCE", "ASS_THREE_NOM").equals("")){
            items += ",{\"xml_prestation_nom\":\"" + utils.getFromSharedPrefs(activity.getApplicationContext(), "FINANCE", "ASS_THREE_NOM") + "\"" +
                    ",\"simulation_ass_montant\":" + utils.getFromSharedPrefs(activity.getApplicationContext(), "FINANCE", "ASS_THREE_MONTANT") +
                    ",\"xml_produit\":" + utils.getFromSharedPrefs(activity.getApplicationContext(), "FINANCE", "ASS_THREE_PRODUIT") + "}";
        }

        final String FIP = "{\"simulation_id\":\"\",\"get_Fipe_Flag\":" + flag + "," +
                "\"type_simulation\":\"android\",\"simulation_info_vehicule_marque\":\"" + utils.getFromSharedPrefs(activity.getApplicationContext(), "INFO_VEH", "MARQUE_LIB") + "\"" +
                ",\"simulation_info_vehicule_modele\":\"" + utils.getFromSharedPrefs(activity.getApplicationContext(), "INFO_VEH", "MODELE_LIB") + "\"" +
                ",\"simulation_prix_vente\":" + utils.getFromSharedPrefs(activity.getApplicationContext(), "INFO_VEH", "MONTANT") +
                ",\"simulation_option_achat\":0" +
                ",\"type_client\":\"" + utils.getFromSharedPrefs(activity.getApplicationContext(), "CLIENT", "TYPE_CLIENT_ID") + "\"" +
                ",\"simulation_info_client_nom\":\"" + utils.getFromSharedPrefs(activity.getApplicationContext(), "CLIENT", "NOM_CLIENT")  +  "\"" +
                ",\"simulation_info_client_prenom\":\"" + utils.getFromSharedPrefs(activity.getApplicationContext(), "CLIENT", "PRENOM_CLIENT") + "\"" +
                ",\"simulation_info_client_raison_sociale\":\"" + utils.getFromSharedPrefs(activity.getApplicationContext(), "CLIENT", "RAISON_CLIENT") + "\"" +
                ",\"simulation_info_client_tel_port\":\""+ utils.getFromSharedPrefs(activity.getApplicationContext(), "CLIENT", "TEL_CLIENT") + "\"" +
                ",\"simulation_info_client_email\":\"" + utils.getFromSharedPrefs(activity.getApplicationContext(), "CLIENT", "EMAIL_CLIENT") + "\"" +
                ",\"simulation_info_client_adresse\":\"" + utils.getFromSharedPrefs(activity.getApplicationContext(), "CLIENT", "ADRESSE_CLIENT") + "\"" +
                ",\"type_financement\":"+ utils.getFromSharedPrefs(activity.getApplicationContext(), "TARIF", "CHOICE") +
                ",\"simulation_tarification_id\":\"" + utils.getFromSharedPrefs(activity.getApplicationContext(), "FINANCE", "TARIFICATION_ID") + "\"" +
                ",\"simulation_version_label\":\"\"" +
                ",\"simulation_apport\":" + utils.getFromSharedPrefs(activity.getApplicationContext(), "FINANCE", "APPORT") +
                ",\"simulation_prestation_premier_loyer_majore\":0" +
                ",\"simulation_valeur_residuelle\":0" +
                ",\"simulation_mensualite_duree\":" + utils.getFromSharedPrefs(activity.getApplicationContext(), "FINANCE", "DUREE") +
                ",\"mensualites\":{\"items\":[{\"simulation_mensualite_montant\":" + utils.getFromSharedPrefs(activity.getApplicationContext(), "FINANCE", "MONS_AVEC_ASS") +
                ",\"simulation_mensualite_montant_sans_assurance\":" + utils.getFromSharedPrefs(activity.getApplicationContext(), "FINANCE", "MONS_SANS_ASS") +
                ",\"simulation_mensualite_duree\":" + utils.getFromSharedPrefs(activity.getApplicationContext(), "FINANCE", "DUREE") + "}]}" +
                ",\"assurances\":{\"items\":" +
                "[" + items + "]}" +
                ",\"packs\":{\"items\":[]}}";

        String URL_FIPE = AppConfig.URL_FIPE + "?login=" + utils.getFromSharedPrefs(activity.getApplicationContext(), "RciFinanceLogin", "login") + "&password=" + utils.getFromSharedPrefs(activity.getApplicationContext(), "RciFinanceLogin", "password") + "&fipe=" + Uri.encode(FIP) + "&_=" + "";
        Log.d("URL : ", URL_FIPE);
        final StringRequest strReq = new StringRequest(Request.Method.GET,
                URL_FIPE , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    utils.saveInSharedPrefs(activity.getApplicationContext(),"SIMULATION", "SIMULATION_ID", jsonObject.getString("simulation_id"));
                    utils.saveInSharedPrefs(activity.getApplicationContext(),"SIMULATION", "SIMULATION_PDF", jsonObject.getString("simulation_lien"));
                } catch (JSONException e) {
                    Log.e("error", "unexpected JSON exception", e);
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
