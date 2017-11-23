package com.orcaformation.calculetterci.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.orcaformation.calculetterci.R;
import com.orcaformation.calculetterci.adapter.CarouselModeleAdapter;
import com.orcaformation.calculetterci.utils.Utils;


public class ModeleActivity extends FragmentActivity {

    public static EditText montantEdit;
    Button btnValiderModele;
    Utils utils;



    public CarouselModeleAdapter adapter;
    public ViewPager pager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modele);

        pager = (ViewPager) findViewById(R.id.myviewpager);
        adapter = new CarouselModeleAdapter(this, this.getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setPageTransformer(false, adapter);
        pager.setCurrentItem(CarouselModeleAdapter.FIRST_PAGE);
        pager.setOffscreenPageLimit(3);
        pager.setPageMargin(-200);

        montantEdit = (EditText) findViewById(R.id.editMontant);

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
