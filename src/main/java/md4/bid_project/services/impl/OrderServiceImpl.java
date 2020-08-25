package md4.bid_project.services.impl;
import md4.bid_project.models.Cart;
import md4.bid_project.models.CartDetail;
import md4.bid_project.models.Order;
import md4.bid_project.models.dto.OrderDTO;
import md4.bid_project.repositories.CartRepository;
import md4.bid_project.repositories.DeliveryAddressRepository;
import md4.bid_project.repositories.OrderRepository;
import md4.bid_project.services.CartDetailService;
import md4.bid_project.services.CartService;
import md4.bid_project.services.OrderService;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


//creator: Đặng Hồng Quân team C
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartService cartService;

    @Autowired
   CartRepository cartRepository;
    @Autowired
    DeliveryAddressRepository deliveryAddressRepository;



    @Override
    public Order findByBuyerId(Long id) {
        return orderRepository.findAllByBuyer_IdAndStatusTrue(id);
    }

    @Override
    public void saveOrder(OrderDTO orderDto) {

        ModelMapper modelMapper = new ModelMapper();
        Order order = modelMapper.map(orderDto, Order.class);
        Cart cart = cartService.findByUserId(orderDto.getBuyer().getId()).orElse(null) ;
        if(cart != null){
        cart.setStatus(false);
        for ( CartDetail cartDetail: cart.getCartDetails()) {
            cartDetail.setStatus(CartDetailService.STATUS_PAID);
        }
        order.setCart(cart);
        order.setStatus(true);
        order.setDeadlineDelivery(LocalDateTime.now());
        order.setCode("ORD"+ RandomStringUtils.randomNumeric(8));
        orderRepository.save(order);
        Cart cartNew = new Cart();
        cartNew.setStatus(true);
        cartNew.setUser(orderDto.getBuyer());
            cartRepository.save(cartNew);}

    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

    //BHung
    @Override
    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}
