package com.orcaformation.calculetterci.utils;

import android.app.Activity;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.orcaformation.calculetterci.content.DBAdapter;
import com.orcaformation.calculetterci.entity.DateFinValidite;
import com.orcaformation.calculetterci.entity.LnkBaremeTvaBiens;
import com.orcaformation.calculetterci.entity.LnkPackTypeClients;
import com.orcaformation.calculetterci.entity.LnkProduitTarifications;
import com.orcaformation.calculetterci.entity.LnkTarificationModeles;
import com.orcaformation.calculetterci.entity.LnkXmlProduitPacks;
import com.orcaformation.calculetterci.entity.LnkXmlTarificationReports;
import com.orcaformation.calculetterci.entity.LnkXmlTarificationTypeClients;
import com.orcaformation.calculetterci.entity.Marque;
import com.orcaformation.calculetterci.entity.Pack;
import com.orcaformation.calculetterci.entity.Prestation;
import com.orcaformation.calculetterci.entity.RefModeles;
import com.orcaformation.calculetterci.entity.RefPasDuree;
import com.orcaformation.calculetterci.entity.RefPrestationBaseCalcul;
import com.orcaformation.calculetterci.entity.RefReport;
import com.orcaformation.calculetterci.entity.RefTypeBareme;
import com.orcaformation.calculetterci.entity.RefTypeClient;
import com.orcaformation.calculetterci.entity.RefTypeFinancement;
import com.orcaformation.calculetterci.entity.RefTypeTva;
import com.orcaformation.calculetterci.entity.TblParamTva;
import com.orcaformation.calculetterci.entity.TblVersions;
import com.orcaformation.calculetterci.entity.TblXmlBaremes;
import com.orcaformation.calculetterci.entity.TblXmlConditions;
import com.orcaformation.calculetterci.entity.TblXmlProduit;
import com.orcaformation.calculetterci.entity.TblXmlProduits;
import com.orcaformation.calculetterci.entity.Url;
import com.orcaformation.calculetterci.entity.XmlTarification;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by PC_MA22 on 04/10/2017.
 */

public class ParseJson {

