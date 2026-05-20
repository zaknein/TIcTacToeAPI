package com.zaknein.TicTacToeAPI.service;

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

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class GameService {
    
        private final UserRepository userRepository ;
        private final GamesRepository gamesRepository;

    public Game challengePlayer(String emailOponent) {



        String emailUser =(String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println(">>> emailOponent recibido: [" + emailOponent + "]11111");

        User playerX;
        User playerO;

        User user = userRepository.findByEmail(emailUser)
                            .orElseThrow(()-> new RuntimeException());  
        
        System.out.println(">>> emailOponent recibido: [" + emailOponent + "]222222");

        User oponentUser = userRepository.findByEmail(emailOponent)
                            .orElseThrow(()-> new RuntimeException("invalid oponent"));  
        
        System.out.println(">>> emailOponent recibido: [" + emailOponent + "]33333");
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
        System.out.println("dentro de service pero antes de buscar el games");
        Game game = gamesRepository.findById(id)
                            .orElseThrow(()-> new RuntimeException());
        System.out.println("soy un sout dentro de gamesby id");
        return game;
    }




}
