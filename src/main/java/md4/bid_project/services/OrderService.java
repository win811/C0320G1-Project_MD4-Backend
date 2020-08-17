package md4.bid_project.services;


import md4.bid_project.models.Order;
import md4.bid_project.models.dto.OrderDto;

//creator: Đặng Hồng Quân team C
public interface OrderService {
   Order findByBuyerId(Long id);
   void saveOrder(OrderDto orderDto);
   void updateOrder(Order order);
}
