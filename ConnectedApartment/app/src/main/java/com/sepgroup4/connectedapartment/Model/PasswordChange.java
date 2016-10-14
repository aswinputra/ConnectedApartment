package com.sepgroup4.connectedapartment.Model;

/**
 * Created by kiman on 10/10/2016.
 */

public class PasswordChange {

    private String NewPassword;
    private String ConfirmPassword;
    private String OldPassword;

    public PasswordChange(String newPassword, String confirmPassword, String oldPassword) {
        NewPassword = newPassword;
        ConfirmPassword = confirmPassword;
        OldPassword = oldPassword;
    }

    public String getNewPassword() {
        return NewPassword;
    }

    public void setNewPassword(String newPassword) {
        NewPassword = newPassword;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }

    public String getOldPassword() {
        return OldPassword;
    }

    public void setOldPassword(String oldPassword) {
        OldPassword = oldPassword;
    }
}
