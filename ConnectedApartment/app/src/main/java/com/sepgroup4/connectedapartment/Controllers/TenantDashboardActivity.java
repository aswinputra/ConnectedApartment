package com.sepgroup4.connectedapartment.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.sepgroup4.connectedapartment.R;

public class TenantDashboardActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout mProfile;
    private LinearLayout mBookActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_dashboard);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_tenant_dashboard_toolbar);
        setSupportActionBar(toolbar);

        mProfile = (LinearLayout) findViewById(R.id.activity_tenant_dashboard_my_profile_linearlayout);
        mBookActivity = (LinearLayout) findViewById(R.id.activity_tenant_dashboard_book_facility_linearlayout);
        mProfile.setOnClickListener(this);
        mBookActivity.setOnClickListener(this);
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


    private void bookFacility(){
        Intent intent = new Intent(TenantDashboardActivity.this, BookFacilityActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_tenant_dashboard_my_profile_linearlayout:
                myProfile();
                break;
            case R.id.activity_tenant_dashboard_book_facility_linearlayout:
                bookFacility();
                break;

        }
    }

    private void myProfile() {
        Intent intent= new Intent(TenantDashboardActivity.this, MyProfileActivity.class);
        startActivity(intent);
    }
}
