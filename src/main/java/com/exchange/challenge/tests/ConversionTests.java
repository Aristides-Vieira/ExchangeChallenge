package com.exchange.challenge.tests;

import com.exchange.challenge.services.ConversionServices;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.util.HashMap;
import java.util.Map;


public class ConversionTests {

    ConversionServices conversionServices;
    Map<String, Double> map;
    Double amount;


    @BeforeTest
    void starter() {
        conversionServices = new ConversionServices();
        map = new HashMap<>();
        map.put("eur", 2.0);
        amount = 5.0;
    }

    @Test
    void testConversion() {
        System.out.println(map.values());
        Assert.assertEquals(map.get("eur") * amount, conversionServices.convertAmount(map, amount).get("eur"), "OK");
    }
}
