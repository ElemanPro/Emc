package com.semicolon.emcmisir.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.semicolon.emcmisir.R;

public class Check_Internet_connection extends AppCompatActivity {

    private ImageButton refresh_btn;
    private static String flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check__internet_connection);
        init_View();

        Intent intent = getIntent();
        if (intent.getExtras()!=null)
        {
            flag = intent.getExtras().getString("flag");

        }
    }

    private void init_View() {
        refresh_btn = (ImageButton) findViewById(R.id.refresh_btn);
        refresh_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Network_aviliable();
            }
        });
    }


    private void Network_aviliable()
    {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        boolean data = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();

        if (!wifi && !data)
        {
            Toast.makeText(this,getString(R.string.internet), Toast.LENGTH_SHORT).show();
        }
        else {
            if (flag.toString().equals("1"))
            {
                startActivity(new Intent(Check_Internet_connection.this,ItemCategory.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

            }
            else if (flag.toString().equals("2"))
            {
                startActivity(new Intent(Check_Internet_connection.this,Manager.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

            }
            else if (flag.toString().equals("3"))
            {
                startActivity(new Intent(Check_Internet_connection.this,Matgar.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

            } else if (flag.toString().equals("4"))
            {
                startActivity(new Intent(Check_Internet_connection.this,ADS.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Check_Internet_connection.this,Category.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}
