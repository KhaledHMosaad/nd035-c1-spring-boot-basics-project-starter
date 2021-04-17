package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.forms.UserSignUpForm;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    private UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String doGet(@ModelAttribute("userSignUpForm") UserSignUpForm userSignUpForm, Model model){
        return "signup";
    }

    @PostMapping
    public String doPost(@ModelAttribute("userSignUpForm") UserSignUpForm userSignUpForm, Model model){
        String errorMessage;
        if(!userService.isUsernameAvailable(userSignUpForm.getUsername())){
            errorMessage = "Username is already taken.";
            model.addAttribute("errorFlag", true);
            model.addAttribute("errorMessage", errorMessage);
            return "signup";
        }
        int res = userService.createUser(userSignUpForm);
        if(res < 1){
            errorMessage = "There was an error signing you up. Please try again.";
            model.addAttribute("errorFlag", true);
            model.addAttribute("errorMessage", errorMessage);
            return "signup";
        }
        model.addAttribute("signUpSuccess", true);
        return "signup";
    }
}
