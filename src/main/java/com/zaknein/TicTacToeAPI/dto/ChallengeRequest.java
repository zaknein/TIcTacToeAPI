package com.zaknein.TicTacToeAPI.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ChallengeRequest(
    @NotBlank(message = "Oponent its required") 
    @Email(message = "Email must be valid") 
    String oponent
) {}
