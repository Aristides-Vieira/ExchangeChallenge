package com.exchange.challenge.services;

import com.exchange.challenge.dtos.AllRatesDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.Map;

@Service
public class AllRatesService {

    private final Request pubApi = new Request();
    private final ObjectMapper objectMapper = new ObjectMapper();


    public Map<String, Long> getRates(String baseCurr) throws IOException, URISyntaxException, InterruptedException {
        HttpResponse<String> response = pubApi.getAllRates(baseCurr);
        Map<String, Object> rates = objectMapper.readValue(response.body(), new TypeReference<Map<String, Object>>() {});

        return onlyRates(rates);
    }

    public Map<String, Long> onlyRates(Map<String, Object> map) throws IOException {
        Object ratesObj = map.get("rates");
        JsonNode ratesNode = objectMapper.valueToTree(ratesObj);
        System.out.println(ratesNode);

        return objectMapper.readValue(ratesNode.toString(), new TypeReference<Map<String, Long>>() {});
    }

    public Map<String, Long> getRatesDTO(String baseCurr) throws IOException, URISyntaxException, InterruptedException {
        AllRatesDTO allRatesDTO = new AllRatesDTO(pubApi.getAllRates(baseCurr));
        Map<String, Object> rates = allRatesDTO.getResponseMap();

        return onlyRates(rates);
    }

}
