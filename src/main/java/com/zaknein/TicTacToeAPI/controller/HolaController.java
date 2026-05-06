package com.zaknein.TicTacToeAPI.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;






@RestController
public class HolaController {
    
    @GetMapping("/holapublic")
    public String getMethodName() {
        return new String("holofo");
    }

    @GetMapping("/holaprivated")
    public String geame(@RequestParam String param) {
        return new String("turn back back");
    }
    
    



}
