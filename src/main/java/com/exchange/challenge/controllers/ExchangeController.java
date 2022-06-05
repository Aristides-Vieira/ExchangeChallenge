package com.exchange.challenge.controllers;

import com.exchange.challenge.services.AllRatesService;
import com.exchange.challenge.services.ConversionServices;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.net.URISyntaxException;


@RestController
public class ExchangeController {



    @RequestMapping(value = "/rates", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<String> getAllRates(@RequestParam(value = "base", defaultValue = "EUR") String baseCurrency) {
        String response;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            response = new AllRatesService().getRatesDTO(baseCurrency);

        } catch (IOException | URISyntaxException | InterruptedException exception) {
            return new ResponseEntity<String>(exception.getMessage(), headers, HttpStatus.OK);
        }



        return new ResponseEntity<String>(response, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/conversion", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<String> conversionRate(@RequestParam(value = "base", defaultValue = "EUR") String baseCurrency,
                                                 @RequestParam(value = "symbols", defaultValue = "USD") String currencies,
                                                 @RequestParam(value = "amount", defaultValue = "0") String amount) {

        String response;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            response = new ConversionServices().getList(baseCurrency, currencies, amount);
        } catch (IOException | URISyntaxException | InterruptedException exception) {
            return new ResponseEntity<String>(exception.getMessage(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<String>(response, headers, HttpStatus.OK);
    };
}
