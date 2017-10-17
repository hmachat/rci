package com.orcaformation.calculetterci.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.orcaformation.calculetterci.R;
import com.orcaformation.calculetterci.utils.SessionManager;

public class MarqueActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btnRenault;
    private ImageButton btnDacia;
    private TextView creditText;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marque);


        // session manager
        session = new SessionManager(getApplicationContext());
        if (!session.isLoggedIn()) {
            logoutUser();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setAlpha(0.25f);
        fab.setOnClickListener(this);

        String typeCredit = getIntent().getStringExtra("TYPE_CREDIT");



        btnRenault = (ImageButton) findViewById(R.id.btnRenault);
        btnDacia = (ImageButton) findViewById(R.id.btnDacia);
        creditText = (TextView) findViewById(R.id.typeCreditText);

        btnRenault.setOnClickListener(this);
        btnDacia.setOnClickListener(this);
        creditText.setText(typeCredit);

    }

    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     * */
    private void logoutUser() {
        session.setLogin(false,null,null);
        Intent intent = new Intent(MarqueActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRenault:
                Intent intentRenoult = new Intent(MarqueActivity.this, ModeleActivity.class);
                intentRenoult.putExtra("Marque","renault");
                String typeCreditRenoult = getIntent().getStringExtra("TYPE_CREDIT");
                intentRenoult.putExtra("TYPE_CREDIT",typeCreditRenoult);
                startActivity(intentRenoult);
                break;
            case R.id.btnDacia:
                Intent intentDacia = new Intent(MarqueActivity.this, ModeleActivity.class);
                intentDacia.putExtra("Marque","daci");
                String typeCreditDacia = getIntent().getStringExtra("TYPE_CREDIT");
                intentDacia.putExtra("TYPE_CREDIT",typeCreditDacia);
                startActivity(intentDacia);
                break;
            case R.id.fab:
                logoutUser();
                break;
            default:
                break;
        }
    }

}
