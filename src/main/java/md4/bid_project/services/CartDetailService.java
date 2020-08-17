package md4.bid_project.services;

import md4.bid_project.models.CartDetail;
import md4.bid_project.models.dto.CartDetailDTO;

public interface CartDetailService {

    // Create: Toàn
    String STATUS_WAITING = "waiting";
    String STATUS_REMOVED = "removed";
    String STATUS_PAID = "paid";
    int DEFAULT_QUANTITY = 1;

    // Create: Toàn
    CartDetail create(CartDetailDTO cartDetailDTO);

    // Create: Toàn
    CartDetail update(Long cartDetailId, Integer quantity);

    // Create: Toàn
    CartDetail delete(Long cartDetailId);

}
