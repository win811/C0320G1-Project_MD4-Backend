package md4.bid_project.services.restful.paypal;


import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import md4.bid_project.models.Cart;
import md4.bid_project.models.CartDetail;
import md4.bid_project.models.dto.TransferDTO;
import md4.bid_project.services.CartService;
import md4.bid_project.services.restful.rateExchange.RateExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PayPalServiceImpl implements PayPalService{


    @Autowired
    private RateExchangeService rateExchangeService;

    @Autowired
    private CartService cartService;

    // create sandbox environment
    private PayPalEnvironment environment = new PayPalEnvironment.Sandbox(
            "AbCzPUUevBpwehD2HBR8Y0_ic2rt8ldWn-y_nn7SgR04TvK3r9tLU9MZonzGDnXTq5exF5hlhdll6wMp",
            "EAu1dy0aOP6XFgc0H43y_wvBCiT_tw5HdrEopIV_oa67_dOhCF4lkEWxvZXNx6nM3XOTsTYwm8SfbTwT");


    // create client for environment
    PayPalHttpClient client = new PayPalHttpClient(environment);

    public PayPalHttpClient client() {
        return this.client;
    }

    private Cart getCart(Long userId) {
        return cartService.findByUserId(userId).orElse(null);
    }

    @Override
    public Transaction createTransaction(TransferDTO transferDTO) throws IOException {
        OrdersCreateRequest request = new OrdersCreateRequest();
        request.prefer("return=minimal");
        request.requestBody(buildOrderRequest(getCart(transferDTO.getUserId()), transferDTO.getDeliveryMethod()));
        // Call PayPal to set up a transaction
        HttpResponse<Order> response = client().execute(request);
        // set captured order
        Transaction transaction = new Transaction();
        transaction.setStatus(response.result().status())
            .setId(response.result().id())
            .setLinks(response.result().links());
        return transaction;
    }

    /**
     *Building empty request body.
     *
     *@return OrderActionRequest with empty body
     */
    public Transaction captureTransaction(String orderId) throws IOException {
        OrdersCaptureRequest request = new OrdersCaptureRequest(orderId);
        request.requestBody(new OrderRequest());
        // Call PayPal to capture an order
        HttpResponse<Order> response = client().execute(request);
        Transaction transaction = new Transaction();
        transaction.setStatus(response.result().status())
                .setId(response.result().id())
                .setLinks(response.result().links());
        return transaction;
    }


    // Set up a transaction

    private ApplicationContext setApplicationContext() {
        return new ApplicationContext().brandName("C03 Auction Group").landingPage("BILLING").shippingPreference("NO_SHIPPING");
    }

    private PurchaseUnitRequest setPurchaseUnitRequest(Cart cart, String deliveryFee) {
        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest();
        // description
        purchaseUnitRequest.description("Auction goods");
        // total price
        purchaseUnitRequest.amountWithBreakdown(setTotalPrice(cart.getTotalPrice(), deliveryFee));
        return purchaseUnitRequest;
    }

    public AmountWithBreakdown setTotalPrice(Double total, String deliveryFee) {
        double toUsd = calcTotalPrice(total, deliveryFee);
        return new AmountWithBreakdown().currencyCode("USD").value(String.valueOf(toUsd));
    }

    private Item setItems(CartDetail cartDetail) {
//       Item item = new Item();
       return new Item();
    };

    private OrderRequest buildOrderRequest(Cart cart, String deliveryFee) {
        OrderRequest orderRequest = new OrderRequest();
        // set intent
        orderRequest.checkoutPaymentIntent("CAPTURE");
        // set application context
        orderRequest.applicationContext(setApplicationContext());
        // set purchase unit
        List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<PurchaseUnitRequest>();
        purchaseUnitRequests.add(setPurchaseUnitRequest(cart, deliveryFee));

        orderRequest.purchaseUnits(purchaseUnitRequests);
        return orderRequest;
    }

    private double calcTotalPrice(Double money, String deliveryFee) {
        double transferFee;
        double rate = rateExchangeService.getRateExchange();
        if ("Giao hàng nhanh".equals(deliveryFee)) {
            transferFee = 39000.0;
        } else {
            transferFee = 19000.0;
        }
        double tempMoney = (money + transferFee) * 1.1;
        double toUsd = Math.round(tempMoney / rate);
        return toUsd;
    }
}
