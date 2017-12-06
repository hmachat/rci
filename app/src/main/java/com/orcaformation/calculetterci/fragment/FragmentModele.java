package com.orcaformation.calculetterci.fragment;

import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orcaformation.calculetterci.R;
import com.orcaformation.calculetterci.activity.ModeleActivity;
import com.orcaformation.calculetterci.adapter.VersionAdapter;
import com.orcaformation.calculetterci.app.AppConfig;
import com.orcaformation.calculetterci.content.DBAdapter;
import com.orcaformation.calculetterci.entity.RefModeles;
import com.orcaformation.calculetterci.entity.TblVersions;
import com.orcaformation.calculetterci.layout.LinearLayoutModele;
import com.orcaformation.calculetterci.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aicha on 17/11/2017.
 */

public class FragmentModele extends Fragment {

    public static ArrayList<RefModeles> RefModelesList =new ArrayList<>();
    public Resources res;

    public static Fragment newInstance(ModeleActivity context, int pos, float scale) {
        Bundle b = new Bundle();
        b.putInt("pos", pos);
        b.putFloat("scale", scale);
        return Fragment.instantiate(context, FragmentModele.class.getName(), b);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        RefModelesList.clear();
        DBAdapter mDbhelper = new DBAdapter(getContext()).open();
        Cursor cr = mDbhelper.fetchModeleByMarqueId(Utils.getFromSharedPrefs(getContext(), "INFO_VEH", "MARQUE_ID"));
        if(cr.moveToFirst()) {
            for (int i = 0; i < cr.getCount(); i++) {
                RefModeles modeleObj = new RefModeles(cr.getString(cr.getColumnIndex("_modele_id")),cr.getString(cr.getColumnIndex("modele_libelle")),cr.getString(cr.getColumnIndex("marque_id")),cr.getString(cr.getColumnIndex("segment_id")),cr.getString(cr.getColumnIndex("finition_version")),cr.getString(cr.getColumnIndex("motorisation_id")),cr.getString(cr.getColumnIndex("genre_vehicule_id")),cr.getString(cr.getColumnIndex("modele_photo")),cr.getString(cr.getColumnIndex("ordre_affichage")),cr.getString(cr.getColumnIndex("created_at")),cr.getString(cr.getColumnIndex("updated_at")),cr.getString(cr.getColumnIndex("deleted_at")));
                RefModelesList.add(modeleObj);
                cr.moveToNext();
            }
        }

        LinearLayout l = (LinearLayout) inflater.inflate(R.layout.modele_list_view_row, container, false);
        int pos = this.getArguments().getInt("pos");
        Log.d("pos", " : " + pos);
        TextView tv = (TextView) l.findViewById(R.id.libelleModele);
        ImageButton ib = (ImageButton) l.findViewById(R.id.contentModele);
        tv.setText(RefModelesList.get(pos).getModeleLibelle());
        String modeleId = RefModelesList.get(pos).getModeleId();
        Cursor crVersion = mDbhelper.fetchPrixTTCByModeleId(modeleId);
        Log.d("modeleId",modeleId);
        ArrayList<TblVersions>  listVersion = new ArrayList<TblVersions>();
        if(crVersion.moveToFirst()) {
            for (int i = 0; i < crVersion.getCount(); i++) {
                TblVersions versionObj = new TblVersions(crVersion.getString(crVersion.getColumnIndex("_version_id")),crVersion.getString(crVersion.getColumnIndex("version_lib")),crVersion.getString(crVersion.getColumnIndex("prix_ttc")));
                if(!versionObj.getVersionLib().isEmpty()){
                    listVersion.add(versionObj);
                }
                crVersion.moveToNext();
            }
        }
        VersionAdapter versionAdapter = new VersionAdapter(getContext(), R.layout.version_list_view_row, listVersion);
        ModeleActivity.spinnerVersion.setAdapter(versionAdapter);

        Utils.saveInSharedPrefs(getContext(), "INFO_VEH", "MODELE_ID",modeleId);
        Utils.saveInSharedPrefs(getContext(), "INFO_VEH", "MODELE_LIB",RefModelesList.get(pos).getModeleLibelle());
        Utils.saveInSharedPrefs(getContext(), "INFO_VEH", "MODELE_PHOTO",RefModelesList.get(pos).getModelePhoto());
        Picasso.with(getContext()).load(AppConfig.URL_RCI + RefModelesList.get(pos).getModelePhoto()).into(ib);

        LinearLayoutModele root = (LinearLayoutModele) l.findViewById(R.id.root);
        float scale = this.getArguments().getFloat("scale");
        root.setScaleBoth(scale);
        return l;
    }
}
