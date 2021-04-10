package com.udacity.jwdnd.course1.cloudstorage.models;

import com.udacity.jwdnd.course1.cloudstorage.forms.UserSignUpForm;

public class User {

    private Integer userId;
    private String username;
    private String salt;
    private String password;
    private String firstname;
    private String lastname;

    public User(Integer userId, String username, String salt, String password, String firstname, String lastname) {
        this.userId = userId;
        this.username = username;
        this.salt = salt;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public User(Integer userId, String salt, String password, UserSignUpForm userSignUpForm) {
        this.userId = userId;
        this.username = userSignUpForm.getUsername();
        this.salt = salt;
        this.password = password;
        this.firstname = userSignUpForm.getFirstName();
        this.lastname = userSignUpForm.getLastName();
    }

    // GETTERS & SETTERS
    public int getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getSalt() {
        return salt;
    }
    public void setSalt(String salt) {
        this.salt = salt;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastName() {
        return lastname;
    }
    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

}
