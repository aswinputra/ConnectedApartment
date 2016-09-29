package com.sepgroup4.connectedapartment.Rest.Handlers;

import com.sepgroup4.connectedapartment.Model.LoginResponse;

/**
 * Created by kiman on 29/09/2016.
 */

public interface AuthenticationHandler {
    void onAuthenticationSuccess(LoginResponse loginResponse);
    void onAuthenticationFailure(String errorMessage);
}
