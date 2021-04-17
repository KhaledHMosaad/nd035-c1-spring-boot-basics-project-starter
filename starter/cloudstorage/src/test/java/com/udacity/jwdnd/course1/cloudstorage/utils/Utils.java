package com.udacity.jwdnd.course1.cloudstorage.utils;

import com.udacity.jwdnd.course1.cloudstorage.controllers.seleniumpages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.controllers.seleniumpages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.controllers.seleniumpages.SignUpPage;
import org.openqa.selenium.WebDriver;

public class Utils {
    public static void userLogin(WebDriver driver, String baseURL, String username, String password){
        LoginPage loginPage = new LoginPage(driver);
        driver.get(baseURL + "/login");
        loginPage.login(username, password);
    }
    public static void userSignUp(WebDriver driver, String baseURL, String firstName, String lastName, String username, String password){
        SignUpPage signUpPage = new SignUpPage(driver);
        driver.get(baseURL + "/signup");
        signUpPage.signup(firstName, lastName, username, password);
    }
    public static void logout(WebDriver driver){
        HomePage homePage = new HomePage(driver);
        homePage.logout();
    }
}
