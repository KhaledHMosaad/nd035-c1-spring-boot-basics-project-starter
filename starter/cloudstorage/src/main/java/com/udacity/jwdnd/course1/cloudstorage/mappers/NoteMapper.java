package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES")
    List<Note> getAllNotes();

    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{noteTitle}, #{noteDescription}, #{userId})")
    int insertNote(Note note);

    @Select("SELECT * FROM NOTES AS n WHERE n.noteid = #{noteId}")
    Note getNoteById(int noteId);

    @Select("SELECT n.noteid, n.notetitle, n.notedescription, n.userid" +
            " FROM USERS AS u" +
            " JOIN NOTES AS n" +
            " ON u.userid = n.userid" +
            " WHERE u.username = #{username}")
    List<Note> getAllNoteForUser(String username);

    @Select("SELECT n.noteid, n.notetitle, n.notedescription, n.userid" +
            " FROM USERS AS u" +
            " JOIN NOTES AS n" +
            " ON u.userid = n.userid" +
            " WHERE u.username = #{username}" +
            " AND n.noteid = #{noteId}")
    Note getNoteForUserByNoteId(String username, Integer noteId);

    @Delete("Delete FROM NOTES;")
    int deleteAllNotes();

    @Delete("DELETE FROM NOTES " +
            "WHERE userid = #{userid}")
    int deleteAllNotesForUser(Integer userid);

    @Delete("DELETE FROM NOTES AS n " +
            "WHERE n.userid = (SELECT u.userid " +
                            " FROM USERS AS u" +
                            " WHERE u.username = #{username})")
    int deleteAllNotesForUserByUsername(String username);

    @Delete("DELETE FROM NOTES AS n WHERE n.noteid = #{noteId}")
    int deleteNoteById(int noteId);

    @Update("Update NOTES AS n " +
            "SET n.notetitle = #{noteTitle}, n.notedescription = #{noteDescription} " +
            "WHERE n.noteid = #{noteId}")
    int updateNote(Note note);
}
