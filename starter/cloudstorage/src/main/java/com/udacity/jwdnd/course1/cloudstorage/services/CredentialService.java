package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {
    private CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }
    public int createCredential(Credential credential){
        return credentialMapper.insertCredential(credential);
    }
    public List<Credential> getAllCredentials(){
        return credentialMapper.getAllCredentials();
    }
    public List<Credential> getAllCredentialsForUser(User user){
        return credentialMapper.getAllCredentialsForUser(user.getUsername());
    }
    public List<Credential> getAllCredentialsByUsername(String username){
        return credentialMapper.getAllCredentialsForUser(username);
    }
    public int deleteAllCredentials(){
        return credentialMapper.deleteAllCredentials();
    }
    public int deleteAllCredentialsForUser(User user){
        return credentialMapper.deleteAllCredentialsForUser(user.getUserId());
    }
    public int deleteAllCredentialsForUserByUsername(String username){
        return credentialMapper.deleteAllCredentialsForUserByUsername(username);
    }
}
