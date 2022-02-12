package com.example.login.models;

import java.util.Date;

public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private boolean is_barber;
    private String passwordResetToken;
    private Date passwordResetExpires;

    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIs_barber() {
        return is_barber;
    }

    public void setIs_barber(boolean is_barber) {
        this.is_barber = is_barber;
    }

    public String getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(String passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    public Date getPasswordResetExpires() {
        return passwordResetExpires;
    }

    public void setPasswordResetExpires(Date passwordResetExpires) {
        this.passwordResetExpires = passwordResetExpires;
    }

    public String getToken() {
        return "Bearer " + token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
