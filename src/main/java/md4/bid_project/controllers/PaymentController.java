package md4.bid_project.controllers;

import md4.bid_project.models.Cart;
import md4.bid_project.models.DeliveryAddress;
import md4.bid_project.services.DeliveryAddressService;
import md4.bid_project.services.restful.paypal.Transaction;
import md4.bid_project.services.restful.paypal.PayPalService;
import org.omg.CORBA.TRANSACTION_MODE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class PaymentController {

    @Autowired
    DeliveryAddressService deliveryAddressService;

    @Autowired
    PayPalService payPalService;

    // Khởi tạo 1 đơn hàng từ paypal
    @PostMapping("/payment/create-transaction")
    public ResponseEntity<Transaction> getTransaction(@RequestBody Cart cart) throws IOException {
        Transaction data = payPalService.createTransaction(cart);
        return ResponseEntity.ok(data);
    }

    // xác nhận đơn hàng đã được trả từ người mua
    @PostMapping("/payment/confirm-transaction")
    public ResponseEntity<Transaction> confirmTransaction(@RequestBody String id) throws IOException {
         Transaction transaction = payPalService.captureTransaction(id);
        return ResponseEntity.ok(transaction);
    }

    // Lấy địa chỉ giao hàng của user
    @GetMapping("/payment/address/{userId}")
    public ResponseEntity<List<DeliveryAddress>> getDeliveryAddress(@PathVariable(value = "userId") Long id) {
        List<DeliveryAddress> data = deliveryAddressService.findDeliveryAddressByUserId(id);
        return ResponseEntity.ok(data);
    }

    // Thêm địa chỉ giao hàng mới
//    @PostMapping("/payment/address/")
//    public ResponseEntity<DeliveryAddress> addDeliveryAddress(@Valid @RequestBody DeliveryAddress deliveryAddress,
//                                                              BindingResult bindingResult) {
//
//        return ResponseEntity.ok(data);
//    }
}
