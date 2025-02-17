package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.controllers.seleniumpages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import static com.udacity.jwdnd.course1.cloudstorage.utils.Utils.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteFlowTest {

    @LocalServerPort
    private int port;
    @Autowired
    private NoteService noteService;
    private static WebDriver driver;
    private String baseURL;
    boolean signedUp = false;

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
    public void beforeEach() throws InterruptedException {
        baseURL = "http://localhost:" + port;
        if (!signedUp){
            userSignUp(driver, baseURL,"John", "Doe","JohnDoe","123456789");
            signedUp = true;
        }
        userLogin(driver, baseURL, "JohnDoe", "123456789");
        HomePage homePage = new HomePage(driver);
        homePage.createNewNote("testNote0","Note Description");
        driver.get(baseURL + "/home");
        homePage.openNotesTab();
    }
    @AfterEach
    public void afterEach(){
        noteService.deleteAllNotesForUserByUsername("JohnDoe");
        logout(driver);
    }
    @Test
    public void TestAddNote() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        WebElement actualNote = homePage.searchNotesByTitle("testNote0");
        WebElement actualDescription = homePage.searchNotesByDescription("Note Description");
        assertNotNull(actualNote);
        assertNotNull(actualDescription);

    }
    @Test
    public void testEditNote() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.editNote("edited note title", "edited note description");
        driver.get(baseURL + "/home");
        homePage.openNotesTab();
        WebElement actualNoteTitle = homePage.searchNotesByTitle("edited note title");
        WebElement actualDescription = homePage.searchNotesByDescription("edited note description");
        assertEquals("edited note title", actualNoteTitle.getText());
        assertEquals("edited note description", actualDescription.getText());
    }
    @Test void testDeleteNote() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.deleteNote();
        driver.get(baseURL + "/home");
        homePage.openNotesTab();
        WebElement actualNote = homePage.searchNotesByTitle("testNote0");
        WebElement actualDescription = homePage.searchNotesByDescription("Note Description");
        assertNull(actualNote);
        assertNull(actualDescription);
    }
}
