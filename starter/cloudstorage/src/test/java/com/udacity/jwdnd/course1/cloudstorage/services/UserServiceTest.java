package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.forms.UserSignUpForm;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest{
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @AfterEach()
    public void afterEach(){
        userMapper.deleteAllUsers();
    }
    @Test
    public void testCreateUser(){
        User user = new User(null, "JohnDoe", null, "password", "John", "Doe");
        userService.createUser(user);
        assertEquals(user.getUsername(),userMapper.getUser("JohnDoe").getUsername());
    }
    @Test
    public void testIsUserNameAvailable(){
        User user = new User(null, "JohnDoe", null, "password", "John", "Doe");
        userService.createUser(user);
        assertFalse(userService.isUsernameAvailable("JohnDoe"));
    }
    @Test
    public void testSuccessfulUserInsertion(){
        User user = new User(null, "JohnDoe", null, "password", "John", "Doe");
        userService.createUser(user);
        assertTrue(userService.getUser(user.getUsername()) != null);
    }
}