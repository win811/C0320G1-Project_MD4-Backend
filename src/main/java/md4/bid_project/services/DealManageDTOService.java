package md4.bid_project.services;

import md4.bid_project.models.CartDetail;
import md4.bid_project.models.dto.DealManageDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DealManageDTOService {

    public List<DealManageDTO> findAllNotDeletedDeal(Pageable pageable);

    public CartDetail findById(Long id);

    public CartDetail save(CartDetail cartDetail);

    public int countTotalItems();

    public List<DealManageDTO> searchBySellerAndBuyerAndProductAndTotalPayAndStatus(String nameBuyer, String nameSeller, String nameProduct, String totalPayment, String status);

    public List<DealManageDTO> searchByOneField(String nameBuyer, String nameSeller, String nameProduct, Double totalPayment, String status);

}
