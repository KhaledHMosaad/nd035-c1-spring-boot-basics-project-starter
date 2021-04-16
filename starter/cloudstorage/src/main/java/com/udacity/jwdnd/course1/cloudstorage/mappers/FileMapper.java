package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata)" +
            " VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int addFile(File file);

    @Select("SELECT * FROM FILES AS f WHERE f.userid = #{userId}")
    List<File> getFilesByUserId(int userId);

    @Select("SELECT * FROM FILES AS f WHERE f.fileid = #{fileId}")
    File getFileById(Integer fileId);

    @Select("SELECT * FROM FILES")
    List<File> getAllFiles();

    @Select("SELECT f.fileid, f.filename, f.contenttype, f.filesize, f.userid, f.filedata" +
            " FROM USERS AS u" +
            " JOIN FILES AS f" +
            " ON u.userid = f.userid" +
            " WHERE u.username = #{username}")
    List<File> getAllFilesByUserName(String username);

    @Select("SELECT f.fileid, f.filename, f.contenttype, f.filesize, f.userid, f.filedata" +
            " FROM USERS AS u" +
            " JOIN FILES AS f" +
            " ON u.userid = f.userid" +
            " WHERE u.username = #{username}" +
            " AND f.fileid = #{fileId}")
    File getFileForUserByFileId(String username, Integer fileId);

    @Delete("DELETE FROM FILES AS f WHERE f.fileid = #{fileId}")
    int deleteFileById(int fileId);
}
