package com.zaknein.TicTacToeAPI.entity;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BoardConverter implements AttributeConverter<String[][], String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(String[][] board) {
        if (board == null) {
            return null;
        }

        try {
            return objectMapper.writeValueAsString(board);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Unable to convert board to JSON", e);
        }
    }

    @Override
    public String[][] convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank()) {
            return null;
        }

        try {
            return objectMapper.readValue(dbData, String[][].class);
        } catch(IOException e){
            throw new IllegalArgumentException("Unable to convert JSON to board", e);
        }
    }
}
