package com.sepgroup4.connectedapartment.Controllers;

import android.accounts.NetworkErrorException;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.sepgroup4.connectedapartment.Model.LoginSession;
import com.sepgroup4.connectedapartment.Model.RequestResponse;
import com.sepgroup4.connectedapartment.Model.TenantDetailRequest;
import com.sepgroup4.connectedapartment.Model.TenantInfoResponse;
import com.sepgroup4.connectedapartment.R;
import com.sepgroup4.connectedapartment.Rest.Handlers.RestResponseHandler;
import com.sepgroup4.connectedapartment.Rest.RestClientManager;
import com.sepgroup4.connectedapartment.Utilities;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by aswinhartono on 11/10/2016.
 */
public class MyProfileActivity extends AppCompatActivity implements View.OnClickListener,RestResponseHandler{

    private EditText mFirstName;
    private EditText mLastName;
    private EditText mDoB;
    private EditText mContact;
    private Button mUpdateProfile;
    private Button mChangePassword;
    private TenantDetailRequest mTenantDetailRequest;
    private DatePickerDialog mPickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_profile_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFirstName = (EditText)findViewById(R.id.activity_my_profile_tenant_first_name_edittext);
        mLastName = (EditText)findViewById(R.id.activity_my_profile_tenant_last_name_edittext);
        mDoB = (EditText)findViewById(R.id.activity_my_profile_DOB_edittext);
        mDoB.setInputType(0x00000000);
        mContact = (EditText)findViewById(R.id.activity_my_profile_contact_number_edittext);
        mUpdateProfile = (Button)findViewById(R.id.update_profile_button);
        mChangePassword = (Button)findViewById(R.id.change_password_button);
        setUpCalendarPrompt();
        populatePersonInfo();

        mUpdateProfile.setOnClickListener(this);
        mChangePassword.setOnClickListener(this);
        mDoB.setOnClickListener(this);
    }

    private void populatePersonInfo() {
        try {
            RestClientManager.getInstance(this).getPersonController().getTenantInformation(this);
        } catch (NetworkErrorException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update_profile_button: {
                getInput();
                try {
                    RestClientManager.getInstance(this).getPersonController().updateTenantInformation(mTenantDetailRequest, this);
                } catch (NetworkErrorException e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.change_password_button:{
                startActivity(MyActivityManager.intentToChangePaswordActivity(this));
                break;
            }
            case R.id.activity_my_profile_DOB_edittext:{
                mPickerDialog.show();
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            if(LoginSession.bm){
                startActivity(MyActivityManager.intentToBMDashBoard(this));
            }else{
                startActivity(MyActivityManager.intentToTenantDashBoard(this));
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpCalendarPrompt(){
        Calendar calendar = Calendar.getInstance();
        int startYear = calendar.get(Calendar.YEAR);
        int starthMonth = calendar.get(Calendar.MONTH);
        int startDate = calendar.get(Calendar.DAY_OF_MONTH);
        mPickerDialog = new DatePickerDialog(this, R.style.DatePickerTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = Utilities.getFormattedDate(dayOfMonth, month + 1, year);
                mDoB.setText(date);
            }
        }, startYear, starthMonth, startDate);
    }

    private void getInput() {
        String firstName = mFirstName.getText().toString();
        String lastName = mLastName.getText().toString();
        String dob = mDoB.getText().toString();
        String contactNumber = mContact.getText().toString();
        mTenantDetailRequest = new TenantDetailRequest(firstName, lastName, dob, contactNumber);
    }

    @Override
    public void onResponseSuccess(RequestResponse requestResponse) {
        if(requestResponse instanceof TenantInfoResponse){
            putDetail((TenantInfoResponse)requestResponse);
        }
        else if(requestResponse != null) {
            Utilities.displayToast(this ,"Your details have been updated successfully");
        }

    }

    private void putDetail(TenantInfoResponse requestResponse) {
        mFirstName.setText(requestResponse.getResult().getFirstName());
        mLastName.setText(requestResponse.getResult().getLastName());
        mDoB.setText(requestResponse.getResult().getDoB());
        mContact.setText(requestResponse.getResult().getPhone());
    }

    @Override
    public void onResponseFailure(String errorMessage) {
        Utilities.displayToast(this, errorMessage);
    }
}
