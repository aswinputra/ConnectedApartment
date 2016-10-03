package com.sepgroup4.connectedapartment.Model;

/**
 * Created by kiman on 29/09/2016.
 */
public class RequestResponse {
    private Boolean IsSuccess;
    private String Message;

    public RequestResponse(Boolean isSuccess, String message) {

        IsSuccess = isSuccess;
        Message = message;
    }

    public Boolean getSuccess() {
        return IsSuccess;
    }

    public String getMessage() {
        return Message;
    }

}
