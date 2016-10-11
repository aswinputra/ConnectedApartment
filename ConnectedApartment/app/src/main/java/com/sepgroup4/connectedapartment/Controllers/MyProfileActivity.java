package com.sepgroup4.connectedapartment.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sepgroup4.connectedapartment.Model.RequestResponse;
import com.sepgroup4.connectedapartment.R;
import com.sepgroup4.connectedapartment.Rest.Handlers.RestResponseHandler;
import com.sepgroup4.connectedapartment.Utilities;

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
    private String firstName;
    private String lastName;
    private String contactNumber;
    private String doB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        mFirstName = (EditText)findViewById(R.id.activity_my_profile_tenant_first_name_edittext);
        mLastName = (EditText)findViewById(R.id.activity_my_profile_tenant_last_name_edittext);
        mDoB = (EditText)findViewById(R.id.activity_my_profile_DOB_edittext);
        mContact = (EditText)findViewById(R.id.activity_my_profile_contact_number_edittext);
        mUpdateProfile = (Button)findViewById(R.id.update_profile_button);
        mChangePassword = (Button)findViewById(R.id.change_password_button);

        mUpdateProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update_profile_button: {
                getInput();
                // Kim an to do the update profile function in person controller
            }
            case R.id.change_password_button:{
                startActivity(MyActivityManager.intentToChangePaswordActivity(this));
            }
        }

    }

    private void getInput() {
        firstName = mFirstName.getText().toString();
        lastName = mLastName.getText().toString();
        doB = mDoB.getText().toString();
        contactNumber = mContact.getText().toString();
    }

    @Override
    public void onResponseSuccess(RequestResponse requestResponse) {
        //kim an do the update profile function
    }

    @Override
    public void onResponseFailure(String errorMessage) {
        Utilities.displayToast(this, errorMessage);
    }
}
