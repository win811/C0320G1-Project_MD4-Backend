package md4.bid_project.services.restful.rateExchange;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashMap;

@Data
public class MoneyExchange {
    private boolean success;
    private String source;
    private LinkedHashMap<String, Double> quotes;

}
