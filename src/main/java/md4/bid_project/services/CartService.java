package md4.bid_project.services;

import md4.bid_project.models.Cart;

import java.util.Optional;

public interface CartService {

    Optional<Cart> findByUserId(Long userId);

    Double updateTotalCost(Long id);

}
