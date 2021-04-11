package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NoteServiceTest {

    @Autowired
    private NoteService noteService;
    @Autowired
    private UserService userService;

    @AfterEach
    public void afterEach(){
        noteService.deleteAllNotes();
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
                noteService.createNote(new Note(null, "testNote" + i, "This note only exists for the purpose of testing",firstUser.getUserId()));
            } else{
                noteService.createNote(new Note(null, "testNote" + i, "This note only exists for the purpose of testing",secondUser.getUserId()));
            }
        }
    }
    @Test
    public void testNoteCreation(){
        User user = userService.getUser("JohnDoe");
        Note note = new Note(null, "testNote", "This note only exists for the purpose of testing",user.getUserId());
        assertEquals(noteService.createNote(note), 1);
    }
    @Test
    public void testGetAllNotes(){
        List<Note> expectedNotes = noteService.getAllNotes();
        assertEquals(expectedNotes.size(), 10);
    }
    @Test
    public void testGetAllNotesForUser(){
        List<Note> expectedNotesFirstUser = noteService.getAllNotesByUsername("JohnDoe");
        List<Note> expectedNotesSecondUser = noteService.getAllNotesByUsername("JackDoe");
        assertEquals(7, expectedNotesFirstUser.size());
        assertEquals(3, expectedNotesSecondUser.size());
    }
    @Test
    public void testGetAllNotesByUsername(){
        assertEquals(7, noteService.getAllNotesByUsername("JohnDoe").size());
        assertEquals(3, noteService.getAllNotesByUsername("JackDoe").size());
    }
    @Test
    public void testDeleteAllNotesForUserByUsername(){
        assertEquals(7, noteService.getAllNotesByUsername("JohnDoe").size());
        noteService.deleteAllNotesForUserByUsername("JohnDoe");
        assertEquals(0, noteService.getAllNotesByUsername("JohnDoe").size());
    }
    @Test
    public void testDeleteAllNotesForUser(){
        assertEquals(7, noteService.getAllNotesByUsername("JohnDoe").size());
        noteService.deleteAllNotesForUser(userService.getUser("JohnDoe"));
        assertEquals(0, noteService.getAllNotesByUsername("JohnDoe").size());
    }
}
