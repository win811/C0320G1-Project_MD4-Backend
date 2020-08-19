package md4.bid_project.services.impl;

import md4.bid_project.models.CartDetail;
import md4.bid_project.models.dto.DealManageDTO;
import md4.bid_project.repositories.CartDetailRepository;
import md4.bid_project.repositories.DealManageDTORepository;
import md4.bid_project.services.DealManageDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//created by Thao
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
            dealDTO.setNameSeller(cartDetail.getAuction().getProduct().getOwner().getFullname());
            dealDTO.setNameBuyer(cartDetail.getCart().getUser().getFullname());
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

    @Override
    public int countTotalItems() {
        return cartDetailRepository.findAllByIsDeleteIsFalse().size();
    }

    //SEARCH
    @Override
    public List<DealManageDTO> searchBySellerAndBuyerAndProductAndTotalPayAndStatus(String nameBuyer, String nameSeller, String nameProduct, String totalPayment, String status) {
        return dealManageDTORepository.queryByFiveFields(nameBuyer, nameSeller, nameProduct, totalPayment, status);
    }

    @Override
    public List<DealManageDTO> searchByOneField(String nameBuyer, String nameSeller, String nameProduct, Double totalPayment, String status) {
        return dealManageDTORepository.findAllByNameBuyerContainingOrNameSellerContainingOrNameProductContainingOrTotalPaymentContainingOrStatusOfDeal(nameBuyer, nameSeller, nameProduct, totalPayment, status);
    }
}
