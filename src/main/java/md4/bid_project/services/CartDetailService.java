package md4.bid_project.services;

import md4.bid_project.models.CartDetail;

import java.util.List;

public interface CartDetailService {
    List<CartDetail> findCartDetailByCartId(Long id);
}
