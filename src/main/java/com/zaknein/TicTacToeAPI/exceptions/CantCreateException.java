package com.zaknein.TicTacToeAPI.exceptions;

public class CantCreateException extends RuntimeException{


    public CantCreateException(String msg){
        super(msg);
    }
}
