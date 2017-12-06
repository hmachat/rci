package com.orcaformation.calculetterci.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonObject;
import com.orcaformation.calculetterci.R;
import com.orcaformation.calculetterci.app.AppConfig;
import com.orcaformation.calculetterci.app.AppController;
import com.orcaformation.calculetterci.utils.DialogManager;
import com.orcaformation.calculetterci.utils.SessionManager;
import com.orcaformation.calculetterci.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends Activity {

    private Button btnLogin;
    private EditText inputLogin;
    private EditText inputPassword;
    private ProgressDialog pDialog;
    private SessionManager session;

    public LoginActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*getApplicationContext().getSharedPreferences("url", Context.MODE_PRIVATE).edit().clear().apply();
        getApplicationContext().getSharedPreferences("marques", Context.MODE_PRIVATE).edit().clear().apply();
        getApplicationContext().getSharedPreferences("prestations", Context.MODE_PRIVATE).edit().clear().apply();
        getApplicationContext().getSharedPreferences("packs", Context.MODE_PRIVATE).edit().clear().apply();
        getApplicationContext().getSharedPreferences("credits", Context.MODE_PRIVATE).edit().clear().apply();
        getApplicationContext().getSharedPreferences("loas", Context.MODE_PRIVATE).edit().clear().apply();
        getApplicationContext().getSharedPreferences("leasings", Context.MODE_PRIVATE).edit().clear().apply();*/

        inputLogin = (EditText) findViewById(R.id.inputLogin);
        inputPassword = (EditText) findViewById(R.id.inputPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        // Session manager
        session = new SessionManager(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to type credit activity
            Intent intent = new Intent(LoginActivity.this, TypeCreditActivity.class);
            startActivity(intent);
            finish();
        }

        // Login button Click Event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                String login = inputLogin.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                // Check for empty data in the form
                if (!login.isEmpty() && !password.isEmpty()) {
                    // login user
                    checkLogin(login, password);
                    //checkLogin("reda.brusse@hotmail.fr", "x5s6rgp6");
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                }
            }

        });


    }

    /**
     * function to verify login details from web service
     * */
    private void checkLogin(final String login, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        pDialog.setMessage("Connéxion ...");
        DialogManager.showDialog(pDialog);

        String URL_LOGIN = AppConfig.URL_LOGIN + "/" + login + "/mdp/" + password;
        StringRequest strReq = new StringRequest(Request.Method.GET,
                URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jObj = new JSONObject(response);
                    String error = jObj.getString("error");
                    // Check for error node in json
                    if (!error.equals("User inconnu")) {
                        session.setLogin(true,login,password);
                        JSONObject user = jObj.getJSONObject("user");
                        JSONObject user0 = user.getJSONObject("0");
                        Utils.saveInSharedPrefs(getApplicationContext(), "RciFinanceLogin", "USER_ID", user0.getString("UserId"));
                        Utils.saveInSharedPrefs(getApplicationContext(), "RciFinanceLogin", "USER_NOM", user0.getString("UserNom"));
                        Utils.saveInSharedPrefs(getApplicationContext(), "RciFinanceLogin", "USER_PRENOM", user0.getString("UserPrenom"));
                        Log.d("user", user.toString());
                        Intent intent = new Intent(LoginActivity.this, TypeCreditActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Error in login. Get the error message
                        Toast.makeText(getApplicationContext(),
                                "Invalid login or password", Toast.LENGTH_LONG).show();
                        DialogManager.hideDialog(pDialog);
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    DialogManager.hideDialog(pDialog);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                DialogManager.hideDialog(pDialog);
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


}
