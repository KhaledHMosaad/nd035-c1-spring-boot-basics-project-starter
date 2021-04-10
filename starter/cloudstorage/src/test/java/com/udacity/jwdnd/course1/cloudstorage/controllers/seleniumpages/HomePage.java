package com.udacity.jwdnd.course1.cloudstorage.controllers.seleniumpages;


import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {
    @FindBy(id = "logoutButton")
    private WebElement logoutButton;

    @FindBy(id = "userTable")
    private WebElement notesTable;

    @FindBy(id = "fileTable")
    private WebElement filesTable;

    @FindBy(id = "credentialTable")
    private WebElement credentialTable;

    public WebElement getLogoutButton() {
        return logoutButton;
    }

    public HomePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
    public void logout(){
        this.logoutButton.click();
    }
}