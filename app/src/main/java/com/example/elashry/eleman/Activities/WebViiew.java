package com.example.elashry.eleman.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.elashry.eleman.R;

public class WebViiew extends AppCompatActivity {
   // private String postUrl = "http://semicolonsoft.com/clients/emc/erpsystem/store";
    private WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_viiew);
        webView = (WebView) findViewById(R.id.webView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        webView.getSettings().setJavaScriptEnabled(true);
        initCollapsingToolbar();


        webView.setHorizontalScrollBarEnabled(false);
        Intent i=getIntent();
        if (i.getExtras()!=null)
        {
            String  postUrl=  i.getStringExtra("link");
            webView.loadUrl(postUrl);
        }

        webView.setWebViewClient(new WebViewClient()
        {
            @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

    }


    private void initCollapsingToolbar() {

    }
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(WebViiew.this,Category.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}