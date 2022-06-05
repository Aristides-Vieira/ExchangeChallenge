package com.exchange.challenge.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class JSONConverter {


    public String convert(Map<String, Double> map) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(map);
    }

}
