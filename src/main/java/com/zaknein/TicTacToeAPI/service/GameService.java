package com.zaknein.TicTacToeAPI.service;

import org.springframework.boot.security.oauth2.server.resource.autoconfigure.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.zaknein.TicTacToeAPI.entity.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class GameService {
    


    public String challengePlayer(String oponent) {


        // TODOS:
        // - encontrar user entity de usuario autenticado*****
        // - encontrar user entity oponente
        // - encontrar quien es el primer jugador con algoritmo aleatorio
        // - asimismo encontrar que jugador es X, O
        // -Inicializar el tablero con una matriz vacia
        // - con esto construir un game y guardarlo usando el repo
        // - Despues retornar


 

        String email =(String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        

        String caca = "caca";

        return caca;

    }


}
