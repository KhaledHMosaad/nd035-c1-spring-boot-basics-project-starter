package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.forms.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    private NoteService noteService;
    private CredentialService credentialService;
    private FileService fileService;

    public HomeController(NoteService noteService, CredentialService credentialService, FileService fileService) {
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.fileService = fileService;
    }

    @GetMapping
    public String doGet(Principal principal,
                        @ModelAttribute("notesList") ArrayList<Note> notesList,
                        @ModelAttribute("credentialsList") ArrayList<Credential> credentialsList,
                        @ModelAttribute("filesList") ArrayList<File> filesList,
                        @ModelAttribute("fileForm") FileForm fileForm,
                        Model model){
        filesList = (ArrayList<File>) fileService.getFilesByUserName(principal.getName());
        notesList = (ArrayList<Note>) noteService.getAllNotesByUsername(principal.getName());
        credentialsList = (ArrayList<Credential>) credentialService.getAllCredentialsByUsername(principal.getName());
        model.addAttribute("notesList", notesList);
        model.addAttribute("credentialsList", credentialsList);
        model.addAttribute("filesList", filesList);
        return "home";
    }
}
