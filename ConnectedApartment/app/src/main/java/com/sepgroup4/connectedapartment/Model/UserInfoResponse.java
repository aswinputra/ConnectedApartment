package com.sepgroup4.connectedapartment.Model;

/**
 * Created by kiman on 4/10/2016.
 */
public class UserInfoResponse extends RequestResponse{

    private UserInfo userInfo;

    public UserInfoResponse(Boolean isSuccess, String message, UserInfo userInfo) {
        super(isSuccess, message);
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
