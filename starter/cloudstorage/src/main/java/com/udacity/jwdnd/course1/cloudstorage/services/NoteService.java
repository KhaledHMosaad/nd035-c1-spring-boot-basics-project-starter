package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public int createNote(Note note){
        return noteMapper.insertNote(note);
    }
    public List<Note> getAllNotes(){
        return noteMapper.getAllNotes();
    }
    public List<Note> getAllNotesForUser(User user){
        return noteMapper.getAllNoteForUser(user.getUsername());
    }
    public List<Note> getAllNotesByUsername(String username){
        return noteMapper.getAllNoteForUser(username);
    }
    public int deleteAllNotes(){
        return noteMapper.deleteAllNotes();
    }
    public int deleteAllNotesForUser(User user){
        return noteMapper.deleteAllNotesForUser(user.getUserId());
    }
    public int deleteAllNotesForUserByUsername(String username){
        return noteMapper.deleteAllNotesForUserByUsername(username);
    }
}
