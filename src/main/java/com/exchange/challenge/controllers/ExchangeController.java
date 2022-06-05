package com.exchange.challenge.controllers;

import com.exchange.challenge.services.AllRatesService;
import com.exchange.challenge.services.ConversionServices;
import io.swagger.annotations.*;
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


    @ApiOperation(value = "Returns all exchange rates for a currency. base= defines the base currency. Example: base=USD")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Status code OK, Returns all exchange rates"),
            @ApiResponse(code = 500, message = "An exception has occurred"),
    })
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

    @ApiOperation(value = "Returns a list of exchange rates for a currency. base= defines the base currency. Example: base=USD, " +
            "symbols= defines the list of currencies to get the exchange rate. Example symbols=EUR,CNY,BZD " +
            "amount= defines an amount to convert to a list of exchange rates. Example amount=250")

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Status code OK, Returns list of exchange rates"),
            @ApiResponse(code = 500, message = "An exception has occurred"),

    })
    @RequestMapping(value = "/conversion", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<String> conversionRate(@RequestParam(value = "base", defaultValue = "EUR") String baseCurrency,
                                                 @RequestParam(value = "symbols", defaultValue = "USD") String currencies,
                                                 @RequestParam(value = "amount", defaultValue = "0") String amount) {

        //Amount is being set as String to be able to do a try{ Double.parseDouble(amountStr) } in case user sends letters in amount;
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
