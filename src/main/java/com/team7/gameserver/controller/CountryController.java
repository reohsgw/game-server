package com.team7.gameserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CountryController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/countries/loading-info")
    public String getLoadingCountries() {
        String url = "https://restcountries.com/v3.1/alpha?codes=KOR,IDN,JPN&fields=name,capital,region,subregion,population,flags,languages,currencies";
        return restTemplate.getForObject(url, String.class);
    }
}