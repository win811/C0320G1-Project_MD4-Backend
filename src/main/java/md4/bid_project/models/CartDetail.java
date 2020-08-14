package md4.bid_project.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cart_details")
@Data
public class CartDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_detail_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnoreProperties("cartDetails")
    private Cart cart;

    @Column(name = "product_win_price")
    private Double productWinPrice;

    @Column(name = "product_quantity")
    private Integer productQuantity;

    @OneToOne
    @JoinColumn(name = "auction_id")
    private Auction auction;

    @Column(name = "delete")
    private Boolean isDelete;

}
