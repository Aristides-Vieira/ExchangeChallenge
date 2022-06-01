package com.example.exchange.challenge.controllers;
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

    private String url = "https://api.exchangerate.host/latest";

    public HttpResponse<String> getAllRates(String baseCurr) throws IOException, InterruptedException, URISyntaxException {


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
