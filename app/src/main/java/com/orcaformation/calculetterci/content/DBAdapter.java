package com.orcaformation.calculetterci.content;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.orcaformation.calculetterci.entity.LnkProduitTarifications;
import com.orcaformation.calculetterci.entity.LnkXmlTarificationReports;
import com.orcaformation.calculetterci.entity.Marque;
import com.orcaformation.calculetterci.entity.RefModeles;
import com.orcaformation.calculetterci.entity.RefReport;
import com.orcaformation.calculetterci.entity.RefTypeClient;
import com.orcaformation.calculetterci.entity.TblVersions;
import com.orcaformation.calculetterci.entity.TblXmlBaremes;
import com.orcaformation.calculetterci.entity.XmlTarification;

/**
 * Created by PC_MA22 on 18/10/2017.
 */

public class DBAdapter {

    private static final String DATABASE_NAME = "calcullete_rci_db";
    private static final int DATABASE_VERSION = 15;

    //Database table
    public static final String TABLE_MARQUE = "marque";
    public static final String TABLE_MODELE = "modele";
    public static final String TABLE_VERSION = "version";
    public static final String TABLE_TARIFICATION = "tarification";
    public static final String TABLE_REF_TYPE_CLIENT = "ref_type_client";
    public static final String TblXmlConditions = "TblXmlConditions";
    public static final String TblXmlBaremes = "TblXmlBaremes";
    public static final String LnkXmlTarificationTypeClients = "LnkXmlTarificationTypeClients";
    public static final String RefPasDuree = "RefPasDuree";
    public static final String LnkProduitTarifications = "LnkProduitTarifications";
    public static final String TblXmlProduit = "TblXmlProduit";
    public static final String TblParamTva = "TblParamTva";
    public static final String LnkXmlTarificationReports = "LnkXmlTarificationReports";
    public static final String RefReport = "RefReport";

    //marque fields
    public static final String ID_REF_MARQUE = "_id_ref_marque";
    public static final String LIBELLE_MARQUE = "libelle_marque";
    public static final String LOGO_MARQUE = "logo_marque";
    public static final String DELTA_TNA = "delta_tna";

    //modele fields
    private static final String MODELE_ID = "_modele_id";
    private static final String MODELE_LIBELLE = "modele_libelle";
    private static final String MARQUE_ID = "marque_id";
    private static final String SEGMENT_ID = "segment_id";
    private static final String FINITION_VERSION = "finition_version";
    private static final String MOTORISATION_ID = "motorisation_id";
    private static final String GENRE_VEHICULE_ID = "genre_vehicule_id";
    private static final String MODELE_PHOTO = "modele_photo";
    private static final String ORDRE_AFFICHAGE = "ordre_affichage";
    private static final String CREATED_AT = "created_at";
    private static final String UPDATED_AT = "updated_at";
    private static final String DELETED_AT = "deleted_at";

    //version fields
    private static final String VERSION_ID = "_version_id";
    private static final String REF_MODELE_ID = "ref_modele_id";
    private static final String VERSION_LIB = "version_lib";
    private static final String PRIX_TTC = "prix_ttc";
    private static final String PRIX_HT = "prix_ht";
    private static final String MONTANT_TVA = "montant_tva";
    private static final String XML_PRODUIT_ID = "xml_produit_id";
    private static final String TAUX_TVA = "taux_tva";

    //tarification fields
    private static final String TYPE_TARIICATION = "type_tarification";
    private static final String TARIFICATION_ID = "_tarification_id";
    private static final String TARIFICATION_LIBELLE = "tarification_libelle";
    private static final String TARIFICATION_TYPE_BAREME = "type_bareme_id";
    private static final String TARIFICATION_TYPE_FINANCEMENT = "TypeFinancementId";
    private static final String TARIFICATION_TYPE_VEHICULE = "type_vehicule_id";
    private static final String TARIFICATION_ID_REF_MARQUE = "id_ref_marque";
    private static final String TARIFICATION_MODELE_ID = "modele_id";

    //ref type client fields
    private static final String REF_TYPE_CLIENT_ID = "ref_type_client_id";
    private static final String REF_TYPE_CLIENT_LIBELLE = "ref_type_client_libelle";
    private static final String REF_TYPE_CLIENT_LIBELLE_LONG = "ref_type_client_libelle_long";
    private static final String REF_TYPE_CLIENT_CODE = "ref_type_client_code";


    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    private final Context mContext;

    /**
     * Database creation sql statement
     */
    private static final String TABLE_MARQUE_CREATE =
            "create table " + TABLE_MARQUE + " (" +ID_REF_MARQUE+ " integer primary key, "
                    + LIBELLE_MARQUE + " text, "
                    + LOGO_MARQUE + " text, "
                    + DELTA_TNA + " text)";

