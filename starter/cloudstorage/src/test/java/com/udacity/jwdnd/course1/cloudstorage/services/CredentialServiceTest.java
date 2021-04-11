package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CredentialServiceTest {
    @Autowired
    private CredentialService credentialService;
    @Autowired
    private UserService userService;

    @AfterEach
    public void afterEach(){
        credentialService.deleteAllCredentials();
        userService.deleteAllUsers();
    }
    @BeforeEach
    public void beforeEach(){

        userService.createUser(new User(null, "JohnDoe", null, "password", "John", "Doe"));
        userService.createUser(new User(null, "JackDoe", null, "password", "Jack", "Doe"));
        User firstUser = userService.getUser("JohnDoe");
        User secondUser = userService.getUser("JackDoe");
        for(int i = 0 ; i < 10 ; ++i){
            if(i < 7){
                credentialService.createCredential(new Credential(null, "/testURL" + i, "username" + i,"password", firstUser.getUserId()));
            } else{
                credentialService.createCredential(new Credential(null, "/testURL" + i, "username"+ i,"password", secondUser.getUserId()));
            }
        }
    }
    @Test
    public void testCredentialsCreation(){
        User user = userService.getUser("JohnDoe");
        Credential credential = new Credential(null, "/testURL", "username","password", user.getUserId());
        assertEquals(credentialService.createCredential(credential), 1);
    }
    @Test
    public void testGetAllCredentials(){
        List<Credential> expectedCredentials = credentialService.getAllCredentials();
        assertEquals(expectedCredentials.size(), 10);
    }
    @Test
    public void testGetAllCredentialsForUser(){
        List<Credential> expectedNotesFirstUser = credentialService.getAllCredentialsByUsername("JohnDoe");
        List<Credential> expectedNotesSecondUser = credentialService.getAllCredentialsByUsername("JackDoe");
        assertEquals(7, expectedNotesFirstUser.size());
        assertEquals(3, expectedNotesSecondUser.size());
    }
    @Test
    public void testGetAllCredentialsByUsername(){
        assertEquals(7, credentialService.getAllCredentialsByUsername("JohnDoe").size());
        assertEquals(3, credentialService.getAllCredentialsByUsername("JackDoe").size());
    }
    @Test
    public void testDeleteAllCredentialsForUserByUsername(){
        assertEquals(7, credentialService.getAllCredentialsByUsername("JohnDoe").size());
        credentialService.deleteAllCredentialsForUserByUsername("JohnDoe");
        assertEquals(0, credentialService.getAllCredentialsByUsername("JohnDoe").size());
    }
    @Test
    public void testDeleteAllNotesForUser(){
        assertEquals(7, credentialService.getAllCredentialsByUsername("JohnDoe").size());
        credentialService.deleteAllCredentialsForUser(userService.getUser("JohnDoe"));
        assertEquals(0, credentialService.getAllCredentialsByUsername("JohnDoe").size());
    }
}
