package com.example.yscontact.model;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private int id=0;
    private String account;
    private String password;
    private String telephone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + account + '\'' +
                ", userPassword='" + password + '\'' +
                ", userPhone='" + telephone + '\'' +
                '}';
    }
}
