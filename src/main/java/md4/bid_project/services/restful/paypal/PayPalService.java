package md4.bid_project.services.restful.paypal;

import com.paypal.http.HttpResponse;
import com.paypal.orders.Order;
import md4.bid_project.models.Cart;

import java.io.IOException;

public interface PayPalService {

    Transaction createTransaction(Cart cart) throws IOException;
    Transaction captureTransaction(String orderId) throws IOException;

}
