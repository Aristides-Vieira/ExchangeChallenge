package com.exchange.challenge.dtos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Map;

public class AllRatesDTO {

    private final Map<String, Object> responseMap;
    private final ObjectMapper objectMapper;


    public AllRatesDTO (HttpResponse<String> response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        this.objectMapper = objectMapper;
        this.responseMap = objectMapper.readValue(response.body(), new TypeReference<Map<String, Object>>() {});
    }


    public Map<String, Long> onlyRates(Map<String, Object> map) throws IOException {
        Object ratesObj = map.get("rates");
        JsonNode ratesNode = objectMapper.valueToTree(ratesObj);
        System.out.println(ratesNode);

        return objectMapper.readValue(ratesNode.toString(), new TypeReference<Map<String, Long>>() {});
    }

    public Map<String, Object> getResponseMap() {
        return responseMap;
    }

}
