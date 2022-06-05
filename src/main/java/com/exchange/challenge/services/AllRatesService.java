package com.exchange.challenge.services;

import com.exchange.challenge.dtos.AllRatesDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;


@Service
public class AllRatesService {


    public String getRatesDTO(String baseCurr) throws IOException, URISyntaxException, InterruptedException {

        Request request = new Request();
        AllRatesDTO allRatesDTO = new AllRatesDTO(request.getAllRates(baseCurr));

        return allRatesDTO.jsonConverter(allRatesDTO.getRates());
    }

}
