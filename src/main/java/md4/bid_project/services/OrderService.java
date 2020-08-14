package md4.bid_project.services;

import md4.bid_project.models.Order;

import java.util.List;

public interface OrderService {

    List<Order> findAll();

    List<Order> getAllNotDeletedDeal();

    public List<Order> deleteDeals(List<Order> deals);

}
