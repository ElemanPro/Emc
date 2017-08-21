package com.example.elashry.eleman.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.elashry.eleman.R;

public class WebViiew extends AppCompatActivity {
    private String postUrl = "https://www.facebook.com/";
    private WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_viiew);

        webView = (WebView) findViewById(R.id.webView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        initCollapsingToolbar();

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(postUrl);
        webView.setHorizontalScrollBarEnabled(false);
    }


    private void initCollapsingToolbar() {

    }
}