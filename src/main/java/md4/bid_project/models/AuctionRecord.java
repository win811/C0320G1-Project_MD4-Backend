package md4.bid_project.models;



import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "record_auction_id")
    private Auction auction;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "record_bidder_id")
    private User bidder;

    @Column (name = "record_bid_time")
    private LocalDateTime bidTime;

    @Column (name = "record_bid_price")
    private Double bidPrice;

    @Column (name = "record_bid_is_winner")
    private Boolean isWinner;

}
