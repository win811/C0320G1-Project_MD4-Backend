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

//    @OneToOne
//    @JoinColumn(name = "product_id", nullable=false)
//    private Product product;
//
//    @OneToOne
//    @JoinColumn(name = "auction_id")
//    private Auction auction;

    @Column(name = "product_win_price")
    private double productWinPrice;

    @Column(name = "product_quantity")
    private int productQuantity;

    @Column(name = "cart_detail_status")
    private String status;

}
