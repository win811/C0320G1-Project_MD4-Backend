package md4.bid_project.models;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "auctions_records")
public class AuctionRecord {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "record_id")
    @Getter @Setter
    private Long id;

    @Column (name = "record_auction_id")
    @Getter @Setter
    private Auction auction;

    @Column (name = "record_bidder_id")
    @Getter @Setter
    private User bidder;

    @Column (name = "record_bid_time")
    @Getter @Setter
    private Date bidTime;

    @Column (name = "record_bid_price")
    @Getter @Setter
    private Double bidPrice;

    @Column (name = "record_bid_is_winner")
    @Getter @Setter
    private Boolean isWinner;

}
