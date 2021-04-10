package com.udacity.jwdnd.course1.cloudstorage.models;

public class Credential {
    private Integer credentialId;
    private String URL;
    private String username;
    private String password;
    private int userId;

    //SETTERS & GETTERS.
    public Integer getCredentialId() {
        return credentialId;
    }
    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }
    public String getURL() {
        return URL;
    }
    public void setURL(String URL) {
        this.URL = URL;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
