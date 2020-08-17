package md4.bid_project.services.impl;

import md4.bid_project.models.CartDetail;
import md4.bid_project.models.dto.DealManageDTO;
import md4.bid_project.repositories.CartDetailRepository;
import md4.bid_project.repositories.OrderRepository;
import md4.bid_project.services.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.TRUE;

@Service
public class CartDetailServiceImpl implements CartDetailService {

    @Autowired
    private CartDetailRepository cartDetailRepository;
    @Autowired
    private OrderRepository orderRepository;

    //created by Thao
    @Override
    public List<DealManageDTO> findAllNotDeletedDeal(Pageable pageable) {
        List<CartDetail> cartDetails = cartDetailRepository.findAllByIsDeleteIsFalse(pageable);
        List<DealManageDTO> dealDTOList = new ArrayList<DealManageDTO>();
        for (CartDetail cartDetail : cartDetails) {
            DealManageDTO dealDTO = new DealManageDTO();
            dealDTO.setId(cartDetail.getId());
            dealDTO.setCode(orderRepository.findByCart(cartDetail.getCart()).getCode());
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

    //created by Thao
    @Override
    public CartDetail findById(Long id) {
        return cartDetailRepository.findById(id).orElse(null);
    }

    //created by Thao
    @Override
    public CartDetail save(CartDetail cartDetail) {
        return cartDetailRepository.save(cartDetail);
    }

    //created by Thao
    @Override
    public int countTotalItems() {
        return cartDetailRepository.findAllByIsDeleteIsFalse().size();
    }

    //created by Thao
    @Override
    public List<DealManageDTO> searchDeals(String nameBuyer, String nameSeller, String nameProduct, String status) {
        List<CartDetail> cartDetails = cartDetailRepository.findAllByAuction_Product_OwnerContainingOrCart_User_FullnameContainingOrAuction_Product_NameContainingOrStatusContaining(nameBuyer, nameSeller, nameProduct, status);
        List<DealManageDTO> dealDTOs = new ArrayList<DealManageDTO>();
        for (CartDetail cartDetail : cartDetails) {
            DealManageDTO dealDTO = new DealManageDTO();
            dealDTO.setId(cartDetail.getId());
            dealDTO.setCode(orderRepository.findByCart(cartDetail.getCart()).getCode());
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
            dealDTOs.add(dealDTO);
        }
        return dealDTOs;
    }
}