    public static Url parseUrl(String response){
        try { //test when empty its an array
            JSONObject jObj = new JSONObject(response);
            // Check for error node in json
            if (jObj.length() != 0) {
                DateFinValidite dateFinValidite = new DateFinValidite(jObj.getJSONObject("dateFinValidite").get("annee").toString(),jObj.getJSONObject("dateFinValidite").get("mois").toString(),jObj.getJSONObject("dateFinValidite").get("jour").toString());
                Url url = new Url(dateFinValidite, jObj.getString("marques"), jObj.getString("prestations"), jObj.getString("packs"), jObj.getString("credit"), jObj.getString("loa"), jObj.getString("leasing"));
                return url;
            }else{
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean parseMarqueIntoDB(String response, Activity activity) {
        final DBAdapter mDbhelper = new DBAdapter(activity).open();
        try {
                JSONArray listMarques = new JSONArray(response);
                // Check for error node in json
                if (listMarques.length() != 0) {
                    Marque[] myLisMarque = new Marque[listMarques.length()];
                    //for (int i = 0; i < listMarques.length(); i++) {
                    for (int i = 0; i < 2; i++) { //Only Renault and Dacia
                        JSONObject marque = listMarques.getJSONObject(i);
                        JSONArray listRefModeles = new JSONArray(marque.getString("RefModeles"));
                        RefModeles[] myLisRefModeles = new RefModeles[listMarques.length()];
                        for (int j = 0; j < listRefModeles.length(); j++) {
                            JSONObject refModele = listRefModeles.getJSONObject(j);
                            JSONArray listLnkTarificationModeles = new JSONArray(refModele.getString("LnkTarificationModeles"));
                            LnkTarificationModeles[] myListLnkTarificationModeles = new LnkTarificationModeles[listLnkTarificationModeles.length()];
                            for (int k = 0; k < listLnkTarificationModeles.length(); k++) {
                                LnkTarificationModeles lnkTarificationModelesObj = new LnkTarificationModeles(listLnkTarificationModeles.getJSONObject(k).getString("XmlTarificaionId"), listLnkTarificationModeles.getJSONObject(k).getString("ModeleId"));
                                myListLnkTarificationModeles[k] = lnkTarificationModelesObj;
                            }
                            JSONArray ListTblVersions = new JSONArray(refModele.getString("TblVersions"));
                            TblVersions[] myListTblVersions = new TblVersions[listLnkTarificationModeles.length()];
                            for (int l = 0; l < ListTblVersions.length(); l++) {
                                Object objTblXmlProduitJson = ListTblVersions.getJSONObject(l).get("TblXmlProduit");
                                TblXmlProduit TblXmlProduitObj;
                                if(objTblXmlProduitJson instanceof JSONObject){
                                    JSONObject TblXmlProduitJson = new JSONObject(ListTblVersions.getJSONObject(l).getString("TblXmlProduit"));
                                     TblXmlProduitObj = new TblXmlProduit(TblXmlProduitJson.getString("XmlProduitId"), TblXmlProduitJson.getString("XmlPrestationId"), TblXmlProduitJson.getString("ZoneId"), TblXmlProduitJson.getString("XmlProduitLibelle"), TblXmlProduitJson.getString("XmlProduitCondition"), TblXmlProduitJson.getString("XmlProduitCode"), TblXmlProduitJson.getString("XmlProduitPrime"), TblXmlProduitJson.getString("XmlProduitTaux"), TblXmlProduitJson.getString("XmlProduitPlancher"), TblXmlProduitJson.getString("XmlProduitPlafond"), TblXmlProduitJson.getString("DeletedAt"), TblXmlProduitJson.getString("CreatedAt"), TblXmlProduitJson.getString("UpdatedAt"), TblXmlProduitJson.getString("PrestationModeFacturationId"), TblXmlProduitJson.getString("PrestationBaseCalculId"), TblXmlProduitJson.getString("PrestationTypeId"), TblXmlProduitJson.getString("PrestationObligatoire"), TblXmlProduitJson.getString("PrestationChecked"), TblXmlProduitJson.getString("PrestationDisabled"), TblXmlProduitJson.getString("PrestationVisible"), TblXmlProduitJson.getString("PrestationIsFd"), TblXmlProduitJson.getString("TypeInformationId"), TblXmlProduitJson.getString("Description"));
                                }else{
                                    TblXmlProduitObj = new TblXmlProduit();
                                }
                                TblVersions TblVersionsObj = new TblVersions(ListTblVersions.getJSONObject(l).getString("VersionId"), ListTblVersions.getJSONObject(l).getString("ModeleId"), ListTblVersions.getJSONObject(l).getString("VersionLib"), ListTblVersions.getJSONObject(l).getString("PrixTtc"), ListTblVersions.getJSONObject(l).getString("PrixHt"), ListTblVersions.getJSONObject(l).getString("TauxTva"), ListTblVersions.getJSONObject(l).getString("MontantTva"), ListTblVersions.getJSONObject(l).getString("XmlProduitId"), TblXmlProduitObj);
                                myListTblVersions[l] = TblVersionsObj;
                                mDbhelper.createVersion(TblVersionsObj);
                            }
                            RefModeles RefModelesObj = new RefModeles(refModele.getString("ModeleId"), refModele.getString("ModeleLibelle"), refModele.getString("MarqueId"), refModele.getString("SegmentId"), refModele.getString("FinitionVersion"), refModele.getString("MotorisationId"), refModele.getString("GenreVehiculeId"), refModele.getString("ModelePhoto"), refModele.getString("OrdreAffichage"), refModele.getString("CreatedAt"), refModele.getString("UpdatedAt"), refModele.getString("DeletedAt"), myListLnkTarificationModeles, myListTblVersions);
                            mDbhelper.createModele(RefModelesObj);
                            myLisRefModeles[i] = RefModelesObj;
                        }
                        Marque marqueObj = new Marque(listMarques.getJSONObject(i).getString("IdRefMarque"), listMarques.getJSONObject(i).getString("LibelleMarque"), listMarques.getJSONObject(i).getString("LogoMarque"), listMarques.getJSONObject(i).getString("DeltaTna"), listMarques.getJSONObject(i).getString("CreatedAt"), listMarques.getJSONObject(i).getString("UpdatedAt"), listMarques.getJSONObject(i).getString("DeletedAt"), myLisRefModeles);
                        mDbhelper.createMarque(marqueObj);
                        myLisMarque[i] = marqueObj;
                    }
                    return true;
                } else {
                    return false;
                }
            } catch (JSONException e) {
                e.printStackTrace();
        }
        return false;
    }

    public static Prestation[] parsePrestation(String response){
        Prestation[] myListPrestation = new Prestation[3];
        try {
            JSONArray listPrestations = new JSONArray(response);
            // Check for error node in json
            if (listPrestations.length() != 0) {
                myListPrestation = new Prestation[listPrestations.length()];
                for (int i = 0; i < listPrestations.length(); i++) {
                    JSONObject prestation = listPrestations.getJSONObject(i);
                    JSONArray listTblXmlProduits = new JSONArray(prestation.getString("TblXmlProduits"));
                    TblXmlProduits[] myListTblXmlProduits = new TblXmlProduits[listTblXmlProduits.length()];
                    for (int j = 0; j < listTblXmlProduits.length(); j++) {
                        JSONObject TblXmlProduits = listTblXmlProduits.getJSONObject(j);
                        RefPrestationBaseCalcul RefPrestationBaseCalculObj = new RefPrestationBaseCalcul();
                        if(TblXmlProduits.get("RefPrestationBaseCalcul") instanceof JSONObject){
                            JSONObject RefPrestationBaseCalcul = new JSONObject(TblXmlProduits.getString("RefPrestationBaseCalcul"));
                            RefPrestationBaseCalculObj = new RefPrestationBaseCalcul(RefPrestationBaseCalcul.getString("PrestationBaseCalculId"), RefPrestationBaseCalcul.getString("PrestationBaseCalculLibelle"));
                        }
                        com.orcaformation.calculetterci.entity.TblXmlProduits TblXmlProduitsObj = new TblXmlProduits(listTblXmlProduits.getJSONObject(j).getString("XmlProduitId"),listTblXmlProduits.getJSONObject(j).getString("XmlPrestationId"),listTblXmlProduits.getJSONObject(j).getString("ZoneId"),listTblXmlProduits.getJSONObject(j).getString("XmlProduitLibelle"),listTblXmlProduits.getJSONObject(j).getString("XmlProduitCondition"),listTblXmlProduits.getJSONObject(j).getString("XmlProduitCode"),listTblXmlProduits.getJSONObject(j).getString("XmlProduitPrime"),listTblXmlProduits.getJSONObject(j).getString("XmlProduitTaux"),listTblXmlProduits.getJSONObject(j).getString("XmlProduitPlancher"),listTblXmlProduits.getJSONObject(j).getString("XmlProduitPlafond"),listTblXmlProduits.getJSONObject(j).getString("PrestationModeFacturationId"),listTblXmlProduits.getJSONObject(j).getString("PrestationBaseCalculId"),listTblXmlProduits.getJSONObject(j).getString("PrestationTypeId"),listTblXmlProduits.getJSONObject(j).getString("PrestationObligatoire"),listTblXmlProduits.getJSONObject(j).getString("PrestationChecked"),listTblXmlProduits.getJSONObject(j).getString("PrestationDisabled"),listTblXmlProduits.getJSONObject(j).getString("PrestationVisible"),listTblXmlProduits.getJSONObject(j).getString("PrestationIsFd"),listTblXmlProduits.getJSONObject(j).getString("TypeInformationId"),listTblXmlProduits.getJSONObject(j).getString("Description"), RefPrestationBaseCalculObj);
                        myListTblXmlProduits[j] = TblXmlProduitsObj;
                    }
                    Prestation PrestationeObj = new Prestation(listPrestations.getJSONObject(i).getString("XmlPrestationId"), listPrestations.getJSONObject(i).getString("XmlPrestationLibelle"), listPrestations.getJSONObject(i).getString("XmlPrestationCode"), listPrestations.getJSONObject(i).getString("XmlPrestationLibelleComplement"), listPrestations.getJSONObject(i).getString("XmlPrestationMention"), listPrestations.getJSONObject(i).getString("XmlPrestationOrdre"), listPrestations.getJSONObject(i).getString("TypeFinancementId"), myListTblXmlProduits);
                    myListPrestation[i] = PrestationeObj;
                }
                return myListPrestation;

            } else {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return myListPrestation;
    }

    public static ArrayList<Pack> parsePack(String response){
        ArrayList<Pack> myListPack = new ArrayList<>();
        try {
            JSONArray listPacks = new JSONArray(response);
            // Check for error node in json
            if (listPacks.length() != 0) {
                myListPack = new ArrayList<>();
                for (int i = 0; i < listPacks.length(); i++) {
                    JSONObject pack = listPacks.getJSONObject(i);

                    JSONObject RefTypeFinancement = new JSONObject(pack.getString("RefTypeFinancement"));
                    com.orcaformation.calculetterci.entity.RefTypeFinancement myRefTypeFinancement = new RefTypeFinancement(RefTypeFinancement.getString("TypeFinancementId"),RefTypeFinancement.getString("TypeFinancementLibelle"),RefTypeFinancement.getString("TypeFinancementLibelleLong"),RefTypeFinancement.getString("TypeInformationId"),RefTypeFinancement.getString("Description"));


                    JSONArray listLnkXmlProduitPacks = new JSONArray(pack.getString("LnkXmlProduitPacks"));
                    LnkXmlProduitPacks[] myLnkXmlProduitPacks = new LnkXmlProduitPacks[listLnkXmlProduitPacks.length()];
                    for (int j = 0; j < listLnkXmlProduitPacks.length(); j++) {
                        JSONObject lnkXmlProduitPacks = listLnkXmlProduitPacks.getJSONObject(j);
                        JSONObject tblXmlProduit = new JSONObject(lnkXmlProduitPacks.getString("TblXmlProduit"));
                        TblXmlProduit TblXmlProduitObj = new TblXmlProduit(tblXmlProduit.getString("XmlProduitId"), tblXmlProduit.getString("XmlPrestationId"), tblXmlProduit.getString("ZoneId"), tblXmlProduit.getString("XmlProduitLibelle"), tblXmlProduit.getString("XmlProduitCondition"), tblXmlProduit.getString("XmlProduitCode"), tblXmlProduit.getString("XmlProduitPrime"), tblXmlProduit.getString("XmlProduitTaux"), tblXmlProduit.getString("XmlProduitPlancher"), tblXmlProduit.getString("XmlProduitPlafond"), tblXmlProduit.getString("DeletedAt"), tblXmlProduit.getString("CreatedAt"), tblXmlProduit.getString("UpdatedAt"), tblXmlProduit.getString("PrestationModeFacturationId"), tblXmlProduit.getString("PrestationBaseCalculId"), tblXmlProduit.getString("PrestationTypeId"), tblXmlProduit.getString("PrestationObligatoire"), tblXmlProduit.getString("PrestationChecked"), tblXmlProduit.getString("PrestationDisabled"), tblXmlProduit.getString("PrestationVisible"), tblXmlProduit.getString("PrestationIsFd"), tblXmlProduit.getString("TypeInformationId"), tblXmlProduit.getString("Description"));
                        LnkXmlProduitPacks LnkXmlProduitPacksObj = new LnkXmlProduitPacks(lnkXmlProduitPacks.getString("XmlPackId"), lnkXmlProduitPacks.getString("XmlProduitId"), TblXmlProduitObj);
                        myLnkXmlProduitPacks[j] = LnkXmlProduitPacksObj;
                    }


                    JSONArray listLnkPackTypeClients = new JSONArray(pack.getString("LnkPackTypeClients"));
                    LnkPackTypeClients[] myLnkPackTypeClients = new LnkPackTypeClients[listLnkPackTypeClients.length()];
                    for (int k = 0; k < listLnkPackTypeClients.length(); k++) {
                        JSONObject lnkPackTypeClients = listLnkPackTypeClients.getJSONObject(i);
                        JSONObject refTypeClient = new JSONObject(lnkPackTypeClients.getString("RefTypeClient"));
                        RefTypeClient RefTypeClientObj = new RefTypeClient(refTypeClient.getString("TypeClientId"), refTypeClient.getString("TypeClientLibelle"), refTypeClient.getString("TypeClientLibelleLong"), refTypeClient.getString("TypeClientCode"));
                        LnkPackTypeClients LnkPackTypeClientsObj = new LnkPackTypeClients(lnkPackTypeClients.getString("XmlPackId"), lnkPackTypeClients.getString("XmlTypeClientId"), RefTypeClientObj);
                        myLnkPackTypeClients[k] = LnkPackTypeClientsObj;
                    }


                    Pack PackObj = new Pack(pack.getString("XmlPackId"), pack.getString("TypeFinancementId"), pack.getString("XmlPackLibelle"), pack.getString("XmlPackTaux"), pack.getString("XmlPackDescreption"), pack.getString("XmlPackEntretien"), pack.getString("CreatedAt"), pack.getString("UpdatedAt"), pack.getString("DeletedAt"),myRefTypeFinancement,myLnkXmlProduitPacks, myLnkPackTypeClients);
                    myListPack.add(PackObj);
                }
                return myListPack;

            } else {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return myListPack;
    }

    public static ArrayList<XmlTarification> parseTarificationIntoDB(String response, String typeTarif, Activity activity){
        final DBAdapter mDbhelper = new DBAdapter(activity).open();
        ArrayList<XmlTarification> myListTarification = new ArrayList<>();
        try {
            JSONObject listTarifications = new JSONObject(response);
            // Check for error node in json
            if (listTarifications.length() != 0) {
                myListTarification = new ArrayList<>();
                JsonParser jsonParser = new JsonParser();
                JsonObject gsonlistTarifications = (JsonObject)jsonParser.parse(listTarifications.toString());
                for (Map.Entry<String,JsonElement> tarification : gsonlistTarifications.entrySet()) {
                    JsonArray LnkProduitTarificationsList = tarification.getValue().getAsJsonObject().getAsJsonArray("LnkProduitTarifications");
                    LnkProduitTarifications[] myLnkProduitTarifications = new LnkProduitTarifications[LnkProduitTarificationsList.size()];
                    int i =0;
                    for (JsonElement lnkProduitTarifications : LnkProduitTarificationsList) {
                        JsonObject tblXmlProduit = lnkProduitTarifications.getAsJsonObject().get("TblXmlProduit").getAsJsonObject();
                        TblXmlProduit tblXmlProduitObj = new TblXmlProduit();
                        LnkProduitTarifications LnkProduitTarificationsObj = new LnkProduitTarifications();
                        if(!tblXmlProduit.entrySet().isEmpty()){ //en pp credit.json 18
                            tblXmlProduitObj = new TblXmlProduit(tblXmlProduit.getAsJsonObject().get("XmlProduitId").toString(), tblXmlProduit.getAsJsonObject().get("XmlPrestationId").toString(), tblXmlProduit.getAsJsonObject().get("ZoneId").toString(), tblXmlProduit.getAsJsonObject().get("XmlProduitLibelle").toString().replace("\"", ""), tblXmlProduit.getAsJsonObject().get("XmlProduitCondition").toString(), tblXmlProduit.getAsJsonObject().get("XmlProduitCode").toString().replace("\"", ""), tblXmlProduit.getAsJsonObject().get("XmlProduitPrime").toString(), tblXmlProduit.getAsJsonObject().get("XmlProduitTaux").toString(), tblXmlProduit.getAsJsonObject().get("XmlProduitPlancher").toString(), tblXmlProduit.getAsJsonObject().get("XmlProduitPlafond").toString(), tblXmlProduit.getAsJsonObject().get("CreatedAt").toString(), tblXmlProduit.getAsJsonObject().get("UpdatedAt").toString(), tblXmlProduit.getAsJsonObject().get("DeletedAt").toString(), tblXmlProduit.getAsJsonObject().get("PrestationModeFacturationId").toString(), tblXmlProduit.getAsJsonObject().get("PrestationBaseCalculId").toString(), tblXmlProduit.getAsJsonObject().get("PrestationTypeId").toString(), tblXmlProduit.getAsJsonObject().get("PrestationObligatoire").toString(), tblXmlProduit.getAsJsonObject().get("PrestationChecked").toString(), tblXmlProduit.getAsJsonObject().get("PrestationDisabled").toString(), tblXmlProduit.getAsJsonObject().get("PrestationVisible").toString(), tblXmlProduit.getAsJsonObject().get("PrestationIsFd").toString(), tblXmlProduit.getAsJsonObject().get("TypeInformationId").toString(), tblXmlProduit.getAsJsonObject().get("Description").toString().replace("\"", ""));
                            mDbhelper.createTblXmlProduit(tblXmlProduitObj);
                            LnkProduitTarificationsObj = new LnkProduitTarifications(lnkProduitTarifications.getAsJsonObject().get("XmlProduitId").toString(), lnkProduitTarifications.getAsJsonObject().get("XmlTarificationId").toString(), tblXmlProduitObj);
                            myLnkProduitTarifications[i] = LnkProduitTarificationsObj;
                            mDbhelper.createLnkProduitTarifications(LnkProduitTarificationsObj);
                        }
                        i++;
                    }
                    JsonObject refTypeBareme = tarification.getValue().getAsJsonObject().getAsJsonObject("RefTypeBareme");
                    RefTypeBareme RefTypeBaremeObj = new RefTypeBareme(refTypeBareme.getAsJsonObject().get("TypeBaremeId").toString(), refTypeBareme.getAsJsonObject().get("TypeBaremeLibelle").toString(), refTypeBareme.getAsJsonObject().get("BaremeIsPromo").toString());
                    JsonArray TblXmlBaremesList = tarification.getValue().getAsJsonObject().getAsJsonArray("TblXmlBaremes");
                    TblXmlBaremes[] myTblXmlBaremes = new TblXmlBaremes[TblXmlBaremesList.size()];
                    int j = 0;
                    for(JsonElement tblXmlBaremesList : TblXmlBaremesList){

                        JsonArray TblXmlConditionsList = tblXmlBaremesList.getAsJsonObject().get("TblXmlConditions").getAsJsonArray();
                        TblXmlConditions[] myTblXmlConditions = new TblXmlConditions[TblXmlConditionsList.size()];
                        int k = 0;
                        for(JsonElement tblXmlConditions : TblXmlConditionsList){
                            TblXmlConditions TblXmlConditionsObj = new TblXmlConditions(tblXmlConditions.getAsJsonObject().get("XmlConditionId").toString(), tblXmlConditions.getAsJsonObject().get("XmlBaremeId").toString(), tblXmlConditions.getAsJsonObject().get("XmlConditionTypeId").toString(), tblXmlConditions.getAsJsonObject().get("XmlConditionLibelle").toString(), tblXmlConditions.getAsJsonObject().get("XmlConditionTna").toString(), tblXmlConditions.getAsJsonObject().get("XmlConditionDeltaTna").toString(), tblXmlConditions.getAsJsonObject().get("XmlConditionNbEcheanceFd").toString(), tblXmlConditions.getAsJsonObject().get("NatureBienId").toString(), tblXmlConditions.getAsJsonObject().get("NatureMoteurId").toString(), tblXmlConditions.getAsJsonObject().get("NiveauPrixId").toString(), tblXmlConditions.getAsJsonObject().get("XmlConditionFd").toString(), tblXmlConditions.getAsJsonObject().get("XmlConditionFdPlafond").toString(), tblXmlConditions.getAsJsonObject().get("XmlConditionMoisMin").toString(), tblXmlConditions.getAsJsonObject().get("XmlConditionMoisMax").toString(), tblXmlConditions.getAsJsonObject().get("XmlConditionMontant").toString(), tblXmlConditions.getAsJsonObject().get("XmlConditionMontantMin").toString(), tblXmlConditions.getAsJsonObject().get("XmlConditionMontantMax").toString(), tblXmlConditions.getAsJsonObject().get("XmlConditionApport").toString(), tblXmlConditions.getAsJsonObject().get("XmlConditionApportMinTx").toString(), tblXmlConditions.getAsJsonObject().get("XmlConditionApportMaxTx").toString(), tblXmlConditions.getAsJsonObject().get("XmlConditionNiveauPrime").toString(), tblXmlConditions.getAsJsonObject().get("XmlConditionDelegation").toString(), tblXmlConditions.getAsJsonObject().get("XmlConditionTxPremierLoyerMin").toString(), tblXmlConditions.getAsJsonObject().get("XmlConditionTxPremierLoyerMax").toString(), tblXmlConditions.getAsJsonObject().get("XmlConditionTxDgMin").toString(), tblXmlConditions.getAsJsonObject().get("XmlConditionTxDgMax").toString(), tblXmlConditions.getAsJsonObject().get("XmlConditionTxVrMin").toString(), tblXmlConditions.getAsJsonObject().get("XmlConditionTxVrMax").toString(), tblXmlConditions.getAsJsonObject().get("XmlConditionDiffere").toString(), tblXmlConditions.getAsJsonObject().get("XmlConditionDuree").toString(), tblXmlConditions.getAsJsonObject().get("XmlConditionDureeMin").toString(), tblXmlConditions.getAsJsonObject().get("XmlConditionDureeMax").toString(), tblXmlConditions.getAsJsonObject().get("XmlProduitId").toString(), tblXmlConditions.getAsJsonObject().get("CreatedAt").toString(), tblXmlConditions.getAsJsonObject().get("UpdatedAt").toString(), tblXmlConditions.getAsJsonObject().get("DeletedAt").toString());
                            myTblXmlConditions[k] = TblXmlConditionsObj;
                            mDbhelper.createTblXmlConditions(TblXmlConditionsObj);
                            k++;
                        }

                        JsonObject refPasDuree = tblXmlBaremesList.getAsJsonObject().get("RefPasDuree").getAsJsonObject();
                        RefPasDuree RefPasDureeObj = new RefPasDuree(refPasDuree.getAsJsonObject().get("PasDureeId").toString(), refPasDuree.getAsJsonObject().get("PasDureeValeur").toString());
                        mDbhelper.createRefPasDuree(RefPasDureeObj);

                        JsonArray LnkBaremeTvaBiensList = tblXmlBaremesList.getAsJsonObject().get("LnkBaremeTvaBiens").getAsJsonArray();
                        LnkBaremeTvaBiens[] myLnkBaremeTvaBiens = new LnkBaremeTvaBiens[LnkBaremeTvaBiensList.size()];
                        int l = 0;
                        for(JsonElement lnkBaremeTvaBiens : LnkBaremeTvaBiensList){
                            JsonObject tblParamTva = lnkBaremeTvaBiens.getAsJsonObject().get("TblParamTva").getAsJsonObject();
                            JsonObject refTypeTva = tblParamTva.getAsJsonObject().get("RefTypeTva").getAsJsonObject();
                            RefTypeTva RefTypeTvaObj = new RefTypeTva();
                            TblParamTva TblParamTvaObj = new TblParamTva();
                            LnkBaremeTvaBiens LnkBaremeTvaBiensObj = new LnkBaremeTvaBiens();
                            if(!refTypeTva.entrySet().isEmpty()){
                                RefTypeTvaObj = new RefTypeTva(refTypeTva.getAsJsonObject().get("TypeTvaId").toString(), refTypeTva.getAsJsonObject().get("TypeTvaLibelle").toString(), refTypeTva.getAsJsonObject().get("TvaStatique").toString());
                                TblParamTvaObj = new TblParamTva(tblParamTva.getAsJsonObject().get("ParamTvaId").toString(), tblParamTva.getAsJsonObject().get("TypeTvaId").toString(), tblParamTva.getAsJsonObject().get("TypeFinancementId").toString(), tblParamTva.getAsJsonObject().get("ValeurTva").toString(), RefTypeTvaObj);
                                LnkBaremeTvaBiensObj = new LnkBaremeTvaBiens(lnkBaremeTvaBiens.getAsJsonObject().get("XmlBaremeId").toString(), lnkBaremeTvaBiens.getAsJsonObject().get("ParamTvaId").toString(), TblParamTvaObj);
                            }
                            myLnkBaremeTvaBiens[l] = LnkBaremeTvaBiensObj;
                            l++;
                        }

                        JsonObject tblParamTva = tblXmlBaremesList.getAsJsonObject().get("TblParamTva").getAsJsonObject();
                        TblParamTva TblParamTvaObj = new TblParamTva(tblParamTva.getAsJsonObject().get("ParamTvaId").toString(), tblParamTva.getAsJsonObject().get("TypeTvaId").toString(), tblParamTva.getAsJsonObject().get("TypeFinancementId").toString(), tblParamTva.getAsJsonObject().get("ValeurTva").toString());
                        mDbhelper.createTblParamTva(TblParamTvaObj);

                        TblXmlBaremes TblXmlBaremesObj = new TblXmlBaremes(tblXmlBaremesList.getAsJsonObject().get("XmlBaremeId").toString(), tblXmlBaremesList.getAsJsonObject().get("XmlTarificationId").toString(), tblXmlBaremesList.getAsJsonObject().get("XmlBaremeCode").toString(), tblXmlBaremesList.getAsJsonObject().get("TypeBaremeId").toString(), tblXmlBaremesList.getAsJsonObject().get("ParamTvaBienId").toString(), tblXmlBaremesList.getAsJsonObject().get("ParamTvaFiId").toString(), tblXmlBaremesList.getAsJsonObject().get("XmlBaremeNbEcheanceFd").toString(), tblXmlBaremesList.getAsJsonObject().get("XmlBaremeFd").toString(), tblXmlBaremesList.getAsJsonObject().get("XmlBaremeFdPlafond").toString(), tblXmlBaremesList.getAsJsonObject().get("XmlBaremeDateOuverture").toString(), tblXmlBaremesList.getAsJsonObject().get("XmlBaremeDateFermeture").toString(), tblXmlBaremesList.getAsJsonObject().get("XmlBaremeTnaDefault").toString(), tblXmlBaremesList.getAsJsonObject().get("PasDureeId").toString(), tblXmlBaremesList.getAsJsonObject().get("XmlBaremeDelegation").toString(), tblXmlBaremesList.getAsJsonObject().get("XmlBaremeTxPremierLoyer").toString(), tblXmlBaremesList.getAsJsonObject().get("XmlBaremeTxDg").toString().replace("\"", ""), tblXmlBaremesList.getAsJsonObject().get("XmlBaremeTxVr").toString().replace("\"", ""), tblXmlBaremesList.getAsJsonObject().get("CreatedAt").toString(), tblXmlBaremesList.getAsJsonObject().get("UpdatedAt").toString(), tblXmlBaremesList.getAsJsonObject().get("DeletedAt").toString(), myTblXmlConditions, RefPasDureeObj, myLnkBaremeTvaBiens, TblParamTvaObj);
                        myTblXmlBaremes[j] = TblXmlBaremesObj;
                        mDbhelper.createTblXmlBaremes(TblXmlBaremesObj);
                        j++;
                    }


                    JsonArray LnkXmlTarificationTypeClientsList = tarification.getValue().getAsJsonObject().getAsJsonArray("LnkXmlTarificationTypeClients");
                    LnkXmlTarificationTypeClients[] myLnkXmlTarificationTypeClients = new LnkXmlTarificationTypeClients[LnkXmlTarificationTypeClientsList.size()];
                    int m = 0;
                    for(JsonElement lnkXmlTarificationTypeClients : LnkXmlTarificationTypeClientsList){
                        JsonObject refTypeClient = lnkXmlTarificationTypeClients.getAsJsonObject().get("RefTypeClient").getAsJsonObject();
                        RefTypeClient RefTypeClientObj = new RefTypeClient(refTypeClient.getAsJsonObject().get("TypeClientId").toString(), refTypeClient.getAsJsonObject().get("TypeClientLibelle").toString().replace("\"", ""), refTypeClient.getAsJsonObject().get("TypeClientLibelleLong").toString().replace("\"", ""), refTypeClient.getAsJsonObject().get("TypeClientCode").toString().replace("\"", ""));
                        mDbhelper.createRefTypeClient(RefTypeClientObj);
                        LnkXmlTarificationTypeClients LnkXmlTarificationTypeClientsObj = new LnkXmlTarificationTypeClients(lnkXmlTarificationTypeClients.getAsJsonObject().get("XmlTarificationId").toString(), lnkXmlTarificationTypeClients.getAsJsonObject().get("TypeClientId").toString(), RefTypeClientObj);
                        myLnkXmlTarificationTypeClients[m] = LnkXmlTarificationTypeClientsObj;
                        mDbhelper.createLnkXmlTarificationTypeClients(LnkXmlTarificationTypeClientsObj);
                        m++;
                    }

                    JsonArray LnkXmlTarificationReportsList = tarification.getValue().getAsJsonObject().getAsJsonArray("LnkXmlTarificationReports");
                    LnkXmlTarificationReports[] myLnkXmlTarificationReports = new LnkXmlTarificationReports[LnkXmlTarificationReportsList.size()];
                    int n = 0;
                    for(JsonElement lnkXmlTarificationReports : LnkXmlTarificationReportsList){
                        JsonObject refReport = lnkXmlTarificationReports.getAsJsonObject().get("RefReport").getAsJsonObject();
                        RefReport RefReportObj = new RefReport();
                        if(!refReport.entrySet().isEmpty()){
                            RefReportObj = new RefReport(refReport.getAsJsonObject().get("ReportId").toString(), refReport.getAsJsonObject().get("ReportLibelle").toString(), refReport.getAsJsonObject().get("ReportValeur").toString());
                            mDbhelper.createRefReport(RefReportObj);
                            LnkXmlTarificationReports LnkXmlTarificationReportsObj = new LnkXmlTarificationReports(lnkXmlTarificationReports.getAsJsonObject().get("XmlTarificationId").toString(), lnkXmlTarificationReports.getAsJsonObject().get("ReportId").toString(), RefReportObj);
                            myLnkXmlTarificationReports[n] = LnkXmlTarificationReportsObj;
                            mDbhelper.createLnkXmlTarificationReports(LnkXmlTarificationReportsObj);
                        }
                        n++;
                    }


                    XmlTarification creditObj = new XmlTarification(typeTarif, tarification.getValue().getAsJsonObject().get("XmlTarificationId").toString(), tarification.getValue().getAsJsonObject().get("XmlTarificationLibelle").toString().replace("\"", ""), tarification.getValue().getAsJsonObject().get("TypeBaremeId").toString(), tarification.getValue().getAsJsonObject().get("TypeVehiculeId").toString(), tarification.getValue().getAsJsonObject().get("TypeClientIdASupp").toString(), tarification.getValue().getAsJsonObject().get("MarcheId").toString(), tarification.getValue().getAsJsonObject().get("TypeFinancementId").toString(), tarification.getValue().getAsJsonObject().get("ZoneId").toString(), tarification.getValue().getAsJsonObject().get("CreatedAt").toString(), tarification.getValue().getAsJsonObject().get("UpdatedAt").toString(), tarification.getValue().getAsJsonObject().get("DeletedAt").toString(), tarification.getValue().getAsJsonObject().get("IdRefMarque").toString(), tarification.getValue().getAsJsonObject().get("ModeleId").toString(), tarification.getValue().getAsJsonObject().get("NatureBienId").toString(), tarification.getValue().getAsJsonObject().get("NatureMoteurId").toString(), tarification.getValue().getAsJsonObject().get("MarqueDeltaTna").toString(), myLnkProduitTarifications, RefTypeBaremeObj, myTblXmlBaremes, myLnkXmlTarificationTypeClients, myLnkXmlTarificationReports);
                    mDbhelper.createTarification(creditObj);
                    myListTarification.add(creditObj);
                }

                return myListTarification;

            } else {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return myListTarification;
    }

}
