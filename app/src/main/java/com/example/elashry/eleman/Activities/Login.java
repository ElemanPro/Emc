package com.example.elashry.eleman.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.elashry.eleman.R;

public class Login extends AppCompatActivity {

    private EditText login_managerName,login_managerPass;
    private CheckBox login_managerCheckBox;
    private Button   login_Btn;
    private SharedPreferences spref,login_spref;
    private AlertDialog.Builder mBuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //initilize view
        init_View();


        login_spref = getSharedPreferences("loginspref",MODE_PRIVATE);
        boolean save_data = login_spref.getBoolean("save_mnger",false);
        if (save_data==true)
        {
            Intent intent = new Intent(Login.this, Manager.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);


        }

        spref = getSharedPreferences("SaveManagerData",MODE_PRIVATE);
        boolean saved =spref.getBoolean("saved",false);

        if (saved)
        {
            String mngr_name  = spref.getString("manager_name","");
            String mnger_pass = spref.getString("manager_pass","");
          //  String mngr_id    = spref.getString("manager_id","");

            login_managerName.setText(mngr_name);
            login_managerPass.setText(mnger_pass);
          // login_managerId.setText(mngr_id);
            login_managerCheckBox.setChecked(true);
        }
        else
            {
                login_managerName.setText(null);
                login_managerPass.setText(null);
              //  login_managerId.setText(null);
            }


        login_managerCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (login_managerCheckBox.isChecked())
                {
                    String mngr_name  =login_managerName.getText().toString();
                    String mnger_pass =login_managerPass.getText().toString();
                  //  String mngr_id    =login_managerId.getText().toString();

                    spref                           = getSharedPreferences("SaveManagerData",MODE_PRIVATE);
                    SharedPreferences.Editor editor = spref.edit();
                    editor.putBoolean("saved",true);
                    editor.putString("manager_name",mngr_name);
                    editor.putString("manager_pass",mnger_pass);
                  //  editor.putString("manager_id",mngr_id);
                    editor.commit();
                    editor.apply();


                }
                else
                {
                    spref                           = getSharedPreferences("SaveManagerData",MODE_PRIVATE);
                    SharedPreferences.Editor editor = spref.edit();
                    editor.clear();
                    editor.commit();
                    editor.apply();
                }

            }
        });
        login_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mngr_name  =login_managerName.getText().toString().toLowerCase();
                String mnger_pass =login_managerPass.getText().toString();
           //     String mngr_id    =login_managerId.getText().toString();

                if (TextUtils.isEmpty(mngr_name))
                {
                    create_AlertDialog(getString(R.string.managerName_txt));
                }
                else if (TextUtils.isEmpty(mnger_pass))
                {
                    create_AlertDialog(getString(R.string.pass_empty));
                }
              /*  else if (TextUtils.isEmpty(mngr_id))
                {
                    create_AlertDialog(getString(R.string.id_empty));
                }*/
                else if (!mngr_name.equals("محمد الشاذلي"))
                    {
                        create_AlertDialog(getString(R.string.check_manager_name));
                    }
                    else if (!mnger_pass.equals("2233"))
                    {
                        create_AlertDialog(getString(R.string.check_manager_pass));
                    }
                    /*else if (!mngr_id.equals("1"))
                    {
                        create_AlertDialog(getString(R.string.check_manager_id));
                    }*/
                    else
                        {
                            createShared("loginspref",mngr_name,mnger_pass);
                            startActivity(new Intent(Login.this, Manager.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                            login_managerName.setText(null);
                            login_managerPass.setText(null);
                         //   login_managerId.setText(null);
                        }
            }
        });
    }

    private void init_View() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        login_managerName     = (EditText) findViewById(R.id.login_managerName);
        login_managerPass     = (EditText) findViewById(R.id.login_managerPass);
        //login_managerId       = (EditText) findViewById(R.id.login_managerId);
        login_managerCheckBox = (CheckBox) findViewById(R.id.login_managerCheckBox);
        login_Btn             = (Button) findViewById(R.id.login_Btn);

        //////////////////////////////////////////////////////
        mBuilder = new AlertDialog.Builder(this);

    }

    private void create_AlertDialog(String msg)
    {
        final AlertDialog mDialog = mBuilder.create();
        mBuilder.setMessage(msg);
        mBuilder.setCancelable(false);
        mBuilder.setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mDialog.dismiss();
            }
        });
        mBuilder.show();
    }

    private void createShared(String shared_name,String mngr_name,String mngr_pass)
    {
        SharedPreferences spref = getSharedPreferences(shared_name,MODE_PRIVATE);
        SharedPreferences.Editor editor = spref.edit();
        editor.putBoolean("save_mnger",true);
        editor.putString("mngr_name",mngr_name);
        editor.putString("mngr_pass",mngr_pass);
      //  editor.putString("mngr_id",mngr_id);
        editor.apply();
        editor.commit();
    }


}
