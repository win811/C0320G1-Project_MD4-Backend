package md4.bid_project.services;

import md4.bid_project.models.CartDetail;
import md4.bid_project.models.dto.DealManageApi;
import md4.bid_project.models.dto.DealManageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DealManageDTOService {

    public List<DealManageDTO> findAllNotDeletedDeal(Pageable pageable);

    public CartDetail findById(Long id);

    public CartDetail save(CartDetail cartDetail);

    public int countTotalItems();

    public int countSearchResult(String nameBuyer, String nameSeller, String nameProduct, Double totalPayment, String status);

    List<DealManageDTO> searchBySellerAndBuyerAndProductAndTotalPayAndStatus(String nameBuyer, String nameSeller, String nameProduct, Double totalPayment, String status, Pageable pageable);

    public ResponseEntity<DealManageApi> setInfoToDealManageApi(List<DealManageDTO> list, int currentPage, int pageSize, int totalItems);

}