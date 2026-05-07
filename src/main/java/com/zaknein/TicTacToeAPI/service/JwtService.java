package com.zaknein.TicTacToeAPI.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.stereotype.Service;

import com.zaknein.TicTacToeAPI.entity.User;
import com.zaknein.TicTacToeAPI.utiles.TokenUtils;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Service
public class JwtService {
    
    private final JwtEncoder jwtEncoder;

    @Value("${jwt.refresh.expiration-minutes}")
    private  long refreshExpirationInMinutes;

    @Value("${jwt.session.expiration-minutes}")
    private  long sessionExpirationInMinutes;


    // TODO PARA USAR CUANDO HAYA REFRESH TOKEN
    public String gerateRefreshToken(User user){
        return TokenUtils.generate(jwtEncoder, user, refreshExpirationInMinutes);
    }

    
    public String gerateSessionToken(User user){
        return TokenUtils.generate(jwtEncoder, user, sessionExpirationInMinutes);
    }

}
