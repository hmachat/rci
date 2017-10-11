package com.orcaformation.calculetterci.utils;

import android.app.ProgressDialog;

/**
 * Created by PC_MA22 on 04/10/2017.
 */

public class DialogManager {

    public static void showDialog(ProgressDialog pDialog) {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    public static void hideDialog(ProgressDialog pDialog) {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
