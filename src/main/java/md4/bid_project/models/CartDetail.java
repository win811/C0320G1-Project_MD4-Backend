package md4.bid_project.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cart_details")
@Getter
@Setter
@NoArgsConstructor
public class CartDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_detail_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column(name = "product_win_price")
    private Double productWinPrice;

    @Column(name = "product_quantity")
    private Integer productQuantity;

    @Column(name = "cart_detail_status")
    private String status;

    @OneToOne
    @JoinColumn(name = "auction_id")
    private Auction auction;

    @Column(name = "delete")
    private Boolean isDelete;
    
}