    private static final String TABLE_MODELE_CREATE =
            "create table " + TABLE_MODELE + " (" +MODELE_ID+ " integer primary key, "
                    + MODELE_LIBELLE + " text, "
                    + MARQUE_ID + " text, "
                    + SEGMENT_ID + " text, "
                    + FINITION_VERSION + " text, "
                    + MOTORISATION_ID + " text, "
                    + GENRE_VEHICULE_ID + " text, "
                    + MODELE_PHOTO + " text, "
                    + ORDRE_AFFICHAGE + " text, "
                    + CREATED_AT + " text, "
                    + UPDATED_AT + " text, "
                    + DELETED_AT + " text)";


    private static final String TABLE_VERSION_CREATE =
            "create table " + TABLE_VERSION + " (" +VERSION_ID+ " integer primary key, "
                    + REF_MODELE_ID + " text, "
                    + VERSION_LIB + " text, "
                    + PRIX_TTC + " text, "
                    + PRIX_HT + " text, "
                    + MONTANT_TVA + " text, "
                    + XML_PRODUIT_ID + " text, "
                    + TAUX_TVA + " text)";

    private static final String TABLE_TARIFICATION_CREATE =
            "create table " + TABLE_TARIFICATION + " (" +TARIFICATION_ID+ " integer primary key, "
                    + TYPE_TARIICATION + " text, "
                    + TARIFICATION_LIBELLE + " text, "
                    + TARIFICATION_TYPE_BAREME + " text, "
                    + TARIFICATION_TYPE_FINANCEMENT + " text, "
                    + TARIFICATION_TYPE_VEHICULE + " text, "
                    + TARIFICATION_ID_REF_MARQUE + " text, "
                    + TARIFICATION_MODELE_ID + " text)";

    private static final String TABLE_REF_TYPE_CLIENT_CREATE =
            "create table " + TABLE_REF_TYPE_CLIENT + " (" +REF_TYPE_CLIENT_ID+ " integer primary key, "
                    + REF_TYPE_CLIENT_LIBELLE + " text, "
                    + REF_TYPE_CLIENT_LIBELLE_LONG + " text, "
                    + REF_TYPE_CLIENT_CODE + " text)";

    private static final String TblXmlConditions_CREATE =
            "create table " + TblXmlConditions + " (" + "XmlConditionId" + " integer primary key, "
                    + "XmlBaremeId" + " text, "
                    + "XmlConditionTypeId" + " text, "
                    + "XmlConditionLibelle" + " text, "
                    + "XmlConditionTna" + " text, "
                    + "XmlConditionDeltaTna" + " text, "
                    + "XmlConditionNbEcheanceFd" + " text, "
                    + "NatureBienId" + " text, "
                    + "NatureMoteurId" + " text, "
                    + "NiveauPrixId" + " text, "
                    + "XmlConditionFd" + " text, "
                    + "XmlConditionFdPlafond" + " text, "
                    + "XmlConditionMoisMin" + " text, "
                    + "XmlConditionMoisMax" + " text, "
                    + "XmlConditionMontant" + " text, "
                    + "XmlConditionMontantMin" + " text, "
                    + "XmlConditionMontantMax" + " text, "
                    + "XmlConditionApport" + " text, "
                    + "XmlConditionApportMinTx" + " text, "
                    + "XmlConditionApportMaxTx" + " text, "
                    + "XmlConditionNiveauPrime" + " text, "
                    + "XmlConditionDelegation" + " text, "
                    + "XmlConditionTxPremierLoyerMin" + " text, "
                    + "XmlConditionTxPremierLoyerMax" + " text, "
                    + "XmlConditionTxDgMin" + " text, "
                    + "XmlConditionTxDgMax" + " text, "
                    + "XmlConditionTxVrMin" + " text, "
                    + "XmlConditionTxVrMax" + " text, "
                    + "XmlConditionDiffere" + " text, "
                    + "XmlConditionDuree" + " text, "
                    + "XmlConditionDureeMin" + " text, "
                    + "XmlConditionDureeMax" + " text, "
                    + "XmlProduitId" + " text, "
                    + "CreatedAt" + " text, "
                    + "UpdatedAt" + " text, "
                    + "DeletedAt" + " text)";


