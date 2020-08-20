package md4.bid_project.controllers;

import md4.bid_project.exception.ViolatedException;
import md4.bid_project.models.Cart;
import md4.bid_project.models.DeliveryAddress;
import md4.bid_project.models.dto.DeliveryAddressDTO;
import md4.bid_project.services.DeliveryAddressService;
import md4.bid_project.services.restful.paypal.Transaction;
import md4.bid_project.services.restful.paypal.PayPalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import md4.bid_project.models.dto.InvoiceDTO;
import md4.bid_project.models.CartDetail;
import md4.bid_project.models.Order;
import md4.bid_project.services.CartDetailService;
import md4.bid_project.services.OrderService;

// Duy
// Cac file liên quan đến DeliveryAddress (repository, service, entity)
// /services/restful/paypal
// /services/restful/rateExchange
// Exception
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class PaymentController {

    @Autowired
    DeliveryAddressService deliveryAddressService;

    @Autowired
    PayPalService payPalService;
    @Autowired
    OrderService orderService;
    @Autowired
    CartDetailService cartDetailService;
    // Khởi tạo 1 đơn hàng từ paypal
    @PostMapping("/payment/create-transaction")
    public ResponseEntity<Transaction> getTransaction(@RequestBody  Long userId) throws IOException {
        Transaction data = payPalService.createTransaction(userId);
        return ResponseEntity.ok(data);
    }

    // xác nhận đơn hàng đã được trả từ người mua
    @PostMapping("/payment/confirm-transaction")
    public ResponseEntity<Transaction> confirmTransaction(@RequestBody String orderId) throws IOException {
         Transaction transaction = payPalService.captureTransaction(orderId);
        return ResponseEntity.ok(transaction);
    }

    // Lấy địa chỉ giao hàng của user
    @GetMapping("/payment/address/{userId}")
    public ResponseEntity<DeliveryAddressDTO> getDeliveryAddress(@PathVariable(value = "userId") Long id) {
        DeliveryAddressDTO data = deliveryAddressService.findDeliveryAddressByUserId(id);
        return ResponseEntity.ok(data);
    }

    // cập nhật địa chỉ giao hàng mới
    @PutMapping("/payment/address")
    public ResponseEntity<DeliveryAddress> updateDeliveryAddress(@Valid @RequestBody DeliveryAddress deliveryAddress,
                                                              BindingResult bindingResult)
    throws ViolatedException {

        if (bindingResult.hasErrors()){
            throw new ViolatedException(bindingResult);
        }
        deliveryAddressService.updateDeliveryAddress(deliveryAddress);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Creator: Nguyễn Xuân Hùng
    @GetMapping("/payment/invoice/{id}")
    public ResponseEntity<InvoiceDTO> getInvoiceById(@PathVariable Long id){
        Order order = orderService.findOrderById(id);
        List<CartDetail> cartDetail = cartDetailService.findCartDetailByCartId(order.getCart().getId());
        if(cartDetail==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setOrder(order);
        invoiceDTO.setCartDetail(cartDetail);
        return new ResponseEntity<>(invoiceDTO,HttpStatus.OK);
    }
}
