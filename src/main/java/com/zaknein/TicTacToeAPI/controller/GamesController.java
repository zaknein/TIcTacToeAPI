package com.zaknein.TicTacToeAPI.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zaknein.TicTacToeAPI.dto.ChallengeRequest;
import com.zaknein.TicTacToeAPI.entity.Game;
import com.zaknein.TicTacToeAPI.service.GameService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@AllArgsConstructor
@RequestMapping("/games/")
@RestController
public class GamesController {

    private final GameService gameService;

    
    @PostMapping()
    public Game challengePlayer(@RequestBody ChallengeRequest challangeRequest) {
        
        return gameService.challengePlayer(challangeRequest.oponent());
    }
    
    @GetMapping()
    public List<Game>  getMethodName() {

        return gameService.getMyGames();
    }

    @GetMapping("/{id}")
    public Game getMethodName(@PathVariable Long id) {

        System.out.println("log dentro del controller");
        return gameService.getGamesById(id);
    }

    // @DeleteMapping("/{id}")
    // public void deleteGame(@RequestParam Long id){

    //     gameService.deleteGame(id);
    // }
    
    




}
