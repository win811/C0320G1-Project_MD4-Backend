package md4.bid_project.services.restful.rateExchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import org.json.JSONException;
import org.json.JSONObject;
@Service
public class RateExchangeService {

    @Autowired
    RestTemplate restTemplate;

    public Double getRateExchange() {
        URI url = getUrl();
        MoneyExchange responseEntity = restTemplate.getForObject(url.toString(), MoneyExchange.class);
        Double rate = responseEntity.getQuotes().get("USDVND");
        return rate;
    }

    private URI getUrl() {
        String schema = "http";
        String host = "api.currencylayer.com";
        String path = "/live";
        String APP_ID = "083064fa128c056c03992762960bf040";
        String CURRENCY = "VND";
        String query = String.format("access_key=%s&currencies=%s", APP_ID, CURRENCY);
        try {
            return new URI(schema, null, host, -1, path, query, null);
        } catch (URISyntaxException u) {
            return URI.create("/");
        }
    }


}
