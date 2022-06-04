package com.exchange.challenge.controllers;

import com.exchange.challenge.services.AllRatesService;
import com.exchange.challenge.services.ConversionServices;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ConversionServices conversionServices = new ConversionServices();

    @RequestMapping("/rates")
    public String getRates(@RequestParam(value = "base", defaultValue = "EUR") String baseCurrency) {

        String json;
        try {
            Map<String, Long> ratesMap = allRatesService.getRates(baseCurrency);
            json = new ObjectMapper().writeValueAsString(ratesMap);
        } catch (IOException | URISyntaxException | InterruptedException exception) {
            return exception.getMessage();
        }


        return json;
    }

    @RequestMapping("/conversion")
    public String conversionRate (@RequestParam(value = "base", defaultValue = "EUR") String baseCurrency,
                                  @RequestParam(value = "symbols", defaultValue = "USD") String currencies,
                                  @RequestParam(value = "amount", defaultValue = "0") String amount) {

        try {

            if (!amount.equals("0")) {
                System.out.println(amount);
                Map<String, Double> conversionMap = conversionServices.getRates(baseCurrency, currencies, amount);
                String json = new ObjectMapper().writeValueAsString(conversionMap);
                return json;
            } else {
                System.out.println(amount);
                Map<String, Double> ratesMap = conversionServices.getRates(baseCurrency, currencies, amount);
                String json = new ObjectMapper().writeValueAsString(ratesMap);
                return json;
            }

        } catch (IOException | URISyntaxException | InterruptedException exception) {
            return exception.getMessage();            
        }
    };


}
