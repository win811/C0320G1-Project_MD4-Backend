package md4.bid_project.services.restful.braintree;

import com.braintreegateway.*;
import md4.bid_project.exception.SettlementException;
import md4.bid_project.models.Cart;
import md4.bid_project.models.User;
import md4.bid_project.models.dto.TransferDTO;
import md4.bid_project.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class BrainTreeServiceImpl implements BrainTreeService {

    @Autowired
    private CartService cartService;


    private static BraintreeGateway gateway = new BraintreeGateway(
            Environment.SANDBOX,
            "vvk2j2ryjqmc4tn5",
            "jngw3dsj8khm7vjk",
            "89dc1aab0b524a0ac5d7d5d394cc09da"
    );

    @Override
    public Map<String, String> getClientToken() {
        String token = gateway.clientToken().generate();
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        return tokenMap;
    }

    @Override
    public Transaction requestTransaction(TransferDTO transferDTO) throws SettlementException {
        Cart cart = cartService.findByUserId(transferDTO.getUserId()).orElse(null);
        assert cart != null;
        Customer customer = addBuyer(cart.getUser());        // convert to number
        BigDecimal decimalAmount = calcTotalPrice(cart.getTotalPrice(), transferDTO .getDeliveryMethod());

        TransactionRequest request = new TransactionRequest()
                .customerId(customer.getId())
                .amount(decimalAmount)
                .paymentMethodNonce(transferDTO.getNonce())
                .options()
                .submitForSettlement(true)
                .done();

        Result<Transaction> result = gateway.transaction().sale(request);
        Transaction transaction;

        if (result.isSuccess()) {
            transaction = result.getTarget();
        }else if (result.getTransaction() != null) {
            transaction = result.getTransaction();
        } else {
            StringBuilder errorString = new StringBuilder();
            for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
                errorString.append("Error: ").append(error.getCode()).append(": ").append(error.getMessage()).append("\n");
            }
            throw new SettlementException(errorString);
        }
        return transaction;
    }

    private BigDecimal calcTotalPrice(Double money, String method) {
        BigDecimal decimalAmount;
        double transferFee;
        if ("Giao hàng nhanh".equals(method)) {
            transferFee = 39000.0;
        } else {
            transferFee = 19000.0;
        }
        double tempMoney = (money + transferFee) * 1.1;
        decimalAmount = new BigDecimal(tempMoney);
        return decimalAmount;
    }

    private Cart getCart(Long userId) {
        return cartService.findByUserId(userId).orElse(null);
    }

    private Customer addBuyer(User user) {
        CustomerRequest request = new CustomerRequest()
                .firstName(user.getFullname())
                .email(user.getEmail())
                .phone(user.getPhoneNumber())
                .customerId(String.valueOf(user.getId()));
        Result<Customer> result = gateway.customer().create(request);
        return result.getTarget();
    }
}
