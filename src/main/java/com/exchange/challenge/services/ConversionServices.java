package com.exchange.challenge.services;

import com.exchange.challenge.dtos.ListRatesDTO;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

@Service
public class ConversionServices {



    public String getList(String baseCurr, String currencies, String amountStr) throws IOException, URISyntaxException, InterruptedException{
        Request request = new Request();
        ListRatesDTO listRatesDTO = new ListRatesDTO(request.conversionRate(baseCurr, currencies));

        try {
            Double amount = Double.parseDouble(amountStr);
            return new JSONConverter().convert(convertAmount(listRatesDTO.getRatesList(), amount));

        } catch (NumberFormatException exception) {
            return new JSONConverter().convert(listRatesDTO.getRatesList());
        }
    }

    public Map<String, Double> convertAmount(Map<String, Double> valuesMap, Double amount) {

        valuesMap.replaceAll((k,v) -> v * amount);
        return valuesMap;
    }

}
