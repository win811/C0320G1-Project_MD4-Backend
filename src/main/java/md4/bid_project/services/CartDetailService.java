package md4.bid_project.services;

import md4.bid_project.models.CartDetail;
import md4.bid_project.models.dto.CartDetailDTO;

public interface CartDetailService {

    String STATUS_WAITING = "waiting";
    String STATUS_REMOVED = "removed";
    String STATUS_PAID = "paid";
    int DEFAULT_QUANTITY = 1;

    CartDetail create(CartDetailDTO cartDetailDTO);

    CartDetail update(Long cartDetailId, Integer quantity);

    CartDetail delete(Long cartDetailId);

}
