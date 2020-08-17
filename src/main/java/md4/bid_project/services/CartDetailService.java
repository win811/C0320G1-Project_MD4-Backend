package md4.bid_project.services;

import md4.bid_project.models.AuctionRecord;
import md4.bid_project.models.CartDetail;
import md4.bid_project.models.dto.DealManageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CartDetailService {
    //Thao
    public List<DealManageDTO> findAllNotDeletedDeal(Pageable pageable);

    //Thao
    public List<DealManageDTO> searchDeals(String nameBuyer,
                                        String nameSeller,
                                        String nameProduct,
                                        String status);

    //Thao
    public CartDetail findById(Long id);

    //Thao
    public CartDetail save(CartDetail cartDetail);

    //Thao
    public int countTotalItems();
}
