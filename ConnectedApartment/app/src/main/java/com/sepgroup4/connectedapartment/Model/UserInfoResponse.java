package com.sepgroup4.connectedapartment.Model;

/**
 * Created by kiman on 4/10/2016.
 */
public class UserInfoResponse extends RequestResponse{

    private UserInfo Result;

    public UserInfoResponse(Boolean isSuccess, String message, UserInfo result) {
        super(isSuccess, message);
        Result = result;
    }

    public UserInfo getUserInfo() {
        return Result;
    }

    public void setResult(UserInfo result) {
        Result = result;
    }
}
