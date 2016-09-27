package com.sepgroup4.connectedapartment.Model;

/**
 * Created by aswinhartono on 26/09/2016.
 */
public class RegisterRequest {
    private String grant_type;
    private String username;

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;


}
