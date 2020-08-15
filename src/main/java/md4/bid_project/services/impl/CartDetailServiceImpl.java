package md4.bid_project.services.impl;

import md4.bid_project.models.Auction;
import md4.bid_project.models.Cart;
import md4.bid_project.models.CartDetail;
import md4.bid_project.models.dto.CartDetailDTO;
import md4.bid_project.repositories.AuctionRepository;
import md4.bid_project.repositories.CartDetailRepository;
import md4.bid_project.repositories.CartRepository;
import md4.bid_project.services.CartDetailService;
import md4.bid_project.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartDetailServiceImpl implements CartDetailService {

    public static final String STATUS_WAITING = "waiting";
    public static final String STATUS_REMOVED = "removed";
    public static final String STATUS_PAID = "paid";
    public static final int DEFAULT_QUANTITY = 1;

    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private CartService cartService;

    @Override
    public CartDetail create(CartDetailDTO cartDetailDTO) {
        Optional<Cart> optionalCart = cartRepository.findByUserIdAndStatusIsTrue(cartDetailDTO.getUserId());
        if (!optionalCart.isPresent()) {
            return null;
        }
        Cart cart = optionalCart.get();
        Optional<Auction> optionalAuction = auctionRepository.findById(cartDetailDTO.getAuctionId());
        if (!optionalAuction.isPresent()) {
            return null;
        }
        CartDetail cartDetail = new CartDetail();
        cartDetail.setCart(cart);
        cartDetail.setProductWinPrice(cartDetailDTO.getWinPrice());
        cartDetail.setProductQuantity(DEFAULT_QUANTITY);
        cartDetail.setAuction(optionalAuction.get());
        cartDetail.setStatus(STATUS_WAITING);
        cartDetail.setCartDetailCost(cartDetailDTO.getWinPrice());
        cartDetailRepository.save(cartDetail);
        cartService.updateTotalCost(cart.getId());
        return cartDetail;
    }

    @Override
    public CartDetail update(Long cartDetailId, Integer quantity) {
        Optional<CartDetail> optional = cartDetailRepository.findById(cartDetailId);
        if (optional.isPresent()) {
            CartDetail cartDetail = optional.get();
            cartDetail.setProductQuantity(quantity);
            cartDetail.setCartDetailCost(quantity * cartDetail.getProductWinPrice());
            return cartDetailRepository.save(cartDetail);
        }
        return null;
    }

    @Override
    public CartDetail delete(Long cartDetailId) {
        Optional<CartDetail> optional = cartDetailRepository.findById(cartDetailId);
        if (optional.isPresent()) {
            CartDetail cartDetail = optional.get();
            cartDetail.setStatus(STATUS_REMOVED);
            return cartDetailRepository.save(cartDetail);
        }
        return null;
    }

}
