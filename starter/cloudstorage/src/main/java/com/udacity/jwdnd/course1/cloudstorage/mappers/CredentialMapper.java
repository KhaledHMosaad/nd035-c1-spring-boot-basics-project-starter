package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("SELECT credentialid, url, username, password, userid " +
            "FROM CREDENTIALS;")
    List<Credential> getAllCredentials();

    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    @Insert("INSERT INTO CREDENTIALS (url, username, password, userid) VALUES (#{url}, #{username}, #{password}, #{userId})")
    int insertCredential(Credential credential);

    @Select("SELECT c.credentialid, c.url, c.username, c.password, c.userid" +
            " FROM USERS AS u" +
            " JOIN CREDENTIALS AS c" +
            " ON u.userid = c.userid" +
            " WHERE u.username = #{username}")
    List<Credential> getAllCredentialsForUser(String username);

    @Select("SELECT c.credentialid, c.url, c.username, c.password, c.userid" +
            " FROM USERS AS u" +
            " JOIN CREDENTIALS AS c" +
            " ON u.userid = c.userid" +
            " WHERE u.username = #{username}" +
            " AND c.credentialid = #{credentialId}")
    Credential getCredentialForUserByCredentialId(String username, Integer credentialId);

    @Delete("Delete FROM CREDENTIALS;")
    int deleteAllCredentials();

    @Delete("DELETE FROM CREDENTIALS " +
            "WHERE userid = #{userid}")
    int deleteAllCredentialsForUser(Integer userid);

    @Delete("DELETE FROM CREDENTIALS AS c " +
            "WHERE c.userid = (SELECT u.userid " +
            " FROM USERS AS u" +
            " WHERE u.username = #{username})")
    int deleteAllCredentialsForUserByUsername(String username);

    @Delete("DELETE FROM CREDENTIALS AS c WHERE c.credentialid = #{credentialId}")
    int deleteCredentialById(int credentialId);

    @Update("Update CREDENTIALS AS c " +
            "SET c.url = #{url}, c.username = #{username}, c.password = #{password}" +
            "WHERE c.credentialId = #{credentialId}")
    int updateCredential(Credential credential);
}
