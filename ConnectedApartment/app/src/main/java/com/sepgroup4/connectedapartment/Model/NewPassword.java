package com.sepgroup4.connectedapartment.Model;

/**
 * Created by aswinhartono on 26/09/2016.
 */
public class NewPassword {

    private String NewPassword;
    private String ConfirmPassword;

    public NewPassword(String newPassword, String confirmPassword) {
        this.NewPassword = newPassword;
        this.ConfirmPassword = confirmPassword;
    }

    public boolean isMatched(){
        return NewPassword.equals(ConfirmPassword);
    }
}
