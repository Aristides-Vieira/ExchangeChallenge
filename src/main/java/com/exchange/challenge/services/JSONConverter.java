package com.exchange.challenge.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JSONConverter {


    public String convert(Map<String, Double> map) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(map);
    }

}
