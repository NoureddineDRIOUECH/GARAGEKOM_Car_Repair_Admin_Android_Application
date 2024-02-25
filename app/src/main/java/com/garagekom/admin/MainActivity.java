package com.garagekom.admin;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


public class MainActivity extends AppCompatActivity {

    WebView mysite;
    SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(getColor(R.color.dark));
        mysite = (WebView) findViewById(R.id.mySite);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mysite.reload();
                Toast t = Toast.makeText(getApplicationContext(), "Refreshing..", Toast.LENGTH_LONG);
                t.show();
                swipe.setRefreshing(false);
            }
        });
        //load url
        mysite.loadUrl("http://garagekom.liveblog365.com/admin/");
        mysite.setWebViewClient(new WebViewClient());
        WebSettings mySett = mysite.getSettings();
        mySett.setJavaScriptEnabled(true);
        mySett.setDomStorageEnabled(true);
        mySett.setUseWideViewPort(true);


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mysite.canGoBack()) {
            mysite.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}