package com.sepgroup4.connectedapartment.Model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kiman on 4/10/2016.
 */

public class UserInfo {

    @SerializedName("Email")
    @Expose
    public String email;
    @SerializedName("HasRegistered")
    @Expose
    public Boolean hasRegistered;
    @SerializedName("LoginProvider")
    @Expose
    public Object loginProvider;
    @SerializedName("Roles")
    @Expose
    public List<String> roles = new ArrayList<String>();


    public UserInfo(String email, Boolean hasRegistered, Object loginProvider, List<String> roles) {
        this.email = email;
        this.hasRegistered = hasRegistered;
        this.loginProvider = loginProvider;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getHasRegistered() {
        return hasRegistered;
    }

    public void setHasRegistered(Boolean hasRegistered) {
        this.hasRegistered = hasRegistered;
    }

    public Object getLoginProvider() {
        return loginProvider;
    }

    public void setLoginProvider(Object loginProvider) {
        this.loginProvider = loginProvider;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getRole() {
        return roles.get(0);
    }
}
