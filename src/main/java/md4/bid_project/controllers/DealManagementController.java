package md4.bid_project.controllers;

import md4.bid_project.models.CartDetail;
import md4.bid_project.models.Order;
import md4.bid_project.repositories.OrderRepository;
import md4.bid_project.services.CartDetailService;
import md4.bid_project.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class DealManagementController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CartDetailService cartDetailService;
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping(value = "/orders")
    public List<Order> testApi1() {
        return orderService.findAll();
    }

    @GetMapping(value = "/cart-details")
    public List<CartDetail> testApi2() {
        return cartDetailService.findAll();
    }

    @GetMapping(value = "/order-by-cart")
    public Order testApi3() {
        CartDetail deal = cartDetailService.findById((long) 2);
        return orderRepository.findByCart(deal.getCart());
    }

    @GetMapping(value = "/list")
    public String testApiFinal() {
        List<CartDetail> dealList = cartDetailService.findAll();
//        String jsonString = new JSONObject()
//                .put("JSON1", "Hello World!")
//                .put("JSON2", "Hello my World!")
//                .put("JSON3", new JSONObject().put("key1", "value1"))
//                .toString();
//
//        System.out.println(jsonString);
        return null;
    }

    @GetMapping(value = "/deals")
    public List<Order> getAllNotDeletedDeal() {
        return null;
    }

    @PutMapping(value = "/delete")
    public ResponseEntity<List<Order>> deleteDeals(@RequestBody List<Order> deals){
        final List<Order> deletedDealManagement = orderService.deleteDeals(deals);
        return ResponseEntity.ok(deletedDealManagement);
    }

}
