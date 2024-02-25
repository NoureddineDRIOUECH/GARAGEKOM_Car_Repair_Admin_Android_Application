package com.garagekom.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class First extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        getWindow().setStatusBarColor(getColor(R.color.dark));
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isWifiConn = false;
        boolean isMobileConn = false;

        for (Network network : connectivityManager.getAllNetworks()) {
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);

            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                isWifiConn |= networkInfo.isConnected();
            }

            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                isMobileConn |= networkInfo.isConnected();
            }
        }

        if (isMobileConn || isWifiConn) {
            Intent openActivity = new Intent(this, MainActivity.class);
            startActivity(openActivity);
            finish();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Check Your Connection and try again", Toast.LENGTH_LONG);
            toast.show();
        }
        ImageView noInternet = (ImageView) findViewById(R.id.noInternet);
        TextView noInternettext = (TextView) findViewById(R.id.noInternetText);
    }


}