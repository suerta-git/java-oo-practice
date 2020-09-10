package com.twu;

public class AdminUser extends User {
    private final String password;

    public AdminUser(String userName, String password) {
        super(userName);
        this.password = password;
    }

    public boolean verify(String password) {
        return this.password.equals(password);
    }
}
