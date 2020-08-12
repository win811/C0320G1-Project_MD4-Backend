package md4.bid_project.models;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "auction_records")
@Data
public class AuctionRecord {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "record_id")
    private Long id;

    @JoinColumn(name = "record_auction_id")
    @ManyToOne
    private Auction auction;

    @JoinColumn (name = "record_bidder_id")
    @ManyToOne
    private User bidder;

    @Column (name = "record_bid_time")
    private Date bidTime;

    @Column (name = "record_bid_price")
    private Double bidPrice;

    @Column (name = "record_bid_is_winner")
    private Boolean isWinner;



}
