package com.exchange.challenge.controllers;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;



@Component
public class ExchangeAPI {

    private final String url = "https://api.exchangerate.host/latest";
    private final String badURl = "://api.exchangerate.host/latest";

    public HttpResponse<String> getAllRates(String baseCurr) throws URISyntaxException, IOException, InterruptedException {

        HttpResponse<String> response = null;

            HttpClient client = HttpClient.newHttpClient();
            URIBuilder uriBuilder = new URIBuilder(badURl);
            uriBuilder
                    .addParameter("base", baseCurr.toUpperCase())
                    .build();
            URI uri = uriBuilder.build();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(uri)
                    .header("accept", "application/json")
                    .build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());


        return response;

    }

    public HttpResponse<String> conversionRate(String baseCurr, String currencies) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        URIBuilder uriBuilder = new URIBuilder(url);
        uriBuilder
                .addParameter("base",baseCurr.toUpperCase())
                .addParameter("symbols", currencies.toUpperCase())
                .build();
        URI uri = uriBuilder.build();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .header("accept","application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(request);
        return response;

    }
}
