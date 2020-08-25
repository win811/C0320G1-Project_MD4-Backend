package md4.bid_project.services;

import md4.bid_project.models.Cart;

import java.util.Optional;

public interface CartService {

    // Create: Toàn
    Optional<Cart> findByUserId(Long userId);

    // Create: Toàn
    Double updateTotalCost(Long id);

}
