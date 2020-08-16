package md4.bid_project.controllers;

import md4.bid_project.dto.InvoiceDto;
import md4.bid_project.models.CartDetail;
import md4.bid_project.models.Order;
import md4.bid_project.services.CartDetailService;
import md4.bid_project.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class PaymentController {
    @Autowired
    OrderService orderService;
    @Autowired
    CartDetailService cartDetailService;
    @GetMapping("/payment/invoice/{id}")
    public ResponseEntity<InvoiceDto> getInvoiceById(@PathVariable Long id){
        Order order = orderService.findOrderById(id);
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