    private static final String TblXmlBaremes_CREATE =
            "create table " + TblXmlBaremes + " (" + "XmlBaremeId" + " integer primary key, "
                    + "XmlTarificationId" + " text, "
                    + "XmlBaremeCode" + " text, "
                    + "TypeBaremeId" + " text, "
                    + "ParamTvaBienId" + " text, "
                    + "ParamTvaFiId" + " text, "
                    + "XmlBaremeNbEcheanceFd" + " text, "
                    + "XmlBaremeFd" + " text, "
                    + "XmlBaremeFdPlafond" + " text, "
                    + "XmlBaremeDateOuverture" + " text, "
                    + "XmlBaremeDateFermeture" + " text, "
                    + "XmlBaremeTnaDefault" + " text, "
                    + "PasDureeId" + " text, "
                    + "XmlBaremeDelegation" + " text, "
                    + "XmlBaremeTxPremierLoyer" + " text, "
                    + "XmlBaremeTxDg" + " text, "
                    + "XmlBaremeTxVr" + " text, "
                    + "CreatedAt" + " text, "
                    + "UpdatedAt" + " text, "
                    + "DeletedAt" + " text)";

    private static final String LnkXmlTarificationTypeClients_CREATE =
            "create table " + LnkXmlTarificationTypeClients + " (" + "XmlTarificationId" + " integer primary key, "
                    + "TypeClientId" + " text)";

    private static final String RefPasDuree_CREATE =
            "create table " + RefPasDuree + " (" + "PasDureeId" + " integer primary key, "
                    + "PasDureeValeur" + " text)";

    private static final String LnkProduitTarifications_CREATE =
            "create table " + LnkProduitTarifications + " (" + "XmlProduitId" + " integer primary key, "
                    + "XmlTarificationId" + " text)";

    private static final String TblXmlProduit_CREATE =
            "create table " + TblXmlProduit + " (" + "XmlProduitId" + " integer primary key, "
                    + "XmlPrestationId" + " text, "
                    + "ZoneId" + " text, "
                    + "XmlProduitLibelle" + " text, "
                    + "XmlProduitCondition" + " text, "
                    + "XmlProduitCode" + " text, "
                    + "XmlProduitPrime" + " text, "
                    + "XmlProduitTaux" + " text, "
                    + "XmlProduitPlancher" + " text, "
                    + "XmlProduitPlafond" + " text, "
                    + "CreatedAt" + " text, "
                    + "UpdatedAt" + " text, "
                    + "DeletedAt" + " text, "
                    + "PrestationModeFacturationId" + " text, "
                    + "PrestationBaseCalculId" + " text, "
                    + "PrestationTypeId" + " text, "
                    + "PrestationObligatoire" + " text, "
                    + "PrestationChecked" + " text, "
                    + "PrestationDisabled" + " text, "
                    + "PrestationVisible" + " text, "
                    + "PrestationIsFd" + " text, "
                    + "TypeInformationId" + " text, "
                    + "Description" + " text)";


    private static final String TblParamTva_CREATE =
            "create table " + TblParamTva + " (" + "ParamTvaId" + " integer primary key, "
                    + "TypeTvaId" + " text, "
                    + "TypeFinancementId" + " text, "
                    + "ValeurTva" + " text)";

    private static final String LnkXmlTarificationReports_CREATE =
            "create table " + LnkXmlTarificationReports + " (" + "XmlTarificationId" + " integer primary key, "
                    + "ReportId" + " text)";

