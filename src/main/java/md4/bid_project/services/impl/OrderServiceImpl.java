package md4.bid_project.services.Impl;

import md4.bid_project.models.Order;
import md4.bid_project.repositories.OrderRepository;
import md4.bid_project.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order findByBuyerId(Long id) {
        return orderRepository.findAllByBuyer_Id(id);
    }

    @Override
    public void saveOrder(Order order) {

    }
}
