package com.zaknein.TicTacToeAPI.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.boot.security.oauth2.server.resource.autoconfigure.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.zaknein.TicTacToeAPI.entity.Game;
import com.zaknein.TicTacToeAPI.entity.GameStatus;
import com.zaknein.TicTacToeAPI.entity.User;
import com.zaknein.TicTacToeAPI.repository.GamesRepository;
import com.zaknein.TicTacToeAPI.repository.UserRepository;

import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class GameService {
    
        private final UserRepository userRepository ;
        private final GamesRepository gamesRepository;

    public Game challengePlayer(String emailOponent) {



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
                    .board(new String[3][3])
                    .currentTurn(playerX)
                    .winner(null)
                    .status(GameStatus.IN_PROGRESS)
                    .created_at(LocalDateTime.now())
                    .build();

        
        return gamesRepository.save(game);



    }

    public List<Game> getMyGames() {

        String emailUser =(String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepository.findByEmail(emailUser)
                            .orElseThrow(()-> new RuntimeException());
                            
                            
        List<Game> playedAsO = user.getPlayerOGames();
        List<Game> playedAsX = user.getPlayerXGames();
        
        playedAsO.addAll(playedAsX);

        return playedAsO;
    }


    public Game getGamesById(Long id){
        Game game = gamesRepository.findById(id)
                            .orElseThrow(()-> new RuntimeException());

        return game;
    }

    public void deleteGame(Long id) {

        String emailUser =(String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Game game = gamesRepository.findById(id)
                    .orElseThrow(()-> new RuntimeException());

        String playedAsO = game.getPlayerO().getEmail();
        String playedAsX = game.getPlayerX().getEmail();

        if(!emailUser.equals(playedAsO) && !emailUser.equals(playedAsX) ){
            
            throw new RuntimeException("User is not allowed to cancel this game");

        }else{
            game.setStatus(GameStatus.CANCELLED);

            gamesRepository.save(game);
        }

    }




}
