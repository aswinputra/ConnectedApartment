package com.sepgroup4.connectedapartment.Model;

import java.util.List;

/**
 * Created by kiman on 4/10/2016.
 */

public class UserInfo {
    private String Email;
    private Boolean HasRegistered;
    private String LoginProvider;
    private List<String> Roles;

    public UserInfo(String email, Boolean hasRegistered, String loginProvider, List<String> roles) {
        Email = email;
        HasRegistered = hasRegistered;
        LoginProvider = loginProvider;
        Roles = roles;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Boolean getHasRegistered() {
        return HasRegistered;
    }

    public void setHasRegistered(Boolean hasRegistered) {
        HasRegistered = hasRegistered;
    }

    public String getLoginProvider() {
        return LoginProvider;
    }

    public void setLoginProvider(String loginProvider) {
        LoginProvider = loginProvider;
    }

    public List<String> getRoles() {
        return Roles;
    }

    public void setRoles(List<String> roles) {
        Roles = roles;
    }
}
