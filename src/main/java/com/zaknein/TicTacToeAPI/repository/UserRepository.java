package com.zaknein.TicTacToeAPI.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zaknein.TicTacToeAPI.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);



}
