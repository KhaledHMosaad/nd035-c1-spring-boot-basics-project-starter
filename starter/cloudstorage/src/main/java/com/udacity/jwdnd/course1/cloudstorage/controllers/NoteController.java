package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/notes")
public class NoteController {
    private UserService userService;
    private NoteService noteService;

    public NoteController(NoteService noteService, UserService userService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @GetMapping("/{id}/delete")
    public String delete(Principal principal, Model model, @PathVariable int id){
        Note note = noteService.getNoteForUserByNoteId(principal.getName(), id);
        if(note == null){
            String message = "You dont have access to this note or it doesn't exist.";
            return "redirect:/result?isSuccessful=" + false + "&message=" + message;
        }
        noteService.deleteNoteById(id);
        String message = "The note has been deleted successfully";
        return "redirect:/result?isSuccessful=" + true + "&message=" + message;
    }
    @PostMapping
    public String post(Principal principal, @ModelAttribute("note") Note note, Model model){
        User currentUser = userService.getUser(principal.getName());
        if(note.getNoteId() > 0){
            System.out.println(note.getNoteTitle());
            noteService.updateNote(note);
            String message = "The note has been edited successfully";
            return "redirect:/result?isSuccessful=" + true + "&message=" + message;
        }
        note.setNoteId(null);
        note.setUserId(currentUser.getUserId());
        noteService.createNote(note);
        String message = "The note has been created successfully";
        return "redirect:/result?isSuccessful=" + true + "&message=" + message;
    }
}
