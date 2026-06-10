package com.team7.gameserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

//fetches country information from external API (for the loading scene)
@RestController
public class CountryController {

    private final RestTemplate restTemplate;

    public CountryController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // calls restcountries.com to get info for japan, korea, and indonesia
    @GetMapping("/countries/loading-info")
    public Object getLoadingCountries() {
        String url = "https://restcountries.com/v3.1/alpha?codes=KOR,IDN,JPN&fields=name,capital,region,subregion,population,flags,languages,currencies";

        try {
            return restTemplate.getForObject(url, String.class);

        } catch (RestClientException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Could not load country data.");
            return error;
        }
    }
}