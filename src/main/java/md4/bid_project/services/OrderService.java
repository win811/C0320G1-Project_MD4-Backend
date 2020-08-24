package md4.bid_project.services;

import md4.bid_project.models.Order;
import md4.bid_project.models.dto.OrderDTO;



public interface OrderService {
   Order findByBuyerId(Long id);
   void saveOrder(OrderDTO orderDto);
   void updateOrder(Order order);

   //BHung:
   Order findOrderById(Long id);
}
