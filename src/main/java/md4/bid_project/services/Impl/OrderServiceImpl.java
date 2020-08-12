package md4.bid_project.services.Impl;
import md4.bid_project.models.Order;
import md4.bid_project.repositories.OrderRepository;
import md4.bid_project.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order findAllByUserId(Long id) {
        return null;
    }

    @Override
    public void save(Order order) {

    }

    @Override
    public List<Order> findAll() {
        return null;
    }
}
