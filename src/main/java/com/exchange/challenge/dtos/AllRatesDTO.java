package com.exchange.challenge.dtos;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModelProperty;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class AllRatesDTO {


    private Map<String,Double> rates;
    private final ObjectMapper objectMapper;


    public AllRatesDTO (HttpResponse<String> response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Double> rates = new HashMap<>();

        this.objectMapper = objectMapper;
        Map<String, Object> responseMap = objectMapper.readValue(response.body(), new TypeReference<Map<String, Object>>() {
        });
        this.rates = rates;
        onlyRates(responseMap);
    }


    public void onlyRates(Map<String, Object> map) throws IOException {
        Object ratesObj = map.get("rates");
        JsonNode ratesNode = objectMapper.valueToTree(ratesObj);
        rates = objectMapper.readValue(ratesNode.toString(), new TypeReference<Map<String, Double>>() {});
    }

    public String jsonConverter(Map<String, Double> map) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(map);
    }
    public Map<String, Double> getRates() {
        return rates;
    }

}
