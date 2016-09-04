package com.sepgroup4.connectedapartment;

import android.content.Intent;
import android.os.Bundle;
import android.os.IInterface;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

public class BMDashboardActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout mCreateAccountView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmdashboard);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_bm_dashboard_toolbar);
        setSupportActionBar(toolbar);

        mCreateAccountView = (LinearLayout) findViewById(R.id.activity_bm_dashboard_create_account_linearlayout);
        mCreateAccountView.setOnClickListener(this);
    }

    private void createTenantAccount(){
        Intent intent = new Intent(this, RegisterFormActivity.class);
        startActivity(intent);
    }

    private void resetTenantPassword(){

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_bm_dashboard_create_account_linearlayout: {
                Intent intent = new Intent(BMDashboardActivity.this, RegisterFormActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
