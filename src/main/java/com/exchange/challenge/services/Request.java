package com.exchange.challenge.services;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Service
@Component
@EnableConfigurationProperties
public class Request {

    private final String getUrl = "https://api.exchangerate.host/latest";
    @Value("${api.url}")
    private String apiUrl;
    public HttpResponse<String> getAllRates(String baseCurr) throws URISyntaxException, IOException, InterruptedException {

        HttpResponse<String> response = null;

            HttpClient client = HttpClient.newHttpClient();
            URIBuilder uriBuilder = new URIBuilder(apiUrl);
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
        URIBuilder uriBuilder = new URIBuilder(getUrl);
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

        return client.send(request, HttpResponse.BodyHandlers.ofString());

    }
}
