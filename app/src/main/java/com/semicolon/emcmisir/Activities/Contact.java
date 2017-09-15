package com.semicolon.emcmisir.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.semicolon.emcmisir.R;

public class Contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
    }
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Contact.this,Category.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}
