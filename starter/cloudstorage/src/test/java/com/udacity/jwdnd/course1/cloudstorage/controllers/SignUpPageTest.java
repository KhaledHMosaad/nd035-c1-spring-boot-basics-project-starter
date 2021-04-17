package com.udacity.jwdnd.course1.cloudstorage.controllers;
import com.udacity.jwdnd.course1.cloudstorage.controllers.seleniumpages.SignUpPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignUpPageTest {

    @LocalServerPort
    public int port;
    public static WebDriver driver;
    public String baseURL;

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
        baseURL ="http://localhost:" + port;
    }
    @Test
    public void testUserSignUp(){
        driver.get(baseURL + "/signup");
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.signup("John", "Doe", "JohnDoe", "123456789");
        WebElement successfulSignupMessage = signUpPage.getSuccessfulSignupMessage();
        assertTrue(successfulSignupMessage.getText().contains("You successfully signed up!"));
    }
}
