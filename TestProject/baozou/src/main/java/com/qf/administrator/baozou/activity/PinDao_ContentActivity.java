package com.qf.administrator.baozou.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.qf.administrator.baozou.R;

public class PinDao_ContentActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_dao__content);

        webView = (WebView) findViewById(R.id.pindao_webView);

//        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);

//        webView.setWebChromeClient(new WebChromeClient());

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        Log.i("info",url);
        webView.loadUrl(url);

    }

    public void back(View view) {
    }
}
