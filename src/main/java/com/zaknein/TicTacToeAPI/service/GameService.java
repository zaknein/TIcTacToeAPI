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


    public GameStatus makeMove(Long gameId, int row, int col){

        Game game = gamesRepository.findById(gameId)
                    .orElseThrow(()-> new RuntimeException());

        String [][] board = game.getBoard();

        if(board[row][col] !=null){
            throw new RuntimeException("Cell already taken");
        }

        String symbol = game.getCurrentTurn().equals(game.getPlayerX()) ? "X" : "O";
        board[row][col] = symbol;

        game.setBoard(board);
        
        User nextTrun = game.getCurrentTurn().equals(game.getPlayerX())
            ? game.getPlayerO()
            : game.getPlayerX();
        game.setCurrentTurn(nextTrun);


       GameStatus result = victoryDetection(game);

        game.setStatus(result);

        gamesRepository.save(game);
     
        return result;
    }


    private GameStatus victoryDetection(Game game){

        
        String[][] board = game.getBoard();

        // Filas
        for (int row = 0; row < 3; row++) {
            if (board[row][0] != null
                    && board[row][0].equals(board[row][1])
                    && board[row][1].equals(board[row][2])) {

                setWinner(game, board[row][0]);
                game.setStatus(GameStatus.FINISHED);
                return GameStatus.FINISHED;
            }
        }

        // Columnas
        for (int col = 0; col < 3; col++) {
            if (board[0][col] != null
                    && board[0][col].equals(board[1][col])
                    && board[1][col].equals(board[2][col])) {

                setWinner(game, board[0][col]);
                game.setStatus(GameStatus.FINISHED);
                return GameStatus.FINISHED;
            }
        }

        // Diagonal principal
        if (board[0][0] != null
                && board[0][0].equals(board[1][1])
                && board[1][1].equals(board[2][2])) {

            setWinner(game, board[0][0]);
            game.setStatus(GameStatus.FINISHED);
            return GameStatus.FINISHED;
        }

        // Diagonal secundaria
        if (board[0][2] != null
                && board[0][2].equals(board[1][1])
                && board[1][1].equals(board[2][0])) {

            setWinner(game, board[0][2]);
            game.setStatus(GameStatus.FINISHED);
            return GameStatus.FINISHED;
        }

        return GameStatus.IN_PROGRESS;

    }

    private void setWinner(Game game, String symbol) {

    if ("X".equals(symbol)) {
        game.setWinner(game.getPlayerX());
    } else {
        game.setWinner(game.getPlayerO());
    }
}

}
