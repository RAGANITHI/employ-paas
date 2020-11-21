package com.insignia.employ;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {
    WebView webViewform;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Intent intent=getIntent();
        url=intent.getStringExtra("link");




        webViewform=findViewById(R.id.webviewform);
        webViewform.setWebViewClient(new MyBrowser());

        webViewform.getSettings().setLoadsImagesAutomatically(true);
        webViewform.getSettings().setJavaScriptEnabled(true);
        webViewform.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webViewform.loadUrl(url);

    }
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}