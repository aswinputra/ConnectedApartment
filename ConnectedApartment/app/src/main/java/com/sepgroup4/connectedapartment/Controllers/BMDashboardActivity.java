package com.sepgroup4.connectedapartment.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.sepgroup4.connectedapartment.Model.LoginSession;
import com.sepgroup4.connectedapartment.R;

public class BMDashboardActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout mCreateAccountView;
    private LinearLayout mViewBooking;
    private LinearLayout mMakeBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmdashboard);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_bm_dashboard_toolbar);
        setSupportActionBar(toolbar);

        mMakeBooking = (LinearLayout) findViewById(R.id.activity_bm_dashboard_make_booking_linearlayout);
        mViewBooking = (LinearLayout) findViewById(R.id.activity_bm_dashboard_view_booking_linearlayout);
        mCreateAccountView = (LinearLayout) findViewById(R.id.activity_bm_dashboard_create_account_linearlayout);
        mCreateAccountView.setOnClickListener(this);
        mViewBooking.setOnClickListener(this);
        mMakeBooking.setOnClickListener(this);
        LoginSession.bm = true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            startActivity(MyActivityManager.logout(this));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void createTenantAccount(){
        Intent intent = new Intent(this, RegisterFormActivity.class);
        startActivity(intent);
    }

    private void makeBooking(){
        Intent intent = new Intent(this, BookFacilityActivity.class);
        startActivity(intent);
    }

    private void viewBooking(){
        Intent intent = new Intent(this, ViewBookingActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_bm_dashboard_create_account_linearlayout: {
                createTenantAccount();
                break;
            }
            case R.id.activity_bm_dashboard_make_booking_linearlayout: {
                makeBooking();
                break;
            }
            case R.id.activity_bm_dashboard_view_booking_linearlayout: {
                viewBooking();
                break;
            }
        }
    }
}
