package com.sepgroup4.connectedapartment.Model;

/**
 * Created by aswinhartono on 26/09/2016.
 */
public class LoginRequest {
    private static final String grantType = "password";
    private String username;
    private String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getGrantType() {
        return grantType;
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

}
