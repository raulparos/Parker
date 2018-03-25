package com.parker.util.authentication;

import com.parker.domain.exception.user.UserException;
import com.parker.domain.model.User;
import com.parker.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        if (authorizedUser(email, password)) {
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(() -> "ROLE_USER");
            Authentication auth = new UsernamePasswordAuthenticationToken(email, password, grantedAuths);
            return auth;
        }

        throw new AuthenticationCredentialsNotFoundException("Invalid credentials for authentication!");
    }

    private boolean authorizedUser(String email, String password) {
        try {
            userService.authenticateUser(email, password);
            return true;
        } catch (UserException e) {
            return false;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}