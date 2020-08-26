package md4.bid_project.services.impl;

import md4.bid_project.models.CartDetail;
import md4.bid_project.models.dto.DealManageApi;
import md4.bid_project.models.dto.DealManageDTO;
import md4.bid_project.repositories.CartDetailRepository;
import md4.bid_project.repositories.DealManageDTORepository;
import md4.bid_project.services.DealManageDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//all created by Thao
@Service
public class DealManageDTOServiceImpl implements DealManageDTOService {

    @Autowired
    DealManageDTORepository dealManageDTORepository;
    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Override
    public List<DealManageDTO> findAllNotDeletedDeal(Pageable pageable) {
        List<CartDetail> cartDetails = cartDetailRepository.findAllByIsDeleteIsFalse(pageable);
        List<DealManageDTO> dealDTOList = new ArrayList<DealManageDTO>();
        for (CartDetail cartDetail : cartDetails) {
            DealManageDTO dealDTO = new DealManageDTO();
            dealDTO.setId(cartDetail.getId());
            dealDTO.setCodeDeal(cartDetail.getId().toString());
            dealDTO.setWinBiddingTime(cartDetail.getAuction().getCloseTime());
            dealDTO.setNameSeller(cartDetail.getAuction().getProduct().getOwner().getFullName());
            dealDTO.setNameBuyer(cartDetail.getCart().getUser().getFullName());
            dealDTO.setNameProduct(cartDetail.getAuction().getProduct().getName());
            dealDTO.setStartingBidPrice(cartDetail.getAuction().getProduct().getInitialPrice());
            dealDTO.setClosingBidPrice(cartDetail.getProductWinPrice());
            dealDTO.setAmount(cartDetail.getProductQuantity());
            dealDTO.setTotalPayment();
            dealDTO.setServiceFee();
            dealDTO.setStatusOfDeal(cartDetail.getStatus());
            dealDTOList.add(dealDTO);
        }
        return dealDTOList;
    }

    @Override
    public CartDetail findById(Long id) {
        return cartDetailRepository.findById(id).orElse(null);
    }

    @Override
    public CartDetail save(CartDetail cartDetail) {
        return cartDetailRepository.save(cartDetail);
    }

    //count total items of list
    @Override
    public int countTotalItems() {
        return cartDetailRepository.findAllByIsDeleteIsFalse().size();
    }

    //count total result of searching
    @Override
    public int countSearchResult(String nameBuyer, String nameSeller, String nameProduct, Double totalPayment, String status) {
        return dealManageDTORepository.countSearchResult(nameBuyer, nameSeller, nameProduct, totalPayment, status);
    }

    //SEARCH
    @Override
    public List<DealManageDTO> searchByFiveFields(String nameBuyer, String nameSeller, String nameProduct, Double totalPayment, String status, Pageable pageable) {
        return dealManageDTORepository.queryByFiveFields(nameBuyer, nameSeller, nameProduct, totalPayment, status, pageable);
    }

    //MAKE API TO SEND CLIENT
    @Override
    public ResponseEntity<DealManageApi> setInfoToDealManageApi(List<DealManageDTO> list, int currentPage, int pageSize, int totalItems) {
        if (list.isEmpty()) {
            return new ResponseEntity<DealManageApi>(HttpStatus.NO_CONTENT);
        } else {
            DealManageApi dealManageApi = new DealManageApi();
            dealManageApi.setItems(list);
            dealManageApi.setCurrentPage(currentPage);
            dealManageApi.setTotalPage((int) Math.ceil((double) totalItems / pageSize));
            dealManageApi.setTotalItems(totalItems);
            return new ResponseEntity<DealManageApi>(dealManageApi, HttpStatus.OK);
        }
    }

}
