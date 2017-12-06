package com.orcaformation.calculetterci.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.orcaformation.calculetterci.R;
import com.orcaformation.calculetterci.adapter.CarouselModeleAdapter;
import com.orcaformation.calculetterci.utils.Utils;


public class ModeleActivity extends AppCompatActivity {

    public static EditText montantEdit;
    public static Spinner spinnerVersion;
    Button btnValiderModele;
    Utils utils;



    public CarouselModeleAdapter adapter;
    public ViewPager pager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modele);

        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        pager = (ViewPager) findViewById(R.id.myviewpager);
        adapter = new CarouselModeleAdapter(this, this.getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setPageTransformer(false, adapter);
        pager.setCurrentItem(CarouselModeleAdapter.FIRST_PAGE);
        pager.setOffscreenPageLimit(3);
        pager.setPageMargin(-200);

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

        btnValiderModele = (Button) findViewById(R.id.btnValiderModele);
        btnValiderModele.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModeleActivity.this, ClientActivity.class);
                utils.saveInSharedPrefs(getApplicationContext(), "INFO_VEH", "MONTANT",montantEdit.getText().toString());
                startActivity(intent);
            }
        });

    }


}
