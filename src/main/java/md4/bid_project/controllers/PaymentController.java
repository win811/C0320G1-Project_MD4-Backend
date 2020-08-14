package md4.bid_project.controllers;

import md4.bid_project.exception.ResourceNotFoundException;
import md4.bid_project.models.Order;
import md4.bid_project.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class PaymentController {

     @Autowired
    private OrderService orderService;

     @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {

         Order order = orderService.findByBuyerId(userId);
         if(order==null)
         throw new ResourceNotFoundException("Order not found for this id: " + userId);
       return ResponseEntity.ok().body(order);
     }
}
