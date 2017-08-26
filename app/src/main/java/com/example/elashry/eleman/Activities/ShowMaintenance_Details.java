package com.example.elashry.eleman.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.elashry.eleman.Adapter.MaintenanceAdapter;
import com.example.elashry.eleman.Model.MaintenanceModel;
import com.example.elashry.eleman.R;

import static com.example.elashry.eleman.Adapter.MaintenanceAdapter.mflag;

public class ShowMaintenance_Details extends AppCompatActivity {

    private TextView client_name,client_phone,client_address,dev_type,dev_brand,dev_warranty_state,dev_damage_type,maintenance_date;
    private TextView nopro_txt;
    private LinearLayout progBar_container,maintenance_data_container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_maintenance__details);
        init_View();
        GetDataFromIntent();
    }
    private void init_View() {
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("تفاصيل الصيانة");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        client_name        = (TextView) findViewById(R.id.maintenance_details_clientname);
        client_phone       = (TextView) findViewById(R.id.maintenance_details__client_phone);
        client_address     = (TextView) findViewById(R.id.maintenance_details_client_address);
        dev_type           = (TextView) findViewById(R.id.maintenance_details_devtype);
        dev_brand          = (TextView) findViewById(R.id.maintenance_details_dev_brand);
        dev_warranty_state = (TextView) findViewById(R.id.maintenance_details_dev_warranty_state);
        dev_damage_type    = (TextView) findViewById(R.id.maintenance_details_damage_type);
        maintenance_date   = (TextView) findViewById(R.id.maintenance_details_date);



        progBar_container    = (LinearLayout)findViewById(R.id.progressBar_container);
        maintenance_data_container = (LinearLayout)findViewById(R.id.maintenance_data_container);
        maintenance_data_container.setVisibility(View.GONE);
        progBar_container.setVisibility(View.VISIBLE);
        nopro_txt            = (TextView)findViewById(R.id.nopro_txt);
        nopro_txt.setVisibility(View.GONE);
    }
    private void GetDataFromIntent() {

        mflag="1";
        Intent intent = getIntent();
        if (intent.getExtras()!=null)
        {

            MaintenanceModel model = (MaintenanceModel) intent.getSerializableExtra("maintenance_data");
            client_name.setText(model.getCname().toString());
            client_phone.setText(model.getCphone().toString());
            client_address.setText(model.getCaddress().toString());
            dev_type.setText(model.getDtype().toString());
            dev_brand.setText(model.getDbrand().toString());
            dev_warranty_state.setText(model.getWstate().toString());
            dev_damage_type.setText(model.getDamagetype().toString());
            maintenance_date.setText(model.getOdate().toString());

            maintenance_data_container.setVisibility(View.VISIBLE);
            progBar_container.setVisibility(View.GONE);

        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
        {
            finish();
            mflag="1";
        }
        return super.onOptionsItemSelected(item);

    }

}
