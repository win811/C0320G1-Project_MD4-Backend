package md4.bid_project.services.restful.braintree;


import com.braintreegateway.*;
import md4.bid_project.exception.SettlementException;

import java.util.Map;

public interface BrainTreeService {

    Map<String, String> getClientToken();

    Transaction requestTransaction(String nonce, Long userId) throws SettlementException;
}
