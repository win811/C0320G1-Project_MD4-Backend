package md4.bid_project.services;

import md4.bid_project.models.CartDetail;
import md4.bid_project.models.dto.CartDetailDTO;

import java.util.List;

public interface CartDetailService {

    // Created by: Toàn
    String STATUS_WAITING = "waiting";
    String STATUS_REMOVED = "removed";
    String STATUS_PAID = "paid";
    int DEFAULT_QUANTITY = 1;

    // Created by: Toàn
    CartDetail create(CartDetailDTO cartDetailDTO);

    // Created by: Toàn
    CartDetail update(Long cartDetailId, Integer quantity);

    // Created by: Toàn
    CartDetail delete(Long cartDetailId);

    List<CartDetail> findCartDetailByCartId(Long id);
}
