package com.sepgroup4.connectedapartment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sepgroup4.connectedapartment.R;

import java.util.Random;

public class CreateAccountResult extends AppCompatActivity {

    private TextView mTenantIdTv;
    private TextView mTempPasswordTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_result);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_create_account_result_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTenantIdTv = (TextView) findViewById(R.id.activity_create_account_result_tenant_id_textview);
        mTempPasswordTv = (TextView) findViewById(R.id.activity_create_account_result_password_textview);

        Intent intent = getIntent();
        String s = intent.getExtras().getString(Constants.TENANT_ID);
        Log.i("TAG", intent.getStringExtra(Constants.TENANT_ID));
        mTenantIdTv.append(intent.getStringExtra(Constants.TENANT_ID));
        mTempPasswordTv.append(getGeneratedPassword());
    }

    private String getGeneratedPassword(){
        Random passRandom = new Random();
        switch (passRandom.nextInt(3)){
            case 0: return "newPassword";
            case 1: return "anotherPassword";
            default: return "otherPassword";
        }
    }

}
