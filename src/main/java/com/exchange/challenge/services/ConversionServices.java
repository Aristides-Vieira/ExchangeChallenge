package com.exchange.challenge.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.Map;

@Service
public class ConversionServices {

    private final Request pubApi = new Request();
    private final ObjectMapper objectMapper = new ObjectMapper();



    public Map<String, Double> getRates(String baseCurr, String currencies, String amountString) throws IOException, URISyntaxException, InterruptedException {
        HttpResponse<String> response = pubApi.conversionRate(baseCurr, currencies);
        Map<String, Object> rates = objectMapper.readValue(response.body(), new TypeReference<Map<String, Object>>() {});

        if(amountString.equals("0")) {
            return onlyRates(rates);
        } else {
            Double amount = Double.parseDouble(amountString);
            return convertValues(rates, amount);
        }
    }


    public Map<String, Double> onlyRates(Map<String, Object> map) throws IOException {
        Object ratesObj = map.get("rates");
        JsonNode ratesNode = objectMapper.valueToTree(ratesObj);
        System.out.println(ratesNode);

        return objectMapper.readValue(ratesNode.toString(), new TypeReference<Map<String, Double>>() {});
    }

    public Map<String, Double> convertValues(Map<String, Object> map, Double amount) throws IOException {
        Object ratesObj = map.get("rates");
        JsonNode ratesNode = objectMapper.valueToTree(ratesObj);
        Map<String, Double> rates = objectMapper.readValue(ratesNode.toString(), new TypeReference<Map<String, Double>>() {});

        rates.replaceAll((k,v) -> v * amount);

        return rates;
    }

}
