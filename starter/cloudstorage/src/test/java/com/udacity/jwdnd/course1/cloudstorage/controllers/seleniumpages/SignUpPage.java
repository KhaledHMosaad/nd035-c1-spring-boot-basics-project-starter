package com.udacity.jwdnd.course1.cloudstorage.controllers.seleniumpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {
    @FindBy(id = "successfulSignUpMessage")
    private WebElement successfulSignupMessage;

    @FindBy(id = "inputFirstName")
    private WebElement firstNameField;

    @FindBy(id = "inputLastName")
    private WebElement lastNameField;

    @FindBy(id = "inputUsername")
    private WebElement usernameField;

    @FindBy(id = "inputPassword")
    private WebElement passwordField;

    @FindBy(id = "signup-submit")
    private WebElement submitButton;

    public WebElement getSuccessfulSignupMessage() {
        return successfulSignupMessage;
    }

    public SignUpPage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
    }

    public void signup(String firstname, String lastname, String username, String password){
        this.firstNameField.sendKeys(firstname);
        this.lastNameField.sendKeys(lastname);
        this.usernameField.sendKeys(username);
        this.passwordField.sendKeys(password);
        this.submitButton.click();
    }
}
