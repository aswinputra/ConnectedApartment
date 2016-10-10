package com.sepgroup4.connectedapartment.Model;

/**
 * Created by kiman on 10/10/2016.
 */

public class ResetPasswordResponse extends RequestResponse{

    private Boolean Result;

    public ResetPasswordResponse(Boolean isSuccess, String message, Boolean result) {
        super(isSuccess, message);
        Result = result;
    }

    public Boolean getResult() {
        return Result;
    }
}
