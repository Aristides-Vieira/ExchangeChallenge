package com.example.exchange.challenge.controllers;

import org.apache.catalina.util.ParameterMap;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.tags.Param;
import org.springframework.web.util.UriBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.URIParameter;
import java.util.HashMap;
import java.util.Map;

@Component
public class ExchangeGetController {

    private String url = "https://api.exchangerate.host/latest";

    public HttpResponse<String> getRates(String baseCurr) throws IOException, InterruptedException, URISyntaxException {


        HttpClient client = HttpClient.newHttpClient();
        URIBuilder uriBuilder = new URIBuilder(url);
        uriBuilder
                .addParameter("base",baseCurr)
                .build();
        URI uri = uriBuilder.build();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .header("accept","application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response;

    }
}