    private static final String RefReport_CREATE =
            "create table " + RefReport + " (" + "ReportId" + " integer primary key, "
                    + "ReportLibelle" + " text, "
                    + "ReportValeur" + " text)";


    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase dataBase) {
            dataBase.execSQL(TABLE_MARQUE_CREATE);
            dataBase.execSQL(TABLE_MODELE_CREATE);
            dataBase.execSQL(TABLE_VERSION_CREATE);
            dataBase.execSQL(TABLE_TARIFICATION_CREATE);
            dataBase.execSQL(TABLE_REF_TYPE_CLIENT_CREATE);
            dataBase.execSQL(TblXmlConditions_CREATE);
            dataBase.execSQL(TblXmlBaremes_CREATE);
            dataBase.execSQL(LnkXmlTarificationTypeClients_CREATE);
            dataBase.execSQL(RefPasDuree_CREATE);
            dataBase.execSQL(LnkProduitTarifications_CREATE);
            dataBase.execSQL(TblXmlProduit_CREATE);
            dataBase.execSQL(TblParamTva_CREATE);
            dataBase.execSQL(LnkXmlTarificationReports_CREATE);
            dataBase.execSQL(RefReport_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase dataBase, int oldVersion, int newVersion) {
            dataBase.execSQL("DROP TABLE IF EXISTS " + TABLE_MARQUE);
            dataBase.execSQL("DROP TABLE IF EXISTS " + TABLE_MODELE);
            dataBase.execSQL("DROP TABLE IF EXISTS " + TABLE_VERSION);
            dataBase.execSQL("DROP TABLE IF EXISTS " + TABLE_TARIFICATION);
            dataBase.execSQL("DROP TABLE IF EXISTS " + TABLE_REF_TYPE_CLIENT);
            dataBase.execSQL("DROP TABLE IF EXISTS " + TblXmlConditions);
            dataBase.execSQL("DROP TABLE IF EXISTS " + TblXmlBaremes);
            dataBase.execSQL("DROP TABLE IF EXISTS " + LnkXmlTarificationTypeClients);
            dataBase.execSQL("DROP TABLE IF EXISTS " + RefPasDuree);
            dataBase.execSQL("DROP TABLE IF EXISTS " + LnkProduitTarifications);
            dataBase.execSQL("DROP TABLE IF EXISTS " + TblXmlProduit);
            dataBase.execSQL("DROP TABLE IF EXISTS " + TblParamTva);
            dataBase.execSQL("DROP TABLE IF EXISTS " + LnkXmlTarificationReports);
            dataBase.execSQL("DROP TABLE IF EXISTS " + RefReport);
            onCreate(dataBase);
        }
    }

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     *
     * @param ctx the Context within which to work
     */
    public DBAdapter(Context ctx) {
        this.mContext = ctx;
    }

    /**
     * Open the my_alerts_db database. If it cannot be opened, try to create a new
     * instance of the database. If it cannot be created, throw an exception to
     * signal the failure
     *
     * @return this (self reference, allowing this to be chained in an
     *         initialization call)
     * @throws SQLException if the database could be neither opened or created
     */
    public DBAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mContext);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public long createMarque(Marque marque)
    {
        ContentValues values = new ContentValues();

        values.put(ID_REF_MARQUE, Integer.parseInt(marque.getIdRefMarque()));
        values.put(LIBELLE_MARQUE, marque.getLibelleMarque());
        values.put(LOGO_MARQUE, marque.getLogoMarque());
        values.put(DELTA_TNA, marque.getDeltaTna());

        return mDb.insert(TABLE_MARQUE, null, values);
    }

    public long createModele(RefModeles modele)
    {
        ContentValues values = new ContentValues();

        values.put(MODELE_ID, Integer.parseInt(modele.getModeleId()));
        values.put(MODELE_LIBELLE, modele.getModeleLibelle());
        values.put(MARQUE_ID, modele.getMarqueId());
        values.put(SEGMENT_ID, modele.getSegmentId());
        values.put(FINITION_VERSION, modele.getFinitionVersion());
        values.put(MOTORISATION_ID, modele.getMotorisationId());
        values.put(GENRE_VEHICULE_ID, modele.getGenreVehiculeId());
        values.put(MODELE_PHOTO, modele.getModelePhoto());
        values.put(ORDRE_AFFICHAGE, modele.getOrdreAffichage());
        values.put(CREATED_AT, modele.getCreatedAt());
        values.put(UPDATED_AT, modele.getUpdatedAt());
        values.put(DELETED_AT, modele.getDeletedAt());

        return mDb.insert(TABLE_MODELE, null, values);
    }

    public long createVersion(TblVersions version)
    {
        ContentValues values = new ContentValues();

        values.put(VERSION_ID, Integer.parseInt(version.getVersionId()));
        values.put(REF_MODELE_ID, version.getModeleId());
        values.put(VERSION_LIB, version.getVersionLib());
        values.put(PRIX_TTC, version.getPrixTtc());
        values.put(PRIX_HT, version.getPrixHt());
        values.put(MONTANT_TVA, version.getMontantTva());
        values.put(XML_PRODUIT_ID, version.getXmlProduitId());
        values.put(TAUX_TVA, version.getTauxTva());

        return mDb.insert(TABLE_VERSION, null, values);
    }

    public long createTarification(XmlTarification tarification)
    {
        ContentValues values = new ContentValues();

        values.put(TARIFICATION_ID, Integer.parseInt(tarification.getXmlTarificationId()));
        values.put(TYPE_TARIICATION, tarification.getTypeId());
        values.put(TARIFICATION_LIBELLE, tarification.getXmlTarificationLibelle());
        values.put(TARIFICATION_TYPE_BAREME, tarification.getTypeBaremeId());
        values.put(TARIFICATION_TYPE_FINANCEMENT, tarification.getTypeFinancementId());
        values.put(TARIFICATION_TYPE_VEHICULE, tarification.getTypeVehiculeId());
        values.put(TARIFICATION_ID_REF_MARQUE, tarification.getIdRefMarque());
        values.put(TARIFICATION_MODELE_ID, tarification.getModeleId());

        return mDb.insert(TABLE_TARIFICATION, null, values);
    }

    public long createRefTypeClient(RefTypeClient client)
    {
        ContentValues values = new ContentValues();

        values.put(REF_TYPE_CLIENT_ID, Integer.parseInt(client.getTypeClientId()));
        values.put(REF_TYPE_CLIENT_LIBELLE, client.getTypeClientLibelle());
        values.put(REF_TYPE_CLIENT_LIBELLE_LONG, client.getTypeClientLibelleLong());
        values.put(REF_TYPE_CLIENT_CODE, client.getTypeClientCode());

        return mDb.insert(TABLE_REF_TYPE_CLIENT, null, values);
    }


    public long createTblXmlConditions(com.orcaformation.calculetterci.entity.TblXmlConditions tblXmlConditions)
    {
        ContentValues values = new ContentValues();

        values.put("XmlConditionId", Integer.parseInt(tblXmlConditions.getXmlConditionId()));
        values.put("XmlBaremeId", tblXmlConditions.getXmlBaremeId());
        values.put("XmlConditionTypeId", tblXmlConditions.getXmlConditionTypeId());
        values.put("XmlConditionLibelle", tblXmlConditions.getXmlConditionLibelle());
        values.put("XmlConditionTna", tblXmlConditions.getXmlConditionDeltaTna());
        values.put("XmlConditionDeltaTna", tblXmlConditions.getXmlConditionDeltaTna());
        values.put("XmlConditionNbEcheanceFd", tblXmlConditions.getXmlConditionNbEcheanceFd());
        values.put("NatureBienId", tblXmlConditions.getNatureBienId());
        values.put("NatureMoteurId", tblXmlConditions.getNatureMoteurId());
        values.put("NiveauPrixId", tblXmlConditions.getNiveauPrixId());
        values.put("XmlConditionFd", tblXmlConditions.getXmlConditionFd());
        values.put("XmlConditionFdPlafond", tblXmlConditions.getXmlConditionFdPlafond());
        values.put("XmlConditionMoisMin", tblXmlConditions.getXmlConditionMoisMin());
        values.put("XmlConditionMoisMax", tblXmlConditions.getXmlConditionMoisMax());
        values.put("XmlConditionMontant", tblXmlConditions.getXmlConditionMontant());
        values.put("XmlConditionMontantMin", tblXmlConditions.getXmlConditionMontantMin());
        values.put("XmlConditionMontantMax", tblXmlConditions.getXmlConditionMontantMax());
        values.put("XmlConditionApport", tblXmlConditions.getXmlConditionApport());
        values.put("XmlConditionApportMinTx", tblXmlConditions.getXmlConditionApportMinTx());
        values.put("XmlConditionApportMaxTx", tblXmlConditions.getXmlConditionApportMaxTx());
        values.put("XmlConditionNiveauPrime", tblXmlConditions.getXmlConditionNiveauPrime());
        values.put("XmlConditionDelegation", tblXmlConditions.getXmlConditionDelegation());
        values.put("XmlConditionTxPremierLoyerMin", tblXmlConditions.getXmlConditionTxPremierLoyerMin());
        values.put("XmlConditionTxPremierLoyerMax", tblXmlConditions.getXmlConditionTxPremierLoyerMax());
        values.put("XmlConditionTxDgMin", tblXmlConditions.getXmlConditionTxDgMin());
        values.put("XmlConditionTxDgMax", tblXmlConditions.getXmlConditionTxDgMax());
        values.put("XmlConditionTxVrMin", tblXmlConditions.getXmlConditionTxVrMin());
        values.put("XmlConditionTxVrMax", tblXmlConditions.getXmlConditionTxVrMax());
        values.put("XmlConditionDiffere", tblXmlConditions.getXmlConditionDiffere());
        values.put("XmlConditionDuree", tblXmlConditions.getXmlConditionDuree());
        values.put("XmlConditionDureeMin", tblXmlConditions.getXmlConditionDureeMin());
        values.put("XmlConditionDureeMax", tblXmlConditions.getXmlConditionDureeMax());
        values.put("XmlProduitId", tblXmlConditions.getXmlProduitId());
        values.put("CreatedAt", tblXmlConditions.getCreatedAt());
        values.put("UpdatedAt", tblXmlConditions.getUpdatedAt());
        values.put("DeletedAt", tblXmlConditions.getDeletedAt());

        return mDb.insert(TblXmlConditions, null, values);
    }


    public long createTblXmlBaremes(TblXmlBaremes tblXmlBaremes)
    {
        ContentValues values = new ContentValues();

        values.put("XmlBaremeId", Integer.parseInt(tblXmlBaremes.getXmlBaremeId()));
        values.put("XmlTarificationId", tblXmlBaremes.getXmlTarificationId());
        values.put("XmlBaremeCode", tblXmlBaremes.getXmlBaremeCode());
        values.put("TypeBaremeId", tblXmlBaremes.getTypeBaremeId());
        values.put("ParamTvaBienId", tblXmlBaremes.getParamTvaBienId());
        values.put("ParamTvaFiId", tblXmlBaremes.getParamTvaFiId());
        values.put("XmlBaremeNbEcheanceFd", tblXmlBaremes.getXmlBaremeNbEcheanceFd());
        values.put("XmlBaremeFd", tblXmlBaremes.getXmlBaremeFd());
        values.put("XmlBaremeFdPlafond", tblXmlBaremes.getXmlBaremeFdPlafond());
        values.put("XmlBaremeDateOuverture", tblXmlBaremes.getXmlBaremeDateOuverture());
        values.put("XmlBaremeDateFermeture", tblXmlBaremes.getXmlBaremeDateFermeture());
        values.put("XmlBaremeTnaDefault", tblXmlBaremes.getXmlBaremeTnaDefault());
        values.put("PasDureeId", tblXmlBaremes.getPasDureeId());
        values.put("XmlBaremeDelegation", tblXmlBaremes.getXmlBaremeDelegation());
        values.put("XmlBaremeTxPremierLoyer", tblXmlBaremes.getXmlBaremeTxPremierLoyer());
        values.put("XmlBaremeTxDg", tblXmlBaremes.getXmlBaremeTxDg());
        values.put("XmlBaremeTxVr", tblXmlBaremes.getXmlBaremeTxVr());
        values.put("CreatedAt", tblXmlBaremes.getCreatedAt());
        values.put("UpdatedAt", tblXmlBaremes.getUpdatedAt());
        values.put("DeletedAt", tblXmlBaremes.getDeletedAt());

        return mDb.insert(TblXmlBaremes, null, values);
    }


    public long createLnkXmlTarificationTypeClients(com.orcaformation.calculetterci.entity.LnkXmlTarificationTypeClients lnkXmlTarificationTypeClients)
    {
        ContentValues values = new ContentValues();
        values.put("XmlTarificationId", Integer.parseInt(lnkXmlTarificationTypeClients.getXmlTarificationId()));
        values.put("TypeClientId", lnkXmlTarificationTypeClients.getTypeClientId());
        return mDb.insert(LnkXmlTarificationTypeClients, null, values);
    }

    public long createLnkXmlTarificationReports(LnkXmlTarificationReports lnkXmlTarificationReports)
    {
        ContentValues values = new ContentValues();
        values.put("XmlTarificationId", Integer.parseInt(lnkXmlTarificationReports.getXmlTarificationId()));
        values.put("ReportId", lnkXmlTarificationReports.getReportId());
        return mDb.insert(LnkXmlTarificationReports, null, values);
    }

    public long createRefPasDuree(com.orcaformation.calculetterci.entity.RefPasDuree refPasDuree)
    {
        ContentValues values = new ContentValues();

        values.put("PasDureeId", Integer.parseInt(refPasDuree.getPasDureeId()));
        values.put("PasDureeValeur", refPasDuree.getPasDureeValeur());

        return mDb.insert(RefPasDuree, null, values);
    }

    public long createLnkProduitTarifications(LnkProduitTarifications lnkProduitTarifications)
    {
        ContentValues values = new ContentValues();

        values.put("XmlProduitId", Integer.parseInt(lnkProduitTarifications.getXmlProduitId()));
        values.put("XmlTarificationId", lnkProduitTarifications.getXmlTarificationId());

        return mDb.insert(LnkProduitTarifications, null, values);
    }

    public long createTblXmlProduit(com.orcaformation.calculetterci.entity.TblXmlProduit tblXmlProduit)
    {
        ContentValues values = new ContentValues();

        values.put("XmlProduitId", Integer.parseInt(tblXmlProduit.getXmlProduitId()));
        values.put("XmlPrestationId", tblXmlProduit.getXmlPrestationId());
        values.put("ZoneId", tblXmlProduit.getZoneId());
        values.put("XmlProduitLibelle", tblXmlProduit.getXmlProduitLibelle());
        values.put("XmlProduitCondition", tblXmlProduit.getXmlProduitCondition());
        values.put("XmlProduitCode", tblXmlProduit.getXmlProduitCode());
        values.put("XmlProduitPrime", tblXmlProduit.getXmlProduitPrime());
        values.put("XmlProduitTaux", tblXmlProduit.getXmlProduitTaux());
        values.put("XmlProduitPlancher", tblXmlProduit.getXmlProduitPlancher());
        values.put("XmlProduitPlafond", tblXmlProduit.getXmlProduitPlafond());
        values.put("CreatedAt", tblXmlProduit.getCreatedAt());
        values.put("UpdatedAt", tblXmlProduit.getUpdatedAt());
        values.put("DeletedAt", tblXmlProduit.getDeletedAt());
        values.put("PrestationModeFacturationId", tblXmlProduit.getPrestationModeFacturationId());
        values.put("PrestationBaseCalculId", tblXmlProduit.getPrestationBaseCalculId());
        values.put("PrestationTypeId", tblXmlProduit.getPrestationTypeId());
        values.put("PrestationObligatoire", tblXmlProduit.getPrestationObligatoire());
        values.put("PrestationChecked", tblXmlProduit.getPrestationChecked());
        values.put("PrestationDisabled", tblXmlProduit.getPrestationDisabled());
        values.put("PrestationVisible", tblXmlProduit.getPrestationVisible());
        values.put("PrestationIsFd", tblXmlProduit.getPrestationIsFd());
        values.put("TypeInformationId", tblXmlProduit.getTypeInformationId());
        values.put("Description", tblXmlProduit.getDescription());

        return mDb.insert(TblXmlProduit, null, values);
    }

    public long createTblParamTva(com.orcaformation.calculetterci.entity.TblParamTva tblParamTva)
    {
        ContentValues values = new ContentValues();

        values.put("ParamTvaId", Integer.parseInt(tblParamTva.getParamTvaId()));
        values.put("TypeTvaId", tblParamTva.getTypeTvaId());
        values.put("TypeFinancementId", tblParamTva.getTypeFinancementId());
        values.put("ValeurTva", tblParamTva.getValeurTva());

        return mDb.insert(TblParamTva, null, values);
    }

    public long createRefReport(RefReport refReport)
    {
        ContentValues values = new ContentValues();
        values.put("ReportId", Integer.parseInt(refReport.getReportId()));
        values.put("ReportLibelle", refReport.getReportLibelle());
        values.put("ReportValeur", refReport.getReportValeur());
        return mDb.insert(RefReport, null, values);
    }

    public Cursor fetchReportValeurByXmlTarificationId(String XmlTarificationId) {
        String reportId = "";
        Cursor cursorReportId = mDb.rawQuery("SELECT ReportId FROM LnkXmlTarificationReports WHERE  XmlTarificationId = '" + XmlTarificationId.trim() + "'", null);
        if(cursorReportId.moveToFirst()) {
            reportId = cursorReportId.getString(cursorReportId.getColumnIndex("ReportId"));
        }
        return mDb.rawQuery("SELECT ReportValeur FROM RefReport WHERE ReportId = '"+ reportId + "'", null);
    }

    public Cursor fetchValeurTvaByTypeFinancementId(String TypeFinancementId) {
        return mDb.rawQuery("SELECT ValeurTva FROM TblParamTva WHERE TypeFinancementId = '"+ TypeFinancementId + "'", null);
    }


    public Cursor fetchModeleByMarqueId(String idMarque) {
        //return mDb.query(TABLE_MODELE, null, MARQUE_ID + " = '" + idMarque + "'", null, null, null, MODELE_ID + " ASC");
        //return mDb.rawQuery("SELECT _modele_id,modele_libelle FROM modele WHERE marque_id = '"+idSMarque+"'", null);
        //return mDb.rawQuery("SELECT * FROM modele", null);
        return mDb.rawQuery("SELECT * FROM " + TABLE_MODELE + " WHERE " + MARQUE_ID + " = '" + idMarque.trim() + "' ORDER BY " + MODELE_ID + " ASC", null);

    }

    public Cursor fetchAllRefTypeClient() {
        return mDb.rawQuery("SELECT * FROM " + TABLE_REF_TYPE_CLIENT + " ORDER BY " + REF_TYPE_CLIENT_ID + " ASC", null);

    }


    public Cursor fetchBaremeLibelleByTypeTarificationAndTypeClientId(String typeTarification, String typeClientId) {
        Cursor cursorXmlTarificationId = mDb.rawQuery("SELECT XmlTarificationId FROM " + LnkXmlTarificationTypeClients + " WHERE  TypeClientId = '" + typeClientId.trim() + "'", null);
        String[] tarificationByTypeClientList = new String[cursorXmlTarificationId.getCount()];
        if(cursorXmlTarificationId.moveToFirst()) {
            for (int i = 0; i < cursorXmlTarificationId.getCount(); i++) {
                tarificationByTypeClientList[i] = cursorXmlTarificationId.getString(cursorXmlTarificationId.getColumnIndex("XmlTarificationId"));
                cursorXmlTarificationId.moveToNext();
            }
        }
        //////////////////////////////////////////////////////
        StringBuilder csvStr = new StringBuilder();
        for(int i=0;i<tarificationByTypeClientList.length;i++){
            if (tarificationByTypeClientList.length > 1 && i !=0) {
                csvStr.append(",");
            }
            csvStr.append ("'").append(tarificationByTypeClientList[i]).append("'");
        }
        /////////////////////////////////////////////////////
        return mDb.rawQuery("SELECT tarification_libelle,_tarification_id FROM tarification WHERE type_tarification = '"+ typeTarification + "' AND _tarification_id IN (".concat(csvStr.toString()).concat(")"), null);
    }

    public Cursor fetchLibelleAndTypeFinancementIdByXmlTarificationId(String XmlTarificationId) {
        return mDb.rawQuery("SELECT tarification_libelle,TypeFinancementId FROM tarification WHERE _tarification_id = '"+ XmlTarificationId + "'", null);
    }

    public Cursor fetchTauxVrByXmlTarificationId(String XmlTarificationId) {
        return mDb.rawQuery("SELECT XmlBaremeId,XmlBaremeTxVr,PasDureeId,XmlBaremeTnaDefault FROM TblXmlBaremes WHERE XmlTarificationId = '"+ XmlTarificationId + "'", null);
    }

    public Cursor fetchDureeByXmlBaremeId(String XmlBaremeId) {
        return mDb.rawQuery("SELECT XmlConditionMoisMin,XmlConditionMoisMax FROM TblXmlConditions WHERE XmlBaremeId = '"+ XmlBaremeId + "'", null);
    }

    public Cursor fetchApportTxByXmlBaremeId(String XmlBaremeId) {
        return mDb.rawQuery("SELECT XmlConditionApportMinTx,XmlConditionApportMaxTx FROM TblXmlConditions WHERE XmlBaremeId = '"+ XmlBaremeId + "'", null);
    }

    //TODO
    public Cursor fetchLoyerTxByXmlBaremeId(String XmlBaremeId) {
        return mDb.rawQuery("SELECT XmlConditionMontantMin,XmlConditionMontantMax,XmlConditionTxPremierLoyerMin,XmlConditionTxPremierLoyerMax FROM TblXmlConditions WHERE XmlBaremeId = '"+ XmlBaremeId + "'", null);
    }

    public Cursor fetchDureePasById(String PasDureeId) {
        return mDb.rawQuery("SELECT PasDureeValeur FROM RefPasDuree WHERE PasDureeId = '"+ PasDureeId + "'", null);
    }

    public Cursor fetchPrixTTCByModeleId(String ModeleId) {
        return mDb.rawQuery("SELECT prix_ttc FROM version WHERE ref_modele_id = '"+ ModeleId + "'", null);
    }

    public Cursor fetchPrixTTCByVersion_id(String version_id) {
        return mDb.rawQuery("SELECT prix_ttc FROM version WHERE _version_id = '"+ version_id + "'", null);
    }

    public Cursor fetchChecksByXmlProduitId(String XmlTarificationId) {
        Cursor cursorXmlProduitId = mDb.rawQuery("SELECT XmlProduitId FROM " + LnkProduitTarifications + " WHERE  XmlTarificationId = '" + XmlTarificationId.trim() + "'", null);
        String[] XmlProduitIdList = new String[cursorXmlProduitId.getCount()];
        if(cursorXmlProduitId.moveToFirst()) {
            for (int i = 0; i < cursorXmlProduitId.getCount(); i++) {
                XmlProduitIdList[i] = cursorXmlProduitId.getString(cursorXmlProduitId.getColumnIndex("XmlProduitId"));
                cursorXmlProduitId.moveToNext();
            }
        }
        //////////////////////////////////////////////////////
        StringBuilder csvStr = new StringBuilder();
        for(int i=0;i<XmlProduitIdList.length;i++){
            if (XmlProduitIdList.length > 1 && i !=0) {
                csvStr.append(",");
            }
            csvStr.append ("'").append(XmlProduitIdList[i]).append("'");
        }
        /////////////////////////////////////////////////////
        return mDb.rawQuery("SELECT XmlProduitId,XmlProduitTaux,XmlProduitPrime,PrestationIsFd,XmlProduitLibelle,PrestationBaseCalculId,PrestationObligatoire,PrestationChecked,PrestationDisabled,PrestationVisible FROM TblXmlProduit WHERE XmlProduitId IN (".concat(csvStr.toString()).concat(")"), null);
    }





}
