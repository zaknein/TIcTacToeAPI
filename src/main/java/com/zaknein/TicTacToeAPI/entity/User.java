package com.zaknein.TicTacToeAPI.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue()
    final private Long id;

    @Column(unique = true, nullable = false)
    final private String email;

    @Column(nullable = false)
    final private String password;



}
