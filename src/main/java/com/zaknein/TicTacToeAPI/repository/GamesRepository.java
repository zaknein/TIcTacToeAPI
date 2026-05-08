package com.zaknein.TicTacToeAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zaknein.TicTacToeAPI.entity.User;

public interface GamesRepository extends JpaRepository<User, Long> {

    
 
}
