package md4.bid_project.controllers;

import md4.bid_project.models.Order;
import md4.bid_project.models.dto.OrderDto;
import md4.bid_project.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class PaymentController {

     @Autowired
    private OrderService orderService;

     @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable(value = "id") Long userId)  {
         Order order = orderService.findByBuyerId(userId);
         if(order==null)
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       return ResponseEntity.ok().body(order);
     }

    @PostMapping("/order")
    public ResponseEntity<Void> create(@RequestBody OrderDto orderDto) {
         orderService.saveOrder(orderDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping("/order")
    public ResponseEntity<Void> update( @RequestBody Order order){
      orderService.updateOrder(order);
        return new ResponseEntity<Void>( HttpStatus.CREATED);
    }
}
