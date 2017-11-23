package com.orcaformation.calculetterci.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.orcaformation.calculetterci.R;
import com.orcaformation.calculetterci.utils.SessionManager;
import com.orcaformation.calculetterci.utils.Utils;

public class MarqueActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btnRenault;
    private ImageButton btnDacia;
    private SessionManager session;
    Activity activity;
    Utils utils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marque);

        btnRenault = (ImageButton) findViewById(R.id.btnRenault);
        btnDacia = (ImageButton) findViewById(R.id.btnDacia);

        btnRenault.setOnClickListener(this);
        btnDacia.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRenault:
                Intent intentRenoult = new Intent(MarqueActivity.this, ModeleActivity.class);
                utils.saveInSharedPrefs(getApplicationContext(), "INFO_VEH","MARQUE_ID","1");
                startActivity(intentRenoult);
                break;
            case R.id.btnDacia:
                Intent intentDacia = new Intent(MarqueActivity.this, ModeleActivity.class);
                utils.saveInSharedPrefs(getApplicationContext(),"INFO_VEH", "MARQUE_ID","2");
                startActivity(intentDacia);
                break;
            default:
                break;
        }
    }

}
