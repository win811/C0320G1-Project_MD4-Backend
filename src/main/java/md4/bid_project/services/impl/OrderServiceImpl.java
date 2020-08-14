package md4.bid_project.services.impl;

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
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getAllNotDeletedDeal() {
        return null;
    }

    @Override
    public List<Order> deleteDeals(List<Order> deals) {
        //code chưa xét điều kiện xóa
        for (Order deal : deals){
//            deal.setDelete(true);
//            dealManagementRepository.save(deal);
        }
        return deals;
    }
}
