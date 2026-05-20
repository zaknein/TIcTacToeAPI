package com.zaknein.TicTacToeAPI.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zaknein.TicTacToeAPI.entity.Game;

public interface GamesRepository extends JpaRepository<Game, Long> {

    
    Optional<Game> findById(Long id);
 
}
