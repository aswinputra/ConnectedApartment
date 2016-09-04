package com.sepgroup4.connectedapartment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class TenantDashboardActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_dashboard);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_tenant_dashboard_toolbar);
        setSupportActionBar(toolbar);

    }

    private void bookFacility(){

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }
}
