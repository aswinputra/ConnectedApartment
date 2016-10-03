package com.sepgroup4.connectedapartment.Model;

/**
 * Created by kiman on 4/10/2016.
 */
public class RegisterResponse extends RequestResponse{
    public RegisterResponse(Boolean isSuccess, String message) {
        super(isSuccess, message);
    }
}
