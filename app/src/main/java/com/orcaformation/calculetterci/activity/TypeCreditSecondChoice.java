package com.orcaformation.calculetterci.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.orcaformation.calculetterci.R;
import com.orcaformation.calculetterci.utils.SessionManager;
import com.orcaformation.calculetterci.utils.Utils;

public class TypeCreditSecondChoice extends Activity implements View.OnClickListener {

    private ImageButton btnCredit;
    private ImageButton btnLoa;
    private SessionManager session;

    Activity activity;
    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_credit_second_choice);

        btnCredit = (ImageButton) findViewById(R.id.btnCredit);
        btnLoa = (ImageButton) findViewById(R.id.btnLoa);

        btnCredit.setOnClickListener(this);
        btnLoa.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCredit:
                Intent intentCredit = new Intent(TypeCreditSecondChoice.this, FinanceCreditActivity.class);
                utils.saveInSharedPrefs(getApplicationContext(), "TARIF", "CHOICE", "1");
                startActivity(intentCredit);
                break;
            case R.id.btnLoa:
                Intent intentLeasing = new Intent(TypeCreditSecondChoice.this, FinanceLoaActivity.class);
                utils.saveInSharedPrefs(getApplicationContext(), "TARIF", "CHOICE", "2");
                startActivity(intentLeasing);
                break;
            default:
                break;
        }
    }
}
