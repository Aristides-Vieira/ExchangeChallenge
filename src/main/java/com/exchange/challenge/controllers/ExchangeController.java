package com.exchange.challenge.controllers;

import com.exchange.challenge.services.AllRatesService;
import com.exchange.challenge.services.ConversionServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.net.URISyntaxException;


@RestController
public class ExchangeController {



    @RequestMapping("/rates")
    public String getRatesDTO(@RequestParam(value = "base", defaultValue = "EUR") String baseCurrency) {
        String json;

        try {
            json = new AllRatesService().getRatesDTO(baseCurrency);

        } catch (IOException | URISyntaxException | InterruptedException exception) {
            return exception.getMessage();
        }


        return json;
    }

    @RequestMapping("/conversion")
    public String conversionRate(@RequestParam(value = "base", defaultValue = "EUR") String baseCurrency,
                                 @RequestParam(value = "symbols", defaultValue = "USD") String currencies,
                                 @RequestParam(value = "amount", defaultValue = "0") String amount) {

        String json;
        try {
            json = new ConversionServices().getList(baseCurrency, currencies, amount);
        } catch (IOException | URISyntaxException | InterruptedException exception) {
            return exception.getMessage();
        }
        return json;
    };
}
