package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.controllers.seleniumpages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import static com.udacity.jwdnd.course1.cloudstorage.utils.Utils.*;

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
        userSignUp(driver, baseURL,"John", "Doe","JohnDoe","123456789");
        userLogin(driver, baseURL, "JohnDoe", "123456789");

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


}
