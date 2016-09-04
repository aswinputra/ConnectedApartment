package com.sepgroup4.connectedapartment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{

    // UI references.
    private EditText mUsernameEt;
    private EditText mPasswordEt;
    private View mProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsernameEt = (EditText) findViewById(R.id.username);
        mPasswordEt = (EditText) findViewById(R.id.password);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBM(mUsernameEt.getText().toString()) && isPasswordCorrect(mPasswordEt.getText().toString())){
                    Intent intent = new Intent(LoginActivity.this, BMDashboardActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(LoginActivity.this, TenantDashboardActivity.class);
                    startActivity(intent);
                }
            }
        });

        mProgressView = findViewById(R.id.login_progress);
    }

    private Boolean isBM(String username){
        return username.equals("building manager");
    }
    private Boolean isPasswordCorrect(String password){
        return !password.equals("incorrect");
    }
}

