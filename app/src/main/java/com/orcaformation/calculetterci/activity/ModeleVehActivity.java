package com.orcaformation.calculetterci.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.orcaformation.calculetterci.R;
import com.orcaformation.calculetterci.adapter.VersionAdapter;
import com.orcaformation.calculetterci.app.AppConfig;
import com.orcaformation.calculetterci.content.DBAdapter;
import com.orcaformation.calculetterci.entity.RefModeles;
import com.orcaformation.calculetterci.entity.TblVersions;
import com.orcaformation.calculetterci.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

public class ModeleVehActivity extends AppCompatActivity {

    public static int INVALID_POSITION = -1;
    public static EditText montantEdit;
    public static Spinner spinnerVersion;
    Button btnValiderModele;
    Utils utils;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modele_veh);

        montantEdit = (EditText) findViewById(R.id.editMontant);
        spinnerVersion = (Spinner) findViewById(R.id.spinnerVersion);
        spinnerVersion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                montantEdit.setText(view.getTag().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final HorizontalAdaptar adapter = new HorizontalAdaptar(this);

        RecyclerView rh = (RecyclerView) findViewById(R.id.list_horizontal);
        // create layout manager with needed params: vertical, cycle
        initRecyclerView(rh, new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, false), adapter);


        btnValiderModele = (Button) findViewById(R.id.btnValiderModele);
        btnValiderModele.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModeleVehActivity.this, ClientActivity.class);
                utils.saveInSharedPrefs(getApplicationContext(), "INFO_VEH", "MONTANT",montantEdit.getText().toString());
                startActivity(intent);
            }
        });

    }

    private void initRecyclerView(final RecyclerView recyclerView, final CarouselLayoutManager layoutManager, final HorizontalAdaptar adapter) {
        // enable zoom effect. this line can be customized
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());

        recyclerView.setLayoutManager(layoutManager);
        // we expect only fixed sized item for now
        recyclerView.setHasFixedSize(true);
        // sample adapter with random data
        recyclerView.setAdapter(adapter);
        // enable center post scrolling
        recyclerView.addOnScrollListener(new CenterScrollListener());

        layoutManager.addOnItemSelectionListener(new CarouselLayoutManager.OnCenterItemSelectionListener() {

            @Override
            public void onCenterItemChanged(final int adapterPosition) {
                ArrayList<RefModeles> RefModelesList = getModels();
                if (INVALID_POSITION != adapterPosition) {
                    final int value = adapter.mPosition[adapterPosition];
                    adapter.mPosition[adapterPosition] = (value % RefModelesList.size()) + (value / RefModelesList.size() + 1) * RefModelesList.size();
                    adapter.notifyItemChanged(adapterPosition);
                }
                DBAdapter mDbhelper = new DBAdapter(ModeleVehActivity.this.getApplicationContext()).open();
                String modeleId = RefModelesList.get(adapterPosition).getModeleId();
                Cursor crVersion = mDbhelper.fetchPrixTTCByModeleId(modeleId);
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
                VersionAdapter versionAdapter = new VersionAdapter(ModeleVehActivity.this.getApplicationContext(), R.layout.version_list_view_row, listVersion);
                ModeleVehActivity.spinnerVersion.setAdapter(versionAdapter);
                Utils.saveInSharedPrefs(ModeleVehActivity.this.getApplicationContext(), "INFO_VEH", "MODELE_ID",modeleId);
                Utils.saveInSharedPrefs(ModeleVehActivity.this.getApplicationContext(), "INFO_VEH", "MODELE_LIB",RefModelesList.get(adapterPosition).getModeleLibelle());
                Utils.saveInSharedPrefs(ModeleVehActivity.this.getApplicationContext(), "INFO_VEH", "MODELE_PHOTO",RefModelesList.get(adapterPosition).getModelePhoto());


            }
        });
    }

    private final class HorizontalAdaptar extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        ArrayList<RefModeles> RefModelesList = getModels();


        @SuppressWarnings("UnsecureRandomNumberGeneration")
        private final Random mRandom = new Random();
        private final int[] mColors;
        private final int[] mPosition;
        private Context context;



        private int mItemsCount = RefModelesList.size();
        LayoutInflater inflater;

        HorizontalAdaptar(Context context) {
            this.context=context;

            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mColors = new int[mItemsCount];
            mPosition = new int[mItemsCount];

            for (int i = 0; mItemsCount > i; ++i) {
                //noinspection MagicNumber
                mColors[i] = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
                mPosition[i] = i;

            }

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = inflater.inflate( R.layout.model_item_view, null) ;
            RecyclerView.ViewHolder holder = new RowNewsViewHolder(view);
            return holder;

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ((RowNewsViewHolder) holder).cItem1.setText(RefModelesList.get(position).getModeleLibelle());
            Picasso.with(ModeleVehActivity.this.getApplicationContext()).load(AppConfig.URL_RCI + RefModelesList.get(position).getModelePhoto()).into(((RowNewsViewHolder) holder).pp);
        }

        @Override
        public int getItemCount() {
            return mItemsCount;
        }
    }

    public static class RowNewsViewHolder extends RecyclerView.ViewHolder {
        TextView cItem1;
        ImageView pp;


        public RowNewsViewHolder(View itemView) {
            super(itemView);
            cItem1 = (TextView) itemView.findViewById(R.id.modeleTitle);
            pp = (ImageView)itemView.findViewById(R.id.modelePicture);

        }
    }

    public ArrayList<RefModeles>  getModels(){
        ArrayList<RefModeles> RefModelesList =new ArrayList<>();
        DBAdapter mDbhelper = new DBAdapter(ModeleVehActivity.this.getApplicationContext()).open();
        Cursor cr = mDbhelper.fetchModeleByMarqueId(Utils.getFromSharedPrefs(ModeleVehActivity.this.getApplicationContext(), "INFO_VEH", "MARQUE_ID"));
        if(cr.moveToFirst()) {
            for (int i = 0; i < cr.getCount(); i++) {
                RefModeles modeleObj = new RefModeles(cr.getString(cr.getColumnIndex("_modele_id")),cr.getString(cr.getColumnIndex("modele_libelle")),cr.getString(cr.getColumnIndex("marque_id")),cr.getString(cr.getColumnIndex("segment_id")),cr.getString(cr.getColumnIndex("finition_version")),cr.getString(cr.getColumnIndex("motorisation_id")),cr.getString(cr.getColumnIndex("genre_vehicule_id")),cr.getString(cr.getColumnIndex("modele_photo")),cr.getString(cr.getColumnIndex("ordre_affichage")),cr.getString(cr.getColumnIndex("created_at")),cr.getString(cr.getColumnIndex("updated_at")),cr.getString(cr.getColumnIndex("deleted_at")));
                RefModelesList.add(modeleObj);
                cr.moveToNext();
            }
        }

        return RefModelesList;
    }

}