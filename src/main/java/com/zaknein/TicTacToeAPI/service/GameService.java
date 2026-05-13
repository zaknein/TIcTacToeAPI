package com.zaknein.TicTacToeAPI.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.boot.security.oauth2.server.resource.autoconfigure.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.zaknein.TicTacToeAPI.entity.Game;
import com.zaknein.TicTacToeAPI.entity.GameStatus;
import com.zaknein.TicTacToeAPI.entity.User;
import com.zaknein.TicTacToeAPI.repository.GamesRepository;
import com.zaknein.TicTacToeAPI.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class GameService {
    
        private final UserRepository userRepository ;
        private final GamesRepository gamesRepository;

    public Game challengePlayer(String emailOponent) {


        // TODOS:
        // - encontrar user entity de usuario autenticado *****
        // - encontrar user entity oponente ****
        // - encontrar quien es el primer jugador con algoritmo aleatorio ****
        // - asimismo encontrar que jugador es X, O ****
        // - Inicializar el tablero con una matriz vacia
        // - con esto construir un game y guardarlo usando el repo
        // - Despues retornar


        String emailUser =(String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        User playerX;
        User playerO;

        User user = userRepository.findByEmail(emailUser)
                            .orElseThrow(()-> new RuntimeException());  
        


        User oponentUser = userRepository.findByEmail(emailOponent)
                            .orElseThrow(()-> new RuntimeException("invalid oponent"));  
        

        Boolean result = new Random().nextBoolean();

        if (result == true) {
            playerX = user;
            playerO = oponentUser;
        }else{
            playerX = oponentUser;
            playerO = user;
        }


        Game game = Game.builder()
                    .playerX(playerX)
                    .playerO(playerO)
                    .board(new Long [3][3])
                    .currentTurn(playerX)
                    .status(GameStatus.IN_PROGRESS)
                    .created_at(LocalDateTime.now())
                    .build();

        
        return gamesRepository.save(game);



    }


}
