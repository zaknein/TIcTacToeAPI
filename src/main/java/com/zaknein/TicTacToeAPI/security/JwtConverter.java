package com.zaknein.TicTacToeAPI.security;

import java.util.ArrayList;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class JwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    // private final UserService userService;

    // public JwtToUserAuthenticationConverter(UserService userService) {
    //     this.userService = userService;
    // }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        final var expiresAt = jwt.getExpiresAt();
        final var isExpired = expiresAt != null && expiresAt.isBefore(java.time.Instant.now());

        System.out.println("Current time   : " + java.time.Instant.now());
        System.out.println("JWT Expires at : " + expiresAt);
        System.out.println("JWT Expired    : " + isExpired);

        // Extract email from JWT subject
        String email = jwt.getSubject();

        // // Fetch user from database
        // User user = userService.findByEmail(email).orElse(null);

        // if (user == null) {
        //     // If user not found, return null (authentication will fail)
        //     return null;
        // }

        // Extract authorities from JWT scope claim
        // final var authorities = TokenUtils.extractAuthorities(jwt);


         

        // Create authentication token with User entity as principal
        return new UsernamePasswordAuthenticationToken(email, jwt, new ArrayList<>());
    }
}
