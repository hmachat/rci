package com.orcaformation.calculetterci.utils;

import android.content.Context;
        import android.content.SharedPreferences;
        import android.content.SharedPreferences.Editor;
        import android.util.Log;

public class SessionManager{
    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "RciFinanceLogin";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn,String login, String password) {

        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.putString(LOGIN, login);
        editor.putString(PASSWORD, password);

        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public String getLogin(){
        return pref.getString(LOGIN,"login");
    }

    public String getPassword(){
        return pref.getString(PASSWORD,"password");
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }
}

