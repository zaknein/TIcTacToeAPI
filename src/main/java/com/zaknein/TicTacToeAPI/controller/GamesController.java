package com.zaknein.TicTacToeAPI.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zaknein.TicTacToeAPI.service.GameService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@AllArgsConstructor
@RequestMapping("/games/")
@RestController
public class GamesController {

    private final GameService gameService;

    @PostMapping()
    public String challengePlayer(@RequestBody String oponent) {
        //TODO: process POST request
        
        return gameService.challengePlayer();
    }
    
    // @GetMapping()
    // public String getMethodName() {

    //     return gameService.getMyGames();
    // }

    // @GetMapping("/{id}")
    // public String getMethodName(@RequestParam Long id) {


    //     return gameService.getAllGames(Long id);
    // }

    // @DeleteMapping("/{id}")
    // public void deleteGame(@RequestParam Long id){

    //     gameService.deleteGame(id);
    // }
    
    




}
