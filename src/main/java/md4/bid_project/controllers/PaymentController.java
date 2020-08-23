package md4.bid_project.controllers;

import md4.bid_project.exception.SettlementException;
import md4.bid_project.exception.ViolatedException;
import md4.bid_project.models.DeliveryAddress;
import md4.bid_project.models.Order;
import md4.bid_project.models.dto.DeliveryAddressDTO;
import md4.bid_project.models.dto.InvoiceDto;
import md4.bid_project.models.dto.OrderDto;
import md4.bid_project.services.CartDetailService;
import md4.bid_project.services.DeliveryAddressService;
import md4.bid_project.services.restful.braintree.BrainTreeService;
import md4.bid_project.services.OrderService;
import md4.bid_project.services.restful.paypal.Transaction;
import md4.bid_project.services.restful.paypal.PayPalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import md4.bid_project.models.dto.InvoiceDto;
import md4.bid_project.models.CartDetail;
import md4.bid_project.models.Order;
import md4.bid_project.services.CartDetailService;
import md4.bid_project.services.OrderService;
import java.util.Map;

// Duy
// Cac file liên quan đến DeliveryAddress (repository, service, entity)
// /services/restful/paypal
// /services/restful/rateExchange
// Exception
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class PaymentController {

    //creator: Đặng Hồng Quân team C
     @Autowired
    private OrderService orderService;
    @Autowired
    private DeliveryAddressService deliveryAddressService;

    @Autowired
    private PayPalService payPalService;

    @Autowired
    private BrainTreeService brainTreeService;

    @Autowired
    private CartDetailService cartDetailService;

     @GetMapping("payment/order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable(value = "id") Long userId)  {
         Order order = orderService.findByBuyerId(userId);
         if(order==null)
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       return ResponseEntity.ok().body(order);
     }

    @PostMapping("payment/order")
    public ResponseEntity<Void> create(@RequestBody OrderDto orderDto) {
         orderService.saveOrder(orderDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping("payment/order")
    public ResponseEntity<Void> update( @RequestBody Order order){
      orderService.updateOrder(order);
        return new ResponseEntity<Void>( HttpStatus.CREATED);
    }

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

    // visa get client token
    @GetMapping("/payment/visa-token")
    public ResponseEntity<Map<String, String>> getClientToken() {
        Map<String, String> token = brainTreeService.getClientToken();
        return ResponseEntity.ok(token);
    }

    @GetMapping("/payment/visa-create")
    public ResponseEntity<?> createPurchase(@RequestParam("userId") Long id, @RequestParam("nonce") String nonce) throws SettlementException {
        com.braintreegateway.Transaction transaction = brainTreeService.requestTransaction(nonce, id);
        return ResponseEntity.ok(transaction);
    }

    //Creator: Nguyễn Xuân Hùng
    @GetMapping("/payment/invoice/{id}")
    public ResponseEntity<InvoiceDto> getInvoiceById(@PathVariable Long id){
        Order order = orderService.findByBuyerId(id);
        List<CartDetail> cartDetail = cartDetailService.findCartDetailByCartId(order.getCart().getId());
        if(cartDetail==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setOrder(order);
        invoiceDto.setCartDetail(cartDetail);
        return new ResponseEntity<>(invoiceDto,HttpStatus.OK);
    }
}
