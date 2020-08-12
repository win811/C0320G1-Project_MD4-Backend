package md4.bid_project.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cart_details")
@Data
public class CartDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_detail_id")
    private long id;

//    @ManyToOne
//    @JoinColumn(name = "cart_id", nullable=false)
//    private Cart cart;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id", nullable=false)
//    private Product product;

    @Column(name = "product_win_price")
    private Long productWinPrice;

    @Column(name = "product_quantity")
    private Long productQuantity;

    @Column(name = "cart_detail_status")
    private boolean status;

    public CartDetail() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    public Cart getCart() {
//        return cart;
//    }
//
//    public void setCart(Cart cart) {
//        this.cart = cart;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }

    public Long getProductWinPrice() {
        return productWinPrice;
    }

    public void setProductWinPrice(Long productWinPrice) {
        this.productWinPrice = productWinPrice;
    }

    public Long getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Long productQuantity) {
        this.productQuantity = productQuantity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
