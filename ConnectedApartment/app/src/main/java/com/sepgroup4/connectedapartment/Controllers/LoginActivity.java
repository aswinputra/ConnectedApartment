package com.sepgroup4.connectedapartment.Controllers;

import android.accounts.NetworkErrorException;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sepgroup4.connectedapartment.Model.Constants;
import com.sepgroup4.connectedapartment.Model.LoginRequest;
import com.sepgroup4.connectedapartment.Model.LoginResponse;
import com.sepgroup4.connectedapartment.Model.LoginSession;
import com.sepgroup4.connectedapartment.Model.RequestResponse;
import com.sepgroup4.connectedapartment.Model.ResetPasswordResponse;
import com.sepgroup4.connectedapartment.Model.UserInfoResponse;
import com.sepgroup4.connectedapartment.R;
import com.sepgroup4.connectedapartment.Rest.Handlers.AuthenticationHandler;
import com.sepgroup4.connectedapartment.Rest.Handlers.RestResponseHandler;
import com.sepgroup4.connectedapartment.Rest.RestClientManager;
import com.sepgroup4.connectedapartment.Utilities;

import java.text.ParseException;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements AuthenticationHandler, RestResponseHandler {

    // UI references.
    private EditText mUsernameEt;
    private EditText mPasswordEt;
    private ProgressBar mProgressBar;
    private TextView mForgotPasswordTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsernameEt = (EditText) findViewById(R.id.activity_login_username);
        mPasswordEt = (EditText) findViewById(R.id.activity_login_password);
        mProgressBar = (ProgressBar) findViewById(R.id.activity_login_progress_bar);
        mForgotPasswordTv = (TextView) findViewById(R.id.activity_login_forgot_password);

        mUsernameEt.setText("aswinptr95@gmail.com");
        mPasswordEt.setText("Aa123!@#");
        Button signInButton = (Button) findViewById(R.id.activity_login_email_sign_in_button);
        signInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                login(mUsernameEt.getText().toString(), mPasswordEt.getText().toString());
            }
        });
        mForgotPasswordTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    showResetPasswordConfirmationDialog();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
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
        LoginSession.userToken = "bearer " + loginResponse.getAccessToken();
        Log.i(Constants.LOG_TAG, LoginSession.userToken);
        sendCheckRoleRequest();
    }

    @Override
    public void onAuthenticationFailure(String errorMessage) {
        mProgressBar.setVisibility(View.GONE);
        Utilities.displayToast(getApplicationContext(), errorMessage);
    }

    private void sendCheckRoleRequest() {
        mProgressBar.setVisibility(View.VISIBLE);
        try {
            RestClientManager.getInstance(LoginActivity.this).getPersonController().getUserInfo(this);
        } catch (NetworkErrorException e) {
            Utilities.displayToast(getApplicationContext(), e.getMessage());
        }
    }

    @Override
    public void onResponseSuccess(RequestResponse requestResponse) {
        mProgressBar.setVisibility(View.GONE);
        if(requestResponse instanceof UserInfoResponse){
            checkRole((UserInfoResponse) requestResponse);
        }else if(requestResponse instanceof ResetPasswordResponse){
            displayResetPasswordSuccess((ResetPasswordResponse) requestResponse);
        }
    }

    private void checkRole(UserInfoResponse userInfoResponse){
        String role = userInfoResponse.getUserInfo().getRole();

        if (role.equals("BuildingManager")) {
            startActivity(MyActivityManager.intentToBMDashBoard(this));
        } else {
            startActivity(MyActivityManager.intentToTenantDashBoard(this));
        }
    }

    private void displayResetPasswordSuccess(ResetPasswordResponse resetPasswordResponse){
        if(resetPasswordResponse.getSuccess()){
            Utilities.displayToast(this, "Please check your email for the new password");
        }
    }

    @Override
    public void onResponseFailure(String errorMessage) {
        mProgressBar.setVisibility(View.GONE);
        Utilities.displayToast(getApplicationContext(), errorMessage);
    }

    private void showResetPasswordConfirmationDialog() throws ParseException {
        String title = "Do you really want to reset your password?";

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DatePickerTheme);
        builder.setTitle(title);

        String positiveText = getString(R.string.yes);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resetPassword();
                    }
                });

        String negativeText = getString(R.string.no);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

    private void resetPassword(){
        String email = mUsernameEt.getText().toString();
        if(email.isEmpty()){
            mUsernameEt.setError("Fill in your email address");
        }else if(email.contains("@")){
            try {
                RestClientManager.getInstance(this).getPersonController().resetPassword(email, this);
            } catch (NetworkErrorException e) {
                e.printStackTrace();
            }
        }else{
            mUsernameEt.setError("Invalid email address");
        }
    }
}

