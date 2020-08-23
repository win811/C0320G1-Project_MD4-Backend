package md4.bid_project.services.restful.braintree;


import com.braintreegateway.*;
import md4.bid_project.exception.SettlementException;
import md4.bid_project.models.dto.TransferDTO;

import java.util.Map;

public interface BrainTreeService {

    Map<String, String> getClientToken();

    Transaction requestTransaction(TransferDTO transferDTO) throws SettlementException;
}
