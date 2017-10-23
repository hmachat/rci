package com.orcaformation.calculetterci.content;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.orcaformation.calculetterci.entity.LnkTarificationModeles;
import com.orcaformation.calculetterci.entity.Marque;
import com.orcaformation.calculetterci.entity.RefModeles;
import com.orcaformation.calculetterci.entity.TblVersions;

/**
 * Created by PC_MA22 on 18/10/2017.
 */

public class DBAdapter {

    private static final String DATABASE_NAME = "rci_finance_db";
    private static final int DATABASE_VERSION = 15;

    //Database table
    public static final String TABLE_MARQUE = "marque";
    public static final String TABLE_MODELE = "modele";
    public static final String TABLE_VERSION = "version";

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


    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase dataBase) {
            dataBase.execSQL(TABLE_MARQUE_CREATE);
            dataBase.execSQL(TABLE_MODELE_CREATE);
            dataBase.execSQL(TABLE_VERSION_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase dataBase, int oldVersion, int newVersion) {

            dataBase.execSQL("DROP TABLE IF EXISTS " + TABLE_MARQUE);
            dataBase.execSQL("DROP TABLE IF EXISTS " + TABLE_MODELE);
            dataBase.execSQL("DROP TABLE IF EXISTS " + TABLE_VERSION);
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

}
