package com.zaknein.TicTacToeAPI.exceptions;

public class InvalidEmailException extends RuntimeException{


    public InvalidEmailException(String msg){
        super(msg);
    }
}
