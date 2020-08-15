package md4.bid_project.services.impl;

import md4.bid_project.models.Cart;
import md4.bid_project.models.CartDetail;
import md4.bid_project.repositories.CartRepository;
import md4.bid_project.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Optional<Cart> findByUserId(Long userId) {
        return cartRepository.findByUserIdAndStatusIsTrue(userId);
    }

    @Override
    public Double updateTotalCost(Long id) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            Double totalCost = 0.0;
            for (CartDetail cartDetail : cart.getCartDetails()) {
                if (cartDetail.getStatus().equals(CartDetailServiceImpl.STATUS_WAITING))
                    totalCost = totalCost + cartDetail.getCartDetailCost();
            }
            cart.setTotalPrice(totalCost);
            cartRepository.save(cart);
            return totalCost;
        }
        return null;
    }

}
