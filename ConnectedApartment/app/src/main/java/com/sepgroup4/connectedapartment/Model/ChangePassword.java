package com.sepgroup4.connectedapartment.Model;

/**
 * Created by kiman on 10/10/2016.
 */

public class ChangePassword {

    private String NewPassword;
    private String ConfirmPassword;
    private String OldPassword;

    public ChangePassword(String newPassword, String confirmPassword, String oldPassword) {
        NewPassword = newPassword;
        ConfirmPassword = confirmPassword;
        OldPassword = oldPassword;
    }

    public String getOldPassword() {
        return OldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.OldPassword = oldPassword;
    }
}
