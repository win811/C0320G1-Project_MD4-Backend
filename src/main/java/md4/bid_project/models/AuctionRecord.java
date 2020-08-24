package md4.bid_project.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

// creator: Hoai Ngan team C
@Entity
@Table(name = "auction_records")
@Getter
@Setter
@NoArgsConstructor
public class AuctionRecord {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "record_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "record_auction_id")
    @JsonIgnoreProperties("records")
    private Auction auction;

    @ManyToOne
    @JoinColumn(name = "record_bidder_id")
    private User bidder;

    @Column (name = "record_bid_time")
    private LocalDateTime bidTime;

    @Column (name = "record_bid_price")
    private Double bidPrice;

    @Column (name = "record_bid_is_winner")
    private Boolean isWinner;

}
