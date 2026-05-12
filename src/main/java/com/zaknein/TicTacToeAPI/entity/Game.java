package com.zaknein.TicTacToeAPI.entity;
/*
{
  "id": "uuid-1234",
  "players": {
    "X": "jugador1",
    "O": "jugador2"
  },
  "board": [
    ["X", null, null],
    [null, "O", null],
    [null, null, null]
  ],
  "current_turn": "jugador1",
  "status": "IN_PROGRESS",
  "winner": null,
  "created_at": "2026-05-03T20:00:00Z"
}
*/

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Game {
  
    @Id
    @GeneratedValue
    private Long id;

    private User playerX;

    private User playerO;

    private Long[][] board;

    private User currentTurn;

    private GameStatus status;

    private User winner;

    private LocalDateTime created_at;

}
