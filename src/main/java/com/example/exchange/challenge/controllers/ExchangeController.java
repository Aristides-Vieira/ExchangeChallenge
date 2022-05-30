package com.example.exchange.challenge.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

@RestController
public class ExchangeController {


    @RequestMapping("/rates")
    public String getRates(@RequestParam(value = "base", defaultValue = "EUR") String baseCurrency) throws IOException, InterruptedException, URISyntaxException {
        ExchangeGetController ex = new ExchangeGetController();
        return ex.getRates(baseCurrency).body();
    }

}
