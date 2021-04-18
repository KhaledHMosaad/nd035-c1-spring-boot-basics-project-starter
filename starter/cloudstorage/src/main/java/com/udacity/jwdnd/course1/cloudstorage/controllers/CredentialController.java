package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/credentials")
public class CredentialController {
    private UserService userService;
    private CredentialService credentialService;
    private EncryptionService encryptionService;

    public CredentialController(UserService userService, CredentialService credentialService, EncryptionService encryptionService) {
        this.userService = userService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @GetMapping("/{id}/delete")
    public String delete(Principal principal, Model model, @PathVariable int id){
        Credential credential = credentialService.getCredentialForUserByCredentialId(principal.getName(), id);
        if(credential == null){
            String message = "You dont have access to this credential or it doesn't exist.";
            return "redirect:/result?isSuccessful=" + false + "&message=" + message;
        }
        credentialService.deleteCredentialById(id);
        String message = "The credential has been deleted successfully";
        return "redirect:/result?isSuccessful=" + true + "&message=" + message;
    }
    @PostMapping
    public String post(Principal principal, @ModelAttribute("credential") Credential credential, Model model){
        User currentUser = userService.getUser(principal.getName());
        String encryptedCredentialPassword = encryptionService.encryptValue(credential.getPassword(),currentUser.getPassword());
        credential.setPassword(encryptedCredentialPassword);
        if(credential.getCredentialId() > 0){
            credentialService.updateCredential(credential);
            String message = "The credential has been edited successfully";
            return "redirect:/result?isSuccessful=" + true + "&message=" + message;
        }
        credential.setCredentialId(null);
        credential.setUserId(currentUser.getUserId());
        credentialService.createCredential(credential);
        String message = "The credential has been created successfully";
        return "redirect:/result?isSuccessful=" + true + "&message=" + message;
    }
}
