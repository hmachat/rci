package com.orcaformation.calculetterci.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.orcaformation.calculetterci.R;
import com.orcaformation.calculetterci.utils.Utils;

public class PdfActivity extends AppCompatActivity {

    WebView webview;
    ProgressBar progressbar;
    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        webview = (WebView)findViewById(R.id.webview);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);

        webview.getSettings().setJavaScriptEnabled(true);

        //String filename ="http://www3.nd.edu/~cpoellab/teaching/cse40816/android_tutorial.pdf";
        String filename =utils.getFromSharedPrefs(getApplicationContext(), "SIMULATION", "SIMULATION_PDF");
        webview.loadUrl("http://docs.google.com/gview?embedded=true&url=" + filename);

        webview.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                progressbar.setVisibility(View.GONE);
            }
        });
    }
}
