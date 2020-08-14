package md4.bid_project.services;


import md4.bid_project.models.Order;

public interface OrderService {
   Order findByBuyerId(Long id);
   void saveOrder(Order order);
}
