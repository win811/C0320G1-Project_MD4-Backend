package md4.bid_project.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="auctions")
@Data
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auction_id")
    private long id;

//    @OneToOne
//    @JoinColumn(name = "auction_product_id")
//    private Product product;

//    @ManyToOne
//    @JoinColumn(name="auction_status_id")
//    private AuctionStatus auctionStatus;

    @Column(name = "auction_close_time")
    private LocalDateTime closeTime;
}
