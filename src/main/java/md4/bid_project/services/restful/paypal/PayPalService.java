package md4.bid_project.services.restful.paypal;

import md4.bid_project.models.dto.TransferDTO;

import java.io.IOException;

public interface PayPalService {

    Transaction createTransaction(TransferDTO transferDTO) throws IOException;
    Transaction captureTransaction(String orderId) throws IOException;

}
