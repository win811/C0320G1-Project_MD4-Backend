package md4.bid_project.services;

import md4.bid_project.models.CartDetail;
import md4.bid_project.models.dto.CartDetailDTO;

public interface CartDetailService {

    CartDetail create(CartDetailDTO cartDetailDTO);

    CartDetail update(Long cartDetailId, Integer quantity);

    CartDetail delete(Long cartDetailId);

}
