package md4.bid_project.services.impl;

import md4.bid_project.models.Auction;
import md4.bid_project.models.Cart;
import md4.bid_project.models.CartDetail;
import md4.bid_project.models.User;
import md4.bid_project.models.dto.CartDetailDTO;
import md4.bid_project.repositories.AuctionRepository;
import md4.bid_project.repositories.CartDetailRepository;
import md4.bid_project.repositories.CartRepository;
import md4.bid_project.repositories.UserRepository;
import md4.bid_project.services.CartDetailService;
import md4.bid_project.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartDetailServiceImpl implements CartDetailService {

    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;

    // Created by: Toàn
    @Override
    public CartDetail create(CartDetailDTO cartDetailDTO) {
        Optional<User> optionalUser = userRepository.findById(cartDetailDTO.getUserId());
        if (!optionalUser.isPresent()) {
            return null;
        }
        Optional<Cart> optionalCart = cartRepository.findByUserIdAndStatusIsTrue(cartDetailDTO.getUserId());
        Cart cart;
        if (!optionalCart.isPresent()) {
            cart = new Cart();
            cart.setUser(optionalUser.get());
        } else {
            cart = optionalCart.get();
        }
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

    // Create: Toàn
    @Override
    public CartDetail update(Long cartDetailId, Integer quantity) {
        Optional<CartDetail> optional = cartDetailRepository.findById(cartDetailId);
        if (optional.isPresent()) {
            CartDetail cartDetail = optional.get();
            cartDetail.setProductQuantity(quantity);
            cartDetail.setCartDetailCost(quantity * cartDetail.getProductWinPrice());
            cartService.updateTotalCost(cartDetail.getCart().getId());
            return cartDetailRepository.save(cartDetail);
        }
        return null;
    }

    // Create: Toàn
    @Override
    public CartDetail delete(Long cartDetailId) {
        Optional<CartDetail> optional = cartDetailRepository.findById(cartDetailId);
        if (optional.isPresent()) {
            CartDetail cartDetail = optional.get();
            cartDetail.setStatus(STATUS_REMOVED);
            cartService.updateTotalCost(cartDetail.getCart().getId());
            return cartDetailRepository.save(cartDetail);
        }
        return null;
    }
    //Creator: Nguyễn Xuân Hùng
    @Override
    public List<CartDetail> findCartDetailByCartId(Long id) {
        return cartDetailRepository.findAllByCart_Id(id);
    }

}
