package md4.bid_project.controllers;

import md4.bid_project.exception.ResourceNotFoundException;
import md4.bid_project.models.Cart;
import md4.bid_project.models.CartDetail;
import md4.bid_project.models.dto.CartDetailDTO;
import md4.bid_project.services.CartDetailService;
import md4.bid_project.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartDetailService cartDetailService;

    // Lấy giỏ hàng bằng user id
    @GetMapping("/cart")
    public ResponseEntity<Cart> getCartByUserId(@RequestParam(name = "userId") Long userId)
            throws ResourceNotFoundException {
        Cart cart = cartService.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for this user :: " + userId));
        return ResponseEntity.ok(cart);
    }

    // Cập nhật tổng tiền của một giỏ hàng
    @PutMapping("/cart")
    public ResponseEntity<Double> updateTotalCost(@RequestBody Map<String, Object> requestBody)
            throws ResourceNotFoundException {
        Long cartId = Long.valueOf(requestBody.get("cartId").toString());
        Double totalCost = cartService.updateTotalCost(cartId);
        if (totalCost != null) {
            return ResponseEntity.ok(totalCost);
        } else {
            throw new ResourceNotFoundException("Failed to update total cost");
        }
    }

    // Thêm vào giỏ hàng một sản phẩm
    @PostMapping("/cart/cart-detail")
    public ResponseEntity<CartDetail> addToCart(@Validated @RequestBody CartDetailDTO cartDetailDTO)
            throws ResourceNotFoundException {
        CartDetail cartDetail = cartDetailService.create(cartDetailDTO);
        if (cartDetail != null) {
            return ResponseEntity.ok().body(cartDetail);
        } else {
            throw new ResourceNotFoundException("Failed to create CartDetail");
        }
    }

    // Cập nhật 1 sản phẩm trong giỏ hàng (thay đổi số lượng)
    @PutMapping("/cart/cart-detail")
    public ResponseEntity<CartDetail> updateCartDetail(@RequestBody Map<String, Object> requestBody)
            throws ResourceNotFoundException {
        Long cartDetailId = Long.valueOf(requestBody.get("cartDetailId").toString());
        Integer quantity = Integer.valueOf(requestBody.get("quantity").toString());
        CartDetail cartDetail = cartDetailService.update(cartDetailId, quantity);
        if (cartDetail != null) {
            return ResponseEntity.ok().body(cartDetail);
        } else {
            throw new ResourceNotFoundException("Failed update for this cartDetailId :: " + cartDetailId);
        }
    }

    // Xóa 1 sản phẩm khỏi giỏ hàng
    @DeleteMapping("/cart/cart-detail/{cartDetailId}")
    public ResponseEntity<CartDetail> deleteCartDetail(@PathVariable(name = "cartDetailId") Long cartDetailId)
            throws ResourceNotFoundException {
        CartDetail cartDetail = cartDetailService.delete(cartDetailId);
        if (cartDetail != null) {
            return ResponseEntity.ok().body(cartDetail);
        } else {
            throw new ResourceNotFoundException("CartDetail not found for this cartDetailId :: " + cartDetailId);
        }
    }
}
