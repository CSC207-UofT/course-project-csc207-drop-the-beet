package com.ui.planner;

public class LoginInfo {
    private final String userName;
    private final String passWord;

    public LoginInfo(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }
}