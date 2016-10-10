package com.sepgroup4.connectedapartment.Model;

/**
 * Created by kiman on 4/10/2016.
 */
public class RegisterResponse extends RequestResponse {

    public RegisterRequest Result;

    public RegisterResponse(Boolean isSuccess, String message, RegisterRequest result) {
        super(isSuccess, message);
        Result = result;
    }

    public RegisterRequest getResult() {
        return Result;
    }

    public void setResult(RegisterRequest result) {
        Result = result;
    }
}
