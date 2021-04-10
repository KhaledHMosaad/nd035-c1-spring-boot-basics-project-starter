package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.controllers.seleniumpages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.controllers.seleniumpages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.controllers.seleniumpages.SignUpPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomePageTest {

    @LocalServerPort
    private int port;
    private static WebDriver driver;
    private String baseURL;

    @BeforeAll
    public static void beforeAll(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @AfterAll
    public static void afterAll(){
        driver.quit();
        driver = null;
    }
    @BeforeEach
    public void beforeEach(){
        baseURL = "http://localhost:" + port;
    }
    @Test
    public void testHomePageNotAccessibleWithoutLoggingIn(){
        driver.get(baseURL + "/home");
        assertTrue(!driver.getCurrentUrl().contains("/home"));
        assertTrue(driver.getCurrentUrl().contains("/login"));
    }

    @Test
    public void TestSignUpLoginLogout(){
        userSignUp();
        userLogin();

        HomePage homePage = new HomePage(driver);
        assertEquals(baseURL + "/home", driver.getCurrentUrl());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        homePage.logout();
        assertEquals(baseURL + "/login", driver.getCurrentUrl());
    }

    @Test
    public void testLoginCreateNoteAndCheckIfVisible(){
        userSignUp();
        userLogin();
    }
    public void userLogin(){
        LoginPage loginPage = new LoginPage(driver);
        driver.get(baseURL + "/login");
        loginPage.login("JohnDoe", "123456789");
    }
    public void userSignUp(){
        String firstName = "John";
        String lastName = "Doe";
        String username = "JohnDoe";
        String password = "123456789";

        SignUpPage signUpPage = new SignUpPage(driver);
        driver.get(baseURL + "/signup");
        signUpPage.signup(firstName, lastName, username, password);
    }
}
