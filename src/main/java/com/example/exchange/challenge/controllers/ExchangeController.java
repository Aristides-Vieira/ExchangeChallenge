package com.example.exchange.challenge.controllers;

import com.example.exchange.challenge.services.AllRatesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import netscape.javascript.JSObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

@RestController
public class ExchangeController {


    private final ExchangeAPI ex = new ExchangeAPI();
    private final AllRatesService allRatesService = new AllRatesService();

    @RequestMapping("/rates")
    public String getRates(@RequestParam(value = "base", defaultValue = "EUR") String baseCurrency) throws IOException, InterruptedException, URISyntaxException {
        Map<String, Long> ratesMap = allRatesService.getRates(baseCurrency);
        String json = new ObjectMapper().writeValueAsString(ratesMap);
        return json;
    }

    @RequestMapping("/conversion")
    public String conversionRate (@RequestParam(value = "base", defaultValue = "EUR") String baseCurrency,
                                  @RequestParam(value = "currencies") String currencies,
                                  @RequestParam(value = "amount") Long amount)  {
        

      return "";
    };


}
