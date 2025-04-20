package ru.omsu.core.model;

import java.util.UUID;

public class User {
    private  UUID userID;
    private  String username;
    private  String password;

    public User() {
    }

    public User(UUID userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    public UUID getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
