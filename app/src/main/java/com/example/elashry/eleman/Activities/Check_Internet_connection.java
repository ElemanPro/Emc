package com.example.elashry.eleman.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.elashry.eleman.R;

public class Check_Internet_connection extends AppCompatActivity {

    private ImageButton refresh_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check__internet_connection);
        init_View();
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
            Toast.makeText(this, "تحقق من الاتصال بالانترنت", Toast.LENGTH_SHORT).show();
        }
        else {
            startActivity(new Intent(Check_Internet_connection.this,ItemCategory.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Check_Internet_connection.this,Category.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}
