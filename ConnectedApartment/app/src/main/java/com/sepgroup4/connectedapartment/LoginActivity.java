package com.sepgroup4.connectedapartment;

import android.accounts.NetworkErrorException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.sepgroup4.connectedapartment.Model.LoginRequest;
import com.sepgroup4.connectedapartment.Model.LoginResponse;
import com.sepgroup4.connectedapartment.Rest.Controller.PersonController;
import com.sepgroup4.connectedapartment.Rest.Handlers.AuthenticationHandler;
import com.sepgroup4.connectedapartment.Rest.RestClientManager;

import okhttp3.internal.Util;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements AuthenticationHandler {

    // UI references.
    private EditText mUsernameEt;
    private EditText mPasswordEt;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsernameEt = (EditText) findViewById(R.id.username);
        mPasswordEt = (EditText) findViewById(R.id.password);
        mProgressBar = (ProgressBar) findViewById(R.id.login_progress);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (isBM(mUsernameEt.getText().toString()) && isPasswordCorrect(mPasswordEt.getText().toString())) {
//                    Intent intent = new Intent(LoginActivity.this, BMDashboardActivity.class);
//                    startActivity(intent);
//                } else {
//                    Intent intent = new Intent(LoginActivity.this, TenantDashboardActivity.class);
//                    startActivity(intent);
//                }
                login(mUsernameEt.getText().toString(), mPasswordEt.getText().toString());
            }
        });


    }

    private Boolean isBM(String username) {
        return username.equals("building manager");
    }

    private Boolean isPasswordCorrect(String password) {
        return !password.equals("incorrect");
    }

    private void login(String name, String password) {
        LoginRequest loginRequest = new LoginRequest(name, password);
        try {
            RestClientManager.getInstance(LoginActivity.this).getPersonController().authenticate(loginRequest, this);
        } catch (NetworkErrorException e) {
            Utilities.displayToast(getApplicationContext(), e.getMessage());
        }
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAuthenticationSuccess(LoginResponse loginResponse) {
        mProgressBar.setVisibility(View.GONE);
        Utilities.displayToast(getApplicationContext(), loginResponse.getAccessToken());
    }

    @Override
    public void onAuthenticationFailure(String errorMessage) {
        mProgressBar.setVisibility(View.GONE);
        Utilities.displayToast(getApplicationContext(), errorMessage);
    }
}

