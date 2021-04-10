package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;

@Service
public class AuthenticationService implements AuthenticationProvider {
    private HashService hashService;
    private UserMapper userMapper;

    public AuthenticationService(HashService hashService, UserMapper userMapper) {
        this.hashService = hashService;
        this.userMapper = userMapper;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userMapper.getUser(username);
        if(user != null){
            // salt is stored encoded, password is stored hashed.
            if(user.getPassword().equals(hashService.getHashedValue(password,user.getSalt()))){
                return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authClass) {
        return authClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
