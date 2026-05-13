package com.zaknein.TicTacToeAPI.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue()
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;


    @OneToMany(mappedBy = "playerX", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<Game>playerXGames;


    @OneToMany(mappedBy = "playerO", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<Game>playerOGames;


    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return List.of();
    }



}
