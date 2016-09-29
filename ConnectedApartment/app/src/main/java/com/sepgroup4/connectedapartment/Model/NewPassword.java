package com.sepgroup4.connectedapartment.Model;

/**
 * Created by aswinhartono on 26/09/2016.
 */
public class NewPassword {

    private String newPassword;
    private String confirmPassword;

    public NewPassword(String newPassword, String confirmPassword) {
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public boolean isMatched(){
        return newPassword.equals(confirmPassword);
    }
}
