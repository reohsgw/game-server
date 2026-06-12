//HASEGAWA REO
package com.team7.gameserver.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CountryController {

    private final RestTemplate restTemplate;

    public CountryController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // GET endpoint used by Unity loading scene
    @GetMapping("/countries/loading-info")
    public Object getLoadingCountries() {
        // RestCountries API was unexpectedly updated to v5, which was noticed on Jun 13.
        // The previous API version no longer works, and v5 requires an API key. So this sudden change was needed.
        // with a different JSON response structure.
        // So, this controller converts the v5 response into the format
        // expected by the existing Unity loading script.
        String[] urls = {
                "https://api.restcountries.com/countries/v5/codes.alpha_3/IDN?response_fields=names.common,capitals,currencies,population",
                "https://api.restcountries.com/countries/v5/codes.alpha_3/KOR?response_fields=names.common,capitals,currencies,population",
                "https://api.restcountries.com/countries/v5/codes.alpha_3/JPN?response_fields=names.common,capitals,currencies,population"
        };

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth("rc_live_4f0b95101a28422c9f605149cbe55484");

            HttpEntity<String> entity = new HttpEntity<>(headers);

            List<Object> result = new ArrayList<>();

            for (String url : urls) {
                ResponseEntity<Object> response = restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        entity,
                        Object.class);

                // Read the v5 response structure data -> objects
                Map body = (Map) response.getBody();
                Map data = (Map) body.get("data");
                List<Map> objects = (List<Map>) data.get("objects");

                if (objects != null && !objects.isEmpty()) {
                    Map country = objects.get(0);

                    Map names = (Map) country.get("names");
                    List<Map> capitals = (List<Map>) country.get("capitals");
                    List<Map> currencies = (List<Map>) country.get("currencies");

                    Map<String, Object> item = new HashMap<>();

                    Map<String, Object> nameMap = new HashMap<>();
                    nameMap.put("common", names.get("common"));

                    List<String> capitalList = new ArrayList<>();
                    capitalList.add((String) capitals.get(0).get("name"));

                    Map currencyData = currencies.get(0);
                    String currencyCode = (String) currencyData.get("code");

                    Map<String, Object> currencyInfo = new HashMap<>();
                    currencyInfo.put("name", currencyData.get("name"));
                    currencyInfo.put("symbol", currencyData.get("symbol"));

                    Map<String, Object> currenciesMap = new HashMap<>();
                    currenciesMap.put("KRW", null);
                    currenciesMap.put("IDR", null);
                    currenciesMap.put("JPY", null);
                    currenciesMap.put(currencyCode, currencyInfo);

                    // return data in the same format expected by the existing Unity script
                    item.put("name", nameMap);
                    item.put("capital", capitalList);
                    item.put("currencies", currenciesMap);
                    item.put("currencyName", currencyData.get("name"));
                    item.put("currencySymbol", currencyData.get("symbol"));
                    item.put("population", country.get("population"));

                    result.add(item);
                }
            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();

            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", e.getMessage());
            return error;
        }
    }
}