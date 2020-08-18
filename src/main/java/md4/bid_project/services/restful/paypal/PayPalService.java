package md4.bid_project.services.restful.paypal;

import java.io.IOException;

public interface PayPalService {

    Transaction createTransaction(Long userId) throws IOException;
    Transaction captureTransaction(String orderId) throws IOException;

}
